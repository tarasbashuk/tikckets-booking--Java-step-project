package logger;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Logger {
    private final File file;
    private final HashMap<String, ArrayList<String>> passangersLogHistory;
    private ArrayList<String> historyArray;

    public Logger() {
        this(new File("./data", "logs.txt"));
    }

    public Logger(File file) {
        this.file = file;
        this.passangersLogHistory = new HashMap<>();

    }

    public ArrayList<String> LogHistory(String login) {
        return passangersLogHistory.get(login);

    }

    public void add(String login, String action) {
        if (login == null) {
            throw new IllegalArgumentException("User is not authorized");
        }


        if (passangersLogHistory.containsKey(login)) {
            historyArray = passangersLogHistory.get(login);
            historyArray.add(action);
            passangersLogHistory.put(login, historyArray);
        } else {
            historyArray = new ArrayList<String>();
            historyArray.add(action);
            passangersLogHistory.put(login, historyArray);
        }
    }


    public void save() {
        try {
            FileOutputStream f = new FileOutputStream(file);
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(passangersLogHistory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
