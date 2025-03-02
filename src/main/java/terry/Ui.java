package terry;

public class Ui {

    public static final String DIVIDER = "    ___________________________________________________________________";
    public static final String NEW_LINE = "\n     ";

    public static void printDivider() {
        System.out.println(DIVIDER);
    }

    public static void printMessage(String message) {
        System.out.println(DIVIDER + NEW_LINE + message + "\n" + DIVIDER);
    }

    public static void printGreeting() {
        printMessage("Hello! I'm Terry" + NEW_LINE + "What can I do for you?");
    }

    public static boolean printGoodbye() {
        printMessage("Bye. Hope to see you again soon!");
        return false;
    }

    public static void printHelp() {
        printMessage(" Here are the commands you can use:" + NEW_LINE
                + "1. todo <task name> - Add a todo task" + NEW_LINE
                + "2. deadline <task name> /by <due date> - Add a deadline task" + NEW_LINE
                + "3. event <task name> /at <event time> - Add an event task" + NEW_LINE
                + "4. list - List all tasks" + NEW_LINE
                + "5. mark <task number> - Mark a task as done" + NEW_LINE
                + "6. unmark <task number> - Mark a task as not done" + NEW_LINE
                + "7. delete <task number> - Delete a task" + NEW_LINE
                + "8. bye - Exit the program" + NEW_LINE);

    }

    public static void printUnknownCommand() {
        printMessage("I don't recognize that command. Type 'help' to see the list of commands.");
    }

}
