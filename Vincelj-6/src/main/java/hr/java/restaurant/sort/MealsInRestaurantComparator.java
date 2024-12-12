package hr.java.restaurant.sort;

import hr.java.restaurant.model.Meal;
import hr.java.restaurant.model.Restaurant;

import java.util.*;

public class MealsInRestaurantComparator {
    private final Map<Meal, List<Restaurant>> mealRestaurantMap;

    public MealsInRestaurantComparator(Map<Meal, List<Restaurant>> mealRestaurantMap) {
        this.mealRestaurantMap = mealRestaurantMap;
    }

    public static void displaySortedMeals(Map<Meal, List<Restaurant>> mealRestaurantMap, Set<Meal> meals) {
        List<Meal> sortedMeals = new ArrayList<>(meals);

        sortedMeals.sort((meal1, meal2) -> {
            int count1 = getRestaurantCount(meal1, mealRestaurantMap);
            int count2 = getRestaurantCount(meal2, mealRestaurantMap);
            return Integer.compare(count2, count1);
        });

        System.out.println("Jela sortirana prema broju restorana:");
        for (Meal meal : sortedMeals)
        {
            int count = getRestaurantCount(meal, mealRestaurantMap);
            System.out.println(meal.getName() + " - Broj restorana: " + count);
        }
    }

    private static int getRestaurantCount(Meal meal, Map<Meal, List<Restaurant>> mealRestaurantMap)
    {
        return mealRestaurantMap.getOrDefault(meal, Collections.emptyList()).size();
    }
}
