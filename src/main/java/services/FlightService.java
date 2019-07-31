package services;

import dao.FlightDAO;
import entities.Flight;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class FlightService {
    private final FlightDAO data;

//    public FlightService() {
//        this(new File("./data", "flights.txt"));
//    }

//    public FlightService(File file) {
//        data = new FlightDAO(file);
//        data.retrieveInitialData();
//    }
//FOR DEV PURPOSE ONLY///
        public FlightService(HashMap<String, Flight> map) {
        data = new FlightDAO(map);
    }


    public List<Flight> getAllFlights() {
        return data.getAll();
    }

    public List<Flight> getSuitableFlights(String destination, Date date, int requiredSeatsQuantity) {
        return data
                .getAll()
                .stream()
                .filter(flight -> (
                        flight.getDestination().equals(destination)
                ))
                .filter(flight -> (
                        flight.getDate().equals(date)
                ))
                .filter(flight -> (
                        flight.getSeats() >= requiredSeatsQuantity
                ))
                .collect(Collectors.toList());
    }

    public boolean bookeSeats(int requiredSeatsQuantity, String flightNumber) {
        if (flightNumber == null) throw new IllegalArgumentException("Invalid arguments: flight number can't be null");
        Flight flight = data.get(flightNumber);

        ///Probably unnecessary check///

        if (!flight.bookeSeats(requiredSeatsQuantity)) return false;

        return data.update(flight);

    }


    public boolean returnSeats(int returningSeatsQuantity, String flightNumber) {
        if (flightNumber == null) throw new IllegalArgumentException("Invalid arguments: flight number can't be null");
        Flight flight = data.get(flightNumber);

        flight.returnSeats(returningSeatsQuantity);
        return data.update(flight);
    }

//
//    public void save() {
//        data.saveData();
//
//    }

}
