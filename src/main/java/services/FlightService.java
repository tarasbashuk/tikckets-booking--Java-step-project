package services;

import dao.FlightDAO;
import entities.Flight;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class FlightService {
    private final FlightDAO data;

    public FlightService() {
        this(new File("./data", "flights.txt"));
    }

    public FlightService(File file) {
        data = new FlightDAO(file);
        data.retrieveInitialData();
    }

    public List<Flight> getAllFlights() {
        return data.getAll();
    }

    public List<Flight> getSuitableFlights(String destination, String date, int requiredSeatsQuantity) {
        return data
                .getAll()
                .stream()
                .filter(flight -> (
                        flight.getDestination().equals(destination)
                ))
                .filter(flight -> (
                        flight.getDateString().equals(date)
                ))
                .filter(flight -> (
                        flight.getSeats() >= requiredSeatsQuantity
                ))
                .collect(Collectors.toList());
    }

    public List<Flight> getNearestFlights() {
        Date dateNow = new Date();
        Date after24Hours = new Date(dateNow.getTime() + 86400000);
        return data
                .getAll()
                .stream()
                .filter(flight -> (
                        flight.getDate().after(dateNow)
                ))
                  .filter(flight -> (
                        flight.getDate().before(after24Hours)
                ))
                .collect(Collectors.toList());
    }

    public Flight getFlightByNumber(String flightNumber) {
        return data.getAll()
                .stream()
                .filter(flight -> (
                        flight.getFlightNumber().equals(flightNumber)
                ))
                .collect(Collectors.toList()).get(0);
    }

    public boolean bookeSeats(int requiredSeatsQuantity, String flightNumber) {
        if (flightNumber == null) throw new IllegalArgumentException("Invalid arguments: flight number can't be null");
        Flight flight = data.get(flightNumber);

        if (!flight.bookeSeats(requiredSeatsQuantity)) return false;

        return data.update(flight);

    }


    public boolean returnSeats(int returningSeatsQuantity, String flightNumber) {
        if (flightNumber == null) throw new IllegalArgumentException("Invalid arguments: flight number can't be null");
        Flight flight = data.get(flightNumber);

        flight.returnSeats(returningSeatsQuantity);
        return data.update(flight);
    }

    public void save() {
        data.saveData();

    }

}
