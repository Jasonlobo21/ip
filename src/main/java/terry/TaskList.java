package terry;

import terry.exception.TaskListFullException;
import terry.task.Deadline;
import terry.task.Event;
import terry.task.Task;
import terry.task.Todo;

/**
 * Represents a list of tasks with a fixed capacity.
 */
public class TaskList {
    /** Array to store tasks. */
    private Task[] taskArray;
    /** Number of tasks currently in the list. */
    private int taskCount;

    final int MAX_TASKS = 100;

    /**
     * Creates a new TaskList with the specified capacity.
     *
     * @param capacity the maximum number of tasks.
     */
    public TaskList(int capacity) {
        taskArray = new Task[capacity];
        taskCount = 0;
    }

    public int getTaskCount() {
        return taskCount;
    }

    public void addTodo(String taskName) throws TaskListFullException {
        if(taskCount == MAX_TASKS) {
            throw new TaskListFullException();
        }
        taskArray[taskCount] = new Todo(taskName);
        System.out.println("       " + taskArray[taskCount]);
        taskCount++;
    }

    public void addDeadline(String taskName, String dueDate) throws TaskListFullException {
        if(taskCount == MAX_TASKS) {
            throw new TaskListFullException();
        }
        taskArray[taskCount] = new Deadline(taskName, dueDate);
        System.out.println("       " + taskArray[taskCount]);
        taskCount++;
    }
    public void addEvent(String taskName, String[] timePeriod) throws TaskListFullException {
        if(taskCount == MAX_TASKS) {
            throw new TaskListFullException();
        }
        taskArray[taskCount] = new Event(taskName, timePeriod);
        System.out.println("       " + taskArray[taskCount]);
        taskCount++;
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index the 1-based index of the task to mark as done.
     */
    public void markTask(int index) {
        taskArray[index - 1].mark();
        System.out.println("       " + taskArray[index - 1]);
    }

    /**
     * Marks the task at the specified index as not done.
     *
     * @param index the 1-based index of the task to mark as not done.
     */
    public void unmarkTask(int index) {
        taskArray[index - 1].unmark();
        System.out.println("       " + taskArray[index - 1]);
    }

    /**
     * Lists all tasks in the list along with their status.
     */
    public void listTasks() {
        if(taskCount == 0) {
            System.out.println("    No tasks available.");
        } else {
            for (int i = 0; i < taskCount; i++) {
                System.out.println("     " + (i + 1) + ". " + taskArray[i]);
            }
        }
    }
}
