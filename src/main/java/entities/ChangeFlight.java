package entities;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.PriorityQueue;

public class ChangeFlight implements Serializable, AirTrip {
    private final PriorityQueue<Flight> storage;

    public ChangeFlight(Flight flight1, Flight flight2) {
        this.storage = new PriorityQueue<>(5, new Comparator<Flight>() {
            @Override
            public int compare(Flight o1, Flight o2) {
                return o1.getDepart().compareTo(o2.getDepart());
            }
        });
        storage.add(flight1);
        storage.add(flight2);
    }


    @Override
    public String getDestination() {
        return null;
    }

    @Override
    public String getFrom() {
        return null;
    }

    @Override
    public String getFlightNumber() {
        return null;
    }

    @Override
    public Date getDepart() {
        return null;
    }

    @Override
    public Date getArrival() {
        return null;
    }

    @Override
    public int getSeats() {
        return 0;
    }

    @Override
    public boolean bookSeats(int bookedSeats) {
        return false;
    }

    @Override
    public void returnSeats(int returnedSeats) {

    }
}
