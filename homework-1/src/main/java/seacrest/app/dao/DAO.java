package seacrest.app.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DAO<T> {
    T save(T obj);
    boolean delete(T obj);
    void deleteAll(List<T> entities);
    void saveAll(List<T> entities);
    List<T> findAll();
    boolean deleteById(long id);
    T getOne(long id);
    T getOne(UUID id);
    Optional<T> getOne(int id);
}
