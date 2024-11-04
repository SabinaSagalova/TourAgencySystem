package payment;

public class HalkBankPayment implements PaymentStrategy {
    @Override
    public boolean pay(double amount) {
        System.out.println("Processing payment of $" + amount + " through HalkBank...");
        return true;
    }
}