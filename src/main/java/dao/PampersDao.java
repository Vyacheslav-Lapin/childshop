package dao;

import domain.Pampers;

import java.util.List;

public interface PampersDao {

    Pampers get(long id);

    List<Pampers> getAll();

    void add(Pampers pampers);

    void save(Pampers pampers);

    void delete(Pampers pampers);
}
