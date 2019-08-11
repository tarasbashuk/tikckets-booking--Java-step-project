package entities;

import java.util.Date;
import java.util.List;

public interface AirTrip {
    String getDestination();
    String getFrom();
    String getFlightNumber();
    Date getDepart();
    Date getArrival();
    int getSeats();
    List<Flight> getDirectFlights();
}
