package terry.exception;

public class TerryException extends Exception {
    public TerryException(String message) {
        super("    Error: " + message);
    }
}
