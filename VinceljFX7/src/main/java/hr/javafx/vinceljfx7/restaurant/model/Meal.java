package hr.javafx.vinceljfx7.restaurant.model;

import hr.javafx.vinceljfx7.repository.MealRepository;
import hr.javafx.vinceljfx7.service.Constants;
import hr.javafx.vinceljfx7.service.Output;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
 * Represents a meal in a restaurant.
 */
public class Meal extends Entity implements Serializable {

    private String name;
    private String mealType;
    private final Category category;
    private Set<Ingredient> ingredients;
    private final BigDecimal price;

    private final static MealRepository<Meal> mealRepository = new MealRepository<>();

    /**
     * Constructs a Meal object using the provided builder.
     *
     * @param name the name of the meal
     * @param category the category of the meal
     * @param ingredients the ingredients.txt of the meal
     * @param price the price of the meal
     */
    public Meal(Long id, String name, String mealType, Category category, Set<Ingredient> ingredients, BigDecimal price) {
        super(id);
        this.name = name;
        this.mealType = mealType;
        this.category = category;
        this.ingredients = ingredients;
        this.price = price;
    }

    public static Set<Meal> getMealByIdentifiers(String mealsInRestaurantIdentifiers) {
        Set<Meal> mealsList = new HashSet<>();
        String[] identifiers = mealsInRestaurantIdentifiers.split(",");
        for (String identifier : identifiers) {
            mealsList.add(mealRepository.findById(Long.parseLong(identifier)));
        }

        return mealsList;
    }

    public String getIngredientsIdentifiers() {
        StringBuilder ingredientIdentifiers = new StringBuilder();
        for (Ingredient ingredient : ingredients) {
            ingredientIdentifiers.append(ingredient.getId()).append(",");
        }

        ingredientIdentifiers.deleteCharAt(ingredientIdentifiers.length() - 1);

        return ingredientIdentifiers.toString();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Set<Ingredient> getIngredients() {
        return ingredients;
    }
    public BigDecimal getPrice() {
        return price;
    }


    /**
     * Calculates the total calories of the meal.
     * @return the total calories of the meal
     */
    public BigDecimal getTotalCalories() {
        BigDecimal mealCalories = BigDecimal.valueOf(0);

        for (Ingredient ingredient : this.getIngredients()) {
            mealCalories = mealCalories.add(ingredient.getKcal());
        }

        return  mealCalories;
    }

    public String getMealType() {
        return mealType;
    }

    public Category getCategory() {
        return category;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return Objects.equals(name, meal.name) && Objects.equals(category, meal.category) && Objects.equals(ingredients, meal.ingredients) && Objects.equals(price, meal.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, category, ingredients, price);
    }

    /**
     * print meal
     * @param tabulators number of tabulators
     */
    public void print(Integer tabulators) {

        Output.tabulatorPrint(tabulators);
        System.out.println("Id: " + this.getId() + ", Naziv: " + this.name + ", Cijena: " + this.price);

        Output.tabulatorPrint(tabulators);
        System.out.println("Kategorija:");
        category.print(tabulators + 1);

        Output.tabulatorPrint(tabulators);
        System.out.println("Sastojci: ");
        int index = 1;


        List<Ingredient> sortedIngredients = new ArrayList<>(this.ingredients);
        sortedIngredients.sort((i1, i2) -> i1.getName().compareTo(i2.getName()));
        for (Ingredient ingredient : sortedIngredients) {
            Output.tabulatorPrint(tabulators + 1);
            System.out.println("Sastojak " + index + ":");
            ingredient.print(tabulators + 2);
            index++;
        }
    }
}