package entities;

import java.util.Date;

public interface AirTrip {
    String getDestination();
    String getFrom();
    String getFlightNumber();
    Date getDepart();
    Date getArrival();
    int getSeats();
    boolean bookSeats(int bookedSeats);
    void returnSeats(int returnedSeats);
}
