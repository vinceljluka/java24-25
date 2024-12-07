package hr.java.restaurant.sort;

import hr.java.restaurant.model.Meal;

import java.util.Comparator;
import java.util.List;

public class MealCaloriesComparator
{
    public static void sortAndPrintMealsDescending(List<Meal> meals)
    {
        Comparator <Meal> comparator = (meal1, meal2) -> meal2.getCalories().compareTo(meal1.getCalories());
        meals.sort(comparator);
        meals.forEach(meal -> System.out.println(meal.getName() + " - " + meal.getCalories() + " calories"));
    }
}
