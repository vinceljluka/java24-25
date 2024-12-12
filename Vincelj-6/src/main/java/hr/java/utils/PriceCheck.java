package hr.java.utils;

import hr.java.restaurant.exception.NegativOrUnrealPrice;
import java.math.BigDecimal;


public class PriceCheck {

    public static void checkSalary(BigDecimal salary)
    {
        BigDecimal max = BigDecimal.valueOf(5000);
        BigDecimal min = BigDecimal.valueOf(300);
        if (salary.compareTo(min) < 0 || salary.compareTo(max) > 0)
        {
            throw new NegativOrUnrealPrice("Unreal salary " + salary);
        }
    }

    public static void checkBonus(BigDecimal bonus)
    {
        BigDecimal max = BigDecimal.valueOf(1000);
        BigDecimal min = BigDecimal.valueOf(100);
        if (bonus.compareTo(min) < 0 || bonus.compareTo(max) > 0)
        {
            throw new NegativOrUnrealPrice("Unreal bonus " + bonus);
        }
    }

    public static void checkPrice(BigDecimal price)
    {
        BigDecimal max = BigDecimal.valueOf(500);
        BigDecimal min = BigDecimal.valueOf(10);
        if (price.compareTo(min) < 0 || price.compareTo(max) > 0)
        {
            throw new NegativOrUnrealPrice("Unreal price " + price);
        }
    }


}
