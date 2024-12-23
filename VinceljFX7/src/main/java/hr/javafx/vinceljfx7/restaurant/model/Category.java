package hr.javafx.vinceljfx7.restaurant.model;

import hr.javafx.vinceljfx7.service.Output;

import java.io.*;


/**
 * Represents a category of a meal.
 */
public class Category extends Entity implements Serializable {

    private String name;
    private String description;

    /**
     * Constructs a Category object using the provided builder.
     * @param builder the builder instance containing category information
     */
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

    /**
     * Builder class for creating instances of {@link Category}.
     */
    public static class Builder {
        private Long id;
        private String name;
        private String description;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Category build() {
            return new Category(this)   ;
        }
    }

    /**
     * Prints the category details with the specified number of tabulators.
     * @param tabulators the number of tabulators to format the output
     */
    public void print(Integer tabulators) {
        Output.tabulatorPrint(tabulators);
        System.out.println("Id: " + this.getId() + ", Naziv kategorije: " + this.name + ", Opis kategorije: "+ this.description);
    }

}