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

    public boolean bookeSeats(int requiredSeatsQuantity, String flightNumber) {
        return service.bookeSeats(requiredSeatsQuantity, flightNumber);
    }

    public boolean returnSeats(int returningSeatsQuantity, String flightNumber) {
        return service.returnSeats(returningSeatsQuantity, flightNumber);
    }


    public void save() {
        service.save();
    }


// TODO Delete before release

    public static void main(String[] args) throws ParseException {
//        System.out.println("=========CREATING THREE INITIAL FLIGHTS DEV PURPOSE ONLY============");

        // TO FILL Flights.txt

//        Flight lh1 = new Flight("LH123", "kyiv", "san-francisko", "30.08.2019-12:00", 300);
//        Flight lh2 = new Flight("LH222", "kyiv", "barcelona", "01.08.2019-12:00", 250);
//        Flight lh3 = new Flight("LH333", "kyiv", "palma", "03.08.2019-12:00", 200);
//        Flight lh4 = new Flight("LH444", "kyiv", "madrid", "04.08.2019-22:00", 250);
//        Flight lh5 = new Flight("LH555", "kyiv", "tel-aviv", "04.08.2019-23:00", 200);
//        Flight lh6 = new Flight("LH666", "kyiv", "new-york", "05.08.2019-12:00", 250);
//        Flight lh7 = new Flight("LH777", "kyiv", "wroclaw", "06.08.2019-11:00", 200);
//        Flight lh8 = new Flight("LH888", "kyiv", "praha", "07.08.2019-12:00", 250);
//        Flight lh9 = new Flight("LH999", "kyiv", "oslo", "07.08.2019-19:00", 200);
//        FlightDAO flDAO = new FlightDAO();
//        flDAO.insert(lh1);
//        flDAO.insert(lh2);
//        flDAO.insert(lh3);
//        flDAO.insert(lh4);
//        flDAO.insert(lh5);
//        flDAO.insert(lh6);
//        flDAO.insert(lh7);
//        flDAO.insert(lh8);
//        flDAO.insert(lh9);
//        flDAO.saveData();
//        FlightService flService = new FlightService();

        FlightController flightMgr = new FlightController();

        System.out.println(flightMgr.getAllFlights());

        System.out.println(flightMgr.getSuitableFlights("palma", "03.08.2019", 3));
        System.out.println(flightMgr.getFlightByNumber("LH123"));
        System.out.println(flightMgr.getNearestFlights());
        flightMgr.bookeSeats(5, "LH222");
        flightMgr.returnSeats(3, "LH222");
        System.out.println(flightMgr.getAllFlights());
        flightMgr.save();
    }
}
