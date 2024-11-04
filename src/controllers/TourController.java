package controllers;

import factories.TourFactory;
import models.Tour;
import models.User;
import services.TourService;
import services.PaymentService;
import services.UserService;
import payment.HalkBankPayment;
import payment.KaspiGoldPayment;
import payment.PaypalPayment;

import java.util.List;
import java.util.Scanner;

public class TourController {
    private TourService tourService = new TourService();
    private UserService userService = new UserService();
    private static final double TRANSPORTATION_COST = 100.0;

    public void initializeTours() {
        tourService.addTour(TourFactory.createTour("adventure", "Mountain Hike", 300, "Almaty"));
        tourService.addTour(TourFactory.createTour("luxury", "City Tour", 700, "Astana"));
        tourService.addTour(TourFactory.createTour("adventure", "Desert Safari", 400, "Shymkent"));
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Tour Agency!");

        while (true) {
            System.out.println("Choose an option: 1. Register 2. Login 3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                userService.registerUser(username, password);
            } else if (choice == 2) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                User user = userService.authenticateUser(username, password);
                if (user != null) {
                    System.out.println("Login successful! Welcome, " + user.getUsername() + ".");
                    bookTour(scanner);
                }
            } else if (choice == 3) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void bookTour(Scanner scanner) {
        System.out.print("Choose tour type (Adventure/Luxury): ");
        String tourType = scanner.nextLine();

        System.out.print("Enter your budget: ");
        double budget = scanner.nextDouble();
        scanner.nextLine();

        List<Tour> tours = tourService.findToursWithinBudget(budget);
        if (tours.isEmpty()) {
            System.out.println("No tours available within your budget.");
            return;
        }

        System.out.println("Available tours:");
        for (int i = 0; i < tours.size(); i++) {
            System.out.println((i + 1) + ". " + tours.get(i).getName() + " - " + tours.get(i).getCity() + " - $" + tours.get(i).getPrice());
        }

        System.out.print("Choose a tour by number: ");
        int tourChoice = scanner.nextInt();
        scanner.nextLine();
        Tour selectedTour = tours.get(tourChoice - 1);

        // Ask about transportation
        System.out.print("Would you like to include transportation for an additional $" + TRANSPORTATION_COST + "? (yes/no): ");
        String transportationChoice = scanner.nextLine().toLowerCase();
        double totalCost = selectedTour.getPrice();
        if ("yes".equals(transportationChoice)) {
            totalCost += TRANSPORTATION_COST;
        }

        System.out.print("Choose payment method (HalkBank/KaspiGold/PayPal): ");
        String paymentMethod = scanner.nextLine();
        PaymentService paymentService;
        switch (paymentMethod.toLowerCase()) {
            case "halkbank":
                paymentService = new PaymentService(new HalkBankPayment());
                break;
            case "kaspigold":
                paymentService = new PaymentService(new KaspiGoldPayment());
                break;
            case "paypal":
                paymentService = new PaymentService(new PaypalPayment());
                break;
            default:
                System.out.println("Invalid payment method.");
                return;
        }

        boolean success = paymentService.processPayment(totalCost);
        if (success) {
            System.out.println("Your tour is successfully booked. Enjoy your trip!");
        } else {
            System.out.println("Sorry, something went wrong. Please try again.");
        }
    }
}
