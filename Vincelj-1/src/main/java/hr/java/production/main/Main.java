package hr.java.production.main;

import hr.java.restaurant.model.*;

import java.util.Scanner;

public class Main {

    private final static Integer MAX_NUMBER = 3;
    private final static Integer MAX_NUMBER_INGREDIENT = 5;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Category[] categories = new Category[MAX_NUMBER];
        Category.inputCategory(categories, scanner);

        Ingredient[] ingredients = new Ingredient[MAX_NUMBER_INGREDIENT];
        Ingredient.inputIngredient(ingredients, categories, scanner);

        Meal[] meals = new Meal[MAX_NUMBER];
        Meal.inputMeal(meals, categories, ingredients, scanner);

        Chef[] chefs = new Chef[MAX_NUMBER];
        Chef.inputChef(chefs,scanner);

        Waiter[] waiters = new Waiter[MAX_NUMBER];
        Waiter.inputWaiter(waiters, scanner);

        Deliverer[] deliverers = new Deliverer[MAX_NUMBER];
        Deliverer.inputDeliverer(deliverers, scanner);

        Restaurant[] restaurants = new Restaurant[MAX_NUMBER];
        Restaurant.inputRestaurant(restaurants, meals, chefs, waiters, deliverers, scanner);

        Order[] orders = new Order[MAX_NUMBER];
        Order.inputOrder(orders, restaurants, meals, chefs, deliverers, scanner);

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

    }
}
