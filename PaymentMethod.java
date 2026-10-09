public abstract class PaymentMethod {
    protected double amount;
    protected String methodName;

    public PaymentMethod(String methodName) {
        this.methodName = methodName;
    }

    // concrete method - same receipt format for every payment type
    public String printReceipt() {
        String receipt = "";
        receipt += "=====================================\n";
        receipt += "            PAYMENT RECEIPT\n";
        receipt += "=====================================\n";
        receipt += "Payment Method : " + methodName + "\n";
        receipt += "Amount Paid    : " + String.format("%.2f", amount) + "\n";
        receipt += "=====================================\n";
        return receipt;
    }

    // abstract method - each payment type processes payment its own way
    public abstract String processPayment(double amount);
}
