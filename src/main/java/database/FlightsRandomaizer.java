package database;


import entities.Flight;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class FlightsRandomaizer {

    private final String[] destinations = {"barcelona", "madrid", "palma", "valencia", "sevillia", "girona"};
    private final int arraySize = destinations.length;
    private final int quantity;
    private String flightNumber;
    private final String from = "kyiv";
    private String date;
    private int seats;
    private final int seatsMin = 100;
    private final int seatsMax = 350;
    private final int yearMin = 2000;
    private final int yearMax = 2016;


    public FlightsRandomaizer(int quantity) {
        this.quantity = quantity;
    }

    int generateValueFromRange(int min, int max) {
        Random r = new Random();
        int val = r.nextInt(max - min);
        return min + val;
    }

    public List<Flight> get() throws ParseException {
        ArrayList<Flight> flights = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            flights.add(generateNewFlight());
        }
        return flights;
    }

    private String generateFlightNumber(String from, String destination, int id) {
        StringBuilder sb = new StringBuilder();
        sb.append(from.charAt(0));
        sb.append(destination.charAt(0));
        sb.append(id);
        return sb.toString();
    }

    private String generateDate() {
        StringBuilder sb = new StringBuilder();

        return sb.toString();
    }

    private Flight generateNewFlight() throws ParseException {
        int id = generateValueFromRange(100, 1000);
        String destination = destinations[generateValueFromRange(0, arraySize)];
        int seats = generateValueFromRange(seatsMin, seatsMax);
        flightNumber = generateFlightNumber(from, destination, id);
        int beginYear = generateValueFromRange(yearMin, yearMax);
        return new Flight(flightNumber, from, destination, "30.08.2019-12:00", seats);
    }


    public static void main(String[] args) throws ParseException {
        FlightsRandomaizer newFlights = new FlightsRandomaizer(10);
        List<Flight> flights = newFlights.get();
        System.out.println(flights.toString());

    }
}
