package hr.java.restaurant.sort;

import hr.java.restaurant.model.Meal;
import hr.java.restaurant.model.Restaurant;

import java.util.*;

public class MealsInRestaurantComparator implements Comparator<Meal> {
    private final Map<Meal, List<Restaurant>> mealRestaurantMap;

    public MealsInRestaurantComparator(Map<Meal, List<Restaurant>> mealRestaurantMap) {
        this.mealRestaurantMap = mealRestaurantMap;
    }

    @Override
    public int compare(Meal meal1, Meal meal2)
    {
        int count1 = getRestaurantCount(meal1);
        int count2 = getRestaurantCount(meal2);
        return Integer.compare(count2, count1);
    }

    private int getRestaurantCount(Meal meal) {
        if (mealRestaurantMap.containsKey(meal)) {
            return mealRestaurantMap.get(meal).size();
        } else {
            return 0;
        }
    }

    public static void displaySortedMeals(Map<Meal, List<Restaurant>> mealRestaurantMap, Set<Meal> meals) {
        List<Meal> sortedMeals = new ArrayList<>(meals);
        sortedMeals.sort(new MealsInRestaurantComparator(mealRestaurantMap));

        System.out.println("Jela sortirana prema broju restorana:");
        for (Meal meal : sortedMeals) {
            int count = new MealsInRestaurantComparator(mealRestaurantMap).getRestaurantCount(meal);
            System.out.println(meal.getName() + " - Broj restorana: " + count);
        }
    }
}
