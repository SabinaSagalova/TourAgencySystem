package payment;

public class PaypalPayment implements PaymentStrategy {
    @Override
    public boolean pay(double amount) {
        System.out.println("Processing payment of $" + amount + " through PayPal...");
        return true;
    }
}
