package terry;

import terry.task.Task;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks.
 * <p>
 * This class provides methods to add, delete, and modify tasks in the list.
 * </p>
 */
public class TaskList {

    /** The internal list storing all tasks. */
    private List<Task> tasks;

    /**
     * Constructs a new empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index the index of the task to retrieve
     * @return the Task at the specified index
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the size of the task list
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Deletes the task at the given index (1-indexed).
     *
     * @param index the 1-indexed position of the task to remove
     */
    public void deleteTask(int index) {
        tasks.remove(index - 1);
    }

    /**
     * Marks the task at the given index (1-indexed) as done.
     *
     * @param index the 1-indexed position of the task to mark as done
     */
    public void markTask(int index) {
        tasks.get(index - 1).mark();
    }

    /**
     * Marks the task at the given index (1-indexed) as not done.
     *
     * @param index the 1-indexed position of the task to unmark
     */
    public void unmarkTask(int index) {
        tasks.get(index - 1).unmark();
    }

    /**
     * Checks if the task list is empty.
     *
     * @return true if the task list has no tasks; false otherwise
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task the task to add
     */
    public void addTask(Task task) {
        tasks.add(task);
    }
}

