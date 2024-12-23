package hr.javafx.vinceljfx7.restaurant.model;

import hr.javafx.vinceljfx7.service.Output;
import java.io.Serializable;
import java.util.*;

/**
 * Represents a restaurant.
 */
public class Restaurant extends Entity implements Serializable {

    private String name;
    private Address address;
    private final Set<Meal> meals;
    private final Set<Chef> chefs;
    private final Set<Waiter> waiters;
    private final Set<Deliverer> deliverers;

    /**
     * Constructs a Restaurant object from provided data.
     * @param name the name of the restaurant
     * @param address the address of the restaurant
     * @param meals the meals in the restaurant
     * @param chefs the chefs in the restaurant
     * @param waiters the waiters in the restaurant
     * @param deliverers the deliverers in the restaurant
     */
    public Restaurant(Long id, String name, Address address, Set<Meal> meals, Set<Chef> chefs, Set<Waiter> waiters, Set<Deliverer> deliverers) {
        super(id);
        this.name = name;
        this.address = address;
        this.meals = meals;
        this.chefs = chefs;
        this.waiters = waiters;
        this.deliverers = deliverers;
    }

    public Address getAddress() {
        return address;
    }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Set<Meal> getMeals() { return meals; }
    public Set<Deliverer> getDeliverers() { return deliverers; }
    public Set<Chef> getChefs() { return chefs; }
    public Set<Waiter> getWaiters() { return waiters; }
    public void setAddress(Address address) { this.address = address; }
    public void setMeals(Set<Meal> meals) { this.meals.clear(); }
    public void setDeliverers(Set<Deliverer> deliverers) { this.deliverers.clear(); }
    public void setChefs(Set<Chef> chefs) { this.chefs.clear(); }
    public void setWaiters(Set<Waiter> waiters) { this.waiters.clear(); }


    /**
     * Finds the number of meals in the restaurant.
     * @param tabulators the number of tabulators before the output
     */
    public void print(Integer tabulators) {

        Output.tabulatorPrint(tabulators);
        System.out.println("Id: " + this.getId());

        Output.tabulatorPrint(tabulators);
        System.out.println("Naziv restorana: " + this.name);
        Output.tabulatorPrint(tabulators);
        System.out.println("Adresa restorana: ");
        address.print(tabulators+1);

        Output.tabulatorPrint(tabulators);
        System.out.println("Sva jela u restoranu:");
        int i=0;
        for(Meal meal : this.meals) {
            Output.tabulatorPrint(tabulators+1);
            System.out.println("Jelo "+(i+1)+":");
            meal.print(tabulators+2);
            i++;
        }

        Output.tabulatorPrint(tabulators);
        System.out.println("Svi kuhari u restoranu:");
        i=0;
        for(Chef chef : this.chefs) {
            Output.tabulatorPrint(tabulators+1);
            System.out.println("Kuhar "+(i+1)+":");
            chef.print(tabulators+2);
            i++;
        }

        Output.tabulatorPrint(tabulators);
        System.out.println("Svi konobari u restoranu:");
        i=0;
        for (Waiter waiter : this.waiters) {
            Output.tabulatorPrint(tabulators+1);
            System.out.println("Konobar "+(i+1)+":");
            waiter.print(tabulators+2);
            i++;
        }

        Output.tabulatorPrint(tabulators);
        System.out.println("Svi dostavljači u restoranu:");
        i=0;
        for(Deliverer deliverer : this.deliverers) {
            Output.tabulatorPrint(tabulators+1);
            System.out.println("Dostavljač "+(i+1)+":");
            deliverer.print(tabulators+2);
            i++;
        }
    }
}