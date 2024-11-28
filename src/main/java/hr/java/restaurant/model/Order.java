package hr.java.restaurant.model;
import hr.java.utils.DataEmployeeInputUtils;
import hr.java.utils.DataInputUtils;
import hr.java.utils.InputValidator;
import hr.java.utils.Messages;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Order extends Entity{
    private Restaurant restaurant;
    private List<Meal> meals;
    private Deliverer deliverer;
    private LocalDateTime deliveryDateAndTime;

    public Order(Long id, Restaurant restaurant, List<Meal> meals, Deliverer deliverer, LocalDateTime deliveryDateAndTime) {
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
    public List<Meal> getMeals() {
        return meals;
    }
    public void setMeals(List<Meal> meals) {
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

    public static BigDecimal calculateOrderTotal(Order order)
    {
        BigDecimal totalValue = BigDecimal.ZERO;

        for (Meal meal : order.getMeals()) {
            totalValue = totalValue.add(meal.getPrice());
        }

        return totalValue;
    }

    public static void inputOrder(List <Order> orders, List<Restaurant> restaurants, List<Meal> meals, Chef[] chefs, Deliverer[] deliverers, Scanner scanner) {
        for (int i = 0; i < orders.size(); i++) {

            System.out.print("Choose a restaurant for your " + (i + 1) + " order (index 1,2,3): \n");
            for (int j = 0; j < restaurants.size(); j++) {
                System.out.print((j + 1) + ". " + restaurants.get(j).getName() + "\n");
            }

            Restaurant helprestaurant = DataInputUtils.getRestaurant(restaurants, scanner, Messages.INVALID_RESTAURANT_INDEX_INPUT);

            System.out.print("Enter the number of meals in the order: ");
            int numberOfMeals = InputValidator.validatePositiveInteger(scanner, Messages.INVALID_NUMBER_OF_MEALS_INPUT);

            System.out.println("Available meals in " + helprestaurant.getName() + ":");
            List<Meal> helpmeals = ((List<Meal>)DataInputUtils.inputMealsFromRestaurant(helprestaurant, scanner, numberOfMeals, Messages.INVALID_INTEGER_INDEX_MEAL_INPUT));

            System.out.print("Choose a deliverer for the order (index 1,2,3): \n");
            List<Deliverer> deliverersList = new ArrayList<>(helprestaurant.getDeliverers());
            for (int j = 0; j < deliverersList.size(); j++) {
                System.out.print((j + 1) + ". " + deliverersList.get(j).getFirstName() + " " + deliverersList.get(j).getLastName() + "\n");
            }

            Deliverer helpdeliverer = DataEmployeeInputUtils.inputDelivererFromRestaurant(helprestaurant, scanner, Messages.INVALID_DELIVERER_INDEX_INPUT);

            System.out.print("Enter the date and time of the order (format: dd-MM-yyyy HH:mm): ");
            String dateTimeInput = scanner.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            LocalDateTime deliveryDateAndTime = LocalDateTime.parse(dateTimeInput, formatter);

            orders.add(new Order(Long.valueOf(i + 1), helprestaurant, helpmeals, helpdeliverer, deliveryDateAndTime));
        }
    }



}