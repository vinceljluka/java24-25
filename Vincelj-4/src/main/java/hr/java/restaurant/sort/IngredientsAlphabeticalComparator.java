package hr.java.restaurant.sort;

import hr.java.restaurant.model.Ingredient;
import hr.java.restaurant.model.Meal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class IngredientsAlphabeticalComparator implements Comparator<Ingredient> {
    @Override
    public int compare(Ingredient ingredient1, Ingredient ingredient2)
    {
        return ingredient1.getName().compareTo(ingredient2.getName());
    }

    public static void printIngredientsSortedByName(Meal meal) {
        List<Ingredient> ingredients = new ArrayList<>(meal.getIngredients());

        ingredients.sort(new IngredientsAlphabeticalComparator());

        System.out.println("Namirnice za jelo \"" + meal.getName() + "\":");
        for (Ingredient ingredient : ingredients) {
            System.out.println("- " + ingredient.getName());
        }
    }
}
