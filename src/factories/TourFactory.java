package factories;

import models.Tour;
import models.AdventureTour;
import models.LuxuryTour;

public class TourFactory {
    public static Tour createTour(String type, String name, double price, String city) {
        switch (type.toLowerCase()) {
            case "adventure":
                return new AdventureTour(name, price, city);
            case "luxury":
                return new LuxuryTour(name, price, city);
            default:
                throw new IllegalArgumentException("Unknown tour type.");
        }
    }
}