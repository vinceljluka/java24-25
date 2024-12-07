package hr.java.restaurant.model;

public sealed interface Meat permits MeatDish{
    String getMeatType();
    String getCookingMethod();
}
