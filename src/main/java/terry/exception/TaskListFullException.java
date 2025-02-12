package terry.exception;

public class TaskListFullException extends TerryException {
    public TaskListFullException() {
        super("The list is full.");
    }
}
