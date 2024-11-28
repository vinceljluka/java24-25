package hr.java.restaurant.sort;

import hr.java.restaurant.model.Person;
import hr.java.restaurant.model.Restaurant;

import java.util.*;

public class EmployeesContractDurationComparator implements Comparator<Person> {
    public int compare(Person p1, Person p2) {
        long duration1 = p1.getContract().getStartDate().until(p1.getContract().getEndDate(), java.time.temporal.ChronoUnit.DAYS);
        long duration2 = p2.getContract().getStartDate().until(p2.getContract().getEndDate(), java.time.temporal.ChronoUnit.DAYS);
        return Long.compare(duration1, duration2);
    }

    public static void printSortedEmployeesByContractDuration(Set<Restaurant> restaurants)
    {
        for (Restaurant restaurant : restaurants) {
            Set<Person> allEmployees = new HashSet<>();
            allEmployees.addAll(restaurant.getChefs());
            allEmployees.addAll(restaurant.getWaiters());
            allEmployees.addAll(restaurant.getDeliverers());

            List<Person> sortedEmployees = new ArrayList<>(allEmployees);

            sortedEmployees.sort(new EmployeesContractDurationComparator());

            System.out.println("Sorted employees by contract duration in restaurant " + restaurant.getName() + ":");

            for (Person employee : sortedEmployees) {
                long contractDuration = employee.getContract().getStartDate().until(employee.getContract().getEndDate(), java.time.temporal.ChronoUnit.DAYS);
                System.out.println(employee.getFirstName() + " " + employee.getLastName() + ": " + contractDuration + " days");
            }
        }
    }

}
