package hr.java.restaurant.model;
import java.util.Scanner;

public class Restaurant extends Entity {
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
            System.out.print("Enter the name of the " + (i+1) + ". " + "restaurant: ");
            String name = scanner.nextLine();

            System.out.print("Enter address details below \n");
            Address address = Address.inputAddress(Long.valueOf(i+1), scanner);

            System.out.print("Enter the number of meals " + (i+1) + ". " + "restaurant has: ");

            int numberOfMeals;
            while (true) {
                if (scanner.hasNextInt()) {
                    numberOfMeals = scanner.nextInt();
                    scanner.nextLine();
                    if (numberOfMeals > 0) {
                        break;
                    } else {
                        System.out.println("Number of meals must be greater than 0.");
                    }
                } else {
                    System.out.println("Invalid input, please enter a valid integer for the number of meals.");
                    scanner.nextLine();
                }
            }
            Meal[] helpmeals = new Meal[numberOfMeals];
            System.out.print("Avaliable meals: \n");
            for (int j = 0; j < meals.length; j++) {
                System.out.println((j+1) + "." + meals[j].getName());
            }
            for (int j = 0; j < numberOfMeals; j++) {
                System.out.print("Choose meal " + (j + 1) + ": ");
                int mealIndex;
                while (true) {
                    if (scanner.hasNextInt()) {
                        mealIndex = scanner.nextInt();
                        scanner.nextLine();
                        if (mealIndex >= 1 && mealIndex <= meals.length) {
                            helpmeals[j] = meals[mealIndex - 1];
                            break;
                        } else {
                            System.out.println("Invalid input, please enter a meal index between 1 and " + meals.length + ".");
                        }
                    } else {
                        System.out.println("Invalid input, please enter a valid integer for meal index.");
                        scanner.nextLine();
                    }
                }
            }
            System.out.print("Enter the number of chefs " + (i+1) + ". " + "restaurant has: ");
            int numberOfChefs;
            while (true) {
                if (scanner.hasNextInt()) {
                    numberOfChefs = scanner.nextInt();
                    scanner.nextLine();
                    if (numberOfChefs > 0) {
                        break;
                    } else {
                        System.out.println("Number of chefs must be greater than 0.");
                    }
                } else {
                    System.out.println("Invalid input, please enter a valid integer for the number of chefs.");
                    scanner.nextLine();
                }
            }
            Chef[] helpchefs = new Chef[numberOfChefs];
            System.out.print("Avaliable chefs: \n");
            for (int j = 0; j < chefs.length; j++) {
                System.out.print((j+1) + ". " + chefs[j].getFirstName() + " " + chefs[j].getLastName() + "\n" );
            }
            for (int j = 0; j < numberOfChefs; j++) {
                System.out.print("Choose chef " + (j + 1) + ": ");
                int chefIndex;
                while (true) {
                    if (scanner.hasNextInt()) {
                        chefIndex = scanner.nextInt();
                        scanner.nextLine();
                        if (chefIndex >= 1 && chefIndex <= chefs.length) {
                            helpchefs[j] = chefs[chefIndex - 1];
                            break;
                        } else {
                            System.out.println("Invalid input, please enter a chef index between 1 and " + chefs.length + ".");
                        }
                    } else {
                        System.out.println("Invalid input, please enter a valid integer for chef index.");
                        scanner.nextLine();
                    }
                }
            }
            System.out.print("Enter the number of waiters " + (i+1) + ". " + "restaurant has: ");
            int numberOfWaiters;
            while (true) {
                if (scanner.hasNextInt()) {
                    numberOfWaiters = scanner.nextInt();
                    scanner.nextLine();
                    if (numberOfWaiters > 0) {
                        break;
                    } else {
                        System.out.println("Number of waiters must be greater than 0.");
                    }
                } else {
                    System.out.println("Invalid input, please enter a valid integer for the number of waiters.");
                    scanner.nextLine();
                }
            }
            Waiter[] helpwaiters = new Waiter[numberOfWaiters];
            System.out.print("Avaliable waiters: \n");
            for (int j = 0; j < waiters.length; j++) {
                System.out.print((j+1) + ". " + waiters[j].getFirstName() + " " + waiters[j].getLastName() + "\n" );
            }
            for (int j = 0; j < numberOfWaiters; j++) {
                System.out.print("Choose waiter " + (j + 1) + ": ");
                int waiterIndex;
                while (true) {
                    if (scanner.hasNextInt()) {
                        waiterIndex = scanner.nextInt();
                        scanner.nextLine();
                        if (waiterIndex >= 1 && waiterIndex <= waiters.length) {
                            helpwaiters[j] = waiters[waiterIndex - 1];
                            break;
                        } else {
                            System.out.println("Invalid input, please enter a waiter index between 1 and " + waiters.length + ".");
                        }
                    } else {
                        System.out.println("Invalid input, please enter a valid integer for waiter index.");
                        scanner.nextLine();
                    }
                }
            }
            System.out.print("Enter the number of deliverers " + (i+1) + ". " + "restaurant has: ");
            int numberOfDeliveries;
            while (true) {
                if (scanner.hasNextInt()) {
                    numberOfDeliveries = scanner.nextInt();
                    scanner.nextLine();
                    if (numberOfDeliveries > 0) {
                        break;
                    } else {
                        System.out.println("Number of deliverers must be greater than 0.");
                    }
                } else {
                    System.out.println("Invalid input, please enter a valid integer for the number of deliverers.");
                    scanner.nextLine();
                }
            }
            Deliverer[] helpdeliverers = new Deliverer[numberOfDeliveries];
            System.out.print("Avaliable deliverers: \n");
            for (int j = 0; j < deliverers.length; j++)
            {
                System.out.print((j+1) + ". " + deliverers[j].getFirstName() + " " + deliverers[j].getLastName() + "\n" );
            }
            for (int j = 0; j < numberOfDeliveries; j++) {
                System.out.print("Choose deliverer " + (j + 1) + ": ");
                int delivererIndex;
                while (true) {
                    if (scanner.hasNextInt()) {
                        delivererIndex = scanner.nextInt();
                        scanner.nextLine();
                        if (delivererIndex >= 1 && delivererIndex <= deliverers.length) {
                            helpdeliverers[j] = deliverers[delivererIndex - 1];
                            break;
                        } else {
                            System.out.println("Invalid input, please enter a deliverer index between 1 and " + deliverers.length + ".");
                        }
                    } else {
                        System.out.println("Invalid input, please enter a valid integer for deliverer index.");
                        scanner.nextLine();
                    }
                }
            }
            restaurants[i] = new Restaurant(Long.valueOf(i+1), name, address, helpmeals, helpchefs, helpwaiters, helpdeliverers);
        }
    }
}