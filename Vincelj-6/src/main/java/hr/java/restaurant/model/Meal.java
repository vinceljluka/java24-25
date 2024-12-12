package hr.java.restaurant.model;

import hr.java.restaurant.exception.NegativOrUnrealPrice;
import hr.java.utils.DataInputUtils;
import hr.java.utils.InputValidator;
import hr.java.utils.Messages;
import hr.java.utils.PriceCheck;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Stream;

public class Meal extends Entity implements Serializable {
    public static final int MAX_LENGTH = 1;

    private static Logger log = LoggerFactory.getLogger(Meal.class);

    private static final String MEALS_FILE_PATH = "dat/meals.txt";

    private String name;
    private Category category;
    private Set <Ingredient> ingredients;
    private BigDecimal price;
    private Integer calories;

    public Meal(Long id, String name, Category category, Set<Ingredient> ingredients, BigDecimal price, Integer calories) {
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

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public static List<Meal> loadMealsFromFile(List<Category> availableCategories, List<Ingredient> availableIngredients) {
        List<Meal> meals = new ArrayList<>();

        try (Stream<String> lines = Files.lines(Path.of(MEALS_FILE_PATH))) {
            List<String> fileRows = lines.collect(Collectors.toList());

            for (int i = 0; i < fileRows.size(); i += 6)
            {
                Long id = Long.parseLong(fileRows.get(i).trim());
                String name = fileRows.get(i + 1).trim();
                Long categoryId = Long.parseLong(fileRows.get(i + 2).trim());

                Category category = availableCategories.stream()
                        .filter(cat -> cat.getId().equals(categoryId))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Invalid category ID: " + categoryId));


                String[] ingredientIds = fileRows.get(i + 3).trim().split(" ");
                Set<Ingredient> ingredients = new HashSet<>();
                for (String ingredientId : ingredientIds) {
                    Long ingId = Long.parseLong(ingredientId.trim());
                    Ingredient ingredient = availableIngredients.stream()
                            .filter(ing -> ing.getId().equals(ingId))
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException("Invalid ingredient ID: " + ingId));
                    ingredients.add(ingredient);
                }

                BigDecimal price = new BigDecimal(fileRows.get(i + 4).trim());
                int totalCalories = Integer.parseInt(fileRows.get(i + 5).trim());

                meals.add(new Meal(id, name, category, ingredients, price, totalCalories));
            }
        } catch (IOException | IllegalArgumentException e) {
            log.error("Error reading meals file: ", e);
        }
        return meals;
    }

    public static void inputMeal(Set <Meal> meals, List <Category> categories, List <Ingredient> ingredients, Scanner scanner) {
        for (int i = 0; i < MAX_LENGTH; i++)
        {
            System.out.print("Enter " + (i + 1) + "." + " meal's name: ");
            String name = scanner.nextLine();

            System.out.print("Choose " + (i + 1) + "." + " meal's category (index 1, 2 or 3) : \n");
            for (int j = 0; j < categories.size(); j++) {
                System.out.println((j + 1) + "." + categories.get(j).getName());
            }
            Category category = DataInputUtils.getCategory(categories, scanner, Messages.INVALID_INTEGER_INDEX_CATEGORY_INPUT);

            System.out.print("Enter number of ingredients needed for this meal: ");
            int numberOfIngredients = InputValidator.validatePositiveInteger(scanner, Messages.INVALID_NUMBER_OF_INGREDIENTS);

            System.out.println("Avaliable ingredients: ");
            Set <Ingredient> avaliableIngredients = DataInputUtils.inputIngredients(scanner, ingredients, numberOfIngredients, Messages.INVALID_INTEGER_INDEX_INGREDIENT_INPUT);

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

            meals.add(new Meal(Long.valueOf(i + 1), name, category, avaliableIngredients, price, calories));
        }
    }

    //------------------2
    public static List<Meal> filterMealsByCalories(List<Meal> meals)
    {
        return meals.stream()
                .filter(meal -> meal.getCalories() != null && meal.getCalories() > 1000)
                .collect(Collectors.toList());
    }
    //------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return Objects.equals(name, meal.name) && Objects.equals(category, meal.category) && Objects.equals(ingredients, meal.ingredients) && Objects.equals(price, meal.price) && Objects.equals(calories, meal.calories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, category, ingredients, price, calories);
    }

    @Override
    public String toString() {
        return "Meal{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", calories=" + calories +
                ", id=" + id +
                '}';
    }
}
