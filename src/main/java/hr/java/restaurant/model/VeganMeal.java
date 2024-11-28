package hr.java.restaurant.model;

import hr.java.restaurant.exception.DuplicateEntityException;
import hr.java.restaurant.exception.NegativOrUnrealPrice;
import hr.java.utils.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public non-sealed class VeganMeal extends Meal implements Vegan {
    private static Logger log = LoggerFactory.getLogger(VeganMeal.class);

    private String veganType;
    private boolean isGlutenFree;

    public VeganMeal(Long id, String name, Category category, Set<Ingredient> ingredients, BigDecimal price, Integer calories, String veganType, boolean isGlutenFree) {
        super(id, name, category, ingredients, price, calories);
        this.veganType = veganType;
        this.isGlutenFree = isGlutenFree;
    }

    @Override
    public String getVeganType() {
        return veganType;
    }

    public void setVeganType(String veganType) {
        this.veganType = veganType;
    }

    @Override
    public boolean isGlutenFree() {
        return isGlutenFree;
    }

    public void setGlutenFree(boolean glutenFree) {
        isGlutenFree = glutenFree;
    }

    public static void inputVeganMeal(List <Meal> veganMeals, List <Category> categories, List<Ingredient> ingredients, Scanner scanner) {
        for (int i = 0; i < veganMeals.size(); i++) {
            Boolean isValid;
            String name;
            do {
                isValid = true;
                System.out.print("Enter " + (i + 1) + ". vegan meal's name: ");
                name = scanner.nextLine();
                try {
                    DuplicateNameCheck.checkDuplicateDish(name, veganMeals, i);
                } catch (DuplicateEntityException e) {
                    isValid = false;
                    log.error(e.getMessage());
                    System.out.println(e.getMessage());
                }
            } while (!isValid);

            System.out.print("Choose " + (i + 1) + ". vegan meal's category (index 1, 2, or 3): \n");
            for (int j = 0; j < categories.size(); j++) {
                System.out.println((j + 1) + "." + categories.get(j).getName());
            }
            Category category = DataInputUtils.getCategory(categories, scanner, Messages.INVALID_INTEGER_INDEX_CATEGORY_INPUT);

            System.out.print("Enter number of ingredients needed for this vegan meal: ");
            Integer numberOfIngredients = InputValidator.validatePositiveInteger(scanner, Messages.INVALID_NUMBER_OF_INGREDIENTS);

            System.out.println("Available ingredients: ");
            Set<Ingredient> availableIngredients = DataInputUtils.inputIngredients(scanner, ingredients, numberOfIngredients, Messages.INVALID_INTEGER_INDEX_INGREDIENT_INPUT);

            System.out.print("Enter the price of " + (i + 1) + ". vegan meal: ");
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

            System.out.print("Enter the vegan type for " + (i + 1) + ". vegan meal (e.g., 'Raw', 'Cooked'): ");
            String veganType = scanner.nextLine();

            System.out.print("Is " + (i + 1) + ". vegan meal gluten-free? (true/false): ");
            boolean isGlutenFree = InputValidator.validateBoolean(scanner, Messages.INVALID_BOOLEAN_INPUT);

            veganMeals.add(new VeganMeal(Long.valueOf(i + 1), name, category, availableIngredients, price, calories, veganType, isGlutenFree));
        }
    }
}

