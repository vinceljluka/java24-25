package hr.java.restaurant.model;
import hr.java.utils.DataEmployeeInputUtils;
import hr.java.utils.DataInputUtils;
import hr.java.utils.InputValidator;
import hr.java.utils.Messages;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Order extends Entity{
    private Restaurant restaurant;
    private Meal[] meals;
    private Deliverer deliverer;
    private LocalDateTime deliveryDateAndTime;

    public Order(Long id, Restaurant restaurant, Meal[] meals, Deliverer deliverer, LocalDateTime deliveryDateAndTime) {
        super(id);
        this.restaurant = restaurant;
        this.meals = meals;
        this.deliverer = deliverer;
        this.deliveryDateAndTime = deliveryDateAndTime;
    }
    public Restaurant getRestaurant() {
        return restaurant;
    }
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
    public Meal[] getMeals() {
        return meals;
    }
    public void setMeals(Meal[] meals) {
        this.meals = meals;
    }
    public Deliverer getDeliverer() {
        return deliverer;
    }
    public void setDeliverer(Deliverer deliverer) {
        this.deliverer = deliverer;
    }
    public LocalDateTime getDeliveryDateAndTime() {
        return deliveryDateAndTime;
    }
    public void setDeliveryDateAndTime(LocalDateTime deliveryDateAndTime) {
        this.deliveryDateAndTime = deliveryDateAndTime;
    }

    public static void inputOrder(Order[] orders, Restaurant[] restaurants, Meal[] meals, Chef[] chefs, Deliverer[] deliverers, Scanner scanner) {
        for (int i = 0; i < orders.length; i++)
        {

            System.out.print("Choose a restaurant from which is your " + (i+1) + "." + " order (index 1,2,3): \n");
            for (int j = 0; j < restaurants.length; j++)
            {
                System.out.print((j+1) + ". " + restaurants[j].getName() + "\n");
            }
            Restaurant helprestaurant = DataInputUtils.getRestaurant(restaurants, scanner, Messages.INVALID_RESTAURANT_INDEX_INPUT);

            System.out.print("Enter the number of meals in the order: ");
            int numberOfMeals = InputValidator.validatePositiveInteger(scanner, Messages.INVALID_NUMBER_OF_MEALS_INPUT);

            System.out.println("Available meals in " + helprestaurant.getName() + ":");
            Meal[] helpmeals = DataInputUtils.inputMealsFromRestaurant(helprestaurant, scanner, numberOfMeals, Messages.INVALID_INTEGER_INDEX_MEAL_INPUT);

            System.out.print("Choose a deliverer thats delivering the order (index 1,2,3): \n");
            for (int j = 0; j < restaurants[i].getDeliverers().length; j++) {
                System.out.print((j+1) + ". " + restaurants[i].getDeliverers()[j].getFirstName() + " " + restaurants[i].getDeliverers()[j].getLastName() + "\n");
            }
            Deliverer helpdeliverer = DataEmployeeInputUtils.inputDelivererFromRestaurant(helprestaurant, scanner, Messages.INVALID_DELIVERER_INDEX_INPUT);

            System.out.print("Enter the date and time of the order (format: dd-MM-yyyy HH:mm): ");
            String dateTimeInput = scanner.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            LocalDateTime deliveryDateAndTime = LocalDateTime.parse(dateTimeInput, formatter);

            orders[i] = new Order(Long.valueOf(i+1), helprestaurant, helpmeals, helpdeliverer, deliveryDateAndTime);
        }
    }

    public BigDecimal totalPrice() {
        BigDecimal total = BigDecimal.valueOf(0);
        for (int j = 0; j < this.meals.length; j++) {
            total = total.add(this.meals[j].getPrice());
        }
        return total;
    }

    public static Order[] findMostExpensiveOrderRestaurants(Order[] orders) {
        Order[] mostExpensiveOrders = new Order[orders.length];
        Integer counterOfMostExpensiveOrders = 0;

        BigDecimal mostExpensive = BigDecimal.valueOf(0);
        BigDecimal totalPrice;
        for (int i = 0; i < orders.length; i++) {
            totalPrice = orders[i].totalPrice();

            if(totalPrice.compareTo(mostExpensive) == 0) {
                mostExpensiveOrders[counterOfMostExpensiveOrders] = orders[i];
                counterOfMostExpensiveOrders++;

            } else if (totalPrice.compareTo(mostExpensive) > 0) {
                mostExpensiveOrders[0] = orders[i];
                counterOfMostExpensiveOrders = 1;
                mostExpensive = totalPrice;
            }
        }
        Order[] mostExpensiveOrdersReturn = new Order[counterOfMostExpensiveOrders];
        for (int i = 0; i < counterOfMostExpensiveOrders; i++) {
            mostExpensiveOrdersReturn[i] = mostExpensiveOrders[i];
        }
        return mostExpensiveOrdersReturn;
    }

    public static Deliverer[] findDelivererWithMostDeliveries (Order[] orders)
    {
        int maxDeliveries = 0;
        int[] deliveryCounts = new int[orders.length];
        Deliverer[] deliverers = new Deliverer[orders.length];
        int delivererCount = 0;

        for (int i = 0; i < orders.length; i++) {
            Deliverer currentDeliverer = orders[i].getDeliverer();
            boolean found = false;
            int delivererIndex = -1;

            for (int j = 0; j < delivererCount; j++) {
                if (deliverers[j].equals(currentDeliverer)) {
                    found = true;
                    delivererIndex = j;
                    break;
                }
            }
            if (!found) {
                deliverers[delivererCount] = currentDeliverer;
                deliveryCounts[delivererCount] = 1;
                delivererCount++;
            } else {
                deliveryCounts[delivererIndex]++;
            }
        }
        for (int i = 0; i < delivererCount; i++) {
            if (deliveryCounts[i] > maxDeliveries) {
                maxDeliveries = deliveryCounts[i];
            }
        }
        int resultCount = 0;
        for (int i = 0; i < delivererCount; i++) {
            if (deliveryCounts[i] == maxDeliveries) {
                resultCount++;
            }
        }
        Deliverer[] topDeliverers = new Deliverer[resultCount];
        int index = 0;
        for (int i = 0; i < delivererCount; i++) {
            if (deliveryCounts[i] == maxDeliveries) {
                topDeliverers[index] = deliverers[i];
                index++;
            }
        }
        return topDeliverers;
    }
}