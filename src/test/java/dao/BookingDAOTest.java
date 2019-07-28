package dao;

import entities.Booking;
import entities.Passenger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class BookingDAOTest {

    private BookingDAO dao;
    private static Booking booking;
    private static Booking b2;


    @BeforeAll
    static void beforeAll(){
        Passenger p1 = new Passenger("Slava", "Stepanchuk");
        Passenger p2 = new Passenger("Nastia", "Stepanchuk");
        Passenger p3 = new Passenger("Oleksandr", "Ivanov");
        Booking b1 = new Booking("FamilyTrip", p1);
        b1.addPassenger(p1);
        b1.addPassenger(p2);
        Booking b2 = new Booking("TripToAmsterdam", p3);
        b2.addPassenger(p3);
        Booking b3 = new Booking("TripToIstanbul", p1);
        b3.addPassenger(p2);
    }

    @BeforeEach
    void beforeEach(){
        dao = new BookingDAO();
    }

    @Test
    void getAll() {
    }

    @Test
    void insert() {
    }

    @Test
    void update() {
    }

    @Test
    void remove() {
    }

    @Test
    void remove1() {
    }

    @Test
    void retrieveInitialData() {
    }

    @Test
    void saveData() {
    }
}