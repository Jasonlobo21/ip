package terry;

import terry.task.Deadline;
import terry.task.Event;
import terry.task.Task;
import terry.task.Todo;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Handles the storage operations for tasks including reading from and writing to the file.
 */
public class Storage {

    /** Directory where data is stored. */
    public static final String DATA_DIRECTORY = "data";

    /** Filename for the storage file. */
    public static final String DATA_FILE = "terry.txt";

    /** The complete path to the storage file. */
    public static final Path DATA_PATH = Paths.get(DATA_DIRECTORY, DATA_FILE);

    /**
     * Loads tasks from the storage file into the provided TaskList.
     * <p>
     * If the file or directory does not exist, they are created.
     * </p>
     *
     * @param tasks the TaskList to load tasks into
     */
    public static void loadTasks(TaskList tasks) {
        try {
            Files.createDirectories(Paths.get(DATA_DIRECTORY));

            if (!Files.exists(DATA_PATH)) {
                Files.createFile(DATA_PATH);
                return;
            }

            for (String line : Files.readAllLines(DATA_PATH)) {
                try {
                    Task task = parseStringToTask(line);
                    if (task != null) {
                        tasks.addTask(task);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Warning: Ignoring invalid task in file: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Warning: Error loading tasks from file. Starting with empty list.");
        }
    }

    /**
     * Parses a line from the storage file into a Task object.
     *
     * @param line the line representing a task
     * @return a Task object corresponding to the line
     * @throws IllegalArgumentException if the task format is invalid
     */
    public static Task parseStringToTask(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid task format");
        }

        String taskType = parts[0];
        boolean isDone = "1".equals(parts[1]);
        Task task;
        String description = parts[2];

        switch (taskType) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            if (parts.length < 4) throw new IllegalArgumentException("Invalid deadline format");
            task = new Deadline(description, parts[3]);
            break;
        case "E":
            if (parts.length < 4) throw new IllegalArgumentException("Invalid event format");
            String[] timeFrame = parts[3].split("-");
            task = new Event(description, timeFrame[0].trim(), timeFrame[1]);
            break;
        default:
            throw new IllegalArgumentException("Unknown task type: " + taskType);
        }

        if (isDone) {
            task.mark();
        }
        return task;
    }

    /**
     * Appends the given task to the storage file.
     *
     * @param task the Task to append to the file
     */
    public static void appendTaskToFile(Task task) {
        try (FileWriter fw = new FileWriter(DATA_PATH.toString(), true)) {
            fw.write(parseTaskToString(task) + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Error appending task to file: " + e.getMessage());
        }
    }

    /**
     * Rewrites all tasks in the TaskList to the storage file.
     *
     * @param tasks the TaskList containing all tasks to write
     */
    public static void rewriteTasksToFile(TaskList tasks) {
        try (FileWriter fw = new FileWriter(DATA_PATH.toString())) {
            for (int i = 0; i < tasks.getSize(); i++) {
                fw.write(parseTaskToString(tasks.getTask(i)) + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error rewriting tasks to file: " + e.getMessage());
        }
    }

    /**
     * Converts a Task object into its string representation for storage.
     *
     * @param task the Task to convert
     * @return the string representation of the task
     */
    public static String parseTaskToString(Task task) {
        StringBuilder sb = new StringBuilder();

        if (task instanceof Todo) {
            sb.append("T");
        } else if (task instanceof Deadline) {
            sb.append("D");
        } else if (task instanceof Event) {
            sb.append("E");
        }

        sb.append(" | ")
                .append(task.isMarked() ? "1" : "0")
                .append(" | ")
                .append(task.getName());

        if (task instanceof Deadline) {
            sb.append(" | ").append(((Deadline) task).getDueDate());
        } else if (task instanceof Event) {
            sb.append(" | ")
                    .append(((Event) task).getFrom())
                    .append("-")
                    .append(((Event) task).getTo());
        }
        return sb.toString();
    }
}
