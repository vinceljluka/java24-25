package hr.java.restaurant.model;

import hr.java.restaurant.exception.DuplicateEntityException;
import hr.java.restaurant.exception.NegativOrUnrealPrice;
import hr.java.utils.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Scanner;

public non-sealed class VegetarianMeal extends Meal implements Vegetarian {

    private static Logger log = LoggerFactory.getLogger(VegetarianMeal.class);

    private boolean containsDairy;
    private boolean containsEggs;

    public VegetarianMeal(Long id, String name, Category category, Ingredient[] ingredients, BigDecimal price, Integer calories, boolean containsDairy, boolean containsEggs) {
        super(id, name, category, ingredients, price, calories);
        this.containsDairy = containsDairy;
        this.containsEggs = containsEggs;
    }

    @Override
    public boolean containsDairy() {
        return containsDairy;
    }

    public void setContainsDairy(boolean containsDairy) {
        this.containsDairy = containsDairy;
    }

    @Override
    public boolean containsEggs() {
        return containsEggs;
    }

    public void setContainsEggs(boolean containsEggs) {
        this.containsEggs = containsEggs;
    }

    public static void inputVegetarianMeal(VegetarianMeal[] vegetarianMeals, Category[] categories, Ingredient[] ingredients, Scanner scanner)
    {
        for (int i = 0; i < vegetarianMeals.length; i++) {
            Boolean isValid;
            String name;
            do {
                isValid = true;
                System.out.print("Enter " + (i + 1) + ". vegetarian meal's name: ");
                name = scanner.nextLine();
                try {
                    DuplicateNameCheck.checkDuplicateDish(name, vegetarianMeals, i);
                } catch (DuplicateEntityException e) {
                    isValid = false;
                    log.error(e.getMessage());
                    System.out.println(e.getMessage());
                }
            } while (!isValid);

            System.out.print("Choose " + (i + 1) + "." + " vegetarian meal's category (index 1, 2, or 3) : \n");
            for (int j = 0; j < categories.length; j++) {
                System.out.println((j + 1) + "." + categories[j].getName());
            }

            Category category = DataInputUtils.getCategory(categories, scanner, Messages.INVALID_INTEGER_INDEX_CATEGORY_INPUT);

            System.out.print("Enter number of ingredients needed for this vegetarian meal: ");
            Integer numberOfIngredients = InputValidator.validatePositiveInteger(scanner, Messages.INVALID_NUMBER_OF_INGREDIENTS);

            System.out.println("Avaliable ingredients: ");
            Ingredient[] helpArray = DataInputUtils.inputIngredients(scanner, ingredients, numberOfIngredients, Messages.INVALID_INTEGER_INDEX_INGREDIENT_INPUT);

            System.out.print("Enter the price of " + (i + 1) + "." + " vegetarian meal: ");
            BigDecimal price;

            do {
                isValid = true;
                price = InputValidator.validatePositiveBigDecimal(scanner, Messages.INVALID_PRICE_NUMBER_INPUT);
                try {
                    PriceCheck.checkPrice(price);
                } catch (NegativOrUnrealPrice e) {
                    isValid = false;
                    log.error(e.getMessage());
                    System.out.println(e.getMessage());
                }
            } while (!isValid);

            System.out.println("Enter number of calories for this meal: ");
            Integer calories = InputValidator.validatePositiveInteger(scanner, Messages.INVALID_BIGDECIMAL_KCAL_INPUT);

            System.out.print("Does " + (i + 1) + "." + " vegetarian meal contain dairy? (true/false): ");
            boolean containsDairy = InputValidator.validateBoolean(scanner, Messages.INVALID_BOOLEAN_INPUT);

            System.out.print("Does " + (i + 1) + "." + " vegetarian meal contain eggs? (true/false): ");
            boolean containsEggs = InputValidator.validateBoolean(scanner, Messages.INVALID_BOOLEAN_INPUT);

            vegetarianMeals[i] = new VegetarianMeal(Long.valueOf(i + 1), name, category, helpArray, price, calories, containsDairy, containsEggs);
        }
    }
}
