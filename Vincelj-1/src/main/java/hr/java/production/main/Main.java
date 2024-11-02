package hr.java.production.main;

import hr.java.restaurant.model.*;
import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static hr.java.restaurant.model.Category.inputCategory;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Category[] categories = new Category[3];
        Ingredient[] ingredients = new Ingredient[5];
        Meal[] meals = new Meal[3];
        Chef[] chefs = new Chef[3];
        Waiter[] waiters = new Waiter[3];
        Deliverer[] deliverers = new Deliverer[3];
        Restaurant[] restaurants = new Restaurant[3];
        Order[] orders = new Order[3];

        Integer total = 50;


        int[] helpDelivery = {0, 0, 0};

        for (int i = 0; i < categories.length; i++)
        {
            categories[i] = inputCategory(scanner, i);
        }

        for (int i = 0; i < ingredients.length; i++)
        {
            ingredients[i] = inputIngredient(scanner, categories, i);
        }

       for (int i = 0; i < meals.length; i++)
       {
           meals[i] = inputMeal(scanner, categories, ingredients, i);
       }

       for (int i = 0; i < chefs.length; i++)
       {
           chefs[i] = inputChefs(scanner, i);
       }

       for (int i = 0; i < waiters.length; i++)
       {
           waiters[i] = inputWaiters(scanner, i);
       }

        for (int i = 0; i < deliverers.length; i++)
        {
            deliverers[i] = inputDeliverer(scanner, i);
        }

        for (int i = 0; i < restaurants.length; i++)
        {
            restaurants[i] = inputRestaurant(scanner, meals, chefs, waiters, deliverers, i);
        }

        for (int i = 0; i < orders.length; i++)
        {
            orders[i] = inputOrder(scanner, restaurants, meals, deliverers, helpDelivery, i);
        }

        returnRestaurant(orders);
        theBusiestDeliverer(orders, helpDelivery,deliverers);
    }


    public static Ingredient inputIngredient(Scanner scanner, Category[] categories, int index) {
        System.out.print("Enter " + (index+1) + "." +  " ingredient's name: ");
        String name = scanner.nextLine();

        System.out.print("Chose " + (index+1) +  "." + " ingredient's category (index 1, 2 or 3) : \n");
        for (int i = 0; i < categories.length; i++)
        {
            System.out.println((i+1) + "." + categories[i].getName());
        }

        int categoryIndex;
        while (true) {
            if (scanner.hasNextInt()) {
                categoryIndex = scanner.nextInt();
                scanner.nextLine();
                if (categoryIndex >= 1 && categoryIndex <= categories.length) {
                    break;
                } else {
                    System.out.println("Invalid input, please enter a category index between 1 and " + categories.length + ".");
                }
            } else {
                System.out.println("Invalid input, please enter a valid integer for category index.");
                scanner.nextLine();
            }
        }
        Category category = categories[categoryIndex - 1];


        System.out.print("Enter " + (index+1) + "." + " ingredient's calories: ");
        BigDecimal kcal;
        while (true) {
            if (scanner.hasNextBigDecimal()) {
                kcal = scanner.nextBigDecimal();
                scanner.nextLine();
                if (kcal.compareTo(BigDecimal.ZERO) > 0) {
                    break;
                } else {
                    System.out.println("Calories must be greater than 0. Please enter a valid number.");
                }
            } else {
                System.out.println("Invalid input, please enter a valid number for calories.");
                scanner.nextLine();
            }
        }
        System.out.print("Enter preparation method for "  + (index+1) + "." + " ingredient: ");
        String preparationMethod = scanner.nextLine();

        System.out.print("Enter current quantity");
        Integer quantity = scanner.nextInt();
        scanner.nextLine();

        return new Ingredient(name, category, kcal, preparationMethod, quantity);
    }



    public static Meal inputMeal(Scanner scanner, Category[] categories, Ingredient[] ingredients, int index) {
        System.out.print("Enter " + (index+1) + "." +  " meal's name: ");
        String name = scanner.nextLine();

        System.out.print("Chose " + (index+1) +  "." + " meal's category (index 1, 2 or 3) : \n");
        for (int i = 0; i < categories.length; i++)
        {
            System.out.println((i+1) + "." + categories[i].getName());
        }

        int categoryIndex;
        while (true) {
            if (scanner.hasNextInt()) {
                categoryIndex = scanner.nextInt();
                scanner.nextLine();
                if (categoryIndex >= 1 && categoryIndex <= categories.length) {
                    break;
                } else {
                    System.out.println("Invalid input, please enter a category index between 1 and " + categories.length + ".");
                }
            } else {
                System.out.println("Invalid input, please enter a valid integer for category index.");
                scanner.nextLine();
            }
        }
        Category category = categories[categoryIndex - 1];

        System.out.print("Enter number of ingredients needed for this meal: ");
        int numberOfIngredients;
        while (true) {
            if (scanner.hasNextInt()) {
                numberOfIngredients = scanner.nextInt();
                scanner.nextLine();
                if (numberOfIngredients > 0) {
                    break;
                } else {
                    System.out.println("Number of ingredients must be greater than 0.");
                }
            } else {
                System.out.println("Invalid input, please enter a valid integer for number of ingredients.");
                scanner.nextLine();
            }
        }

        Ingredient[] helpArray = new Ingredient[numberOfIngredients];
        for (int i = 0; i < ingredients.length; i++)
        {
            System.out.println((i+1) + "." + ingredients[i].getName());
        }

        for (int i = 0; i < numberOfIngredients; i++)
        {
            System.out.print("Choose ingredient " + (i + 1) + ": ");
            int ingredientIndex = scanner.nextInt();
            scanner.nextLine();
            helpArray[i] = ingredients[ingredientIndex - 1];
            ingredients[ingredientIndex - 1].decreaseQuantityOnStock();
        }


        System.out.print("Enter the price of " + (index+1) + "." + " meal : ");
        BigDecimal price;
        while (true) {
            if (scanner.hasNextBigDecimal()) {
                price = scanner.nextBigDecimal();
                scanner.nextLine();
                if (price.compareTo(BigDecimal.ZERO) >= 0) {
                    break;
                } else {
                    System.out.println("Price cannot be negative. Please enter a valid price.");
                }
            } else {
                System.out.println("Invalid input, please enter a valid number for price.");
                scanner.nextLine();
            }
        }
        return new Meal(name, category, helpArray, price);
    }

    public static Chef inputChefs(Scanner scanner, int index) {
        System.out.print("Enter " + (index+1) + "." +  " chef's first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter " + (index+1) + "." +  " chef's last name: ");
        String lastName = scanner.nextLine();

        BigDecimal salary;
        while (true) {
            System.out.print("Enter " + (index+1) + "." + " chef's salary: ");
            if (scanner.hasNextBigDecimal()) {
                salary = scanner.nextBigDecimal();
                scanner.nextLine();
                if (salary.compareTo(BigDecimal.ZERO) >= 0) {
                    break;
                } else {
                    System.out.println("Salary cannot be negative. Please enter a valid salary.");
                }
            } else {
                System.out.println("Invalid input, please enter a valid salary.");
                scanner.nextLine();
            }
        }
        return new Chef(firstName, lastName, salary);
    }

    public static Waiter inputWaiters(Scanner scanner, int index) {
        System.out.print("Enter " + (index+1) + "." +  " waiter's first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter " + (index+1) + "." +  " waiter's last name: ");
        String lastName = scanner.nextLine();

        BigDecimal salary;
        while (true) {
            System.out.print("Enter " + (index+1) + "." + " waiters's salary: ");
            if (scanner.hasNextBigDecimal()) {
                salary = scanner.nextBigDecimal();
                scanner.nextLine();
                if (salary.compareTo(BigDecimal.ZERO) >= 0) {
                    break;
                } else {
                    System.out.println("Salary cannot be negative. Please enter a valid salary.");
                }
            } else {
                System.out.println("Invalid input, please enter a valid salary.");
                scanner.nextLine();
            }
        }
        return new Waiter(firstName, lastName, salary);
    }

    public static Deliverer inputDeliverer(Scanner scanner, int index) {
        System.out.print("Enter " + (index+1) + "." +  " deliverers first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter " + (index+1) + "." +  " deliverers last name: ");
        String lastName = scanner.nextLine();

        BigDecimal salary;
        while (true) {
            System.out.print("Enter " + (index+1) + "." + " deliverer's salary: ");
            if (scanner.hasNextBigDecimal()) {
                salary = scanner.nextBigDecimal();
                scanner.nextLine();
                if (salary.compareTo(BigDecimal.ZERO) >= 0) {
                    break;
                } else {
                    System.out.println("Salary cannot be negative. Please enter a valid salary.");
                }
            } else {
                System.out.println("Invalid input, please enter a valid salary.");
                scanner.nextLine();
            }
        }
        return new Deliverer(firstName, lastName, salary);
    }

    public static Address inputAddress(Scanner scanner) {
        System.out.print("Enter the street: ");
        String streetAddress = scanner.nextLine();

        System.out.print("Enter the house number: ");
        String houseNumber = scanner.nextLine();

        System.out.print("Enter the city: ");
        String city = scanner.nextLine();

        System.out.print("Enter the postcode: ");
        String postalCode = scanner.nextLine();

        return new Address(streetAddress, houseNumber, city, postalCode);
    }

    public static Restaurant inputRestaurant(Scanner scanner, Meal[] meals, Chef[] chefs, Waiter[] waiters, Deliverer[] deliverers, int index) {
        System.out.print("Enter the name of the " + (index+1) + ". " + "restaurant: ");
        String name = scanner.nextLine();
        System.out.print("Enter address details below \n");
        Address address = inputAddress(scanner);

        System.out.print("Enter the number of meals " + (index+1) + ". " + "restaurant has: ");

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
        for (int i = 0; i < meals.length; i++)
        {
            System.out.println((i+1) + "." + meals[i].getName());
        }
        for (int i = 0; i < numberOfMeals; i++)
        {
            System.out.print("Choose meal " + (i + 1) + ": ");
            int mealIndex;
            while (true) {
                if (scanner.hasNextInt()) {
                    mealIndex = scanner.nextInt();
                    scanner.nextLine();
                    if (mealIndex >= 1 && mealIndex <= meals.length) {
                        helpmeals[i] = meals[mealIndex - 1];
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

        System.out.print("Enter the number of chefs " + (index+1) + ". " + "restaurant has: ");
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
        for (int i = 0; i < chefs.length; i++)
        {
            System.out.print((i+1) + ". " + chefs[i].getFirstName() + " " + chefs[i].getLastName() + "\n" );
        }

        for (int i = 0; i < numberOfChefs; i++)
        {
            System.out.print("Choose chef " + (i + 1) + ": ");
            int chefIndex;
            while (true) {
                if (scanner.hasNextInt()) {
                    chefIndex = scanner.nextInt();
                    scanner.nextLine();
                    if (chefIndex >= 1 && chefIndex <= chefs.length) {
                        helpchefs[i] = chefs[chefIndex - 1];
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

        System.out.print("Enter the number of waiters " + (index+1) + ". " + "restaurant has: ");
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
        for (int i = 0; i < waiters.length; i++)
        {
            System.out.print((i+1) + ". " + waiters[i].getFirstName() + " " + waiters[i].getLastName() + "\n" );
        }

        for (int i = 0; i < numberOfWaiters; i++)
        {
            System.out.print("Choose waiter " + (i + 1) + ": ");
            int waiterIndex;
            while (true) {
                if (scanner.hasNextInt()) {
                    waiterIndex = scanner.nextInt();
                    scanner.nextLine();
                    if (waiterIndex >= 1 && waiterIndex <= waiters.length) {
                        helpwaiters[i] = waiters[waiterIndex - 1];
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

        System.out.print("Enter the number of deliverers " + (index+1) + ". " + "restaurant has: ");
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
        for (int i = 0; i < deliverers.length; i++)
        {
            System.out.print((i+1) + ". " + deliverers[i].getFirstName() + " " + deliverers[i].getLastName() + "\n" );
        }

        for (int i = 0; i < numberOfDeliveries; i++)
        {
            System.out.print("Choose deliverer " + (i + 1) + ": ");
            int delivererIndex;
            while (true) {
                if (scanner.hasNextInt()) {
                    delivererIndex = scanner.nextInt();
                    scanner.nextLine();
                    if (delivererIndex >= 1 && delivererIndex <= deliverers.length) {
                        helpdeliverers[i] = deliverers[delivererIndex - 1];
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
        return new Restaurant(name, address, helpmeals, helpchefs, helpwaiters, helpdeliverers);
    }

    public static Order inputOrder(Scanner scanner, Restaurant[] restaurants, Meal[] meals, Deliverer[] deliverers, int[] helpDelivery, int index) {

        System.out.print("Choose a restaurant from which is your " + (index+1) + "." + " order (index 1,2,3): \n");
        for (int i = 0; i < restaurants.length; i++)
        {
            System.out.print((i+1) + ". " + restaurants[i].getName() + "\n");
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
        for (int i = 0; i < meals.length; i++)
        {
            System.out.println((i+1) + "." + meals[i].getName());
        }

        for (int i = 0; i < numberOfMeals; i++)
        {
            System.out.print("Choose meal " + (i + 1) + ": ");
            int mealIndex;
            while (true) {
                if (scanner.hasNextInt()) {
                    mealIndex = scanner.nextInt();
                    scanner.nextLine();
                    if (mealIndex >= 1 && mealIndex <= meals.length) {
                        helpmeals[i] = meals[mealIndex - 1];
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
        for (int i = 0; i < deliverers.length; i++)
        {
            System.out.print((i+1) + ". " + deliverers[i].getFirstName() + " " + deliverers[i].getLastName() + "\n");
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
        helpDelivery[delivererIndex-1]++;
        Deliverer helpdeliverer = deliverers[delivererIndex - 1];

        System.out.print("Enter the date and time of the order (format: dd-MM-yyyy HH:mm): ");
        String dateTimeInput = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime deliveryDateAndTime = LocalDateTime.parse(dateTimeInput, formatter);

        return new Order(helprestaurant, helpmeals, helpdeliverer, deliveryDateAndTime);
    }

    public static void printRestaurant (Restaurant restaurant, Meal[] meals, Chef[] chefs, Waiter[] waiters, Deliverer[] deliverers) {
        System.out.println("Name: " + restaurant.getName());
        System.out.println("Address: " + restaurant.getAddress().getStreet() + " " + restaurant.getAddress().getHouseNumber());
        for (Meal meal : restaurant.getMeals())
        {
            System.out.println("Meals: " + meal.getName());
        }
        for (Chef chef : restaurant.getChefs())
        {
            System.out.println("Chefs: " + chef.getFirstName() + " " + chef.getLastName());
        }
        for (Waiter waiter : restaurant.getWaiters())
        {
            System.out.println("Waiters: " + waiter.getFirstName() + " " + waiter.getLastName());
        }
        for (Deliverer deliverer : restaurant.getDeliverers())
        {
            System.out.println("Deliverers: " + deliverer.getFirstName() + " " + deliverer.getLastName());
        }
    }


    public static void returnRestaurant(Order[] orders)
    {
        BigDecimal sum = BigDecimal.valueOf(0);
        BigDecimal max = BigDecimal.valueOf(0);

        int index = 0;

        for (int i = 0; i < orders.length; i++)
        {
            for (int j = 0; j < orders[i].getMeals().length; j++)
            {
                sum = sum.add(orders[i].getMeals()[j].getPrice());
            }
            if (sum.compareTo(max) > 0) {
                max = sum;
                index = i;
            }
            sum = BigDecimal.valueOf(0);
        }

        BigDecimal helpSum = BigDecimal.valueOf(0);

        for (int i = index; i < orders.length; i++)

        {
            for (int j = 0; j < orders[i].getMeals().length; j++)
            {
                helpSum = helpSum.add(orders[i].getMeals()[j].getPrice());
            }
            if (helpSum.compareTo(max) == 0)
            {

                printRestaurant(orders[i].getRestaurant(), orders[i].getRestaurant().getMeals(), orders[i].getRestaurant().getChefs(), orders[i].getRestaurant().getWaiters(), orders[i].getRestaurant().getDeliverers());
            }
            helpSum = BigDecimal.valueOf(0);
        }
    }

    public static void theBusiestDeliverer(Order[] orders, int[] helpDelivery, Deliverer[] deliverers)
    {
        int max = 0;
        int index = 0;
        for (int i = 0; i < helpDelivery.length; i++) {
            if (helpDelivery[i] > max) {
                max = helpDelivery[i];
                index = i;
            }
        }
        System.out.println("\nMost delivered order(s) are delivered by:");
        for(int i = index; i< helpDelivery.length; i++) {
            if(helpDelivery[i] == max) {
                System.out.println(deliverers[i].getFirstName() + " " + deliverers[i].getLastName());
            }
        }
    }
}