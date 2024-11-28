package hr.java.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputValidator {

    private static Logger log = LoggerFactory.getLogger(InputValidator.class);

    public static Integer validatePositiveInteger(Scanner scanner, String errorMessage) {
        Boolean isValid;
        Integer number = 0;
        do {
            try {
                isValid = true;
                number = scanner.nextInt();
                if (number < 0)
                {
                    isValid = false;
                    System.out.println(errorMessage);
                }
                scanner.nextLine();
            } catch (InputMismatchException e) {
                log.error(errorMessage);
                isValid = false;
                System.out.println(errorMessage);
                scanner.nextLine();
            }
        } while (!isValid);
        return number;
    }

    public static BigDecimal validatePositiveBigDecimal(Scanner scanner, String errorMessage) {
        Boolean isValid;
        BigDecimal number = BigDecimal.ZERO;
        do {
            try {
                isValid = true;
                number = scanner.nextBigDecimal();
                if (number.compareTo(BigDecimal.ZERO) < 0)
                {
                    isValid = false;
                    System.out.println(errorMessage);
                }
                scanner.nextLine();
            } catch (InputMismatchException e) {
                log.error(errorMessage);
                isValid = false;
                System.out.println(errorMessage);
                scanner.nextLine();
            }
        } while (!isValid);
        return number;
    }

    public static boolean validateBoolean(Scanner scanner, String errorMessage) {
        boolean result;
        while (true) {
            try {
                result = scanner.nextBoolean();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                log.error(errorMessage);
                System.out.println(errorMessage);
                scanner.nextLine();
            }
        }
        return result;
    }
}
