package hr.javafx.vinceljfx7.repository;

import hr.javafx.vinceljfx7.restaurant.model.Entity;
import java.util.Set;

public abstract class AbstractRepository <T extends Entity> {

    public abstract T findById(Long id);
    public abstract Set<T> findAll();
    public abstract void save(Set<T> entity);
}
