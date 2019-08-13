package controllers;

import dao.FlightDAO;
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

    public List<AirTrip> getSuitableFlights(String destination, String date, int requiredSeatsQuantity) {
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

    public List<Flight> getAllTripsWithinTimeRange(String date1, String date2) throws ParseException{
        Date d1 = new SimpleDateFormat("dd.MM.yyyy").parse(date1);
        Date d2 = new SimpleDateFormat("dd.MM.yyyy").parse(date2);

        Date fullD2 = new Date(d2.getTime()+(1000*60*60*24-1000));
        return service.getAllFlightsWithinTimeRange(d1,fullD2);
    }


    public void save() {
        service.save();
    }


// TODO Delete before release

    public static void main(String[] args) throws ParseException {
        System.out.println("=========CREATING THREE INITIAL FLIGHTS DEV PURPOSE ONLY============");

        FlightController flightMgr = new FlightController();

//        System.out.println(flightMgr.getAllFlights());

        List<Flight> flights = flightMgr.getAllTripsWithinTimeRange("05.08.2019", "07.08.2019");
        List<AirTrip> fl = new ArrayList<>(flights);
        List<AirTrip> connections = new FlightsScanner("kyiv","barcelona", fl).findAllConnections();
        connections.forEach(System.out::println);


//        System.out.println(flightMgr.getSuitableFlights("barcelona", "05.08.2019", 3));
//        System.out.println(flightMgr.getNearestFlights());
    }
}
