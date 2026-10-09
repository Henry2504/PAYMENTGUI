public class CardPayment extends PaymentMethod {
    public CardPayment() {
        super("Card");
    }

    @Override
    public String processPayment(double amount) {
        this.amount = amount;
        return "Card payment of " + String.format("%.2f", amount) + " processed successfully.";
    }
}
