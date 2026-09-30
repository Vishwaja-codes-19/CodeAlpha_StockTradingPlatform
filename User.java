import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private String name;
    private double cash;
    private HashMap<String, Integer> portfolio;
    private ArrayList<Transaction> transactions;

    public User(String name, double cash) {
        this.name = name;
        this.cash = cash;
        this.portfolio = new HashMap<>();
        this.transactions = new ArrayList<>();
    }

    public void buyStock(Stock stock, int quantity) {
        if (quantity <= 0) {
            System.out.println("Invalid quantity.");
            return;
        }

        double totalCost = stock.getPrice() * quantity;

        if (totalCost > cash) {
            System.out.println("Insufficient funds.");
            return;
        }

        cash -= totalCost;

        portfolio.put(
                stock.getSymbol(),
                portfolio.getOrDefault(stock.getSymbol(), 0) + quantity
        );

        transactions.add(
                new Transaction(
                        "BUY",
                        stock.getSymbol(),
                        quantity,
                        stock.getPrice()
                )
        );

        System.out.println("Stock purchased successfully.");
    }

    public void sellStock(Stock stock, int quantity) {
        if (quantity <= 0) {
            System.out.println("Invalid quantity.");
            return;
        }

        int owned = portfolio.getOrDefault(stock.getSymbol(), 0);

        if (owned < quantity) {
            System.out.println("You don't own enough shares.");
            return;
        }

        double totalValue = stock.getPrice() * quantity;
        cash += totalValue;

        int remaining = owned - quantity;

        if (remaining == 0) {
            portfolio.remove(stock.getSymbol());
        } else {
            portfolio.put(stock.getSymbol(), remaining);
        }

        transactions.add(
                new Transaction(
                        "SELL",
                        stock.getSymbol(),
                        quantity,
                        stock.getPrice()
                )
        );

        System.out.println("Stock sold successfully.");
    }

    public void displayPortfolio(ArrayList<Stock> stocks) {
        System.out.println("\n========== YOUR PORTFOLIO ==========");

        double portfolioValue = 0;

        if (portfolio.isEmpty()) {
            System.out.println("No stocks in portfolio.");
        } else {
            System.out.printf(
                    "%-10s %-20s %-10s %-15s%n",
                    "Symbol", "Company", "Quantity", "Value"
            );

            for (Stock stock : stocks) {
                if (portfolio.containsKey(stock.getSymbol())) {
                    int quantity = portfolio.get(stock.getSymbol());
                    double value = quantity * stock.getPrice();

                    portfolioValue += value;

                    System.out.printf(
                            "%-10s %-20s %-10d ₹%-14.2f%n",
                            stock.getSymbol(),
                            stock.getCompanyName(),
                            quantity,
                            value
                    );
                }
            }
        }

        System.out.printf("\nCash Balance: ₹%.2f%n", cash);
        System.out.printf("Portfolio Value: ₹%.2f%n", portfolioValue);
        System.out.printf(
                "Total Account Value: ₹%.2f%n",
                cash + portfolioValue
        );
    }

    public void displayTransactions() {
        System.out.println("\n========== TRANSACTION HISTORY ==========");

        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }

        System.out.printf(
                "%-8s %-10s %-10s %-15s%n",
                "Type", "Symbol", "Quantity", "Price"
        );

        for (Transaction transaction : transactions) {
            transaction.displayTransaction();
        }
    }
    public double getCash() {
        return cash;
    }
    public String getName() {
        return name;
    }
    }