package terry.task;

/**
 * Represents a todo task.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the specified name.
     *
     * @param name the name or description of the todo task
     */
    public Todo(String name) {
        super(name, "[T]");
    }
}
