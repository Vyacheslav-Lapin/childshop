package dao;

import domain.Dress;

import java.util.List;

public interface DressDao {
    Dress get(long id);
    List<Dress> getAll();
    void add(Dress dress);
    void save(Dress dress);
    void delete(Dress dress);
}
