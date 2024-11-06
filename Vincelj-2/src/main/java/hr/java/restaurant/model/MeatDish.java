package hr.java.restaurant.model;

import java.math.BigDecimal;
import java.util.Scanner;

public non-sealed class MeatDish extends Meal implements Meat {
    private String meatSource;

    public MeatDish(Long id, String name, Category category, Ingredient[] ingredients, BigDecimal price, Integer calories, String meatSource) {
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

    public static void inputMeatDish(MeatDish[] meatDishes, Category[] categories, Ingredient[] ingredients, Scanner scanner) {
        for (int i = 0; i < meatDishes.length; i++) {
            System.out.print("Enter " + (i + 1) + "." + " meat dish's name: ");
            String name = scanner.nextLine();

            System.out.print("Choose " + (i + 1) + "." + " meat dish's category (index 1, 2, or 3) : \n");
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

            System.out.print("Enter number of ingredients needed for this meat dish: ");
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

            System.out.print("Enter the price of " + (i + 1) + "." + " meat dish: ");
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
                System.out.print("Enter the calories for " + (i + 1) + "." + " meat dish: ");
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

            System.out.print("Enter the meat source for " + (i + 1) + "." + " meat dish: ");
            String meatSource = scanner.nextLine();

            meatDishes[i] = new MeatDish(Long.valueOf(i + 1), name, category, helpArray, price, calories, meatSource);
        }
    }


}
