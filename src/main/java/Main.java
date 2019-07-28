import controllers.BookingController;
import entities.Booking;
import entities.Passenger;

import java.util.List;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {

        BookingController bookingMgr = new BookingController();

        System.out.println("=================================================================");

        Passenger ps1 = new Passenger("Slava", "Stepanchuk");
        Passenger ps2 = new Passenger("Nastia", "Stepanchuk");
        Passenger ps3 = new Passenger("Oleksandr", "Ivanov");

        bookingMgr.makeBooking("MyFamilyTrip", ps1,ps1,ps2,ps1,ps1);
        bookingMgr.makeBooking("IvanovToAmsterdam", ps3, ps3);
        bookingMgr.makeBooking("NastyaToStambul", ps2, ps2);

        System.out.println("=================================================================");

        List<Booking> allBookings = bookingMgr.getAllBookings();
        allBookings.forEach(new Consumer<Booking>() {
            @Override
            public void accept(Booking booking) {
                System.out.println(booking);
            }
        });

        System.out.println("=================================================================");
        bookingMgr.save();


    }
}
