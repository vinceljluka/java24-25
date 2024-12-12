package hr.java.production.main;

import hr.java.restaurant.enums.ContractType;
import hr.java.restaurant.generics.EmployeeAssignment;
import hr.java.restaurant.generics.RestaurantLabourExchangeOffice;
import hr.java.restaurant.model.*;
import hr.java.restaurant.sort.EmployeesContractDurationComparator;
import hr.java.restaurant.sort.EmployeesPayComparator;
import hr.java.restaurant.sort.IngredientsAlphabeticalComparator;
import hr.java.restaurant.sort.MealCaloriesComparator;
import hr.java.utils.ListPrinter;
import hr.java.utils.MealRestaurantKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    private static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        log.info("Application started!");

        // Kreiranje kategorije
        Category mainCourse = new Category.Builder()
                .withId(1L)
                .withName("Main Course")
                .withDescription("Meals served as the main dish.")
                .build();

        // Kreiranje sastojaka
        Ingredient tomato = new Ingredient(1L, "Tomato", mainCourse, BigDecimal.valueOf(100), "Fresh");
        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(tomato);

        // --------------------------2
        Meal pizza = new Meal(1L, "Cheese Pizza", mainCourse, ingredients, BigDecimal.valueOf(100), 8100);
        Meal tomatoSoup= new Meal(2L, "Tomato Soup", mainCourse, ingredients, BigDecimal.valueOf(100), 1100);
        List<Meal> meals = new ArrayList<>();
        meals.add(tomatoSoup);
        meals.add(pizza);


        // --------------------------2
        List <Meal> fileteredMeals = new ArrayList<>();
        fileteredMeals.add(tomatoSoup);
        fileteredMeals.add(pizza);
        Meal.filterMealsByCalories(fileteredMeals);
        MealCaloriesComparator.sortAndPrintMealsDescending(fileteredMeals);

        //--------------------------------3

        ListPrinter.printList(fileteredMeals);


        // Kreiranje ugovora ---- 1
        Contract contract = new Contract(1L, BigDecimal.valueOf(0), LocalDate.of(2024, 9, 14), LocalDate.of(2025, 9, 15), ContractType.FULL_TIME);
        contract.displayContractDetails();



        // Kreiranje zaposlenih
        Chef chef = new Chef.Builder()
                .withFirstName("John")
                .withLastName("Doe")
                .withContract(contract)
                .withBonus(new Bonus(BigDecimal.valueOf(100)))
                .build();

        Waiter waiter = new Waiter.Builder()
                .withFirstName("Luka")
                .withLastName("Vincelj")
                .withContract(contract)
                .withBonus(new Bonus(BigDecimal.valueOf(200)))
                .build();

        Deliverer deliverer = new Deliverer.Builder()
                .withFirstName("Niko")
                .withLastName("Vincelj")
                .withContract(contract)
                .withBonus(new Bonus(BigDecimal.valueOf(100)))
                .build();

        // Kreiranje adrese
        Address address = new Address.Builder()
                .withId(1L)
                .withStreet("Main Street")
                .withHouseNumber("12A")
                .withCity("Zagreb")
                .withPostalCode("10000")
                .build();

        Set<Meal> meals1 = new HashSet<>(meals);

        // Kreiranje restorana
        Restaurant restaurant = new Restaurant(1L, "Zagorka", address, meals1, Set.of(chef), Set.of(waiter), Set.of(deliverer));
        Restaurant restaurant1 = new Restaurant(2L, "Cucek", address, meals1, Set.of(chef), Set.of(waiter), Set.of(deliverer));

        // Set restorana
        Set <Restaurant> restaurants = Set.of(restaurant, restaurant1);

        // Kreiranje narudžbe
        LocalDateTime orderTime = LocalDateTime.now();
        List<Meal> mealList = new ArrayList<>(meals);
        Order order1 = new Order(1L, restaurant, mealList, deliverer, orderTime);

        Set<Order> orders = new HashSet<>();
        orders.add(order1);







        /* MealRestaurantKey.displayRestaurantsForMeal(meals, restaurants, scanner);
        EmployeesPayComparator.printHighestPaidEmployees(restaurants);
        EmployeesContractDurationComparator.printSortedEmployeesByContractDuration(restaurants);
        for (Meal meal : meals)
        {
            IngredientsAlphabeticalComparator.printIngredientsSortedByName(meal);
        } */

        //--------------------------------4

//        RestaurantLabourExchangeOffice <Restaurant> popisRestorana = new RestaurantLabourExchangeOffice<>(restaurants);
//
//        //--------------------------------6
//        Restaurant mostEmployeesRestaurant =
//                restaurants.stream()
//                .max(Comparator.comparingInt(r -> r.getTotalEmployees())) // Komparira broj zaposlenih
//                .get();
//
//        System.out.println("Restaurant with the most employees:");
//        System.out.println("Name: " + mostEmployeesRestaurant.getName());
//        System.out.println("Number of employees: " + mostEmployeesRestaurant.getTotalEmployees());
//
//        //---------------------------------7
//
//        Meal mostOrderedMeal = orders.stream()
//                .flatMap(order -> order.getMeals().stream())
//                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
//                .entrySet()
//                .stream()
//                .max(Map.Entry.comparingByValue())
//                .get()
//                .getKey();
//
//        System.out.println("Najčešće naručivano jelo:");
//        System.out.println("Naziv: " + mostOrderedMeal.getName());
//        System.out.println("Cijena: " + mostOrderedMeal.getPrice());
//        System.out.println("Kalorije: " + mostOrderedMeal.getCalories());
//
//        //----------------------------------8
//
//        orders.forEach(order -> {System.out.println("Narudžba ID: " + order.getId());
//            order.getMeals()
//                    .stream()
//                    .flatMap(meal -> meal.getIngredients().stream())
//                    .forEach(ingredient ->
//                            System.out.println("- " + ingredient.getName() + " (" + ingredient.getCategory().getName() + ")")
//                    );
//        });
//
//        //----------------------------------9
//        System.out.println("Cijene pojedinačnih obroka:");
//        orders.stream()
//                .flatMap(order -> order.getMeals().stream())
//                .map(Meal::getPrice)
//                .forEach(price -> System.out.println(price));
//
//        //----------------------------------10
//
//        restaurants.stream()
//                .collect(Collectors.groupingBy(r -> r.getAddress().getCity()))
//                .forEach((city, cityRestaurants) -> {
//                    System.out.println("Grad: " + city);
//                    cityRestaurants.forEach(r ->
//                            System.out.println("- " + r.getName()));
//                });
      }
}
