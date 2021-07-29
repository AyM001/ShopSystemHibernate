package persistence;

import java.util.List;
import java.util.Optional;
import java.util.Set;

// Interfata generica cu metode CRUD
public interface Dao<T> {

    public void add(T object);


    public List<T> getFromDb(T object);


    public void deleteFromDb(T object);


    public void updateDB(T object);


}
