package hr.java.restaurant.model;

import hr.java.restaurant.exception.DuplicateEntityException;
import hr.java.utils.DuplicateNameCheck;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Category extends Entity {

    private static Logger log = LoggerFactory.getLogger(Category.class);

    private String name;
    private String description;

    private Category(Builder builder) {
        super(builder.id);
        this.name = builder.name;
        this.description = builder.description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static class Builder {
        private Long id;
        private String name;
        private String description;

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Category build() {
            return new Category(this);
        }
    }

    public static void inputCategory(Category[] categories, Scanner scanner) {
        for (int i = 0; i < categories.length; i++)
        {
            Boolean isValid;
            String name;
            do {
                isValid = true;
                System.out.print("Enter the name of the " + (i + 1) + ". category: ");
                name = scanner.nextLine();
                try {
                    DuplicateNameCheck.checkDuplicateCategory(name, categories, i);
                } catch (DuplicateEntityException e) {
                    isValid = false;
                    log.error(e.getMessage());
                    System.out.println(e.getMessage());
                }
            } while (!isValid);

            System.out.print("Enter the description of the " + (i+1) + "." + " category: ");
            String description = scanner.nextLine();
            categories[i] = new Category.Builder().withId((long) (i + 1)).withName(name).withDescription(description).build();
        }
    }

}
