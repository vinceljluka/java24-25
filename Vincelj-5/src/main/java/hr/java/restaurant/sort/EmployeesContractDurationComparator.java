package hr.java.restaurant.sort;

import hr.java.restaurant.model.Person;
import hr.java.restaurant.model.Restaurant;

import java.time.temporal.ChronoUnit;
import java.util.*;

public class EmployeesContractDurationComparator {
    public static void printSortedEmployeesByContractDuration(Set<Restaurant> restaurants)
    {
        for (Restaurant restaurant : restaurants)
        {
            Set<Person> allEmployees = new HashSet<>();
            allEmployees.addAll(restaurant.getChefs());
            allEmployees.addAll(restaurant.getWaiters());
            allEmployees.addAll(restaurant.getDeliverers());

            List<Person> sortedEmployees = allEmployees.stream()
                    .sorted((p1, p2) -> Long.compare(
                            ChronoUnit.DAYS.between(p1.getContract().getStartDate(), p1.getContract().getEndDate()),
                            ChronoUnit.DAYS.between(p2.getContract().getStartDate(), p2.getContract().getEndDate())
                    ))
                    .toList();

            System.out.println("Sorted employees by contract duration in restaurant " + restaurant.getName() + ":");
            for (Person employee : sortedEmployees)
            {
                long contractDuration = employee.getContract().getStartDate().until(employee.getContract().getEndDate(), java.time.temporal.ChronoUnit.DAYS);
                System.out.println(employee.getFirstName() + " " + employee.getLastName() + ": " + contractDuration + " days");
            }
        }
    }

}
