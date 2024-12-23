package hr.javafx.vinceljfx7.restaurant.model;

import hr.javafx.vinceljfx7.service.Output;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

/**
 * Represents a meal that is vegan.
 */
public final class VeganMeal extends Meal implements Vegan, Serializable {
    private final String proteinSource;
    private final boolean organic;
    private final boolean glutenFree;

    /**
     * Constructs a VeganMeal object.
     * @param name the name of the meal
     * @param category the category of the meal
     * @param ingredients the ingredients.txt of the meal
     * @param price the price of the meal
     * @param proteinSource the source of protein
     * @param organic if the meal is organic
     * @param glutenFree if the meal is gluten-free
     */
    public VeganMeal(Long id, String name, String mealType, Category category, Set<Ingredient> ingredients, BigDecimal price, String proteinSource, boolean organic, boolean glutenFree) {
        super(id, name, mealType, category, ingredients, price);
        this.proteinSource = proteinSource;
        this.organic = organic;
        this.glutenFree = glutenFree;
    }


    @Override
    public boolean isOrganic() {
        return this.organic;
    }

    @Override
    public boolean isGlutenFree() {
        return this.glutenFree;
    }

    public String getProteinSource() {
        return proteinSource;
    }

    /**
     * Prints the vegan meal.
     * @param tabulators the number of tabulators
     */
    @Override
    public void print(Integer tabulators) {
        super.print(tabulators);
        Output.tabulatorPrint(tabulators);
        System.out.println("Izvor proteina: " + this.proteinSource);
        Output.tabulatorPrint(tabulators);
        System.out.println("Bez glutena: " + (this.glutenFree ? "Da" : "Ne"));
        Output.tabulatorPrint(tabulators);
        System.out.println("Organsko: " + (this.organic ? "Da" : "Ne"));
    }
}
