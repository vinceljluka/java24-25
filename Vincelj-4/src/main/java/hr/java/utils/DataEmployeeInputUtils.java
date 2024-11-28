package hr.java.utils;

import hr.java.restaurant.model.Chef;
import hr.java.restaurant.model.Deliverer;
import hr.java.restaurant.model.Restaurant;
import hr.java.restaurant.model.Waiter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class DataEmployeeInputUtils {

    private static Logger log = LoggerFactory.getLogger(DataEmployeeInputUtils.class);

    public static Set<Chef> selectChefs(Scanner scanner, Set<Chef> chefs, int numberOfChefs, String errorMessage) {
        Set<Chef> selectedChefs = new HashSet<>();
        List<Chef> chefList = new ArrayList<>(chefs);

        for (int i = 0; i < chefList.size(); i++) {
            System.out.println((i + 1) + ". " + chefList.get(i).getFirstName() + " " + chefList.get(i).getLastName());
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
                    if (chefIndex < 1 || chefIndex > chefList.size()) {
                        System.out.println(errorMessage);
                        isValid = false;
                        scanner.nextLine();
                    } else {
                        selectedChefs.add(chefList.get(chefIndex - 1));
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

    public static Set<Waiter> selectWaiters(Scanner scanner, Set<Waiter> waiters, int numberOfWaiters, String errorMessage) {
        Set<Waiter> selectedWaiters = new HashSet<>();
        List<Waiter> waiterList = new ArrayList<>(waiters);

        for (int i = 0; i < waiterList.size(); i++) {
            System.out.println((i + 1) + ". " + waiterList.get(i).getFirstName() + " " + waiterList.get(i).getLastName());
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
                    if (waiterIndex < 1 || waiterIndex > waiterList.size()) {
                        System.out.println(errorMessage);
                        isValid = false;
                    } else {
                        selectedWaiters.add(waiterList.get(waiterIndex - 1));
                        isValid = true;
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

    public static Set<Deliverer> selectDeliverers(Scanner scanner, Set<Deliverer> deliverers, int numberOfDeliveries, String errorMessage) {
        Set<Deliverer> selectedDeliverers = new HashSet<>();
        List<Deliverer> delivererList = new ArrayList<>(deliverers);

        for (int i = 0; i < delivererList.size(); i++) {
            System.out.println((i + 1) + ". " + delivererList.get(i).getFirstName() + " " + delivererList.get(i).getLastName());
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
                    if (delivererIndex < 1 || delivererIndex > delivererList.size()) {
                        System.out.println(errorMessage);
                        isValid = false;
                    } else {
                        selectedDeliverers.add(delivererList.get(delivererIndex - 1));
                        isValid = true;
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

        List<Deliverer> delivererList = new ArrayList<>(restaurant.getDeliverers());

        do {
            for (int i = 0; i < delivererList.size(); i++) {
                System.out.println((i + 1) + ". " + delivererList.get(i).getFirstName() + " " + delivererList.get(i).getLastName());
            }

            System.out.print("Choose deliverer: ");
            try {
                delivererIndex = scanner.nextInt();
                scanner.nextLine();
                if (delivererIndex < 1 || delivererIndex > delivererList.size()) {
                    System.out.println(errorMessage);
                    isValid = false;
                } else {
                    deliverer = delivererList.get(delivererIndex - 1);
                    isValid = true;
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

        return deliverer;
    }
}
