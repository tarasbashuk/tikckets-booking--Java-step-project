package entities;


import java.io.Serializable;

public class Passenger implements Serializable {
//    private final String id;
    private final String name;
    private final String surname;

    public Passenger(String name, String surname) {
        if (name != null && surname != null) {
//            this.id = UUID.randomUUID().toString();
            this.name = name;
            this.surname = surname;
        } else {
            throw new IllegalArgumentException("Invalid arguments: null is not accepted");
        }
    }

//    public String getId(){
//        return this.id;
//    }

    public String getFullName(){
        return String.format("%s %s", name, surname);
    }

    @Override
    public int hashCode() {
//        return id.hashCode();
        return this.getFullName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        Passenger passenger = (Passenger) obj;
//        return this.id.equals(passenger.id);
        return this.getFullName().equals(passenger.getFullName());
    }

    @Override
    public String toString() {
//        return String.format("{Passenger id: %s, full name: %s}", id, this.getFullName());
        return String.format("{%s}", this.getFullName());
    }

}
