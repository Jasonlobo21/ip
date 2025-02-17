package terry;

import terry.exception.*;

import java.util.Scanner;

public class Terry {
    public static void main(String[] args) {

        final String SPACES = "    ";
        final String DIVIDER = SPACES + "____________________________________";
        final int MAX_TASKS = 100;
        final String COMMAND_BYE = "bye";
        final String COMMAND_LIST = "list";
        final String COMMAND_MARK = "mark";
        final String COMMAND_UNMARK = "unmark";
        final String COMMAND_TODO = "todo";
        final String COMMAND_DEADLINE = "deadline";
        final String COMMAND_EVENT = "event";
        final String COMMAND_HELP = "help";
        final String COMMAND_DELETE = "delete";

        handleGreetings(DIVIDER, SPACES);

        Scanner in = new Scanner(System.in);
        boolean isLooping = true;

        TaskList tasks = new TaskList(MAX_TASKS);

        while (isLooping) {

            try{
                String input = in.nextLine();
                String command = input.split(" ")[0];
                switch (command) {
                    case COMMAND_BYE:
                        isLooping = handleBye(DIVIDER, SPACES);
                        break;
                    case COMMAND_LIST:
                        handleList(tasks, DIVIDER);
                        break;
                    case COMMAND_UNMARK:
                        handleUnmark(tasks, input, DIVIDER, SPACES);
                        break;
                    case COMMAND_MARK:
                        handleMark(tasks, input, DIVIDER, SPACES);
                        break;
                    case COMMAND_TODO:
                        handleTodo(tasks, input, DIVIDER, SPACES);
                        break;
                    case COMMAND_DEADLINE:
                        handleDeadline(tasks, input, DIVIDER, SPACES);
                        break;
                    case COMMAND_EVENT:
                        handleEvent(tasks, input, DIVIDER, SPACES);
                        break;
                    case COMMAND_HELP:
                        handleHelp(DIVIDER, SPACES);
                        break;
                    case COMMAND_DELETE:
                        handleDelete(tasks, input, DIVIDER, SPACES);
                        break;
                    default:
                        handleUnknownCommand(DIVIDER, SPACES);
                        break;
                }
            } catch (TaskListFullException | InvalidMarkException | InvalidTodoException | InvalidDeadlineException |
                     InvalidEventException  | InvalidDeleteException e) {
                System.out.println(e.getMessage());
                System.out.println(DIVIDER + "\n");
            }
        }
    }

    private static void handleGreetings(String DIVIDER, String SPACES) {
        System.out.println(DIVIDER);
        System.out.println(SPACES + " Hello! I'm Terry ☺\n" +
                SPACES + " What can I do for you?");
        System.out.println(DIVIDER + "\n");
    }

    private static boolean handleBye(String DIVIDER, String SPACES) {
        System.out.println(DIVIDER);
        System.out.println(SPACES + " Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
        return false; // Ends the loop
    }

    private static void handleList(TaskList tasks, String DIVIDER) {
        System.out.println(DIVIDER);
        tasks.listTasks();
        System.out.println(DIVIDER + "\n");
    }

    private static void handleUnmark(TaskList tasks, String input, String DIVIDER, String SPACES) throws InvalidMarkException {
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

    private static void handleMark(TaskList tasks, String input, String DIVIDER, String SPACES) throws InvalidMarkException {
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

    private static void handleTodo(TaskList tasks, String input, String DIVIDER, String SPACES) throws InvalidTodoException, TaskListFullException {
        System.out.println(DIVIDER);
        String[] parts = input.trim().split(" ");
        if (parts.length < 2) {
            throw new InvalidTodoException();
        }
        System.out.println(SPACES + " Got it. I've added this task:");
        tasks.addTodo(input.substring(5));
        System.out.println(SPACES + " Now you have " + tasks.getTaskCount() + " tasks in the list.");
        System.out.println(DIVIDER + "\n");
    }

    private static void handleDeadline(TaskList tasks, String input, String DIVIDER, String SPACES) throws TaskListFullException, InvalidDeadlineException {
        System.out.println(DIVIDER);
        if (!input.contains(" /by")) {
            throw new InvalidDeadlineException();
        }
        String[] deadline = input.substring(9).split("/by");
        if (deadline.length < 2) {
            throw new InvalidDeadlineException();
        }
        System.out.println(SPACES + " Got it. I've added this task:");
        tasks.addDeadline(deadline[0], deadline[1]);
        System.out.println(SPACES + " Now you have " + tasks.getTaskCount() + " tasks in the list.");
        System.out.println(DIVIDER + "\n");
    }

    private static void handleEvent(TaskList tasks, String input, String DIVIDER, String SPACES) throws TaskListFullException, InvalidEventException {
        System.out.println(DIVIDER);
        if (!input.contains(" /from") || !input.contains(" /to")) {
            throw new InvalidEventException();
        }
        String[] event = input.substring(6).split("/");
        event[1] = event[1].substring(5);
        event[2] = event[2].substring(3);
        if (event[1].isEmpty() || event[2].isEmpty()) {
            throw new InvalidEventException();
        }
        System.out.println(SPACES + " Got it. I've added this task:");
        tasks.addEvent(event[0], event);
        System.out.println(SPACES + " Now you have " + tasks.getTaskCount() + " tasks in the list.");
        System.out.println(DIVIDER + "\n");
    }

    private static void handleHelp(String DIVIDER, String SPACES) {
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

    private static void handleDelete(TaskList tasks, String input, String DIVIDER, String SPACES) throws InvalidDeleteException {
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

    private static void handleUnknownCommand(String DIVIDER, String SPACES) {
        System.out.println(DIVIDER);
        System.out.println(SPACES + " I don't recognize that command. Try 'help' for a list of commands.");
        System.out.println(DIVIDER + "\n");
    }
}
