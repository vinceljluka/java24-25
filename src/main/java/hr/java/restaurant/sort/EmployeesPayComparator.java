package hr.java.restaurant.sort;

import hr.java.restaurant.model.Person;
import hr.java.restaurant.model.Restaurant;

import java.util.*;

public class EmployeesPayComparator implements Comparator<Person> {
    @Override
    public int compare(Person e1, Person e2) {
        return e2.getContract().getSalary().compareTo(e1.getContract().getSalary());
    }

    public static void printHighestPaidEmployees(Set<Restaurant> restaurants) {
        for (Restaurant restaurant : restaurants) {
            Set<Person> allEmployees = new HashSet<>();
            allEmployees.addAll(restaurant.getChefs());
            allEmployees.addAll(restaurant.getWaiters());
            allEmployees.addAll(restaurant.getDeliverers());

            List<Person> sortedEmployees = new ArrayList<>(allEmployees);
            sortedEmployees.sort(new EmployeesPayComparator());

            if (!sortedEmployees.isEmpty()) {
                Person highestPaidEmployee = sortedEmployees.get(0);
                System.out.println("The employee with the highest salary in restaurant " + restaurant.getName() + " is " + highestPaidEmployee.getFirstName() + " " + highestPaidEmployee.getLastName() + " with a salary of " + highestPaidEmployee.getContract().getSalary());
            }
        }
    }

}
