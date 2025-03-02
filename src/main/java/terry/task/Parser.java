package terry.task;

import terry.TaskList;
import terry.Ui;
import terry.command.ListCommand;
import terry.exception.*;

public class Parser {

    public static boolean handleInput(TaskList tasks, String input) {
        try {
            String command = input.split(" ")[0].toLowerCase();
            switch (command) {
                case "bye":
                    return Ui.printGoodbye();
                case "list":
                    //tasks.listTasks();
                    new ListCommand().execute(tasks);
                    return true;
                case "unmark":
                    handleUnmark(input, tasks);
                    Storage.rewriteTasksToFile(tasks);
                    return true;
                case "mark":
                    handleMark(input, tasks);
                    Storage.rewriteTasksToFile(tasks);
                    return true;
                case "todo":
                    handleTodo(input, tasks);
                    return true;
                case "deadline":
                    handleDeadline(input, tasks);
                    return true;
                case "event":
                    handleEvent(input, tasks);
                    return true;
                case "help":
                    Ui.printHelp();
                    return true;
                case "delete":
                    handleDelete(input, tasks);
                    return true;
                default:
                    Ui.printUnknownCommand();
                    return true;
            }
        } catch (InvalidMarkException | InvalidTodoException | InvalidDeadlineException |
                 InvalidEventException | InvalidDeleteException e) {
            System.out.println(e.getMessage());
            Ui.printDivider();
        }
        return true;
    }

    private static void handleMark(String input, TaskList tasks) throws InvalidMarkException {
        Ui.printDivider();
        String[] parts = input.trim().split(" ");
        if (parts.length < 2) {
            throw new InvalidMarkException(tasks.getTaskCount(), "mark");
        }
        if (!parts[1].matches("\\d+")) {
            throw new InvalidMarkException(tasks.getTaskCount(), "mark");
        }
        int index = Integer.parseInt(parts[1]);
        if (index < 1 || index > tasks.getTaskCount()) {
            throw new InvalidMarkException(tasks.getTaskCount(), "mark");
        }
        tasks.markTask(index);
        System.out.println("     Nice! I've marked this task as done:");
        Ui.printDivider();
    }

    private static void handleUnmark(String input, TaskList tasks) throws InvalidMarkException {
        Ui.printDivider();
        String[] parts = input.split(" ");
        if (parts.length < 2) { // Check if the input has fewer than 2 parts
            throw new InvalidMarkException(tasks.getTaskCount(), "unmark");
        }
        if (!parts[1].matches("\\d+")) {
            throw new InvalidMarkException(tasks.getTaskCount(), "unmark");
        }
        int index = Integer.parseInt(parts[1]);
        if (index < 1 || index > tasks.getTaskCount()) { // Validate task number range
            throw new InvalidMarkException(tasks.getTaskCount(), "unmark");
        }
        tasks.unmarkTask(index);
        System.out.println("     OK, I've marked this task as not done yet:");
        Ui.printDivider();
    }

    private static void handleTodo(String input, TaskList tasks) throws InvalidTodoException {
        Ui.printDivider();
        String[] parts = input.trim().split(" ");
        if (parts.length < 2) {
            throw new InvalidTodoException();
        }
        System.out.println("     Got it. I've added this task:");
        Task todoTask = new Todo(input.substring(5));
        tasks.addTask(todoTask);
        Storage.appendTaskToFile(todoTask);
        System.out.println("     Now you have " + tasks.getTaskCount() + " tasks in the list.");
        Ui.printDivider();
    }

    private static void handleDeadline(String input, TaskList tasks) throws InvalidDeadlineException {
        Ui.printDivider();
        if (!input.contains(" /by")) {
            throw new InvalidDeadlineException();
        }
        String[] deadline = input.substring(9).split("/by");
        if (deadline.length < 2) {
            throw new InvalidDeadlineException();
        }
        System.out.println("     Got it. I've added this task:");
        Task deadlineTask = new Deadline(deadline[0], deadline[1]);
        tasks.addTask(deadlineTask);
        Storage.appendTaskToFile(deadlineTask);
        System.out.println("     Now you have " + tasks.getTaskCount() + " tasks in the list.");
        Ui.printDivider();
    }

    private static void handleDelete(String input, TaskList tasks) throws InvalidDeleteException {
        Ui.printDivider();
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            throw new InvalidDeleteException();
        }
        if (!parts[1].matches("\\d+")) {
            throw new InvalidDeleteException();
        }
        int index = Integer.parseInt(parts[1]);
        if (index < 1 || index > tasks.getTaskCount()) {
            throw new InvalidDeleteException();
        }
        tasks.deleteTask(index);
        Storage.rewriteTasksToFile(tasks);
        Ui.printDivider();
    }

    private static void handleEvent(String input, TaskList tasks) throws InvalidEventException {
        Ui.printDivider();
        if (!input.contains("/from") || !input.contains("/to")) {
            throw new InvalidEventException();
        }
        String[] event = input.substring(6).split("/");
        event[1] = event[1].substring(4);
        event[2] = event[2].substring(2);
        if (event[1].isEmpty() || event[2].isEmpty()) {
            throw new InvalidEventException();
        }
        System.out.println("     Got it. I've added this task:");
        Task eventTask = new Event(event[0], event[1].trim(), event[2].trim());
        tasks.addTask(eventTask);
        Storage.appendTaskToFile(eventTask);
        System.out.println("     Now you have " + tasks.getTaskCount() + " tasks in the list.");
        Ui.printDivider();
    }

}
