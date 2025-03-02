package terry.task;

import terry.TaskList;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {
    public static final String DATA_DIRECTORY = "data";
    public static final String DATA_FILE = "terry.txt";
    public static final Path DATA_PATH = Paths.get(DATA_DIRECTORY, DATA_FILE);

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
                        tasks.loadTask(task);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Warning: Ignoring invalid task in file: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Warning: Error loading tasks from file. Starting with empty list.");
        }
    }

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

    public static void appendTaskToFile(Task task) {
        try (FileWriter fw = new FileWriter(DATA_PATH.toString(), true)) {
            fw.write(parseTaskToString(task) + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Error appending task to file: " + e.getMessage());
        }
    }

    public static void rewriteTasksToFile(TaskList tasks) {
        try (FileWriter fw = new FileWriter(DATA_PATH.toString())) {

            for (int i = 0; i < tasks.getTaskCount(); i++) {
                fw.write(parseTaskToString(tasks.getTask(i)) + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error rewriting tasks to file: " + e.getMessage());
        }
    }

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
