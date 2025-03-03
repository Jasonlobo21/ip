package terry.task;

/**
 * Represents a deadline task with a due date.
 */
public class Deadline extends Task {

    /** The due date of the deadline task. */
    private String dueDate;

    /**
     * Constructs a Deadline task with the specified name and due date.
     *
     * @param name    the name or description of the task
     * @param dueDate the due date of the task
     */
    public Deadline(String name, String dueDate) {
        super(name, "[D]");
        this.dueDate = dueDate.trim();
    }

    /**
     * Returns the due date of this deadline.
     *
     * @return the due date as a String
     */
    public String getDueDate() {
        return dueDate;
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return the formatted string including task type, status, name, and due date
     */
    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), dueDate);
    }
}
