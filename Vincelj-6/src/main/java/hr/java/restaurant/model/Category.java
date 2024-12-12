package hr.java.restaurant.model;

import hr.java.restaurant.exception.DuplicateEntityException;
import hr.java.utils.DuplicateNameCheck;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Category extends Entity implements Serializable {
    public static final int MAX_LENGTH = 1;

    private static Logger log = LoggerFactory.getLogger(Category.class);

    private String name;
    private String description;

    private Category(Builder builder) {
        super(builder.id);
        this.name = builder.name;
        this.description = builder.description;
    }

    private static final String CATEGORIES_FILE_PATH = "dat/categories.txt";

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

    public static List<Category> loadCategoriesFromFile() {
        List<Category> categories = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Path.of(CATEGORIES_FILE_PATH)))
        {
            List<String> fileRows = lines.collect(Collectors.toList()); // Učitaj sve linije u listu

            for (int i = 0; i < fileRows.size(); i += 3)
            {
                Long id = Long.parseLong(fileRows.get(i).trim());
                String name = fileRows.get(i + 1).trim();
                String description = fileRows.get(i + 2).trim();
                categories.add(new Category.Builder()
                        .withId(id)
                        .withName(name)
                        .withDescription(description)
                        .build());
            }
        } catch (IOException | NumberFormatException e)
        {
            log.error("Error reading categories file: ", e);
        }
        return categories;
    }

    public static void inputCategory(List <Category> categories, Scanner scanner) {
        for (int i = 0; i < MAX_LENGTH; i++)
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
            categories.add(new Category.Builder().withId((long) (i + 1)).withName(name).withDescription(description).build());
        }
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
