package hr.javafx.vinceljfx7.restaurant.model;

import hr.javafx.vinceljfx7.service.Output;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

/**
 * Represents a meal that contains meat.
 */
public final class MeatMeal extends Meal implements Meat, Serializable {
    private final String meatType;
    private final String meatOrigin;
    private final String meatCookingType;

    /**
     * Constructs a MeatMeal object.
     *
     * @param name the name of the meal
     * @param category the category of the meal
     * @param ingredients the ingredients.txt of the meal
     * @param price the price of the meal
     * @param meatType the type of meat
     * @param meatOrigin the origin of meat
     * @param meatCookingType the cooking type of meat
     */
    public MeatMeal(Long id, String name, String mealType, Category category, Set<Ingredient> ingredients, BigDecimal price, String meatType, String meatOrigin, String meatCookingType) {
        super(id, name, mealType, category, ingredients, price);
        this.meatType = meatType;
        this.meatOrigin = meatOrigin;
        this.meatCookingType = meatCookingType;
    }

    @Override
    public String getMeatType() {
        return this.meatType;
    }

    @Override
    public String getMeatOrigin() {
        return this.meatOrigin;
    }


    public String getMeatCookingType() {
        return meatCookingType;
    }

    /**
     * Prints a meat meal.
     * @param tabulators the number of tabulators
     */
    @Override
    public void print(Integer tabulators) {
        super.print(tabulators);
        Output.tabulatorPrint(tabulators);
        System.out.println("Tip mesa: " + this.meatType);
        Output.tabulatorPrint(tabulators);
        System.out.println("Podrijetlo mesa: " + this.meatOrigin);
        Output.tabulatorPrint(tabulators);
        System.out.println("Način kuhanja: " + this.meatCookingType);
    }
}
