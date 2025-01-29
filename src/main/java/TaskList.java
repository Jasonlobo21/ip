/**
 * Represents a list of tasks with a fixed capacity.
 */
public class TaskList {
    /** Array to store tasks. */
    private Task[] taskArray;
    /** Number of tasks currently in the list. */
    private int taskCount;

    /**
     * Creates a new TaskList with the specified capacity.
     *
     * @param capacity the maximum number of tasks.
     */
    public TaskList(int capacity) {
        taskArray = new Task[capacity];
        taskCount = 0;
    }

    /**
     * Adds a task to the list.
     *
     * @param taskName the task to be added.
     */
    public void addTask(String taskName) {
        taskArray[taskCount++] = new Task(taskName);
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index the 1-based index of the task to mark as done.
     */
    public void markTask(int index) {
        taskArray[index - 1].mark();
        System.out.println("       [X] " + taskArray[index - 1].getName());
    }

    /**
     * Marks the task at the specified index as not done.
     *
     * @param index the 1-based index of the task to mark as not done.
     */
    public void unmarkTask(int index) {
        taskArray[index - 1].unmark();
        System.out.println("       [ ] " + taskArray[index - 1].getName());
    }

    /**
     * Lists all tasks in the list along with their status.
     */
    public void listTasks() {
        if(taskCount == 0) {
            System.out.println("    No tasks available.");
        }
        else {
            for (int i = 0; i < taskCount; i++) {
                String statusIcon = taskArray[i].getStatusIcon();
                System.out.println("     " + (i + 1) + "." + statusIcon + taskArray[i].getName());
            }
        }
    }
}
