public class Main {
    public static void main(String[] args) {
        System.out.println("--- Testing Adapter Design Pattern ---");

        
        PayPalGateway payPalGateway = new PayPalGateway();
        PaymentProcessor payPalProcessor = new PayPalAdapter(payPalGateway);
        payPalProcessor.processPayment(120.50);

        System.out.println();

        StripeGateway stripeGateway = new StripeGateway();
        PaymentProcessor stripeProcessor = new StripeAdapter(stripeGateway);
        stripeProcessor.processPayment(350.75);
    }
}
