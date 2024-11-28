package hr.java.production.main;

import hr.java.restaurant.enums.ContractType;
import hr.java.restaurant.model.*;
import hr.java.restaurant.sort.EmployeesContractDurationComparator;
import hr.java.restaurant.sort.EmployeesPayComparator;
import hr.java.restaurant.sort.IngredientsAlphabeticalComparator;
import hr.java.utils.MealRestaurantKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {

    private static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        log.info("Application started!");

        Scanner scanner = new Scanner(System.in);

        List<Category> categories = new ArrayList<>();
        categories.add(new Category.Builder()
                .withId(1L)
                .withName("Main Course")
                .withDescription("Meals that are served as the main dish.")
                .build());


        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(new Ingredient(1L, "Tomato", categories.get(0), BigDecimal.valueOf(100), "Mjau"));

        Set<Meal> meals = new HashSet<>();
        meals.add(new Meal(1L, "Tomato Cheese Pizza", categories.get(0), ingredients, BigDecimal.valueOf(100), 100));


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Contract contract = new Contract(BigDecimal.valueOf(1000), LocalDate.parse("14-09-2024", formatter), LocalDate.parse("15-09-2025", formatter), ContractType.FULL_TIME);
        Contract contract1 = new Contract(BigDecimal.valueOf(1200), LocalDate.parse("14-09-2024", formatter), LocalDate.parse("15-09-2025", formatter), ContractType.FULL_TIME);

        Set<Chef> chefs = new HashSet<>();
        chefs.add(new Chef.Builder()
                .withFirstName("John")
                .withLastName("Doe")
                .withContract(contract1)
                .withBonus(new Bonus(BigDecimal.valueOf(100)))
                .build());

        Set<Waiter> waiters = new HashSet<>();
        waiters.add(new Waiter.Builder()
                .withFirstName("Luka")
                .withLastName("Vincelj")
                .withContract(contract)
                .withBonus(new Bonus(BigDecimal.valueOf(200)))
                .build());

        Set<Deliverer> deliverers = new HashSet<>();
        deliverers.add(new Deliverer.Builder()
                .withFirstName("Niko")
                .withLastName("Vincelj")
                .withContract(contract)
                .withBonus(new Bonus(BigDecimal.valueOf(100)))
                .build());

        Address address = new Address.Builder()
                .withId(1L)
                .withStreet("Main Street")
                .withHouseNumber("12A")
                .withCity("Zagreb")
                .withPostalCode("10000")
                .build();

        Restaurant restaurant = new Restaurant(1L, "Zagorka", address, meals, chefs, waiters, deliverers);

        Set<Restaurant> restaurants = new HashSet<>();
        restaurants.add(restaurant);

        MealRestaurantKey.displayRestaurantsForMeal(meals, restaurants, scanner);
        EmployeesPayComparator.printHighestPaidEmployees(restaurants);
        EmployeesContractDurationComparator.printSortedEmployeesByContractDuration(restaurants);
        for (Meal meal : meals)
        {
            IngredientsAlphabeticalComparator.printIngredientsSortedByName(meal);
        }


    }
}
