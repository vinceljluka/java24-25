package hr.java.production.main;

import hr.java.restaurant.model.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MainFiles {
    public static void main(String[] args) {

        List <Category> categories = new ArrayList<>();
        categories = Category.loadCategoriesFromFile();

        List <Ingredient> ingredients = new ArrayList<>();
        ingredients = Ingredient.loadIngredientsFromFile(categories);

        List<Meal> meals = new ArrayList<>();
        meals = Meal.loadMealsFromFile(categories, ingredients);

        List<Contract> contracts = new ArrayList<>();
        contracts = Contract.loadContractsFromFile();

        List<Chef> chefs = new ArrayList<>();
        chefs = Chef.loadChefsFromFile(contracts);

        List<Deliverer> deliverers = new ArrayList<>();
        deliverers = Deliverer.loadDeliverersFromFile(contracts);

        List<Waiter> waiters = new ArrayList<>();
        waiters = Waiter.loadWaitersFromFile(contracts);

        List<Address> addresses = new ArrayList<>();
        addresses = Address.loadAddressesFromFile();

        List<Restaurant> restaurants = new ArrayList<>();
        restaurants = Restaurant.loadRestaurantsFromFile(addresses, chefs, waiters, deliverers, meals);

        List<Order> orders = new ArrayList<>();
        orders = Order.loadOrdersFromFile(restaurants, meals, deliverers);

        //serializeObjects(restaurants, orders, contracts);
        //deserializeObjects();

    }

    public static void serializeObjects(List<Restaurant> restaurants, List<Order> orders, List<Contract> contracts)
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Path.of("dat/serialized_data.dat"))))
        {
            oos.writeObject(restaurants);
            oos.writeObject(orders);
            oos.writeObject(contracts);
            System.out.println("Objects have been serialized successfully.");
        } catch (IOException e) {
            System.err.println("Error during serialization: " + e.getMessage());
        }
    }

    public static void deserializeObjects() {
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Path.of("dat/serialized_data.dat")))) {
            List<Restaurant> restaurants = (List<Restaurant>) ois.readObject();
            List<Order> orders = (List<Order>) ois.readObject();
            List<Contract> contracts = (List<Contract>) ois.readObject();

            System.out.println("Objects have been deserialized successfully.");
            System.out.println("Restaurants: " + restaurants.toString());
            System.out.println("Orders: " + orders.toString());
            System.out.println("Contracts: " + contracts.toString());
        } catch (IOException | ClassNotFoundException e)
        {
            System.err.println("Error during deserialization: " + e.getMessage());
        }
    }

}
