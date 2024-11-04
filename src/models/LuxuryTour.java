package models;

public class LuxuryTour extends Tour {
    public LuxuryTour(String name, double price, String city) {
        super(name, price, city);
    }

    @Override
    public void displayDetails() {
        System.out.println("Luxury Tour - " + name + " in " + city + " costs $" + price);
    }
}