package dao;

import entities.Booking;

import java.io.*;
import java.util.*;

public class BookingDAO implements DAO<Booking> {
    private final File file = new File("./data", "bookings.txt");
    private final HashMap<String, Booking> map = new HashMap<>();

    public Booking get(String id) {
        return map.get(id);
    }

    public List<Booking> getAll() {
        return new ArrayList<>(map.values());
    }

    public boolean insert(Booking booking) {
        String id = booking.getId();
        if (map.containsKey(id)) {
            return false;
        } else {
            map.put(id, booking);
            return true;
        }
    }

    public boolean update(Booking booking) {
        String id = booking.getId();
        if (map.containsKey(id)) {
            map.put(id, booking);
            return true;
        } else {
            return false;
        }
    }

    public boolean remove(String id) {
        if (map.containsKey(id)) {
            map.remove(id);
            return true;
        } else {
            return false;
        }
    }

    public boolean remove(Booking booking) {
        String id = booking.getId();
        if (map.containsKey(id)) {
            map.remove(id);
            return true;
        } else {
            return false;
        }
    }

    public void retrieveData() throws IOException {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
    }

    public void saveData() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        map.forEach((key, value) -> {
            try {
                bw.write(value.toString());
                bw.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bw.close();
    }
}

