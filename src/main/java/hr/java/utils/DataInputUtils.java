package hr.java.utils;
import hr.java.restaurant.model.Category;
import hr.java.restaurant.model.Ingredient;
import hr.java.restaurant.model.Meal;
import hr.java.restaurant.model.Restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

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

    public static Category getCategory(List <Category> categories, Scanner scanner, String errorMessage)
    {
        Integer categoryIndex;
        Boolean isValid;
        Category category = null;
        do {
            try {
                isValid = true;
                categoryIndex = scanner.nextInt();
                category = categories.get(categoryIndex - 1);
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

    public static Restaurant getRestaurant(List <Restaurant> restaurants, Scanner scanner, String errorMessage) {
        Integer restaurantIndex;
        Boolean isValid;
        Restaurant restaurant = null;

        do {
            try {
                isValid = true;
                restaurantIndex = scanner.nextInt();
                restaurant = restaurants.get(restaurantIndex - 1);
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

    public static Set<Ingredient> inputIngredients(Scanner scanner, List<Ingredient> ingredients, int numberOfIngredients, String errorMessage) {
        List<Ingredient> ingredientList = new ArrayList<>(ingredients);
        Set<Ingredient> selectedIngredients = new HashSet<>();

        for (int i = 0; i < ingredientList.size(); i++) {
            System.out.println((i + 1) + ". " + ingredientList.get(i).getName());
        }

        for (int i = 0; i < numberOfIngredients; i++) {
            System.out.print("Choose ingredient " + (i + 1) + ": ");
            int ingredientIndex;
            boolean isValid;

            do {
                isValid = true;
                try {
                    ingredientIndex = scanner.nextInt();
                    scanner.nextLine();
                    if (ingredientIndex < 1 || ingredientIndex > ingredientList.size()) {
                        System.out.println(errorMessage);
                        isValid = false;
                    } else {
                        selectedIngredients.add(ingredientList.get(ingredientIndex - 1));
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

    public static Set<Meal> inputMeals(Scanner scanner, Set<Meal> meals, int numberOfMeals, String errorMessage) {
        List <Meal> mealList = new ArrayList<>(meals);
        Set<Meal> selectedMeals = new HashSet<>();

        for (int i = 0; i < mealList.size(); i++) {
            System.out.println((i + 1) + ". " + mealList.get(i).getName());
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
                    if (mealIndex < 1 || mealIndex > meals.size()) {
                        System.out.println(errorMessage);
                        isValid = false;
                    } else {
                        selectedMeals.add(mealList.get(mealIndex - 1));
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

    public static Set<Meal> inputMealsFromRestaurant(Restaurant restaurant, Scanner scanner, int numberOfMeals, String errorMessage) {
        Set <Meal> selectedMeals = new HashSet<>();
        List <Meal> mealList = new ArrayList<>(restaurant.getMeals());

        for (int i = 0; i < mealList.size(); i++) {
            System.out.println((i + 1) + ". " + mealList.get(i).getName());
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
                    if (mealIndex < 1 || mealIndex > mealList.size()) {
                        System.out.println(errorMessage);
                        isValid = false;
                    } else {
                        selectedMeals.add(mealList.get(mealIndex - 1));
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
