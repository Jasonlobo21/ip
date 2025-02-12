package terry.exception;

public class InvalidEventException extends TerryException {
    public InvalidEventException() {
        super("Invalid event command (e.g., event run /from 7pm /to 9pm)");
    }
}
