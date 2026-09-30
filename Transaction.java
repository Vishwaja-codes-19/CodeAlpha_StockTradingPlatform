public class Transaction {
    private String type;
    private String stockSymbol;
    private int quantity;
    private double price;

    public Transaction(String type, String stockSymbol, int quantity, double price) {
        this.type = type;
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.price = price;
    }

    public void displayTransaction() {
        System.out.printf(
                "%-8s %-10s %-10d ₹%.2f%n",
                type, stockSymbol, quantity, price
        );
    }
}