package services;

import models.Tour;

import java.util.ArrayList;
import java.util.List;

public class TourService {
    private List<Tour> tours = new ArrayList<>();

    public void addTour(Tour tour) {
        tours.add(tour);
    }

    public List<Tour> findToursWithinBudget(double budget) {
        List<Tour> affordableTours = new ArrayList<>();
        for (Tour tour : tours) {
            if (tour.getPrice() <= budget) {
                affordableTours.add(tour);
            }
        }
        return affordableTours;
    }
}
