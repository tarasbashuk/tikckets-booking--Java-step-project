import entities.Booking;
import entities.Passenger;

public class Main {
    public static void main(String[] args) {
        Passenger passenger = new Passenger("Slava", "Stepanchuk");
        Booking booking = new Booking("FlightId", passenger);
        System.out.println(booking.hashCode());
    }
}
