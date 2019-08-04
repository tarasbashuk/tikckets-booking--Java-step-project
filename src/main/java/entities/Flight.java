package entities;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Flight implements Serializable {
    private final String flightNumber;
    private final String from;
    private final String destination;
    private final Date date;
    private int seats;

    private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy-HH:mm");
    private SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.yyyy");
    private SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");


    public Flight(String flightNumber, String from, String destination, String date, int seats) throws ParseException {
        this.flightNumber = flightNumber;
        this.from = from;
        this.destination = destination;
        this.date = format.parse(date);
        this.seats = seats;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDestination() {
        return destination;
    }

    public String getDateString() {
        return formatDate.format(date);
    }

    public Date getDate() {
        return date;
    }

    public String getTime() {
        return formatTime.format(date);
    }

    public int getSeats() {
        return seats;
    }

    public boolean bookeSeats(int bookedSeats) {
        if (this.seats - bookedSeats < 0) {
            return false;
        }
        this.seats -= bookedSeats;
        return true;
    }
    public void returnSeats(int bookedSeats) {
        this.seats += bookedSeats;
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
        sb.append(", date: ");
        sb.append(formatDate.format(date));
        sb.append(", at: ");
        sb.append(formatTime.format(date));
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