/**
 * Represents a list of tasks with a fixed capacity.
 */
public class TaskList {
    private String[] tasks; // Stores tasks.
    private int taskCount; // Number of tasks in the list.

    /**
     * Creates a new TaskList with the specified capacity.
     *
     * @param capacity the maximum number of tasks.
     */
    public TaskList(int capacity) {
        tasks = new String[capacity];
        taskCount = 0;
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to be added.
     */
    public void addTask(String task) {
        tasks[taskCount++] = task;
    }

    /**
     * Prints all tasks in the list.
     */
    public void listTasks() {
        if(taskCount == 0) {
            System.out.println("    No tasks available.");
        }
        else {
            for (int i = 0; i < taskCount; i++) {
                System.out.println("     " + (i + 1) + ". " + tasks[i]);
            }
        }
    }
}
