package hr.java.restaurant.model;

import java.math.BigDecimal;

public class Ingredient {
    private String name;
    private Category category;
    private BigDecimal kcal;
    private String preparationMethod;
    private Integer quantityOnStock;
    private Integer currentQuantityOnStock;
    public static final int DEFAULT_QUANTITY_ON_STOCK = 100;

    public Ingredient(String name, Category category, BigDecimal kcal, String preparationMethod, Integer quantityOnStock) {
        this.name = name;
        this.category = category;
        this.kcal = kcal;
        this.preparationMethod = preparationMethod;
        this.quantityOnStock = quantityOnStock;
        this.currentQuantityOnStock = quantityOnStock;
    }

    public Ingredient(String name, Category category, BigDecimal kcal, String preparationMethod) {
        this.name = name;
        this.category = category;
        this.kcal = kcal;
        this.preparationMethod = preparationMethod;
        this.quantityOnStock = DEFAULT_QUANTITY_ON_STOCK;
        this.currentQuantityOnStock = quantityOnStock;
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

    public Integer getQuantityOnStock() {
        return quantityOnStock;
    }

    public void setQuantityOnStock(Integer quantityOnStock) {
        this.quantityOnStock = quantityOnStock;
    }

    public Integer getCurrentQuantityOnStock() {
        return currentQuantityOnStock;
    }

    public void setCurrentQuantityOnStock(Integer currentQuantityOnStock) {
        this.currentQuantityOnStock = currentQuantityOnStock;
    }

    public void decreaseQuantityOnStock()
    {
        while (true)
        {
            if (quantityOnStock > 0)
            {
                currentQuantityOnStock = quantityOnStock - 1;
                break;
            }
            else {
                System.out.println("You don't have enough stock to decrease quantity");
            }
        }

    }



}
