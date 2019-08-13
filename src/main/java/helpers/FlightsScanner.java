package helpers;

import entities.AirTrip;
import entities.FlightConnection;
import helpers.ConPossible;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


public class FlightsScanner {
    private final List<AirTrip> origin;
    private final String from;
    private final String destination;
    private List<AirTrip> relevantTrips = new ArrayList<>();
    private List<AirTrip> furtherOptions = new ArrayList<>();

    public FlightsScanner(String from, String destination, List<AirTrip> origin) {
        this.from = from;
        this.destination = destination;
        this.origin = origin;
    }

    public List<AirTrip> createTripExtensions(AirTrip trip){
        List<AirTrip> result = new ArrayList<>();
        origin.stream().filter(new ConPossible(trip)).forEach(airTrip -> result.add(new FlightConnection(trip, airTrip)));
        return result;
    }

    public void sortTrips(List<AirTrip> options) {
        List<AirTrip> newFurOptions = new ArrayList<>();
        options.forEach(trip -> {
            if (trip.getFrom().equals(from) & trip.getDestination().equals(destination)) {
                relevantTrips.add(trip);
            } else {
                newFurOptions.add(trip);
            }
        });
        furtherOptions = newFurOptions;
    }

    public List<AirTrip> investigateOptions(){
        List<AirTrip> result = new ArrayList<>();
        furtherOptions.forEach(trip -> result.addAll(createTripExtensions(trip)));
        return result;
    }

    public List<AirTrip> findAllConnections(){
        sortTrips(origin);
        while (!furtherOptions.isEmpty()){
           sortTrips(investigateOptions());
        }
        return relevantTrips;
    }


}
