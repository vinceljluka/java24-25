package hr.java.restaurant.model;

import java.math.BigDecimal;
import java.util.Scanner;

public class Ingredient extends Entity{
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

    public static void inputIngredient(Ingredient[] ingredients, Category[] categories, Scanner scanner) {
        for (int i = 0; i < ingredients.length; i++)
        {
            System.out.print("Enter " + (i+1) + "." +  " ingredient's name: ");
            String name = scanner.nextLine();

            System.out.print("Chose " + (i+1) +  "." + " ingredient's category (index 1, 2 or 3) : \n");
            for (int j = 0; j < categories.length; j++)
            {
                System.out.println((j+1) + "." + categories[j].getName());
            }

            int categoryIndex;
            while (true) {
                if (scanner.hasNextInt()) {
                    categoryIndex = scanner.nextInt();
                    scanner.nextLine();
                    if (categoryIndex >= 1 && categoryIndex <= categories.length) {
                        break;
                    } else {
                        System.out.println("Invalid input, please enter a category index between 1 and " + categories.length + ".");
                    }
                } else {
                    System.out.println("Invalid input, please enter a valid integer for category index.");
                    scanner.nextLine();
                }
            }
            Category category = categories[categoryIndex - 1];

            System.out.print("Enter " + (i+1) + "." + " ingredient's calories: ");
            BigDecimal kcal;
            while (true) {
                if (scanner.hasNextBigDecimal()) {
                    kcal = scanner.nextBigDecimal();
                    scanner.nextLine();
                    if (kcal.compareTo(BigDecimal.ZERO) > 0) {
                        break;
                    } else {
                        System.out.println("Calories must be greater than 0. Please enter a valid number.");
                    }
                } else {
                    System.out.println("Invalid input, please enter a valid number for calories.");
                    scanner.nextLine();
                }
            }
            System.out.print("Enter preparation method for "  + (i+1) + "." + " ingredient: ");
            String preparationMethod = scanner.nextLine();

            ingredients[i] = new Ingredient(Long.valueOf(i+1), name, category, kcal, preparationMethod);

        }
    }

}
