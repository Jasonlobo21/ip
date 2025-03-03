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

    public int getSize() {
        return tasks.size();
    }

    public void deleteTask(int index) {
        tasks.remove(index - 1);
    }

    public void markTask(int index) {
        tasks.get(index - 1).mark();
    }

    public void unmarkTask(int index) {
        tasks.get(index - 1).unmark();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }
}

