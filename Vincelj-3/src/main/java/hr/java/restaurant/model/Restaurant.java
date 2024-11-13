package hr.java.restaurant.model;
import hr.java.restaurant.exception.DuplicateEntityException;
import hr.java.utils.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Restaurant extends Entity {

    private static Logger log = LoggerFactory.getLogger(Restaurant.class);

    private String name;
    private Address address;
    private Meal[] meals;
    private Chef[] chefs;
    private Waiter[] waiters;
    private Deliverer[] deliverers;

    public Restaurant(Long id, String name, Address address, Meal[] meals, Chef[] chefs, Waiter[] waiters, Deliverer[] deliverers) {
        super(id);
        this.name = name;
        this.address = address;
        this.meals = meals;
        this.chefs = chefs;
        this.waiters = waiters;
        this.deliverers = deliverers;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public Meal[] getMeals() {
        return meals;
    }
    public void setMeals(Meal[] meals) {
        this.meals = meals;
    }
    public Chef[] getChefs() {
        return chefs;
    }
    public void setChefs(Chef[] chefs) {
        this.chefs = chefs;
    }
    public Waiter[] getWaiters() {
        return waiters;
    }
    public void setWaiters(Waiter[] waiters) {
        this.waiters = waiters;
    }
    public Deliverer[] getDeliverers() {
        return deliverers;
    }
    public void setDeliverers(Deliverer[] deliverers) {
        this.deliverers = deliverers;
    }

    public static void inputRestaurant(Restaurant[] restaurants, Meal[] meals, Chef[] chefs, Waiter[] waiters, Deliverer[] deliverers, Scanner scanner) {
        for (int i = 0; i < restaurants.length; i++) {
            Boolean isValid;
            String name;

            do {
                isValid = true;
                System.out.print("Enter the name of the " + (i + 1) + ". restaurant: ");
                name = scanner.nextLine();
                try {
                    DuplicateNameCheck.checkDuplicateRestaurant(name, restaurants, i);
                } catch (DuplicateEntityException e) {
                    isValid = false;
                    log.error(e.getMessage());
                    System.out.println(e.getMessage());
                }
            } while (!isValid);

            System.out.print("Enter address details below \n");
            Address address = Address.inputAddress(Long.valueOf(i+1), scanner);

            System.out.print("Enter the number of meals " + (i+1) + ". " + "restaurant has: ");
            int numberOfMeals = InputValidator.validatePositiveInteger(scanner, Messages.INVALID_NUMBER_OF_MEALS_INPUT);

            System.out.println("Avaliable meals: ");
            Meal[] helpmeals = DataInputUtils.inputMeals(scanner, meals, numberOfMeals, Messages.INVALID_INTEGER_INDEX_MEAL_INPUT);

            System.out.print("Enter the number of chefs " + (i+1) + ". " + "restaurant has: ");
            int numberOfChefs = InputValidator.validatePositiveInteger(scanner, Messages.INVALID_NUMBER_OF_CHEFS_INPUT);

            System.out.println("Avaliable chefs: ");
            Chef[] helpchefs = DataEmployeeInputUtils.selectChefs(scanner,chefs,numberOfChefs,Messages.INVALID_CHEF_INDEX_INPUT);

            System.out.print("Enter the number of waiters " + (i+1) + ". " + "restaurant has: ");
            int numberOfWaiters = InputValidator.validatePositiveInteger(scanner, Messages.INVALID_NUMBER_OF_WAITERS_INPUT);

            System.out.println("Avaliable waiters: ");
            Waiter[] helpwaiters = DataEmployeeInputUtils.selectWaiters(scanner, waiters, numberOfWaiters, Messages.INVALID_WAITER_INDEX_INPUT);

            System.out.print("Enter the number of deliverers " + (i+1) + ". " + "restaurant has: ");
            int numberOfDeliveries = InputValidator.validatePositiveInteger(scanner, Messages.INVALID_NUMBRR_OF_DELIVERERS_INPUT);

            System.out.println("Avaliable deliverers: ");
            Deliverer[] helpdeliverers = DataEmployeeInputUtils.selectDeliverers(scanner, deliverers, numberOfDeliveries, Messages.INVALID_DELIVERER_INDEX_INPUT);

            restaurants[i] = new Restaurant(Long.valueOf(i+1), name, address, helpmeals, helpchefs, helpwaiters, helpdeliverers);
        }
    }
}