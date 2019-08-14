package controllers;

import entities.AirTrip;
import entities.Flight;
import helpers.FlightsScanner;
import services.FlightService;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

public class FlightController {
    private final FlightService service;

    public FlightController(){
        this(new File("./data", "flights.txt"));
    }

    public FlightController(File file){
        service = new FlightService(file);
    }

    public List<Flight> getAllFlights() {
        return service.getAllFlights();
    }

//    public List<Flight> getSuitableFlights(String destination, String date, int requiredSeatsQuantity) {
//        return service.getSuitableFlights(destination, date, requiredSeatsQuantity);
//    }

    public List<AirTrip> getSuitableFlights(String destination, String date, int requiredSeatsQuantity) {
        Date d;
        try {
            d = new SimpleDateFormat("dd.MM.yyyy").parse(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException(String.format("Invalid date format parameter %s", date));
        }

        List<Flight> options = service.getAllFlightsWithinTimeRange(d, new Date(d.getTime()+(1000*60*60*72)));
        return new FlightsScanner("kyiv",destination, new ArrayList<>(options)).findAllConnections(d, requiredSeatsQuantity);
    }

    public Flight getFlightByNumber(String flightNumber) {
        return service.getFlightByNumber(flightNumber);
    }

    public List<Flight> getNearestFlights() {
        return service.getNearestFlights();
    }

    public boolean bookSeats(int requiredSeatsQuantity, String flightNumber) {
        List<String> backup = new ArrayList<>();
        for (String flight: flightNumber.split("&")) {
            if (!service.bookSeats(requiredSeatsQuantity, flight)){
                backup.forEach(s -> service.returnSeats(requiredSeatsQuantity, flight));
                return false;
            }
            backup.add(flight);
        }
            return true;
    }

    public boolean returnSeats(int returningSeatsQuantity, String flightNumber) {
        for (String flight: flightNumber.split("&")) {
            if (!service.returnSeats(returningSeatsQuantity, flight)){
                return false;
            }
        }
        return true;
    }

    public void save() {
        service.save();
    }


// TODO Delete before release

    public static void main(String[] args) throws ParseException {
        System.out.println("=========CREATING THREE INITIAL FLIGHTS DEV PURPOSE ONLY============");

        FlightController flightMgr = new FlightController();

//        System.out.println(flightMgr.getAllFlights());

//        List<Flight> flights = flightMgr.getAllTripsWithinTimeRange("06.08.2019", "08.08.2019");
//        List<Flight> flights = flightMgr.getAllTripsWithinTimeRange("06.08.2019", "08.08.2019");
        List<AirTrip> flights = flightMgr.getSuitableFlights("amsterdam","05.08.2019",1);
        System.out.println(flights);
    }
}
