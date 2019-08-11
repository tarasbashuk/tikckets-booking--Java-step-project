package view;

import controllers.BookingController;
import controllers.FlightController;
import controllers.UserController;
import entities.Flight;
import entities.Passenger;
import logger.Logger;
import services.UserService;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ConsoleView {
    // here Passenger's input and Controllers must combine
    UserController uc = new UserController();
    BookingController bc = new BookingController();
    PassengerInputs pi = new PassengerInputs();
    FlightController fc = new FlightController();
    private boolean isAuthorized = false;
    Logger logger = new Logger();
    private Passenger passenger;
    private String currentUser;


    public void startApp() {
        System.out.println("Press 1 to Register new user");
        System.out.println("Press 2 to Authorize");
        System.out.println("Press 3 to continue without Authorization");

        switch (pi.getMenuItem()) {
            case 1:
                System.out.println("Registration");
                System.out.println("\n");
                startRegistration();
                break;
            case 2:
                System.out.println("Authorization:");
                startAuthorization();
            case 3:
                this.showMenu();
                break;
            default:
                System.out.println("Bye");
                System.exit(0);
        }
    }

    private void startAuthorization() {
        String login = pi.getLogin();
        String password = pi.getPassword();
        passenger = uc.getPassengerData(login, password);
        isAuthorized = true;
        System.out.println(passenger);
    }

    public void startRegistration() {
        String login = pi.getLogin();
        String password = pi.getPassword();
        String name = pi.getName();
        String surname = pi.getSurname();
        passenger = uc.createUser(login, password, name, surname);
        isAuthorized = true;
        System.out.println(passenger);
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
        this.menuItemReader();
    }


    public void getNearestFlight() {
        List<Flight> nearestFlights = fc.getNearestFlights();
        nearestFlights.forEach(System.out::println);
    }

    public void getFlightByIdAndShowIt() {
        Flight flightByNumber =
                fc.getFlightByNumber(pi.getFlightId());
        System.out.println(flightByNumber);
    }

    public void searchAndBooking() {
        AtomicInteger flightNumber = new AtomicInteger(1);
        String destination = pi.getDestination();
        String date = pi.getDate();
        int ticketsQuantity = pi.getTicketsQuantity();
        List<Flight> suitableFlights = fc.getSuitableFlights(destination, date, ticketsQuantity);
        suitableFlights.forEach(f -> System.out.println(flightNumber.getAndIncrement() + " . " + f));

        Flight flight = suitableFlights.get(pi.getMenuItem() - 1);

        bc.makeBooking(flight.getFlightNumber(), new Passenger(pi.getName(), pi.getSurname()));
        fc.bookeSeats(ticketsQuantity,
                flight.getFlightNumber());
    }


    public void menuItemReader() {

        if (isAuthorized) {
            switch (pi.getMenuItem()) {
                case 1:
                    // get flight for 24 hours
                    getNearestFlight();
                    logger.add("sdfdsf", "asdsd");
                    break;
                case 2:
                    // get flight by id
                    // then show all the info about flight(date, time, destination, free seats)
                    getFlightByIdAndShowIt();
                    break;
                case 3:
                    // Search for the flight and booking
                    searchAndBooking();
                    break;
                case 4:
                    // cancel booking
                    String flightId = pi.getBookingId();
                    bc.cancelBooking(flightId);
                    // TODO
                    //fc.returnSeats(flightId);
                    break;
                case 5:
                    // get my bookings
                    String name = pi.getName();
                    String surname = pi.getSurname();
                    System.out.println(bc.getFlightsOfPassenger(new Passenger(name, surname)));
                    break;
                case 6:
                    // exit
                    logger.save();
                    fc.save();
                    bc.save();
                    System.exit(1);
                    break;
                case 7:
                    logger.save();
                    //TODO logout
                default:
                    System.out.println("Try to enter menu item again ");
                    menuItemReader();
            }
            this.showMenu();
        } else {
            switch (pi.getMenuItem()) {
                case 1:
                    // get flight for 24 hours
                    getNearestFlight();
                    logger.add("sdfdsf", "asdsd");
                    break;
                case 2:
                    // get flight by id
                    // then show all the info about flight(date, time, destination, free seats)
                    getFlightByIdAndShowIt();
                    break;
                case 3:
                    // Search for the flight and booking
                    searchAndBooking();
                    break;
                case 4:
                    // cancel booking
                    String flightId = pi.getBookingId();
                    bc.cancelBooking(flightId);
                    // TODO
                    //fc.returnSeats(flightId);
                    break;
                case 5:
                    // get my bookings
                    String name = pi.getName();
                    String surname = pi.getSurname();
                    System.out.println(bc.getFlightsOfPassenger(new Passenger(name, surname)));
                    break;
                case 6:
                    // exit
                    fc.save();
                    bc.save();
                    logger.save();
                    System.exit(1);
                    break;
                default:
                    System.out.println("Try to enter menu item again ");
                    menuItemReader();
            }
            this.showMenu();
        }


    }
}
