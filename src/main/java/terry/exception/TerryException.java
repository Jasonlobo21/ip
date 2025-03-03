package terry.exception;

/**
 * Represents exceptions specific to the Terry application.
 * <p>
 * This exception is thrown when there is an error in processing user commands or task operations.
 * </p>
 */
public class TerryException extends Exception {

    /**
     * Constructs a new TerryException with the specified detail message.
     *
     * @param message the detail message
     */
    public TerryException(String message) {
        super("    Error: " + message);
    }
}
