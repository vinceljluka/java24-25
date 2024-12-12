package hr.java.restaurant.sort;

import hr.java.restaurant.model.Person;
import hr.java.restaurant.model.Restaurant;

import java.util.*;

public class EmployeesPayComparator {
    public static void printHighestPaidEmployees(Set<Restaurant> restaurants) {

        for (Restaurant restaurant : restaurants)
        {
            Set<Person> allEmployees = new HashSet<>();
            allEmployees.addAll(restaurant.getChefs());
            allEmployees.addAll(restaurant.getWaiters());
            allEmployees.addAll(restaurant.getDeliverers());

            List<Person> sortedEmployees =
                    allEmployees.stream()
                    .sorted((e1, e2) -> e2.getContract().getSalary().compareTo(e1.getContract().getSalary()))
                    .toList();

            if (!sortedEmployees.isEmpty()) {
                Person highestPaidEmployee = sortedEmployees.get(0);
                System.out.println("The employee with the highest salary in restaurant "
                        + restaurant.getName() + " is " + highestPaidEmployee.getFirstName() + " "
                        + highestPaidEmployee.getLastName() + " with a salary of "
                        + highestPaidEmployee.getContract().getSalary());
            }
        }

    }

}
