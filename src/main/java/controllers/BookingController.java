package controllers;

import entities.Booking;
import entities.Passenger;
import services.BookingService;

import java.io.File;
import java.util.List;
import java.util.function.Consumer;


public class BookingController {
    private final BookingService service;

    public BookingController() {
        this(new File("./data", "bookings.txt"));
    }

    public BookingController(File file) {
        service = new BookingService(file);
    }

    public List<Booking> getAllBookings() {
        return service.getAllBookings();
    }

    public boolean makeBooking(String flight, Passenger bookedBy, Passenger... passengers) {
        return service.makeBooking(flight, bookedBy, passengers);
    }

    public List<Booking> getFlightsOfPassenger(Passenger passenger) {
        return service.getFlightsOfPassenger(passenger);
    }

    public Booking cancelBooking(String bookingId) {
        return service.cancelBooking(bookingId);
    }

    public boolean cancelBooking(Booking booking) {
        return service.cancelBooking(booking);
    }

    public void save() {
        service.save();
    }


// TODO Delete before release

    public static void main(String[] args) {
//
//        System.out.println("=========CREATING THREE INITIAL BOOKINGS DEV PURPOSE ONLY============");
        BookingController bookingMgr = new BookingController();
        Passenger ps1 = new Passenger("Slava", "Stepanchuk");
        Passenger ps2 = new Passenger("Nastia", "Stepanchuk");
        Passenger ps3 = new Passenger("Oleksandr", "Ivanov");

        bookingMgr.makeBooking("MyFamilyTrip", ps1, ps1, ps2, ps1, ps1);
        bookingMgr.makeBooking("IvanovToAmsterdam", ps3, ps3);
        bookingMgr.makeBooking("NastyaToStambul", ps2, ps2);
        bookingMgr.save();
        List<Booking> allBookings = bookingMgr.getAllBookings();
        allBookings.forEach(new Consumer<Booking>() {
            @Override
            public void accept(Booking booking) {
                System.out.println(booking);
            }
        });
    }
}
