package models;

public class AdventureTour extends Tour {
    public AdventureTour(String name, double price, String city) {
        super(name, price, city);
    }

    @Override
    public void displayDetails() {
        System.out.println("Adventure Tour - " + name + " in " + city + " costs $" + price);
    }
}