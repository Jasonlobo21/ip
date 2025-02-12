package terry.exception;

public class InvalidDeadlineException extends TerryException {
    public InvalidDeadlineException() {
        super("Invalid deadline command (e.g., deadline run /by 9pm)");
    }
}
