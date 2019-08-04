package dao;

import entities.User;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserDAO {
    private final File file;
    private final HashMap<String, User> map;

    public UserDAO(){
        this(new File("./data", "users.bin"));
    }

    public UserDAO (File file){
        this.file = file;
        this.map = new HashMap<>();
    }

    public User get(String login) {
        return map.get(login);
    }

    public List<User> getAll() {
        return new ArrayList<>(map.values());
    }

    public List<String> getKeys() {
        return new ArrayList<>(map.keySet());
    }

    public boolean insert(User user) {
        if (user == null) throw new IllegalArgumentException("Invalid insert arguments: null is not accepted");
        String login = user.getLogin();
        if (map.containsKey(login)) {
            return false;
        } else {
            map.put(login, user);
            return true;
        }
    }

    public boolean update(User user) {
        if (user == null) throw new IllegalArgumentException("Invallogin insert arguments: null is not accepted");
        String login = user.getLogin();
        if (map.containsKey(login)) {
            map.put(login, user);
            return true;
        } else {
            return false;
        }
    }

    public boolean remove(String login) {
        if (login == null) throw new IllegalArgumentException("Invallogin insert arguments: null is not accepted");
        if (map.containsKey(login)) {
            map.remove(login);
            return true;
        } else {
            return false;
        }
    }

    public boolean remove(User user) {
        if (user == null) throw new IllegalArgumentException("Invallogin insert arguments: null is not accepted");
        String login = user.getLogin();
        if (map.containsKey(login)) {
            map.remove(login);
            return true;
        } else {
            return false;
        }
    }

    public void retrieveInitialData() {
        try {
            FileInputStream fis = new FileInputStream(file);
            if (fis.available() > 0) {
                ObjectInputStream ois = new ObjectInputStream(fis);

                List data = (ArrayList) ois.readObject();
                data.forEach(o -> {
                    User bkg = (User) o;
                    map.put(bkg.getLogin(), bkg);
                });
                ois.close();
                fis.close();
            }
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Users.bin file not found");
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error while initializing stream");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveData() {
        try {
            FileOutputStream f = new FileOutputStream(file);
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(new ArrayList<>(map.values()));
            o.close();
            f.close();
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Users.bin file not found");
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error while initializing stream");

        }
    }
}
