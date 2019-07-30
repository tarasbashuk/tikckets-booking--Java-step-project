package entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Booking implements Serializable {
    private final String id;
    private final Passenger bookedBy;
    private final String flight;
    private final Set<Passenger> passengers = new HashSet<>();

    public Booking(String flight, Passenger bookedBy) {
        this(flight, bookedBy, UUID.randomUUID().toString());
    }

    public Booking(String flight, Passenger bookedBy, String id) {
        if (flight != null && bookedBy != null) {
            this.id = id;
            this.bookedBy = bookedBy;
            this.flight = flight;
        } else {
            throw new IllegalArgumentException("Invalid arguments: null is not accepted");
        }
    }

    public Passenger getBookedBy() {
        return bookedBy;
    }

    public Set<Passenger> getPassengers() {
        return passengers;
    }

    public String getId(){
        return this.id;
    }

    public void addPassenger (Passenger psg){
        if (psg !=null) {
            passengers.add(psg);
        }
    }

    public String getFlight() {
        return flight;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{Booking id: ");
        sb.append(id);
        sb.append(", flight: ");
        sb.append(flight);
        sb.append(", booked by: ");
        sb.append(bookedBy.getFullName());
        sb.append(", passengers: [ ");
        passengers.stream().forEach(passenger -> {
            sb.append(passenger.toString());
            sb.append(" ");
        });
        sb.append("]}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        Booking booking = (Booking) obj;
        return this.id.equals(booking.id);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
}
