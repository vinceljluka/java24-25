package hr.java.restaurant.model;

import hr.java.restaurant.exception.DuplicateEntityException;
import hr.java.utils.DataInputUtils;
import hr.java.utils.DuplicateNameCheck;
import hr.java.utils.InputValidator;
import hr.java.utils.Messages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Scanner;

public class Ingredient extends Entity{

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

    public static void inputIngredient(Ingredient[] ingredients, Category[] categories, Scanner scanner)
    {
        for (int i = 0; i < ingredients.length; i++)
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
            for (int j = 0; j < categories.length; j++)
            {
                System.out.println((j + 1) + ". " + categories[j].getName());
            }
            Category category = DataInputUtils.getCategory(categories, scanner, Messages.INVALID_INTEGER_INDEX_CATEGORY_INPUT);

            System.out.print("Enter " + (i + 1) + ". ingredient's calories: ");
            BigDecimal kcal = InputValidator.validatePositiveBigDecimal(scanner, Messages.INVALID_BIGDECIMAL_KCAL_INPUT);

            System.out.print("Enter preparation method for " + (i + 1) + ". ingredient: ");
            String preparationMethod = scanner.nextLine();

            ingredients[i] = new Ingredient(Long.valueOf(i + 1), name, category, kcal, preparationMethod);
        }
    }

}
