package hr.java.restaurant.model;

public sealed interface Vegan permits VeganMeal{
    String getVeganType();
    boolean isGlutenFree();
}
