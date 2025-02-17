package terry.exception;

public class InvalidDeleteException extends TerryException {
    public InvalidDeleteException() {
      super("Invalid delete command (e.g., delete 2)");
    }
}
