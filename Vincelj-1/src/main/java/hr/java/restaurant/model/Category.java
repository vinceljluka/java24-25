package hr.java.restaurant.model;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Category {
    private String name;
    private String description;

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
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

    public static void inputCategory(Category[] categories, Scanner scanner) {
        for (int i = 0; i < categories.length; i++)
        {
            System.out.print("Enter the name of the " + (i+1) + "." + " category: ");
            String name = scanner.nextLine();
            System.out.print("Enter the description of the " + (i+1) + "." + " category: ");
            String description = scanner.nextLine();
            categories[i] = new Category(name,description);
        }
    }

}
