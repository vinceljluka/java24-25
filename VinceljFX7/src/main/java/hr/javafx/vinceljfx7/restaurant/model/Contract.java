package hr.javafx.vinceljfx7.restaurant.model;

import hr.javafx.vinceljfx7.restaurant.enums.ContractType;
import hr.javafx.vinceljfx7.service.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Represents a contract of a person.
 */
public class Contract implements Serializable {

    private BigDecimal salary;
    private LocalDate startDate;
    private LocalDate endDate;
    private ContractType contractType;

    /**
     * Constructs a Contract object using the provided builder.
     * @param salary the salary of the contract
     * @param startDate the start date of the contract
     * @param endDate  the end date of the contract
     * @param contractType the type of the contract
     */
    public Contract(BigDecimal salary, LocalDate startDate, LocalDate endDate, ContractType contractType) {
        this.salary = salary;
        this.startDate = startDate;
        this.endDate = endDate;
        this.contractType = contractType;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public ContractType getContractType() {
        return contractType;
    }


    /**
     * Prints the contract information.
     * @param tabulators the number of tabulators
     */
    public void print(Integer tabulators) {
        Output.tabulatorPrint(tabulators);
        System.out.println("Plaća: " + this.salary + ", Početak ugovora: " + this.startDate + ", Kraj ugovora: " + this.endDate + ", Tip ugovora: " + this.contractType);
    }
}
