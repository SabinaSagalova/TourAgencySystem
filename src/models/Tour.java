package models;

import java.util.ArrayList;
import java.util.List;

public abstract class Tour {
    protected String name;
    protected double price;
    protected String city;
    protected List<Transportation> transportationOptions = new ArrayList<>();

    public Tour(String name, double price, String city) {
        this.name = name;
        this.price = price;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCity() {
        return city;
    }

    public void addTransportation(Transportation transportation) {
        transportationOptions.add(transportation);
    }

    public List<Transportation> getTransportationOptions() {
        return transportationOptions;
    }

    public abstract void displayDetails();
}