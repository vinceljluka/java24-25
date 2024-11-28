package hr.java.restaurant.model;

import hr.java.restaurant.exception.DuplicateEntityException;
import hr.java.restaurant.exception.NegativOrUnrealPrice;
import hr.java.utils.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Scanner;

public non-sealed class MeatDish extends Meal implements Meat {

    private static Logger log = LoggerFactory.getLogger(MeatDish.class);

    private String meatSource;

    public MeatDish(Long id, String name, Category category, Set<Ingredient> ingredients, BigDecimal price, Integer calories, String meatSource) {
        super(id, name, category, ingredients, price, calories);
        this.meatSource = meatSource;
    }

    @Override
    public String getMeatType() {
        return meatSource;
    }

    public void setMeatSource(String meatSource) {
        this.meatSource = meatSource;
    }

    @Override
    public String getCookingMethod() {
        return "Grilled";
    }

    public static void inputMeatDish(List <Meal> meatDishes, List <Category> categories, List<Ingredient> ingredients, Scanner scanner) {
        for (int i = 0; i < meatDishes.size(); i++) {
            Boolean isValid;
            String name;
            do {
                isValid = true;
                System.out.print("Enter " + (i + 1) + ". meat dish's name: ");
                name = scanner.nextLine();
                try {
                    DuplicateNameCheck.checkDuplicateDish(name, meatDishes, i);
                } catch (DuplicateEntityException e) {
                    isValid = false;
                    log.error(e.getMessage());
                    System.out.println(e.getMessage());
                }
            } while (!isValid);

            System.out.print("Choose " + (i + 1) + ". meat dish's category (index 1, 2, or 3) : \n");
            for (int j = 0; j < categories.size(); j++) {
                System.out.println((j + 1) + "." + categories.get(j).getName());
            }
            Category category = DataInputUtils.getCategory(categories, scanner, Messages.INVALID_INTEGER_INDEX_CATEGORY_INPUT);

            System.out.print("Enter number of ingredients needed for this meat dish: ");
            Integer numberOfIngredients = InputValidator.validatePositiveInteger(scanner, Messages.INVALID_NUMBER_OF_INGREDIENTS);

            System.out.println("Available ingredients: ");
            Set<Ingredient> availableIngredients = DataInputUtils.inputIngredients(scanner, ingredients, numberOfIngredients, Messages.INVALID_INTEGER_INDEX_INGREDIENT_INPUT);

            System.out.print("Enter the price of " + (i + 1) + ". meat dish: ");
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

            System.out.print("Enter the meat source for " + (i + 1) + ". meat dish: ");
            String meatSource = scanner.nextLine();

            meatDishes.add(new MeatDish(Long.valueOf(i + 1), name, category, availableIngredients, price, calories, meatSource));
        }
    }
}
