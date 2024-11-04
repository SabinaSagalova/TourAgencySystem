package payment;

public class KaspiGoldPayment implements PaymentStrategy {
    @Override
    public boolean pay(double amount) {
        System.out.println("Processing payment of $" + amount + " through Kaspi Gold...");
        return true;
    }
}