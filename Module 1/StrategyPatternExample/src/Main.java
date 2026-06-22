public class Main {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();

        context.setPaymentStrategy(new CreditCardPayment("1234567890123456", "John Doe"));
        context.executePayment(250.00);

        System.out.println();

        context.setPaymentStrategy(new PayPalPayment("john.doe@example.com"));
        context.executePayment(75.50);
    }
}
