package hr.java.utils;
import hr.java.restaurant.model.Category;
import hr.java.restaurant.model.Ingredient;
import hr.java.restaurant.model.Meal;
import hr.java.restaurant.model.Restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Služi za unos podataka
 */

public class DataInputUtils {

    private static Logger log = LoggerFactory.getLogger(DataInputUtils.class);

    /**
     * Služi za odabir jednog od unesenih "Category" objekata
     *
     * @param categories polje prethodno unesenih objekata klase "Category"
     * @param scanner objekt klase "Scanner koji omogućava unos podataka
     * @param errorMessage poruka koja se prikazuje prilikom pogresnog unosa
     * @return odabrani objekt klase "Category"
     */

    public static Category getCategory(Category[] categories, Scanner scanner, String errorMessage)
    {
        Integer categoryIndex;
        Boolean isValid;
        Category category = null;
        do {
            try {
                isValid = true;
                categoryIndex = scanner.nextInt();
                category = categories[categoryIndex - 1];
            } catch (InputMismatchException e) {
                log.error(errorMessage);
                isValid = false;
                System.out.println(errorMessage);
                scanner.nextLine();
            } catch (IndexOutOfBoundsException e) {
                log.error(errorMessage);
                isValid = false;
                System.out.println(errorMessage);
                scanner.nextLine();
            }
        } while (!isValid);

        return category;
    }

    public static Restaurant getRestaurant(Restaurant[] restaurants, Scanner scanner, String errorMessage) {
        Integer restaurantIndex;
        Boolean isValid;
        Restaurant restaurant = null;

        do {
            try {
                isValid = true;
                restaurantIndex = scanner.nextInt();
                restaurant = restaurants[restaurantIndex - 1];
            } catch (InputMismatchException e) {
                log.error(errorMessage);
                isValid = false;
                System.out.println(errorMessage);
                scanner.nextLine();
            } catch (IndexOutOfBoundsException e) {
                log.error(errorMessage);
                isValid = false;
                System.out.println(errorMessage);
                scanner.nextLine();
            }
        } while (!isValid);

        return restaurant;
    }

    public static Ingredient[] inputIngredients(Scanner scanner, Ingredient[] ingredients, int numberOfIngredients, String errorMessage) {
        Ingredient[] selectedIngredients = new Ingredient[numberOfIngredients];

        for (int i = 0; i < ingredients.length; i++)
        {
            System.out.println((i + 1) + ". " + ingredients[i].getName());
        }

        for (int i = 0; i < numberOfIngredients; i++)
        {
            System.out.print("Choose ingredient " + (i + 1) + ": ");
            int ingredientIndex;
            boolean isValid = true;

            do {
                isValid = true;
                try {
                    ingredientIndex = scanner.nextInt();
                    scanner.nextLine();
                    if (ingredientIndex < 0 || ingredientIndex > ingredients.length)
                    {
                        System.out.println(errorMessage);
                        isValid = false;
                    } else {
                        selectedIngredients[i] = ingredients[ingredientIndex - 1];
                        isValid = true;
                    }
                } catch (InputMismatchException e) {
                    log.error(errorMessage);
                    System.out.println(errorMessage);
                    isValid = false;
                    scanner.nextLine();
                } catch (IndexOutOfBoundsException e) {
                    log.error(errorMessage);
                    System.out.println(errorMessage);
                    isValid = false;
                    scanner.nextLine();
                }

            } while (!isValid);
        }
        return selectedIngredients;
    }

    public static Meal[] inputMeals(Scanner scanner, Meal[] meals, int numberOfMeals, String errorMessage) {
        Meal[] selectedMeals = new Meal[numberOfMeals];

        for (int i = 0; i < meals.length; i++) {
            System.out.println((i + 1) + ". " + meals[i].getName());
        }

        for (int i = 0; i < numberOfMeals; i++)
        {
            System.out.print("Choose meal " + (i + 1) + ": ");
            int mealIndex;
            boolean isValid;

            do {
                isValid = true;
                try {
                    mealIndex = scanner.nextInt();
                    scanner.nextLine();
                    if (mealIndex < 1 || mealIndex > meals.length) {
                        System.out.println(errorMessage);
                        isValid = false;
                    } else {
                        selectedMeals[i] = meals[mealIndex - 1];
                        isValid = true;
                    }
                } catch (InputMismatchException e) {
                    log.error(errorMessage);
                    System.out.println(errorMessage);
                    isValid = false;
                    scanner.nextLine();
                } catch (IndexOutOfBoundsException e) {
                    log.error(errorMessage);
                    System.out.println(errorMessage);
                    isValid = false;
                    scanner.nextLine();
                }
            } while (!isValid);
        }
        return selectedMeals;
    }

    public static Meal[] inputMealsFromRestaurant(Restaurant restaurant, Scanner scanner, int numberOfMeals, String errorMessage) {
        Meal[] selectedMeals = new Meal[numberOfMeals];

        for (int i = 0; i < restaurant.getMeals().length; i++)
        {
            System.out.println((i + 1) + ". " + restaurant.getMeals()[i].getName());
        }

        for (int i = 0; i < numberOfMeals; i++) {
            System.out.print("Choose meal " + (i + 1) + ": ");
            int mealIndex;
            boolean isValid;

            do {
                isValid = true;
                try {
                    mealIndex = scanner.nextInt();
                    scanner.nextLine();
                    if (mealIndex < 1 || mealIndex > restaurant.getMeals().length) {
                        System.out.println(errorMessage);
                        isValid = false;
                    } else {
                        selectedMeals[i] = restaurant.getMeals()[mealIndex - 1];
                        isValid = true;
                    }
                } catch (InputMismatchException e) {
                    log.error(errorMessage);
                    System.out.println(errorMessage);
                    isValid = false;
                    scanner.nextLine();
                }
            } while (!isValid);
        }
        return selectedMeals;
    }
}
