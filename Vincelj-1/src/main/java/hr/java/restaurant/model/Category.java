package hr.java.restaurant.model;

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

    public static Category inputCategory(Scanner scanner, int index) {
        System.out.print("Enter " + (index+1) + "." + " category's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter " + (index+1) + "." + " category's description: ");
        String description = scanner.nextLine();
        return new Category(name, description);
    }
}
