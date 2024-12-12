package hr.java.restaurant.sort;

import hr.java.restaurant.model.Ingredient;
import hr.java.restaurant.model.Meal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class IngredientsAlphabeticalComparator  {

    public static void printIngredientsSortedByName(Meal meal)
    {
        System.out.println("Namirnice za jelo \"" + meal.getName() + "\":");

        meal.getIngredients().stream()
                .sorted((ingredient1, ingredient2) -> ingredient1.getName().compareTo(ingredient2.getName()))
                .forEach(ingredient -> System.out.println("- " + ingredient.getName()));
    }
}
