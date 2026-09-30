import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<Stock> stocks = new ArrayList<>();

        stocks.add(new Stock("TCS", "Tata Consultancy", 3500));
        stocks.add(new Stock("INFY", "Infosys", 1800));
        stocks.add(new Stock("RELIANCE", "Reliance Industries", 2900));
        stocks.add(new Stock("HDFC", "HDFC Bank", 1700));
        stocks.add(new Stock("ITC", "ITC Limited", 500));

        System.out.println("======================================");
        System.out.println("       STOCK TRADING PLATFORM");
        System.out.println("======================================");

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter initial cash: ₹");
        double initialCash = scanner.nextDouble();

        User user = new User(name, initialCash);

        int choice;

        do {
            System.out.println("\n========== MAIN MENU ==========");
            System.out.println("1. Display Market Data");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. View Transaction History");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    displayMarketData(stocks);
                    break;

                case 2:
                    buyStock(scanner, stocks, user);
                    break;

                case 3:
                    sellStock(scanner, stocks, user);
                    break;

                case 4:
                    user.displayPortfolio(stocks);
                    break;

                case 5:
                    user.displayTransactions();
                    break;

                case 6:
                    System.out.println("\nThank you for using");
                    System.out.println("Stock Trading Platform!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 6);

        scanner.close();
    }

    public static void displayMarketData(ArrayList<Stock> stocks) {

        System.out.println("\n========== MARKET DATA ==========");

        System.out.printf(
                "%-10s %-20s %s%n",
                "Symbol", "Company", "Price"
        );

        for (Stock stock : stocks) {
            stock.displayStock();
        }
    }

    public static void buyStock(
            Scanner scanner,
            ArrayList<Stock> stocks,
            User user) {

        displayMarketData(stocks);

        System.out.print("\nEnter stock symbol to buy: ");
        String symbol = scanner.next().toUpperCase();

        Stock stock = findStock(stocks, symbol);

        if (stock == null) {
            System.out.println("Stock not found.");
            return;
        }

        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();

        user.buyStock(stock, quantity);
    }

    public static void sellStock(
            Scanner scanner,
            ArrayList<Stock> stocks,
            User user) {

        displayMarketData(stocks);

        System.out.print("\nEnter stock symbol to sell: ");
        String symbol = scanner.next().toUpperCase();

        Stock stock = findStock(stocks, symbol);

        if (stock == null) {
            System.out.println("Stock not found.");
            return;
        }

        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();

        user.sellStock(stock, quantity);
    }

    public static Stock findStock(
            ArrayList<Stock> stocks,
            String symbol) {

        for (Stock stock : stocks) {

            if (stock.getSymbol().equalsIgnoreCase(symbol)) {
                return stock;
            }
        }

        return null;
    }
}