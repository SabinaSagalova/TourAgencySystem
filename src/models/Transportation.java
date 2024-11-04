package models;

public class Transportation {
    private String mode;
    private double price;
    private int duration;

    public Transportation(String mode, double price, int duration) {
        this.mode = mode;
        this.price = price;
        this.duration = duration;
    }
}