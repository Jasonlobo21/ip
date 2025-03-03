package terry.task;

/**
 * Represents a generic task.
 * <p>
 * This class is the superclass for all types of tasks such as Todo, Deadline, and Event.
 * It contains common fields and methods used by all task types.
 * </p>
 */
public class Task {

    /** The name or description of the task. */
    private String name;

    /** Indicates whether the task is marked as done. */
    private boolean isMarked;

    /** The task type identifier (e.g., "[T]", "[D]", "[E]"). */
    private String taskType;

    /**
     * Constructs a Task with the given name and type.
     *
     * @param name the name or description of the task
     * @param type the type identifier of the task
     */
    public Task(String name, String type) {
        this.name = name;
        isMarked = false;
        this.taskType = type;
    }

    /**
     * Returns the name of the task.
     *
     * @return the task name
     */
    public String getName() {
        return name;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isMarked = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmark() {
        this.isMarked = false;
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return true if the task is marked; false otherwise
     */
    public boolean isMarked() {
        return isMarked;
    }

    /**
     * Returns the status icon representing whether the task is done.
     *
     * @return "[X] " if the task is marked as done, or "[ ] " otherwise
     */
    public String getStatusIcon() {
        return (isMarked ? "[X] " : "[ ] ");
    }

    /**
     * Returns the string representation of the task.
     *
     * @return the formatted string including task type, status icon, and name
     */
    @Override
    public String toString() {
        return taskType + getStatusIcon() + name;
    }
}
