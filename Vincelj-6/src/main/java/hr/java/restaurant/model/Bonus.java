package hr.java.restaurant.model;

import java.math.BigDecimal;

public record Bonus(BigDecimal amount) {

    public Bonus(BigDecimal amount)
    {
        this.amount = amount;
    }
}
