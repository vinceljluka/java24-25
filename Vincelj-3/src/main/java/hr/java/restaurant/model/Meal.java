package hr.java.restaurant.model;

import hr.java.restaurant.exception.NegativOrUnrealPrice;
import hr.java.utils.DataInputUtils;
import hr.java.utils.InputValidator;
import hr.java.utils.Messages;
import hr.java.utils.PriceCheck;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Scanner;

public class Meal extends Entity {

    private static Logger log = LoggerFactory.getLogger(Meal.class);

    private String name;
    private Category category;
    private Ingredient[] ingredients;
    private BigDecimal price;
    private Integer calories;  // Added calories field

    public Meal(Long id, String name, Category category, Ingredient[] ingredients, BigDecimal price, Integer calories) {
        super(id);
        this.name = name;
        this.category = category;
        this.ingredients = ingredients;
        this.price = price;
        this.calories = calories;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Ingredient[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredient[] ingredients) {
        this.ingredients = ingredients;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public static void inputMeal(Meal[] meals, Category[] categories, Ingredient[] ingredients, Scanner scanner) {
        for (int i = 0; i < meals.length; i++) {
            System.out.print("Enter " + (i + 1) + "." + " meal's name: ");
            String name = scanner.nextLine();

            System.out.print("Choose " + (i + 1) + "." + " meal's category (index 1, 2 or 3) : \n");
            for (int j = 0; j < categories.length; j++) {
                System.out.println((j + 1) + "." + categories[j].getName());
            }
            Category category = DataInputUtils.getCategory(categories, scanner, Messages.INVALID_INTEGER_INDEX_CATEGORY_INPUT);

            System.out.print("Enter number of ingredients needed for this meal: ");
            int numberOfIngredients = InputValidator.validatePositiveInteger(scanner, Messages.INVALID_NUMBER_OF_INGREDIENTS);

            System.out.println("Avaliable ingredients: ");
            Ingredient[] helpArray = DataInputUtils.inputIngredients(scanner, ingredients, numberOfIngredients, Messages.INVALID_INTEGER_INDEX_INGREDIENT_INPUT);

            System.out.print("Enter the price of " + (i + 1) + ". meal: ");
            Boolean isValid;
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

            System.out.println("Enter calories for this meal: ");
            Integer calories = InputValidator.validatePositiveInteger(scanner, Messages.INVALID_BIGDECIMAL_KCAL_INPUT);

            meals[i] = new Meal(Long.valueOf(i + 1), name, category, helpArray, price, calories);
        }
    }
}
