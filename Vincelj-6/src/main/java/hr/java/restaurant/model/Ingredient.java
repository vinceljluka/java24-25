package hr.java.restaurant.model;

import hr.java.restaurant.exception.DuplicateEntityException;
import hr.java.utils.DataInputUtils;
import hr.java.utils.DuplicateNameCheck;
import hr.java.utils.InputValidator;
import hr.java.utils.Messages;

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

public class Ingredient extends Entity implements Serializable {
    public static final int MAX_LENGTH = 1;

    private static Logger log = LoggerFactory.getLogger(Ingredient.class);

    private String name;
    private Category category;
    private BigDecimal kcal;
    private String preparationMethod;

    public Ingredient(Long id, String name, Category category, BigDecimal kcal, String preparationMethod) {
        super(id);
        this.name = name;
        this.category = category;
        this.kcal = kcal;
        this.preparationMethod = preparationMethod;
    }

    private static final String INGREDIENTS_FILE_PATH = "dat/ingredients.txt";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getKcal() {
        return kcal;
    }

    public void setKcal(BigDecimal kcal) {
        this.kcal = kcal;
    }

    public String getPreparationMethod() {
        return preparationMethod;
    }

    public void setPreparationMethod(String preparationMethod) {
        this.preparationMethod = preparationMethod;
    }

    public static List<Ingredient> loadIngredientsFromFile(List <Category> availableCategories)
    {
        List<Ingredient> ingredients = new ArrayList<>();

        try (Stream<String> lines = Files.lines(Path.of(INGREDIENTS_FILE_PATH))) {
            List<String> fileRows = lines.collect(Collectors.toList());

            for (int i = 0; i < fileRows.size(); i += 5)
            {
                Long id = Long.parseLong(fileRows.get(i).trim());
                String name = fileRows.get(i + 1).trim();
                Long categoryId = Long.parseLong(fileRows.get(i + 2).trim());
                BigDecimal kcal = new BigDecimal(fileRows.get(i + 3).trim());
                String preparationMethod = fileRows.get(i + 4).trim();

                Category category = availableCategories.stream()
                        .filter(cat -> cat.getId().equals(categoryId))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Invalid category ID: " + categoryId));

                ingredients.add(new Ingredient(id, name, category, kcal, preparationMethod));
            }
        } catch (IOException | IllegalArgumentException e) {
            log.error("Error reading ingredients file: ", e);
        }

        return ingredients;
    }

    public static void inputIngredient(List<Ingredient> ingredients, List <Category> categories, Scanner scanner)
    {
        for (int i = 0; i < MAX_LENGTH; i++)
        {
            Boolean isValid;
            String name;
            do {
                isValid = true;
                System.out.print("Enter " + (i + 1) + ". ingredient's name: ");
                name = scanner.nextLine();
                try {
                    DuplicateNameCheck.checkDuplicateIngredient(name, ingredients, i);
                } catch (DuplicateEntityException e) {
                    isValid = false;
                    log.error(e.getMessage());
                    System.out.println(e.getMessage());
                }
            } while (!isValid);

            System.out.println("Choose " + (i + 1) + ". ingredient's category (index 1, 2, or 3): ");
            for (int j = 0; j < categories.size(); j++)
            {
                System.out.println((j + 1) + ". " + categories.get(j).getName());
            }
            Category category = DataInputUtils.getCategory(categories, scanner, Messages.INVALID_INTEGER_INDEX_CATEGORY_INPUT);

            System.out.print("Enter " + (i + 1) + ". ingredient's calories: ");
            BigDecimal kcal = InputValidator.validatePositiveBigDecimal(scanner, Messages.INVALID_BIGDECIMAL_KCAL_INPUT);

            System.out.print("Enter preparation method for " + (i + 1) + ". ingredient: ");
            String preparationMethod = scanner.nextLine();

            ingredients.add(new Ingredient(Long.valueOf(i + 1), name, category, kcal, preparationMethod));
        }
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "name='" + name + '\'' +
                ", category=" + category +
                ", kcal=" + kcal +
                ", preparationMethod='" + preparationMethod + '\'' +
                ", id=" + id +
                '}';
    }
}
