package hr.java.production.main;

import hr.java.restaurant.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Main {

    private static Logger log = LoggerFactory.getLogger(Main.class);

    private final static Integer MAX_NUMBER = 3;
    private final static Integer MAX_NUMBER_INGREDIENT = 5;

    public static void main(String[] args) {

        log.info("Application started!");

        Scanner scanner = new Scanner(System.in);

        Category[] categories = new Category[MAX_NUMBER];
        Category.inputCategory(categories, scanner);

        Ingredient[] ingredients = new Ingredient[MAX_NUMBER_INGREDIENT];
        Ingredient.inputIngredient(ingredients, categories, scanner);

        MeatDish[] meatDishes = new MeatDish[3];
        VeganMeal[] veganMeals = new VeganMeal[3];
        VegetarianMeal[] vegetarianMeals = new VegetarianMeal[3];

        MeatDish.inputMeatDish(meatDishes, categories, ingredients, scanner);
        VeganMeal.inputVeganMeal(veganMeals, categories, ingredients, scanner);
        VegetarianMeal.inputVegetarianMeal(vegetarianMeals, categories, ingredients, scanner);

        Meal[] allMeals = new Meal[meatDishes.length + veganMeals.length + vegetarianMeals.length];
        int br = 0;
        for (Meal meal : meatDishes)
        {
            allMeals[br++] = meal;
        }
        for (Meal meal : veganMeals)
        {
            allMeals[br++] = meal;
        }
        for (Meal meal : vegetarianMeals)
        {
            allMeals[br++] = meal;
        }

        Chef[] chefs = new Chef[MAX_NUMBER];
        Chef.inputChef(chefs,scanner);

        Waiter[] waiters = new Waiter[MAX_NUMBER];
        Waiter.inputWaiter(waiters, scanner);

        Deliverer[] deliverers = new Deliverer[MAX_NUMBER];
        Deliverer.inputDeliverer(deliverers, scanner);

        Restaurant[] restaurants = new Restaurant[MAX_NUMBER];
        Restaurant.inputRestaurant(restaurants, allMeals, chefs, waiters, deliverers, scanner);

        Order[] orders = new Order[MAX_NUMBER];
        Order.inputOrder(orders, restaurants, allMeals, chefs, deliverers, scanner);

        Order[] mostExpensiveOrders  = Order.findMostExpensiveOrderRestaurants(orders);
        System.out.println("\n\nRestorani s najskupljim dostavama: ");
        for (int i = 0; i < mostExpensiveOrders.length; i++)
        {
            System.out.println(mostExpensiveOrders[i].getRestaurant().getName());
        }

        Deliverer[] mostDeliveriesDeliverers = Order.findDelivererWithMostDeliveries(orders);
        System.out.println("\n\nDostavljaci s najvise dostava: ");
        for (int i = 0; i < mostDeliveriesDeliverers.length; i++)
        {
            System.out.println(mostDeliveriesDeliverers[i].getFirstName() + " " + mostDeliveriesDeliverers[i].getLastName());
        }
        System.out.println("\n");

        Person[] employees = new Person[chefs.length + waiters.length + deliverers.length];
        int index = 0;
        for (Chef chef : chefs) {
            employees[index++] = chef;
        }
        for (Waiter waiter : waiters) {
            employees[index++] = waiter;
        }
        for (Deliverer deliverer : deliverers) {
            employees[index++] = deliverer;
        }
        Person.highestPayAndEmployment(employees);

        Meal maxCaloriesMeal = allMeals[0];
        Meal minCaloriesMeal = allMeals[0];

        for (Meal meal : allMeals) {
            if (meal.getCalories() > maxCaloriesMeal.getCalories())
            {
                maxCaloriesMeal = meal;
            }
            if (meal.getCalories() < minCaloriesMeal.getCalories())
            {
                minCaloriesMeal = meal;
            }
        }

        System.out.println("\nJelo s najviše kalorija:");
        System.out.println("Naziv: " + maxCaloriesMeal.getName());
        System.out.println("Kategorija: " + maxCaloriesMeal.getCategory().getName());
        System.out.println("Kalorije: " + maxCaloriesMeal.getCalories());

        System.out.println("\nJelo s najmanje kalorija:");
        System.out.println("Naziv: " + minCaloriesMeal.getName());
        System.out.println("Kategorija: " + minCaloriesMeal.getCategory().getName());
        System.out.println("Kalorije: " + minCaloriesMeal.getCalories());


    }
}
