package hr.java.restaurant.model;

import hr.java.restaurant.exception.DuplicateEntityException;
import hr.java.restaurant.exception.NegativOrUnrealPrice;
import hr.java.utils.DuplicateNameCheck;
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
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Deliverer extends Person implements Serializable {
    public final static int MAX_LENGHT = 1;

    private static Logger log = LoggerFactory.getLogger(Deliverer.class);

    private Bonus bonus;

    private Deliverer(Builder builder) {
        super(builder.id, builder.firstName, builder.lastName, builder.contract);
        this.bonus = builder.bonus;
    }

    public Bonus getBonus() {
        return bonus;
    }

    public void setBonus(Bonus bonus) {
        this.bonus = bonus;
    }



    private static final String DELIVERERS_FILE_PATH = "dat/deliverers.txt";

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deliverer deliverer = (Deliverer) o;
        return Objects.equals(getFirstName(), deliverer.getFirstName()) &&
                Objects.equals(getLastName(), deliverer.getLastName()) &&
                Objects.equals(getContract(), deliverer.getContract()) &&
                Objects.equals(bonus, deliverer.bonus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getContract(), bonus);
    }

    @Override
    public String toString() {
        return "Deliverer{" +
                "bonus=" + bonus +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contract=" + contract +
                '}';
    }

    public static class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private Contract contract;
        private Bonus bonus;

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withContract(Contract contract) {
            this.contract = contract;
            return this;
        }

        public Builder withBonus(Bonus bonus) {
            this.bonus = bonus;
            return this;
        }

        public Deliverer build() {
            return new Deliverer(this);
        }
    }

    public static List<Deliverer> loadDeliverersFromFile(List<Contract> contracts) {
        List<Deliverer> deliverers = new ArrayList<>();

        try (Stream<String> lines = Files.lines(Path.of(DELIVERERS_FILE_PATH))) {
            List<String> fileRows = lines.collect(Collectors.toList());

            for (int i = 0; i < fileRows.size(); i += 5) {
                Long id = Long.parseLong(fileRows.get(i).trim());
                String firstName = fileRows.get(i + 1).trim();
                String lastName = fileRows.get(i + 2).trim();
                Long contractId = Long.parseLong(fileRows.get(i + 3).trim());

                Contract contract = contracts.stream()
                        .filter(c -> c.getId().equals(contractId))
                        .findFirst()
                        .orElse(null);

                if (contract == null) {
                    log.error("Contract with ID " + contractId + " not found for deliverer " + firstName + " " + lastName);
                    continue;
                }

                BigDecimal salary = new BigDecimal(fileRows.get(i + 4).trim());
                Bonus bonus = new Bonus(salary);

                Deliverer deliverer = new Deliverer.Builder()
                        .withId(id)
                        .withFirstName(firstName)
                        .withLastName(lastName)
                        .withContract(contract)
                        .withBonus(bonus)
                        .build();

                deliverers.add(deliverer);
            }
        } catch (IOException e) {
            log.error("Error reading deliverers from file", e);
        } catch (NumberFormatException e) {
            log.error("Invalid data format in deliverer file", e);
        }

        return deliverers;
    }

    public static void inputDeliverer(Set<Deliverer> deliverers, Scanner scanner) {
        for (int i = 0; i < MAX_LENGHT; i++)
        {
            Boolean isValid;
            String firstName;
            String lastName;
            do {
                isValid = true;
                System.out.print("Enter " + (i + 1) + ". deliverer's first name: ");
                firstName = scanner.nextLine();
                System.out.print("Enter " + (i + 1) + ". deliverer's last name: ");
                lastName = scanner.nextLine();
                try {
                    DuplicateNameCheck.checkDuplicateDeliverer(firstName, lastName, deliverers, i);
                } catch (DuplicateEntityException e) {
                    isValid = false;
                    log.error(e.getMessage());
                    System.out.println(e.getMessage());
                }

            } while (!isValid);

            System.out.println("Enter details for " + (i + 1) + ". deliverer's contract:");
            Contract contract = Contract.inputContract(scanner);

            System.out.print("Enter " + (i + 1) + ". deliverer's bonus amount: ");
            BigDecimal bonusAmount;

            do {
                isValid = true;
                bonusAmount = InputValidator.validatePositiveBigDecimal(scanner, Messages.INVALID_BONUS_INPUT);
                try {
                    PriceCheck.checkBonus(bonusAmount);
                } catch (NegativOrUnrealPrice e) {
                    isValid = false;
                    log.error(e.getMessage());
                    System.out.println(e.getMessage());
                }
            } while (!isValid);

            Bonus bonus = new Bonus(bonusAmount);
            deliverers.add(new Deliverer.Builder().withFirstName(firstName).withLastName(lastName).withContract(contract).withBonus(bonus).build());
        }
    }
}
