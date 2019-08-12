package view;

import controllers.ConsoleController;
import entities.Passenger;
import logger.Logger;


public class ConsoleView {
    PassengerInputs pi = new PassengerInputs();
    Logger logger = new Logger();
    private ConsoleController CC = new ConsoleController();
    private Passenger passenger;
    private boolean isAuthorized = false;

    public void startApp() {
        System.out.println("Press 1 to Register new user");
        System.out.println("Press 2 to Authorize");
        System.out.println("Press 3 to continue without Authorization");
        switch (pi.getMenuItem()) {
            case 1:
                System.out.println("Registration:\n");
                passenger = CC.startRegistration();
                System.out.println(passenger);
                isAuthorized = true;
                this.showMenu();
                break;
            case 2:
                System.out.println("Authorization:\n");
                passenger = CC.startAuthorization();
                if (passenger == null) {
                    System.out.println("Wrong data");
                    System.exit(0);
                }
                isAuthorized = true;
                System.out.println(passenger);
                this.showMenu();
                isAuthorized = true;
            case 3:
                this.showMenu();
                break;
            default:
                System.out.println("Bye");
                System.exit(0);
        }
    }

    public void showMenu() {
        System.out.print("\n");
        System.out.print("\n");
        System.out.println("1.Online-table;");
        System.out.println("2.Check info about flight;");
        System.out.println("3.Searching and Booking;");
        System.out.println("4.Cancel Booking;");
        System.out.println("5.My Flights ;");
        System.out.println("6.Exit;");
        if (isAuthorized) {
            System.out.println("7.Logout");
        }
        this.menuItemReader();
    }


    public void menuItemReader() {
        switch (pi.getMenuItem()) {
            case 1:
                CC.getNearestFlight();
                if (isAuthorized) logger.add(passenger.getName(), "Searched for nearest flight");
                break;
            case 2:
                CC.getFlightByIdAndShowIt();
                if (isAuthorized) logger.add(passenger.getName(), "Got flight by Id");
                break;
            case 3:
                CC.searchAndBooking();
                if (isAuthorized) logger.add(passenger.getName(), "Searched and Booked");
                break;
            case 4:
                CC.cancelBooking();
                if (isAuthorized) logger.add(passenger.getName(), "Cancelled booking");
                break;
            case 5:
                CC.getBookingsOfPassenger(passenger, isAuthorized);
                if (isAuthorized) logger.add(passenger.getName(), "Searched for own bookings");
                break;
            case 6:
                CC.saveChanges(isAuthorized);
                if (isAuthorized) logger.save();
                System.exit(1);
                break;
            case 7:
                CC.saveChanges(isAuthorized);
                if (isAuthorized) {
                    logger.save();
                    passenger = null;
                    isAuthorized = false;
                    this.startApp();
                } else {
                    System.exit(1);
                }
            default:
                System.out.println("Try to enter menu item again ");
                menuItemReader();
        }
        this.showMenu();
    }
}
