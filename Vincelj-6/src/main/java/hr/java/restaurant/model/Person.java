package hr.java.restaurant.model;

import java.io.Serializable;
import java.util.List;

public abstract class Person extends Entity implements Serializable {
    protected String firstName;
    protected String lastName;
    protected Contract contract;

    public Person(Long id, String firstName, String lastName, Contract contract) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.contract = contract;
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

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }


    public static void highestPayAndEmployment(List <Person> employees)
    {
        Person highestPaidEmployee = employees.get(0);
        Person longestContractEmployee = employees.get(0);

        for(Person employee : employees)
        {
            if(employee != null && employee.getContract().getSalary().compareTo(highestPaidEmployee.getContract().getSalary()) > 0)
            {
                highestPaidEmployee = employee;
            }
            if (employee.getContract().getStartDate().isBefore(longestContractEmployee.getContract().getStartDate()))
            {
                longestContractEmployee = employee;
            }

        }
        System.out.println("Employee with the highest salary:");
        System.out.println("Name: " + highestPaidEmployee.getFirstName() + " " + highestPaidEmployee.getLastName());
        System.out.println("Salary: " + highestPaidEmployee.getContract().getSalary());

        System.out.println("Employee with the longest contract:");
        System.out.println("Name: " + longestContractEmployee.getFirstName() + " " + longestContractEmployee.getLastName());
        System.out.println("Contract Start Date: " + longestContractEmployee.getContract().getStartDate());

    }
}
