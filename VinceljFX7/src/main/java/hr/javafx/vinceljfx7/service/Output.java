package hr.javafx.vinceljfx7.service;

/**
 * Represents an output service.
 */
public class Output {
    /**
     * Prints tabulators before the message.
     * @param tabulators tabulators.
     */
    public static void tabulatorPrint(Integer tabulators) {
        for (int i = 0; i < tabulators; i++) {
            System.out.print("|\t");
        }
    }
}
