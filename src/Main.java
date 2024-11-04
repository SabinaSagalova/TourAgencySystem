import controllers.TourController;

public class Main {
    public static void main(String[] args) {
        TourController controller = new TourController();
        controller.initializeTours();
        controller.start();
    }
}