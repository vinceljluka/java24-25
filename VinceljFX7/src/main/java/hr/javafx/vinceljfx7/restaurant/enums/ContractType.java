package hr.javafx.vinceljfx7.restaurant.enums;

public enum ContractType {
    FULL_TIME("Full Time"),
    PART_TIME("Part Time");

    private final String name;

    ContractType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
