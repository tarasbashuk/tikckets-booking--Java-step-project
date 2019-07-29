package dao;

import entities.Booking;
import entities.Passenger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class BookingDAOTest {

    private BookingDAO dao;
    private Booking b1;
    private Booking b2;
    private Booking b3;
    private Passenger p1;
    private Passenger p2;


    @BeforeEach
    void beforeEach(){
        p1 = new Passenger("Slava", "Stepanchuk");
        p2 = new Passenger("Nastia", "Stepanchuk");
        Passenger p3 = new Passenger("Oleksandr", "Ivanov");
        b1 = new Booking("FamilyTrip", p1);
        b1.addPassenger(p1);
        b1.addPassenger(p2);
        b2 = new Booking("TripToAmsterdam", p3);
        b2.addPassenger(p3);
        b3 = new Booking("TripToIstanbul", p1);
        b3.addPassenger(p2);
        dao = new BookingDAO();
        dao.insert(b1);
        dao.insert(b2);
    }

    @Test
    void getAll() {
        List<Booking> result = dao.getAll();
        assertEquals(2, result.size());
        assertTrue(result.contains(b1));
        assertTrue(result.contains(b2));
    }

    @Test
    void insert() {
        dao.insert(b3);
        List<Booking> result = dao.getAll();
        assertEquals(3, result.size());
        assertTrue(result.contains(b1));
        assertTrue(result.contains(b2));
        assertTrue(result.contains(b3));
        assertThrows(IllegalArgumentException.class, ()->dao.insert(null));
    }

    @Test
    void update() {
        Booking b4 = new Booking("TripToDubai",p2);
        b4.addPassenger(p2);
        dao.insert(b4);
        assertEquals(b4, dao.get(b4.getId()));
        Booking b4copy = new Booking("TripToDubai",p2, b4.getId());
        b4copy.addPassenger(p1);
        dao.update(b4copy);
        assertEquals(b4copy, dao.get(b4.getId()));
        assertTrue(dao.getAll().contains(b1));
        assertTrue(dao.getAll().contains(b2));
        assertThrows(IllegalArgumentException.class, ()->dao.insert(null));
    }

    @Test
    void remove_by_object() {
        dao.remove(b2);
        List<Booking> result = dao.getAll();
        assertEquals(1, result.size());
        assertTrue(result.contains(b1));
        assertFalse(result.contains(b2));
        assertThrows(IllegalArgumentException.class, ()->dao.insert(null));
    }

    @Test
    void remove1() {
        dao.remove(b2.getId());
        List<Booking> result = dao.getAll();
        assertEquals(1, result.size());
        assertTrue(result.contains(b1));
        assertFalse(result.contains(b2));
        assertThrows(IllegalArgumentException.class, ()->dao.insert(null));
    }
}