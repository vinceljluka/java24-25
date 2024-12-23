package hr.javafx.vinceljfx7.restaurant.model;

import hr.javafx.vinceljfx7.service.Output;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Abstract class that represents a person.
 */
public abstract class Person extends Entity implements Serializable {
    private final String firstName;
    private final String lastName;

    /**
     * Constructs a Person object using the provided data.
     * @param id the id of the person
     * @param firstName the first name of the person
     * @param lastName the last name of the person
     */
    public Person(Long id, String firstName, String lastName) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public static <T extends Person> Set<T> getPersonByIdentifiers(String deliverersInRestaurantIdentifiers, Set<T> people) {
        Set<T> peopleByIdentifiers = new HashSet<>();
        String[] identifiers = deliverersInRestaurantIdentifiers.split(",");

        for (String identifier : identifiers) {
            for (T person : people) {
                if (person.getId().equals(Long.parseLong(identifier))) {
                    peopleByIdentifiers.add(person);
                }
            }
        }

        return peopleByIdentifiers;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }

    public abstract BigDecimal getSalary();
    public abstract Contract getContract();

    /**
     * Prints the person's information.
     * @param tabulators the number of tabulators to format the output
     */
    public void print(Integer tabulators) {
        System.out.println("Ime: " + firstName);
        System.out.println("Prezime: " + lastName);
    }

    /**
     * Prints the person's full information.
     * @param tabulators the number of tabulators to format the output
     * @param id the id of the person
     * @param firstName the first name of the person
     * @param lastName the last name of the person
     * @param contract the contract of the person
     * @param bonus the bonus of the person
     */
    public static void outputFullData(Integer tabulators, Long id, String firstName, String lastName, Contract contract, Bonus bonus) {
        Output.tabulatorPrint(tabulators);
        System.out.print("Id: " + id + ", Ime: " + firstName + ", Prezime: " + lastName);

        if(bonus.amount().compareTo(BigDecimal.ZERO) > 0) {
            System.out.println(", Bonus: " + bonus.amount());
        } else {
            System.out.println();
        }

        Output.tabulatorPrint(tabulators+1);
        System.out.println("Ugovor: ");
        contract.print(tabulators+2);
    }
}
