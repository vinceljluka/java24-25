package hr.javafx.vinceljfx7.restaurant.model;

import java.io.Serializable;

/**
 * Represents an entity.
 */
public abstract class Entity implements Serializable {
    private Long id;

    public Entity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
