package terry;

import terry.exception.TaskListFullException;
import terry.task.Deadline;
import terry.task.Event;
import terry.task.Task;
import terry.task.Todo;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks with a fixed capacity.
 */
public class TaskList {
    /**
     * Array to store tasks.
     */
    private List<Task> tasks;

    final int MAX_TASKS = 100;


    public TaskList() {
        tasks = new ArrayList<>();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public void addTodo(String taskName) throws TaskListFullException {
        if (tasks.size() >= 100) {
            throw new TaskListFullException();
        }
        tasks.add(new Todo(taskName));
        System.out.println("       " + tasks.get(tasks.size() - 1));
    }

    public void addDeadline(String taskName, String dueDate) throws TaskListFullException {
        if (tasks.size() >= 100) {
            throw new TaskListFullException();
        }
        tasks.add(new Deadline(taskName, dueDate));
        System.out.println("       " + tasks.get(tasks.size() - 1));
    }

    public void addEvent(String taskName, String from, String to) throws TaskListFullException {
        if (tasks.size() >= 100) {
            throw new TaskListFullException();
        }
        tasks.add(new Event(taskName, from, to));
        System.out.println("       " + tasks.get(tasks.size() - 1));
    }

    public void deleteTask(int index) {
        System.out.println("       Noted. I've removed this task:");
        System.out.println("         " + tasks.get(index - 1));
        tasks.remove(index - 1);
        System.out.println("       Now you have " + tasks.size() + " tasks in the list.");

    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index the 1-based index of the task to mark as done.
     */
    public void markTask(int index) {
        tasks.get(index - 1).mark();
        System.out.println("       " + tasks.get(index - 1));
    }

    /**
     * Marks the task at the specified index as not done.
     *
     * @param index the 1-based index of the task to mark as not done.
     */
    public void unmarkTask(int index) {
        tasks.get(index - 1).unmark();
        System.out.println("       " + tasks.get(index - 1));
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("    No tasks available.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("     " + (i + 1) + ". " + tasks.get(i));
            }
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("       " + tasks.get(tasks.size() - 1));
    }


    public void add(Task task) {
        tasks.add(task);
    }
}

