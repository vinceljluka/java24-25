package hr.javafx.vinceljfx7.restaurant.model;

import hr.javafx.vinceljfx7.service.Output;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Represents an order.
 */
public class Order extends Entity implements Serializable {

    private Restaurant restaurant;
    private List<Meal> meals;
    private final Deliverer deliverer;
    private final LocalDateTime deliveryDateAndTime;

    /**
     * Constructs an Order object.
     * @param restaurant the restaurant
     * @param meals the meals
     * @param deliverer the deliverer
     * @param deliveryDateAndTime the delivery date and time
     */
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
        return this.meals;
    }

    public Deliverer getDeliverer() {
        return deliverer;
    }

    /**
     * Prints order
     */
    public void print() {
        Output.tabulatorPrint(1);
        System.out.println("Id: " + this.getId());

        Output.tabulatorPrint(1);
        System.out.println("Restoran:");
        this.restaurant.print(2);

        Output.tabulatorPrint(1);
        System.out.println("Naručena jela:");
        for(int i=0;i<this.meals.size();i++) {
            Output.tabulatorPrint(2);
            System.out.println("Jelo "+(i+1)+":");
            this.meals.get(i).print(3);
        }

        Output.tabulatorPrint(1);
        System.out.print("Dostavljač narudžbe: ");
        this.deliverer.print(0);
        Output.tabulatorPrint(1);
        System.out.print("Datum dostave: ");
        System.out.println(this.deliveryDateAndTime);
    }

    public static void serializeToFile(Set<Order> orders) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("dat/orders.dat"));
            out.writeObject(orders);
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Set<Order> deserializeFromFile() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("dat/orders.dat"));

            System.out.println(1);

            Set<Order> orders  = (Set<Order>) in.readObject();
            System.out.println("Podaci o pročitanom objektu:");
            for (Order order : orders) {
                order.print();
            }

            in.close();

            return orders;
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
