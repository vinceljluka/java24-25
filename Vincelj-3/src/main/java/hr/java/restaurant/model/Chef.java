package hr.java.restaurant.model;

import hr.java.restaurant.exception.DuplicateEntityException;
import hr.java.restaurant.exception.NegativOrUnrealPrice;
import hr.java.utils.DuplicateNameCheck;
import hr.java.utils.InputValidator;
import hr.java.utils.Messages;
import hr.java.utils.PriceCheck;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Scanner;

public class Chef extends Person {

    private static Logger log = LoggerFactory.getLogger(Chef.class);

    private Bonus bonus;

    private Chef(Builder builder) {
        super(builder.firstName, builder.lastName, builder.contract);
        this.bonus = builder.bonus;
    }

    public Bonus getBonus() {
        return bonus;
    }

    public void setBonus(Bonus bonus) {
        this.bonus = bonus;
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private Contract contract;
        private Bonus bonus;

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

        public Chef build() {
            return new Chef(this);
        }
    }

    public static void inputChef(Chef[] chefs, Scanner scanner) {
        for (int i = 0; i < chefs.length; i++)
        {
            Boolean isValid;
            String firstName;
            String lastName;
            do {
                isValid = true;
                System.out.print("Enter " + (i + 1) + ". chef's first name: ");
                firstName = scanner.nextLine();
                System.out.print("Enter " + (i + 1) + ". chef's last name: ");
                lastName = scanner.nextLine();
                try {
                    DuplicateNameCheck.checkDuplicateChef(firstName, lastName, chefs, i);
                } catch (DuplicateEntityException e) {
                    isValid = false;
                    log.error(e.getMessage());
                    System.out.println(e.getMessage());
                }

            } while (!isValid);

            System.out.println("Enter details for " + (i + 1) + ". chef's contract:");
            Contract contract = Contract.inputContract(scanner);

            System.out.print("Enter " + (i + 1) + ". chef's bonus amount: ");
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

            chefs[i] = new Chef.Builder().withFirstName(firstName).withLastName(lastName).withContract(contract).withBonus(bonus).build();
        }
    }
}
