package hr.javafx.vinceljfx7.service;

import hr.javafx.vinceljfx7.restaurant.model.*;

import java.util.Set;

/**
 * Provides methods for finding entities.
 */
public class EntityFinder {

    public static Category categoryById(Long categoryId, Set<Category> categories) {
        for (Category category : categories) {
            if (category.getId().equals(categoryId)) {
                return category;
            }
        }
        return null;
    }
}
