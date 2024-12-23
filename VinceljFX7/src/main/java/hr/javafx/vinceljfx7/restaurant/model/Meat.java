package hr.javafx.vinceljfx7.restaurant.model;

/**
 * Represents a meat meal.
 */
public sealed interface Meat permits MeatMeal {
    String getMeatType();
    String getMeatOrigin();
}
