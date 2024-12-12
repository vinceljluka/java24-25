package hr.java.restaurant.model;

import java.io.Serializable;

public abstract class Entity implements Serializable {
    protected Long id;

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
