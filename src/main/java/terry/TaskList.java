package terry;

import terry.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public void deleteTask(int index) {
        System.out.println("       Noted. I've removed this task:");
        System.out.println("         " + tasks.get(index - 1));
        tasks.remove(index - 1);
        System.out.println("       Now you have " + tasks.size() + " tasks in the list.");

    }

    public void markTask(int index) {
        tasks.get(index - 1).mark();
        System.out.println("       " + tasks.get(index - 1));
    }

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

    public void loadTask(Task task) {
        tasks.add(task);
    }
}

