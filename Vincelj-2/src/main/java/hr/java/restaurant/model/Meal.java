package hr.java.restaurant.model;

import java.math.BigDecimal;
import java.util.Scanner;

public class Meal extends Entity {
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

            int categoryIndex;
            while (true) {
                if (scanner.hasNextInt()) {
                    categoryIndex = scanner.nextInt();
                    scanner.nextLine();
                    if (categoryIndex >= 1 && categoryIndex <= categories.length) {
                        break;
                    } else {
                        System.out.println("Invalid input, please enter a category index between 1 and " + categories.length + ".");
                    }
                } else {
                    System.out.println("Invalid input, please enter a valid integer for category index.");
                    scanner.nextLine();
                }
            }
            Category category = categories[categoryIndex - 1];

            System.out.print("Enter number of ingredients needed for this meal: ");
            int numberOfIngredients;
            while (true) {
                if (scanner.hasNextInt()) {
                    numberOfIngredients = scanner.nextInt();
                    scanner.nextLine();
                    if (numberOfIngredients > 0) {
                        break;
                    } else {
                        System.out.println("Number of ingredients must be greater than 0.");
                    }
                } else {
                    System.out.println("Invalid input, please enter a valid integer for number of ingredients.");
                    scanner.nextLine();
                }
            }
            Ingredient[] helpArray = new Ingredient[numberOfIngredients];
            for (int j = 0; j < ingredients.length; j++) {
                System.out.println((j + 1) + "." + ingredients[j].getName());
            }

            for (int j = 0; j < numberOfIngredients; j++) {
                System.out.print("Choose ingredient " + (j + 1) + ": ");
                int ingredientIndex;
                while (true) {
                    if (scanner.hasNextInt()) {
                        ingredientIndex = scanner.nextInt();
                        scanner.nextLine();
                        if (ingredientIndex >= 1 && ingredientIndex <= ingredients.length) {
                            helpArray[j] = ingredients[ingredientIndex - 1];
                            break;
                        } else {
                            System.out.println("Invalid input, please enter an ingredient index between 1 and " + ingredients.length + ".");
                        }
                    } else {
                        System.out.println("Invalid input, please enter a valid integer for the ingredient index.");
                        scanner.nextLine();
                    }
                }
            }

            System.out.print("Enter the price of " + (i + 1) + "." + " meal: ");
            BigDecimal price;
            while (true) {
                if (scanner.hasNextBigDecimal()) {
                    price = scanner.nextBigDecimal();
                    scanner.nextLine();
                    if (price.compareTo(BigDecimal.ZERO) >= 0) {
                        break;
                    } else {
                        System.out.println("Price cannot be negative. Please enter a valid price.");
                    }
                } else {
                    System.out.println("Invalid input, please enter a valid number for price.");
                    scanner.nextLine();
                }
            }

            Integer calories = null;
            while (true) {
                System.out.print("Enter the calories for " + (i + 1) + "." + " meal: ");
                if (scanner.hasNextInt()) {
                    calories = scanner.nextInt();
                    scanner.nextLine();
                    if (calories >= 0) {
                        break;
                    } else {
                        System.out.println("Calories cannot be negative. Please enter a valid number.");
                    }
                } else {
                    System.out.println("Invalid input, please enter a valid number for calories.");
                    scanner.nextLine();
                }
            }
            meals[i] = new Meal(Long.valueOf(i + 1), name, category, helpArray, price, calories);
        }
    }
}
