package terry;

import terry.exception.*;
import terry.task.Deadline;
import terry.task.Event;
import terry.task.Task;
import terry.task.Todo;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Terry {

    private static final String DATA_DIRECTORY = "data";
    private static final String DATA_FILE = "terry.txt";
    private static final Path DATA_PATH = Paths.get(DATA_DIRECTORY, DATA_FILE);

    private static TaskList tasks = new TaskList();
    private static final String SPACES = "    ";
    private static final String DIVIDER = SPACES + "____________________________________";

    public static void main(String[] args) throws IOException {

        final String COMMAND_BYE = "bye";
        final String COMMAND_LIST = "list";
        final String COMMAND_MARK = "mark";
        final String COMMAND_UNMARK = "unmark";
        final String COMMAND_TODO = "todo";
        final String COMMAND_DEADLINE = "deadline";
        final String COMMAND_EVENT = "event";
        final String COMMAND_HELP = "help";
        final String COMMAND_DELETE = "delete";

        handleGreetings();
        loadTasks();
        Scanner in = new Scanner(System.in);
        boolean isLooping = true;

        while (isLooping) {

            try{
                String input = in.nextLine();
                String command = input.split(" ")[0].toLowerCase();
                switch (command) {
                    case COMMAND_BYE:
                        isLooping = handleBye();
                        break;
                    case COMMAND_LIST:
                        handleList();
                        break;
                    case COMMAND_UNMARK:
                        handleUnmark(input);
                        rewriteTasksToFile();
                        break;
                    case COMMAND_MARK:
                        handleMark(input);
                        rewriteTasksToFile();
                        break;
                    case COMMAND_TODO:
                        handleTodo(input);
                        break;
                    case COMMAND_DEADLINE:
                        handleDeadline(input);
                        break;
                    case COMMAND_EVENT:
                        handleEvent(input);
                        break;
                    case COMMAND_HELP:
                        handleHelp();
                        break;
                    case COMMAND_DELETE:
                        handleDelete(input);
                        break;
                    default:
                        handleUnknownCommand();
                        break;
                }
            } catch (TaskListFullException | InvalidMarkException | InvalidTodoException | InvalidDeadlineException |
                     InvalidEventException  | InvalidDeleteException e) {
                System.out.println(e.getMessage());
                System.out.println(DIVIDER + "\n");
            }
        }
    }

    private static void handleGreetings() {
        System.out.println(DIVIDER);
        System.out.println(SPACES + " Hello! I'm Terry ☺\n" +
                SPACES + " What can I do for you?");
        System.out.println(DIVIDER + "\n");
    }

    private static boolean handleBye() {
        System.out.println(DIVIDER);
        System.out.println(SPACES + " Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
        return false; // Ends the loop
    }

    private static void handleList() {
        System.out.println(DIVIDER);
        tasks.listTasks();
        System.out.println(DIVIDER + "\n");
    }

    private static void handleUnmark(String input) throws InvalidMarkException {
        System.out.println(DIVIDER);
        String[] parts = input.split(" ");
        if (parts.length < 2) { // Check if the input has fewer than 2 parts
            throw new InvalidMarkException(tasks.getTaskCount(), "unmark");
        }
        if(!parts[1].matches("\\d+")) {
            throw new InvalidMarkException(tasks.getTaskCount(), "unmark");
        }
        int index = Integer.parseInt(parts[1]);
        if (index < 1 || index > tasks.getTaskCount()) { // Validate task number range
            throw new InvalidMarkException(tasks.getTaskCount(), "unmark");
        }
        tasks.unmarkTask(index);
        System.out.println(SPACES + " OK, I've marked this task as not done yet:");
        System.out.println(DIVIDER + "\n");
    }

    private static void handleMark(String input) throws InvalidMarkException {
        System.out.println(DIVIDER);
        String[] parts = input.trim().split(" ");
        if (parts.length < 2) {
            throw new InvalidMarkException(tasks.getTaskCount(), "mark");
        }
        if(!parts[1].matches("\\d+")) {
            throw new InvalidMarkException(tasks.getTaskCount(), "mark");
        }
        int index = Integer.parseInt(parts[1]);
        if (index < 1 || index > tasks.getTaskCount()) {
            throw new InvalidMarkException(tasks.getTaskCount(), "mark");
        }
        tasks.markTask(index);
        System.out.println(SPACES + " Nice! I've marked this task as done:");
        System.out.println(DIVIDER + "\n");
    }

    private static void handleTodo(String input) throws InvalidTodoException, TaskListFullException {
        System.out.println(DIVIDER);
        String[] parts = input.trim().split(" ");
        if (parts.length < 2) {
            throw new InvalidTodoException();
        }
        System.out.println(SPACES + " Got it. I've added this task:");
        Task todoTask = new Todo(input.substring(5));
        tasks.addTask(todoTask);
        appendTaskToFile(todoTask);
        System.out.println(SPACES + " Now you have " + tasks.getTaskCount() + " tasks in the list.");
        System.out.println(DIVIDER + "\n");
    }

    private static void handleDeadline(String input) throws TaskListFullException, InvalidDeadlineException {
        System.out.println(DIVIDER);
        if (!input.contains(" /by")) {
            throw new InvalidDeadlineException();
        }
        String[] deadline = input.substring(9).split("/by");
        if (deadline.length < 2) {
            throw new InvalidDeadlineException();
        }
        System.out.println(SPACES + " Got it. I've added this task:");
        Task deadlineTask = new Deadline(deadline[0], deadline[1]);
        tasks.addTask(deadlineTask);
        appendTaskToFile(deadlineTask);
        System.out.println(SPACES + " Now you have " + tasks.getTaskCount() + " tasks in the list.");
        System.out.println(DIVIDER + "\n");
    }

    private static void handleEvent(String input) throws TaskListFullException, InvalidEventException {
        System.out.println(DIVIDER);
        if (!input.contains("/from") || !input.contains("/to")) {
            throw new InvalidEventException();
        }
        String[] event = input.substring(6).split("/");
        event[1] = event[1].substring(4);
        event[2] = event[2].substring(2);
        if (event[1].isEmpty() || event[2].isEmpty()) {
            throw new InvalidEventException();
        }
        System.out.println(SPACES + " Got it. I've added this task:");
        Task eventTask = new Event(event[0], event[1].trim(), event[2].trim());
        tasks.addTask(eventTask);
        appendTaskToFile(eventTask);
        System.out.println(SPACES + " Now you have " + tasks.getTaskCount() + " tasks in the list.");
        System.out.println(DIVIDER + "\n");
    }

    private static void handleHelp() {
        System.out.println(DIVIDER);
        System.out.println(SPACES + " Here are the commands you can use:");
        System.out.println(SPACES + " 1. todo <task name> - Add a todo task");
        System.out.println(SPACES + " 2. deadline <task name> /by <due date> - Add a deadline task");
        System.out.println(SPACES + " 3. event <task name> /at <time period> - Add an event task");
        System.out.println(SPACES + " 4. list - List all tasks");
        System.out.println(SPACES + " 5. mark <task number> - Mark a task as done");
        System.out.println(SPACES + " 6. unmark <task number> - Mark a task as not done");
        System.out.println(SPACES + " 7. bye - Exit the program");
        System.out.println(DIVIDER + "\n");
    }

    private static void handleDelete(String input) throws InvalidDeleteException {
        System.out.println(DIVIDER);
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            throw new InvalidDeleteException();
        }
        if(!parts[1].matches("\\d+")) {
            throw new InvalidDeleteException();
        }
        int index = Integer.parseInt(parts[1]);
        if (index < 1 || index > tasks.getTaskCount()) {
            throw new InvalidDeleteException();
        }
        tasks.deleteTask(index);
        System.out.println(DIVIDER + "\n");
    }

    private static void handleUnknownCommand() {
        System.out.println(DIVIDER);
        System.out.println(SPACES + " I don't recognize that command. Try 'help' for a list of commands.");
        System.out.println(DIVIDER + "\n");
    }

    private static void loadTasks() {
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
                        tasks.add(task);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Warning: Ignoring invalid task in file: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Warning: Error loading tasks from file. Starting with empty list.");
        }
    }

    private static Task parseStringToTask(String line) {
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

    private static void appendTaskToFile(Task task) {
        try (FileWriter fw = new FileWriter(DATA_PATH.toString(), true)) {
            fw.write(parseTaskToString(task) + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Error appending task to file: " + e.getMessage());
        }
    }

    // Rewrites the entire file with the current tasks from the task list.
    private static void rewriteTasksToFile() {
        try (FileWriter fw = new FileWriter(DATA_PATH.toString())) {
            for (int i = 0; i < tasks.getTaskCount(); i++) {
                fw.write(parseTaskToString(tasks.getTask(i)) + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error rewriting tasks to file: " + e.getMessage());
        }
    }

    // Converts a Task object to a string in the format:
    // [TaskType] | [isDone: 1 or 0] | [Description] | ([By] for Deadline or [StartTime] | [EndTime] for Event)
    private static String parseTaskToString(Task task) {
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
