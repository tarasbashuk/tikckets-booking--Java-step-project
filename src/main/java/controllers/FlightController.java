package controllers;

import dao.FlightDAO;
import entities.Flight;
import services.FlightService;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class FlightController {
    private final FlightService service;

    public FlightController(){
        this(new File("./data", "flights.txt"));
    }

    public FlightController(File file){
        service = new FlightService(file);
    }
//FOR DEV PURPOSE ONLY///
//        public FlightController(HashMap<String, Flight> map){
//        service = new FlightService(map);
//    }

    public List<Flight> getAllFlights() {
        return service.getAllFlights();
    }

    public List<Flight> getSuitableFlights(String destination, Date date, int requiredSeatsQuantity) {
        return service.getSuitableFlights(destination, date, requiredSeatsQuantity);
    }

    public boolean bookeSeats(int requiredSeatsQuantity, String flightNumber) {
        return service.bookeSeats(requiredSeatsQuantity, flightNumber);
    }

    public boolean returnSeats(int returningSeatsQuantity, String flightNumber) {
        return service.returnSeats(returningSeatsQuantity, flightNumber);
    }

//
//    public void save() {
//        service.save();
//    }


// TODO Delete before release

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy-HH:mm");
        Date date = format.parse("01.08.2019-12:00");
        System.out.println(format.format(date));
        System.out.println("=========CREATING THREE INITIAL FLIGHTS DEV PURPOSE ONLY============");

//        Flight lh1 = new Flight("LH123", "kyiv", "san-francisko", "30.08.2019-12:00", 300);
//        Flight lh2 = new Flight("LH222", "kyiv", "barcelona", "01.08.2019-12:00", 250);
//        Flight lh3 = new Flight("LH333", "kyiv", "palma", "03.08.2019-12:00", 200);
//        HashMap<String, Flight> map = new HashMap<>();
//        map.put(lh1.getFlightNumber(), lh1);
//        map.put(lh2.getFlightNumber(), lh3);
//        map.put(lh3.getFlightNumber(), lh3);


        FlightDAO flDAO = new FlightDAO();
        FlightService flService = new FlightService();

        FlightController flightMgr = new FlightController();

        System.out.println(flightMgr.getAllFlights());

        System.out.println(flightMgr.getSuitableFlights("barcelona", date, 300));

//        System.out.println(lh2.getDate().equals(date));
//
//        System.out.println(lh2.getDestination().equals("barcelona"));

        flightMgr.bookeSeats(5, "LH123");
        System.out.println(flightMgr.getAllFlights());
//        flightMgr.returnSeats(3, "LH222");
//        System.out.println(flightMgr.getAllFlights());

    }
}
