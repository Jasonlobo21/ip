package terry;

/**
 * Provides static utility methods for interacting with the user via the console.
 */
public class Ui {

    /** Divider line used for formatting output. */
    public static final String DIVIDER = "    ___________________________________________________________________";

    /** New line and indent used for formatting output. */
    public static final String NEW_LINE = "\n     ";

    /**
     * Prints a divider line to the console.
     */
    public static void printDivider() {
        System.out.println(DIVIDER);
    }

    /**
     * Prints a message with indentation.
     *
     * @param message the message to print
     */
    public static void printWithSpaces(String message) {
        System.out.println("    " + message);
    }

    /**
     * Prints a message enclosed by divider lines.
     *
     * @param message the message to print
     */
    public static void printMessage(String message) {
        System.out.println(DIVIDER + NEW_LINE + message + "\n" + DIVIDER);
    }

    /**
     * Prints the greeting message to the user.
     */
    public static void printGreeting() {
        printMessage("Hello! I'm Terry" + NEW_LINE + "What can I do for you?");
    }

    /**
     * Prints the goodbye message and returns false to signal the termination of the program.
     *
     * @return false to indicate that the program should exit
     */
    public static boolean printGoodbye() {
        printMessage("Bye. Hope to see you again soon!");
        return false;
    }

    /**
     * Prints the list of available commands for the user.
     */
    public static void printHelp() {
        printMessage("Here are the commands you can use:" + NEW_LINE
                + "1. todo <task name> - Add a todo task" + NEW_LINE
                + "2. deadline <task name> /by <due date> - Add a deadline task" + NEW_LINE
                + "3. event <task name> /at <event time> - Add an event task" + NEW_LINE
                + "4. list - List all tasks" + NEW_LINE
                + "5. mark <task number> - Mark a task as done" + NEW_LINE
                + "6. unmark <task number> - Mark a task as not done" + NEW_LINE
                + "7. delete <task number> - Delete a task" + NEW_LINE
                + "8. bye - Exit the program" + NEW_LINE);
    }

    /**
     * Prints a message indicating that the entered command is unknown.
     */
    public static void printUnknownCommand() {
        printMessage("I don't recognize that command. Type 'help' to see the list of commands.");
    }

    /**
     * Prints the task in a formatted list with its index.
     *
     * @param tasks the task list containing the tasks
     * @param index the index of the task to print
     */
    public static void printListedFormat(TaskList tasks, int index) {
        printWithSpaces((index + 1) + ". " + tasks.getTask(index));
    }
}
