package hr.java.restaurant.model;
import hr.java.restaurant.exception.DuplicateEntityException;
import hr.java.utils.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Restaurant extends Entity {
    public static final int MAX_LENGTH = 1;

    private static Logger log = LoggerFactory.getLogger(Restaurant.class);

    private String name;
    private Address address;
    private Set <Meal> meals;
    private Set <Chef> chefs;
    private Set <Waiter> waiters;
    private Set <Deliverer> deliverers;

    public Restaurant(Long id, String name, Address address, Set<Meal> meals, Set<Chef> chefs, Set<Waiter> waiters, Set<Deliverer> deliverers) {
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

    public int getTotalEmployees()
    {
        return chefs.size() + waiters.size() + deliverers.size();
    }

    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public Set<Meal> getMeals() {
        return meals;
    }
    public void setMeals(Set<Meal> meals) {
        this.meals = meals;
    }
    public Set<Chef> getChefs() {
        return chefs;
    }
    public void setChefs(Set<Chef> chefs) {
        this.chefs = chefs;
    }
    public Set<Waiter> getWaiters() {
        return waiters;
    }
    public void setWaiters(Set<Waiter> waiters) {
        this.waiters = waiters;
    }
    public Set<Deliverer> getDeliverers() {
        return deliverers;
    }
    public void setDeliverers(Set<Deliverer> deliverers) {
        this.deliverers = deliverers;
    }

    public static void inputRestaurant(Set<Restaurant> restaurants, Set<Meal> meals, Set<Chef> chefs, Set<Waiter> waiters, Set<Deliverer> deliverers, Scanner scanner){
        for (int i = 0; i < MAX_LENGTH; i++) {

            System.out.print("Enter the name of the " + (i + 1) + ". restaurant: ");
            String name = scanner.nextLine();

            System.out.print("Enter address details below \n");
            Address address = Address.inputAddress(Long.valueOf(i+1), scanner);

            System.out.print("Enter the number of meals " + (i+1) + ". " + "restaurant has: ");
            int numberOfMeals = InputValidator.validatePositiveInteger(scanner, Messages.INVALID_NUMBER_OF_MEALS_INPUT);

            System.out.println("Avaliable meals: ");
            Set <Meal> helpmeals = DataInputUtils.inputMeals(scanner, meals, numberOfMeals, Messages.INVALID_INTEGER_INDEX_MEAL_INPUT);

            System.out.print("Enter the number of chefs " + (i+1) + ". " + "restaurant has: ");
            int numberOfChefs = InputValidator.validatePositiveInteger(scanner, Messages.INVALID_NUMBER_OF_CHEFS_INPUT);

            System.out.println("Avaliable chefs: ");
            Set<Chef> helpchefs = DataEmployeeInputUtils.selectChefs(scanner, chefs, numberOfChefs, Messages.INVALID_CHEF_INDEX_INPUT);

            System.out.print("Enter the number of waiters " + (i+1) + ". " + "restaurant has: ");
            int numberOfWaiters = InputValidator.validatePositiveInteger(scanner, Messages.INVALID_NUMBER_OF_WAITERS_INPUT);

            System.out.println("Avaliable waiters: ");
            Set<Waiter> helpwaiters = DataEmployeeInputUtils.selectWaiters(scanner, waiters, numberOfWaiters, Messages.INVALID_WAITER_INDEX_INPUT);

            System.out.print("Enter the number of deliverers " + (i+1) + ". " + "restaurant has: ");
            int numberOfDeliveries = InputValidator.validatePositiveInteger(scanner, Messages.INVALID_NUMBRR_OF_DELIVERERS_INPUT);

            System.out.println("Avaliable deliverers: ");
            Set<Deliverer> helpdeliverers = DataEmployeeInputUtils.selectDeliverers(scanner, deliverers, numberOfDeliveries, Messages.INVALID_DELIVERER_INDEX_INPUT);

            restaurants.add(new Restaurant(Long.valueOf(i+1), name, address, helpmeals, helpchefs, helpwaiters, helpdeliverers));

        }
    }
}