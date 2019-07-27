package controllers;

import entities.Booking;
import entities.Passenger;
import services.BookingService;

import java.util.List;

public class BookingController {
    private final BookingService service = new BookingService();

    public List<Booking> getAllBookings(){
        return service.getAllBookings();
    }

    public boolean makeBooking(String flight, Passenger bookedBy, Passenger... passengers){
        return service.makeBooking(flight, bookedBy, passengers);
    }

    public List<Booking> getFlightsOfPassenger(Passenger passenger) {
        return service.getFlightsOfPassenger(passenger);
    }

    public boolean cancelBooking(String bookingId) {
        return service.cancelBooking(bookingId);
    }

    public boolean cancelBooking(Booking booking) {
        return service.cancelBooking(booking);
    }

    public void save(){
        service.save();
    }
}
