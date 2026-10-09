public class CashPayment extends PaymentMethod {
    public CashPayment() {
        super("Cash");
    }

    @Override
    public String processPayment(double amount) {
        this.amount = amount;
        return "Cash payment of " + String.format("%.2f", amount) + " received successfully.";
    }
}
