package services;

import dao.FlightDAO;
import entities.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightServiceTest {
    private static File file;
    private static List<Flight> referenceArray = new ArrayList<>();
    private static FlightDAO flDAO;
    private static FlightService flService;
    private static Flight lh1;
    private static Flight lh2;
    private static Flight lh3;
    private static Flight lh4;
    private static Flight lh5;
    private static Flight lh6;
    private static Flight lh7;
    private static Flight lh8;
    private static Flight lh9;

    @BeforeEach
    void beforeEach() throws ParseException {
        file = new File("./src/test/data", "flightsTest.txt");
        lh1 = new Flight("LH123", "kyiv", "san-francisko", "30.08.2019-12:00", 300);
        lh2 = new Flight("LH222", "kyiv", "barcelona", "01.08.2019-12:00", 250);
        lh3 = new Flight("LH333", "kyiv", "palma", "03.08.2019-12:00", 200);
        lh4 = new Flight("LH444", "kyiv", "madrid", "04.08.2019-22:00", 250);
        lh5 = new Flight("LH555", "kyiv", "tel-aviv", "04.08.2019-23:00", 200);
        lh6 = new Flight("LH666", "kyiv", "new-york", "05.08.2019-12:00", 250);
        lh7 = new Flight("LH777", "kyiv", "wroclaw", "06.08.2019-11:00", 200);
        lh8 = new Flight("LH888", "kyiv", "praha", "07.08.2019-12:00", 250);
        lh9 = new Flight("LH999", "kyiv", "oslo", "07.08.2019-19:00", 200);
        flDAO = new FlightDAO(file);
        flDAO.insert(lh1);
        flDAO.insert(lh2);
        flDAO.insert(lh3);
        flDAO.insert(lh4);
        flDAO.insert(lh5);
        flDAO.insert(lh6);
        flDAO.insert(lh7);
        flDAO.insert(lh8);
        flDAO.insert(lh9);
        flDAO.saveData();

        referenceArray.add(lh1);
        referenceArray.add(lh2);
        referenceArray.add(lh3);
        referenceArray.add(lh4);
        referenceArray.add(lh5);
        referenceArray.add(lh6);
        referenceArray.add(lh7);
        referenceArray.add(lh8);
        referenceArray.add(lh9);
        flService = new FlightService(file);
    }

    @Test
    void getAllFlights() {

        assertEquals(flService.getAllFlights(), referenceArray);
    }

    @Test
    void getSuitableFlights() {

    }

    @Test
    void getNearestFlights() {
    }

    @Test
    void getFlightByNumber() {
    }

    @Test
    void bookSeats() {
    }

    @Test
    void returnSeats() {
    }

    @Test
    void save() {
    }

}