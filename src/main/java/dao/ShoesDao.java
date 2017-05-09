package dao;

import domain.Shoes;

import java.util.List;

public interface ShoesDao {
    Shoes get(long id);
    List<Shoes> getAll();
    void add(Shoes shoes);
    void save(Shoes shoes);
    void delete(Shoes shoes);
}
