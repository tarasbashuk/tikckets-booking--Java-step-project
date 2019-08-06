package controllers;

import dao.FlightDAO;
import entities.Flight;
import services.FlightService;

import java.io.File;
import java.text.ParseException;
import java.util.List;

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

    public List<Flight> getSuitableFlights(String destination, String date, int requiredSeatsQuantity) {
        return service.getSuitableFlights(destination, date, requiredSeatsQuantity);
    }

    public Flight getFlightByNumber(String flightNumber) {
        return service.getFlightByNumber(flightNumber);
    }

    public List<Flight> getNearestFlights() {
        return service.getNearestFlights();
    }

    public boolean bookSeats(int requiredSeatsQuantity, String flightNumber) {
        return service.bookSeats(requiredSeatsQuantity, flightNumber);
    }

    public boolean returnSeats(int returningSeatsQuantity, String flightNumber) {
        return service.returnSeats(returningSeatsQuantity, flightNumber);
    }


    public void save() {
        service.save();
    }


// TODO Delete before release

    public static void main(String[] args) throws ParseException {
        System.out.println("=========CREATING THREE INITIAL FLIGHTS DEV PURPOSE ONLY============");

        FlightController flightMgr = new FlightController();

        System.out.println(flightMgr.getAllFlights());

        System.out.println(flightMgr.getSuitableFlights("barcelona", "05.08.2019", 3));
        System.out.println(flightMgr.getNearestFlights());
    }
}
