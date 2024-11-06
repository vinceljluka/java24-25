package hr.java.restaurant.model;

public abstract class Person {
    protected String firstName;
    protected String lastName;
    protected Contract contract; // New attribute added

    public Person(String firstName, String lastName, Contract contract) {
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

    public static void highestPayAndEmployment(Person[] employees)
    {
        Person highestPaidEmployee = employees[0];
        Person longestContractEmployee = employees[0];

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
