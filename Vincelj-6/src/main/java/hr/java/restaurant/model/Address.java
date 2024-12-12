package hr.java.restaurant.model;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Address extends Entity implements Serializable {
    private String street;
    private String houseNumber;
    private String city;
    private String postalCode;

    private Address(Builder builder) {
        super(builder.id);
        this.street = builder.street;
        this.houseNumber = builder.houseNumber;
        this.city = builder.city;
        this.postalCode = builder.postalCode;
    }

    private static final String ADDRESSES_FILE_PATH = "dat/addresses.txt";

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public static class Builder {
        private Long id;
        private String street;
        private String houseNumber;
        private String city;
        private String postalCode;

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withStreet(String street) {
            this.street = street;
            return this;
        }

        public Builder withHouseNumber(String houseNumber) {
            this.houseNumber = houseNumber;
            return this;
        }

        public Builder withCity(String city) {
            this.city = city;
            return this;
        }

        public Builder withPostalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }

    public static List<Address> loadAddressesFromFile() {
        List<Address> addresses = new ArrayList<>();

        try (Stream<String> lines = Files.lines(Path.of(ADDRESSES_FILE_PATH))) {
            List<String> fileRows = lines.collect(Collectors.toList());

            for (int i = 0; i < fileRows.size(); i += 5) {
                Long id = Long.parseLong(fileRows.get(i).trim());
                String street = fileRows.get(i + 1).trim();
                String houseNumber = fileRows.get(i + 2).trim();
                String city = fileRows.get(i + 3).trim();
                String postalCode = fileRows.get(i + 4).trim();

                Address address = new Address.Builder()
                        .withId(id)
                        .withStreet(street)
                        .withHouseNumber(houseNumber)
                        .withCity(city)
                        .withPostalCode(postalCode)
                        .build();

                addresses.add(address);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }

        return addresses;
    }

    public static Address inputAddress(Long id, Scanner scanner) {
        System.out.print("Enter the street: ");
        String streetAddress = scanner.nextLine();

        System.out.print("Enter the house number: ");
        String houseNumber = scanner.nextLine();

        System.out.print("Enter the city: ");
        String city = scanner.nextLine();

        System.out.print("Enter the postcode: ");
        String postalCode = scanner.nextLine();

        return new Address.Builder().withId(id).withStreet(streetAddress).withHouseNumber(houseNumber).withCity(city).withPostalCode(postalCode).build();
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", id=" + id +
                '}';
    }
}
