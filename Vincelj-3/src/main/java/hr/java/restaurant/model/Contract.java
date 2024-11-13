package hr.java.restaurant.model;

import hr.java.restaurant.exception.NegativOrUnrealPrice;
import hr.java.utils.InputValidator;
import hr.java.utils.Messages;
import hr.java.utils.PriceCheck;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Contract {

    private static Logger log = LoggerFactory.getLogger(Contract.class);

    public static final String FULL_TIME = "FULL_TIME";
    public static final String PART_TIME = "PART_TIME";

    private BigDecimal salary;
    private LocalDate startDate;
    private LocalDate endDate;
    private String employmentType;

    public Contract(BigDecimal salary, LocalDate startDate, LocalDate endDate, String employmentType) {
        this.salary = salary;
        this.startDate = startDate;
        this.endDate = endDate;
        this.employmentType = employmentType;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public static Contract inputContract(Scanner scanner)
    {

        System.out.println("Enter the salary: ");
        Boolean isValid;
        BigDecimal salary;

        do {
            isValid = true;
            salary = InputValidator.validatePositiveBigDecimal(scanner, Messages.INVALID_SALARY_INPUT);
            try {
                PriceCheck.checkSalary(salary);
            } catch (NegativOrUnrealPrice e) {
                isValid = false;
                log.error(e.getMessage());
                System.out.println(e.getMessage());
            }
        } while (!isValid);

        System.out.println("Enter the start date: (dd-MM-yyyy)");
        LocalDate startDate = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        LocalDate endDate;
        while (true)
        {
            System.out.print("Enter the end date (dd-MM-yyyy): ");
            endDate = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));

            if (!endDate.isBefore(startDate)) {
                break;
            } else {
                System.out.println("End date cannot be before the start date. Please enter a valid end date.");
            }
        }

        String employmentType;
        while (true) {
            System.out.print("Enter the employment type (FULL_TIME/PART_TIME): ");
            employmentType = scanner.nextLine();

            if (FULL_TIME.equals(employmentType) || PART_TIME.equals(employmentType)) {
                break;
            } else {
                System.out.println("Invalid input. Please enter either FULL_TIME or PART_TIME.");
            }
        }
        return new Contract(salary, startDate, endDate, employmentType);
    }
}
