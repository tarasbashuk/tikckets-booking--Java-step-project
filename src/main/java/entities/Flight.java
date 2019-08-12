package entities;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Flight implements Serializable, AirTrip {
    private final String flightNumber;
    private final String from;
    private final String destination;
    private final Date depart;
    private final Date arrival;
    private int seats;

    private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy-HH:mm");
    private SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.yyyy");
    private SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");


    public Flight(String flightNumber, String from, String destination, String depart, String arrival, int seats) throws ParseException {
        this.flightNumber = flightNumber;
        this.from = from;
        this.destination = destination;
        this.depart = format.parse(depart);
        this.arrival = format.parse(arrival);
        this.seats = seats;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDestination() {
        return destination;
    }

    @Override
    public String getFrom() {
        return from;
    }

    public String getDepartString() {
        return formatDate.format(depart);
    }

    public Date getDepart() {
        return depart;
    }

    public String getDepartTime() {
        return formatTime.format(depart);
    }

    @Override
    public Date getArrival() {
        return arrival;
    }

    public String getArrivalTime() {
        return formatTime.format(arrival);
    }

    public int getSeats() {
        return seats;
    }

    public boolean bookSeats(int bookedSeats) {
        if (this.seats - bookedSeats < 0) {
            return false;
        }
        this.seats -= bookedSeats;
        return true;
    }
    public void returnSeats(int bookedSeats) {
        this.seats += bookedSeats;
    }

    public List<Flight> getDirectFlights(){
        List<Flight> result = new ArrayList<>();
        result.add(this);
        return result;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{Flight number: ");
        sb.append(flightNumber);
        sb.append(", from: ");
        sb.append(from);
        sb.append(", to: ");
        sb.append(destination);
        sb.append(", departure: ");
        sb.append(formatDate.format(depart));
        sb.append(", at: ");
        sb.append(getDepartTime());
        sb.append(", arrival: ");
        sb.append(formatDate.format(arrival));
        sb.append(", at: ");
        sb.append(getArrivalTime());
        sb.append(", seats available: ");
        sb.append(seats);
        sb.append("}");
        return sb.toString();
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        Flight flight = (Flight) obj;
        return this.flightNumber.equals(flight.flightNumber);
    }

    @Override
    public int hashCode() {
        return this.flightNumber.hashCode();
    }

}