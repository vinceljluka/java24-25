package hr.java.utils;

import hr.java.restaurant.model.Meal;
import hr.java.restaurant.model.Restaurant;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Scanner;


public class MealRestaurantKey {

    public static void displayRestaurantsForMeal(Set<Meal> meals, Set<Restaurant> restaurants, Scanner scanner) {
        Map<Meal, List<Restaurant>> mealRestaurantMap = new HashMap<>();
        for (Meal meal : meals) {
            List<Restaurant> restaurantList = new ArrayList<>();
            for (Restaurant restaurant : restaurants) {
                if (restaurant.getMeals().contains(meal)) {
                    restaurantList.add(restaurant);
                }
            }
            mealRestaurantMap.put(meal, restaurantList);
        }

        boolean isValid;
        do {
            isValid = false;
            System.out.println("Ako želite završiti napišite broj koji nije ponuđen");
            System.out.println("Odaberite jelo:");

            for (Meal meal : meals) {
                System.out.println(meal.getId() + ": " + meal.getName());
            }

            long mealId = scanner.nextLong();
            Meal selectedMeal = null;

            for (Meal meal : meals) {
                if (meal.getId() == mealId) {
                    selectedMeal = meal;
                    break;
                }
            }

            if (selectedMeal != null) {
                List <Restaurant> restaurantsForMeal = mealRestaurantMap.get(selectedMeal);
                if (restaurantsForMeal != null && !restaurantsForMeal.isEmpty()) {
                    System.out.println("Restorani koji nude jelo \"" + selectedMeal.getName() + "\":");
                    for (Restaurant restaurant : restaurantsForMeal) {
                        System.out.println("- " + restaurant.getName());
                    }
                } else {
                    System.out.println("Nema restorana koji nude odabrano jelo.");
                }
            } else {
                System.out.println("Jelo s unesenim ID-om ne postoji.");
                isValid = true;
            }
        } while (!isValid);
    }

}
