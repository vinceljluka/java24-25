package hr.java.utils;

import hr.java.restaurant.model.Chef;
import hr.java.restaurant.model.Deliverer;
import hr.java.restaurant.model.Restaurant;
import hr.java.restaurant.model.Waiter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DataEmployeeInputUtils {

    private static Logger log = LoggerFactory.getLogger(DataEmployeeInputUtils.class);

    public static Chef[] selectChefs(Scanner scanner, Chef[] chefs, int numberOfChefs, String errorMessage) {
        Chef[] selectedChefs = new Chef[numberOfChefs];

        for (int i = 0; i < chefs.length; i++) {
            System.out.println((i + 1) + ". " + chefs[i].getFirstName() + " " + chefs[i].getLastName());
        }

        for (int i = 0; i < numberOfChefs; i++) {
            System.out.print("Choose chef " + (i + 1) + ": ");
            int chefIndex;
            boolean isValid;

            do {
                isValid = true;
                try {
                    chefIndex = scanner.nextInt();
                    scanner.nextLine();
                    if (chefIndex < 1 || chefIndex > chefs.length) {
                        System.out.println(errorMessage);
                        isValid = false;
                        scanner.nextLine();
                    } else {
                        selectedChefs[i] = chefs[chefIndex - 1];
                    }
                } catch (InputMismatchException e) {
                    log.error(errorMessage);
                    System.out.println(errorMessage);
                    isValid = false;
                    scanner.nextLine();
                } catch (IndexOutOfBoundsException e) {
                    log.error(errorMessage);
                    System.out.println(errorMessage);
                    isValid = false;
                    scanner.nextLine();
                }
            } while (!isValid);
        }
        return selectedChefs;
    }

    public static Waiter[] selectWaiters(Scanner scanner, Waiter[] waiters, int numberOfWaiters, String errorMessage) {
        Waiter[] selectedWaiters = new Waiter[numberOfWaiters];

        for (int i = 0; i < waiters.length; i++) {
            System.out.println((i + 1) + ". " + waiters[i].getFirstName() + " " + waiters[i].getLastName());
        }

        for (int i = 0; i < numberOfWaiters; i++) {
            System.out.print("Choose waiter " + (i + 1) + ": ");
            int waiterIndex;
            boolean isValid;

            do {
                isValid = true;
                try {
                    waiterIndex = scanner.nextInt();
                    scanner.nextLine();
                    if (waiterIndex < 1 || waiterIndex > waiters.length) {
                        System.out.println(errorMessage);
                        isValid = false;
                    } else {
                        selectedWaiters[i] = waiters[waiterIndex - 1];
                    }
                } catch (InputMismatchException e) {
                    log.error(errorMessage);
                    System.out.println(errorMessage);
                    isValid = false;
                    scanner.nextLine();
                } catch (IndexOutOfBoundsException e) {
                    log.error(errorMessage);
                    System.out.println(errorMessage);
                    isValid = false;
                    scanner.nextLine();
                }
            } while (!isValid);
        }
        return selectedWaiters;
    }

    public static Deliverer[] selectDeliverers(Scanner scanner, Deliverer[] deliverers, int numberOfDeliveries, String errorMessage) {
        Deliverer[] selectedDeliverers = new Deliverer[numberOfDeliveries];

        for (int i = 0; i < deliverers.length; i++) {
            System.out.println((i + 1) + ". " + deliverers[i].getFirstName() + " " + deliverers[i].getLastName());
        }

        for (int i = 0; i < numberOfDeliveries; i++) {
            System.out.print("Choose deliverer " + (i + 1) + ": ");
            int delivererIndex;
            boolean isValid;

            do {
                isValid = true;
                try {
                    delivererIndex = scanner.nextInt();
                    scanner.nextLine();
                    if (delivererIndex < 1 || delivererIndex > deliverers.length) {
                        System.out.println(errorMessage);
                        isValid = false;
                    } else {
                        selectedDeliverers[i] = deliverers[delivererIndex - 1];
                    }
                } catch (InputMismatchException e) {
                    log.error(errorMessage);
                    System.out.println(errorMessage);
                    isValid = false;
                    scanner.nextLine();
                } catch (IndexOutOfBoundsException e) {
                    log.error(errorMessage);
                    System.out.println(errorMessage);
                    isValid = false;
                    scanner.nextLine();
                }
            } while (!isValid);
        }
        return selectedDeliverers;
    }

    public static Deliverer inputDelivererFromRestaurant(Restaurant restaurant, Scanner scanner, String errorMessage) {
        Integer delivererIndex;
        Boolean isValid;
        Deliverer deliverer = null;

        do {
            try {
                isValid = true;
                delivererIndex = scanner.nextInt();
                scanner.nextLine();
                deliverer = restaurant.getDeliverers()[delivererIndex - 1];
            } catch (InputMismatchException e) {
                log.error(errorMessage);
                isValid = false;
                System.out.println(errorMessage);
                scanner.nextLine();
            } catch (IndexOutOfBoundsException e) {
                log.error(errorMessage);
                isValid = false;
                System.out.println(errorMessage);
                scanner.nextLine();
            }
        } while (!isValid);

        return deliverer;
    }


}
