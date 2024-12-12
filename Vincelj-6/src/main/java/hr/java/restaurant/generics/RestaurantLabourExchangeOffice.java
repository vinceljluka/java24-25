package hr.java.restaurant.generics;

import hr.java.restaurant.model.Restaurant;

import java.util.HashSet;
import java.util.Set;

public class RestaurantLabourExchangeOffice <T extends Restaurant> {
    private Set<T> restaurants;

    public RestaurantLabourExchangeOffice(Set<T> restaurants)
    {
        this.restaurants = new HashSet<T>(restaurants);
    }

    public Set<T> getRestaurants() {
        return new HashSet<>(restaurants);
    }

    public void addRestaurant(T restaurant) {
        this.restaurants.add(restaurant);
    }

    public boolean removeRestaurant(T restaurant)
    {
        return this.restaurants.remove(restaurant);
    }

}
