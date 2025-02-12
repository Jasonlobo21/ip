package terry.exception;

public class InvalidTodoException extends TerryException {
    public InvalidTodoException() {
        super("Invalid todo command (e.g., todo run)");
    }
}
