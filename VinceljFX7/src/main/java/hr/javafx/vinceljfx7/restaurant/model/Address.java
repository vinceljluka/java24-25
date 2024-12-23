package hr.javafx.vinceljfx7.restaurant.model;

import hr.javafx.vinceljfx7.service.Output;

import java.io.Serializable;

/**
 * Represents an address of a restaurant.
 * */
public class Address extends Entity implements Serializable {

    private final String street;
    private final String houseNumber;
    private String city;
    private final String postalCode;

    /**
     * Constructs an Address object using the provided builder.
     * @param builder the builder instance containing address information
     */
    private Address(Builder builder) {
        super(builder.id);
        this.street = builder.street;
        this.houseNumber = builder.houseNumber;
        this.city = builder.city;
        this.postalCode = builder.postalCode;
    }

    public String getCity() {
        return this.city;
    }

    /**
     * Builder class for creating instances of {@link Address}.
     */
    public static class Builder {
        private Long id;
        private String street;
        private String houseNumber;
        private String city;
        private String postalCode;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder street(String street) {
            this.street = street;
            return this;
        }

        public Builder houseNumber(String houseNumber) {
            this.houseNumber = houseNumber;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder postalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }

    @Override
    public String toString() {
        return street + " " + houseNumber + " " + city + " " + postalCode;
    }

    /**
     * Prints the address details with the specified number of tabulators.
     * @param tabulators the number of tabulators to format the output
     */
    public void print(Integer tabulators) {
        Output.tabulatorPrint(tabulators);
        System.out.println("Id: " + this.getId() + ", Ulica: " + this.street + ", Kućni broj: " + this.houseNumber + ", Grad: " + this.city + ", Poštanski broj: " + this.postalCode);
    }
}