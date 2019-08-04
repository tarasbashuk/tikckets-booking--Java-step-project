package view;

import controllers.BookingController;
import controllers.FlightController;
import entities.Flight;
import entities.Passenger;
import services.UserService;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ConsoleView {
    // here Passenger's input and Controlles must combine
    UserService us = new UserService();
    BookingController bc = new BookingController();
    PassengerInputs pi = new PassengerInputs();
    FlightController fc = new FlightController();
//    private Scanner scan = new Scanner(System.in);

    public void startApp() {
        System.out.println("Press 1 to Authorize");
        System.out.println("Press 2 to continue without Authorization");

        switch (pi.getMenuItem()) {
            case 1:
                System.out.println("Authorization:");
                System.out.println("\n");
                startAuthorization();
                break;
            case 2:
                this.showMenu();
                break;
            default:
                System.out.println("Bye");
                System.exit(0);
        }
    }

    public void startAuthorization() {
        String login = pi.getLogin();
        String password = pi.getPassword();
        String name = pi.getName();
        String surname = pi.getSurname();
        Passenger user = us.createUser(login, password, name, surname);
        System.out.println(user);
    }

    public void showMenu() {
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("\n");
        System.out.println("1.Online-table Онайн-табло;");
        System.out.println("2.Check info about flightПосмотреть информацию о рейсе;");
        System.out.println("3.Searching and BookingПоиск и бронировка рейса;");
        System.out.println("4.Cancel BookingОтменить бронирование;");
        System.out.println("5.My Flights Мои рейсы;");
        System.out.println("6.Выход;");
        this.menuItemReader();
    }


    public void menuItemReader() {
        switch (pi.getMenuItem()) {
            case 1:
                // get flight for 24 hours
                List<Flight> nearestFlights = fc.getNearestFlights();
                nearestFlights.forEach(System.out::println);
                break;
            case 2:
                // get flight by id
                // then show all the info about flight(date, time, destination, free seats)
                Flight flightByNumber =
                        fc.getFlightByNumber(pi.getFlightId());
                System.out.println(flightByNumber);
                break;
            case 3:
                // Search for the flight and booking
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
                break;
            case 4:
                // cancel booking
                String flightId = pi.getBookingId();
                bc.cancelBooking(flightId);


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
                System.exit(1);
                break;
            default:
                System.out.println("Try to enter menu item again ");
                menuItemReader();
        }
        this.showMenu();
    }
}
