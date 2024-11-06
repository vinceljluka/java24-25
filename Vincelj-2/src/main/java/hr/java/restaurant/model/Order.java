package hr.java.restaurant.model;
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
        for (int i = 0; i < orders.length; i++) {
            System.out.print("Choose a restaurant from which is your " + (i+1) + "." + " order (index 1,2,3): \n");
            for (int j = 0; j < restaurants.length; j++) {
                System.out.print((j+1) + ". " + restaurants[j].getName() + "\n");
            }

            int restaurantIndex;
            while (true) {
                if (scanner.hasNextInt()) {
                    restaurantIndex = scanner.nextInt();
                    scanner.nextLine();
                    if (restaurantIndex >= 1 && restaurantIndex <= restaurants.length) {
                        break;
                    } else {
                        System.out.println("Invalid index. Please choose a valid restaurant index.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine();
                }
            }
            Restaurant helprestaurant = restaurants[restaurantIndex - 1];

            System.out.print("Enter the number of meals in the order: ");
            int numberOfMeals;
            while (true) {
                if (scanner.hasNextInt()) {
                    numberOfMeals = scanner.nextInt();
                    scanner.nextLine();
                    if (numberOfMeals > 0) {
                        break;
                    } else {
                        System.out.println("Number of meals must be greater than 0. Please try again.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine();
                }
            }
            Meal[] helpmeals = new Meal[numberOfMeals];
            System.out.print("Avaliable meals: \n");
            for (int j = 0; j < restaurants[i].getMeals().length; j++) {
                System.out.println((j+1) + "." + restaurants[i].getMeals()[j].getName());
            }
            for (int j = 0; j < numberOfMeals; j++) {
                System.out.print("Choose meal " + (j + 1) + ": ");
                int mealIndex;
                while (true) {
                    if (scanner.hasNextInt()) {
                        mealIndex = scanner.nextInt();
                        scanner.nextLine();
                        if (mealIndex >= 1 && mealIndex <= meals.length) {
                            helpmeals[j] = restaurants[i].getMeals()[mealIndex - 1];
                            break;
                        } else {
                            System.out.println("Invalid index. Please choose a valid meal index.");
                        }
                    } else {
                        System.out.println("Invalid input. Please enter a number.");
                        scanner.nextLine();
                    }
                }
            }
            System.out.print("Choose a deliverer thats delivering the order (index 1,2,3): \n");
            for (int j = 0; j < restaurants[i].getDeliverers().length; j++) {
                System.out.print((j+1) + ". " + restaurants[i].getDeliverers()[j].getFirstName() + " " + restaurants[i].getDeliverers()[j].getLastName() + "\n");
            }
            int delivererIndex;
            while (true) {
                if (scanner.hasNextInt()) {
                    delivererIndex = scanner.nextInt();
                    scanner.nextLine();
                    if (delivererIndex >= 1 && delivererIndex <= deliverers.length) {
                        break;
                    } else {
                        System.out.println("Invalid index. Please choose a valid deliverer index.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine();
                }
            }
            Deliverer helpdeliverer = restaurants[i].getDeliverers()[delivererIndex - 1];

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