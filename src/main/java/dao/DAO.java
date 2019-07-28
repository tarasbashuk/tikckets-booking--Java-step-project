package dao;

import java.util.List;

public interface DAO<T> {
    T get (String id);
    List<T> getAll();
    boolean insert(T item);
    boolean update(T item);
    boolean remove(T item);
    void retrieveInitialData();
    void saveData();
}
