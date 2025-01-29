public class Task {
    /** Array to store tasks. */
    private String name;
    /** Number of tasks currently in the list. */
    private boolean isMarked;

    /**
     * Creates a new task with the given name.
     *
     * @param name the name or description of the task.
     */
    public Task(String name) {
        this.name = name;
        isMarked = false;
    }

    /**
     * Returns the name of the task.
     *
     * @return the task's name.
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
     * Returns the status icon for the task.
     *
     * @return "[X]" if marked, "[ ]" if not marked.
     */
    public String getStatusIcon() {
        return (isMarked ? "[X] " : "[ ] ");
    }
}
