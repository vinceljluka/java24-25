package hr.java.restaurant.model;

import java.math.BigDecimal;
import java.util.Scanner;

public class Chef {
    private String firstName;
    private String lastName;
    private BigDecimal salary;

    public Chef(String firstName, String lastName, BigDecimal salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public static void inputChef(Chef[] chefs, Scanner scanner) {
        for (int i = 0; i < chefs.length; i++) {
            System.out.print("Enter " + (i+1) + "." +  " chef's first name: ");
            String firstName = scanner.nextLine();
            System.out.print("Enter " + (i+1) + "." +  " chef's last name: ");
            String lastName = scanner.nextLine();

            BigDecimal salary;
            while (true) {
                System.out.print("Enter " + (i+1) + "." + " chef's salary: ");
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
            chefs[i] = new Chef(firstName, lastName, salary);
        }
    }

}
