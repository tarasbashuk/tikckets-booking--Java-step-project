package services;

import dao.BookingDAO;
import entities.Booking;
import entities.Passenger;

import java.util.List;
import java.util.stream.Collectors;

public class BookingService {
    private final BookingDAO data;

    public BookingService(){
        data = new BookingDAO();
        data.retrieveInitialData();
    }

    public List<Booking> getAllBookings(){
        return data.getAll();
    }

    public boolean makeBooking(String flight, Passenger bookedBy, Passenger... passengers){
        if (passengers.length > 0) {
            Booking booking = new Booking(flight, bookedBy);
            for (Passenger person: passengers) {
                booking.addPassenger(person);
            }
            return data.insert(booking);
        } else {
            throw new IllegalArgumentException("Invalid booking: at least one flying passenger is required");
        }
    }

    public List<Booking> getFlightsOfPassenger(Passenger passenger) {
        return data
                .getAll()
                .stream()
                .filter(booking -> (
                        booking.getBookedBy().equals(passenger) ||
                                booking.getPassengers().contains(passenger)
                ))
                .collect(Collectors.toList());
    }

    public boolean cancelBooking(String bookingId) {
        return data.remove(bookingId);
    }

    public boolean cancelBooking(Booking booking) {
        return data.remove(booking);
    }

    public void save(){
        data.saveData();

    }

}
