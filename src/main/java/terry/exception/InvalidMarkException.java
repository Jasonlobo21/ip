package terry.exception;

public class InvalidMarkException extends TerryException {
    public InvalidMarkException(int taskCount, String message) {
        super(String.format("Please provide a valid task number (e.g., '%s 2') between 1 to %d ☹", message, taskCount));
    }
}
