package hr.java.restaurant.model;

import hr.java.restaurant.enums.ContractType;
import hr.java.restaurant.exception.NegativOrUnrealPrice;
import hr.java.utils.InputValidator;
import hr.java.utils.Messages;
import hr.java.utils.PriceCheck;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Contract extends Entity implements Serializable {

    private static Logger log = LoggerFactory.getLogger(Contract.class);

    private BigDecimal salary;
    private LocalDate startDate;
    private LocalDate endDate;
    private ContractType employmentType;

    private static final String CONTRACTS_FILE_PATH = "dat/contracts.txt";

    public Contract(Long id, BigDecimal salary, LocalDate startDate, LocalDate endDate, ContractType employmentType) {
        super(id);
        if (salary.compareTo(BigDecimal.ZERO) == 0) {
            this.salary = null;
        } else {
            this.salary = salary;
        }
        this.startDate = startDate;
        this.endDate = endDate;
        this.employmentType = employmentType;
    }

    public ContractType getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(ContractType employmentType) {
        this.employmentType = employmentType;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        if (salary.compareTo(BigDecimal.ZERO) == 0) {
            this.salary = null;
        } else {
            this.salary = salary;
        }
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

    public void displayContractDetails() {
        String salaryOutput = Optional.ofNullable(salary)
                .map(s -> s.toString())
                .orElse("potrebno definirati plaću");
        System.out.println("Employment Type: " + employmentType);
        System.out.println("Salary: " + salaryOutput);
        System.out.println("Start Date: " + startDate);
        System.out.println("End Date: " + endDate);
    }

    public static List<Contract> loadContractsFromFile() {
        List<Contract> contracts = new ArrayList<>();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try {
            List<String> fileLines = Files.readAllLines(Path.of(CONTRACTS_FILE_PATH));

            for (int i = 0; i < fileLines.size(); i += 5) {

                Long id = Long.parseLong(fileLines.get(i).trim());
                BigDecimal salary = new BigDecimal(fileLines.get(i + 1).trim());

                LocalDate startDate = LocalDate.parse(fileLines.get(i + 2).trim(), dateFormatter);
                LocalDate endDate = LocalDate.parse(fileLines.get(i + 3).trim(), dateFormatter);

                int contractTypeChoice = Integer.parseInt(fileLines.get(i + 4).trim());

                ContractType employmentType = (contractTypeChoice == 1) ? ContractType.FULL_TIME : ContractType.PART_TIME;

                contracts.add(new Contract(id, salary, startDate, endDate, employmentType));
            }
        } catch (IOException e) {
            log.error("Error reading contracts from file", e);
        } catch (NumberFormatException | DateTimeParseException e) {
            log.error("Invalid data in contract file", e);
        }
        return contracts;
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

        ContractType employmentType;
        while (true) {
            System.out.print("Enter the employment type (FULL_TIME/PART_TIME): ");
            String employmentTypeInput = scanner.nextLine();
            try {
                employmentType = ContractType.valueOf(employmentTypeInput);
                break;
            } catch (IllegalArgumentException e)
            {
                System.out.println("Invalid input. Please enter either FULL_TIME or PART_TIME.");
            }
        }
        return new Contract(1L, salary, startDate, endDate, employmentType);
    }

    @Override
    public String toString() {
        return "Contract{" +
                "salary=" + salary +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", employmentType=" + employmentType +
                ", id=" + id +
                '}';
    }
}
