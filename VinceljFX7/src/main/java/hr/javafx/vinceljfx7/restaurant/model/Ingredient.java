package hr.javafx.vinceljfx7.restaurant.model;

import hr.javafx.vinceljfx7.restaurant.exception.DuplicateEntryException;
import hr.javafx.vinceljfx7.repository.IngredientRepository;
import hr.javafx.vinceljfx7.service.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
 * Represents an ingredient of a meal.
 */
public class Ingredient extends Entity implements Serializable {

    private String name;
    private final Category category;
    private final BigDecimal kcal;
    private final String preparationMethod;

    private static final IngredientRepository<Ingredient> ingredientRepository = new IngredientRepository<>();

    /**
     * Constructs an Ingredient object
     * @param name the name of the ingredient
     * @param category the category of the ingredient
     * @param kcal the calories of the ingredient
     * @param preparationMethod the preparation method of the ingredient
     */
    public Ingredient(Long id, String name, Category category, BigDecimal kcal, String preparationMethod) {
        super(id);
        this.name = name;
        this.category = category;
        this.kcal = kcal;
        this.preparationMethod = preparationMethod;
    }

    public Category getCategory() {
        return category;
    }

    public String getPreparationMethod() {
        return preparationMethod;
    }

    public static Set<Ingredient> getIngredientsByIdentifiers(String ingredientsIdentifiers) {
        Set<Ingredient> ingredientsList = new HashSet<>();
        String[] identifiers = ingredientsIdentifiers.split(",");
        for (String identifier : identifiers) {
            ingredientsList.add(ingredientRepository.findById(Long.parseLong(identifier)));
        }

        return ingredientsList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getKcal() {
        return kcal;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(name, that.name) && Objects.equals(category, that.category) && Objects.equals(kcal, that.kcal) && Objects.equals(preparationMethod, that.preparationMethod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, category, kcal, preparationMethod);
    }

    /**
     * Prints the ingredient information.
     * @param tabulators the number of tabulators to be printed before the ingredient information
     */
    public void print(Integer tabulators) {
        Output.tabulatorPrint(tabulators);
        System.out.println("Id: " + this.getId() + ", Naziv: " + this.name + ", Kalorije: " + this.kcal + ", Metoda pripreme: " + this.preparationMethod);

        Output.tabulatorPrint(tabulators);
        System.out.println("Kategorija:");
        category.print(tabulators+1);
    }
}
