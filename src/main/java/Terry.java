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

        handleGreetings(DIVIDER, SPACES);

        Scanner in = new Scanner(System.in);
        boolean isLooping = true;

        TaskList tasks = new TaskList(MAX_TASKS);

        while (isLooping) {
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
                default:
                    handleUnknownCommand(DIVIDER, SPACES);
                    break;
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

    private static void handleUnmark(TaskList tasks, String input, String DIVIDER, String SPACES) {
        System.out.println(DIVIDER);
        System.out.println(SPACES + " OK, I've marked this task as not done yet:");
        try {
            int index = Integer.parseInt(input.substring(7));
            tasks.unmarkTask(index);
        } catch (NumberFormatException e) {
            System.out.println(SPACES + " Invalid input. Please provide a valid task index.");
        }
        System.out.println(DIVIDER + "\n");
    }

    private static void handleMark(TaskList tasks, String input, String DIVIDER, String SPACES) {
        System.out.println(DIVIDER);
        System.out.println(SPACES + " Nice! I've marked this task as done:");
        try {
            int index = Integer.parseInt(input.substring(5));
            tasks.markTask(index);
        } catch (NumberFormatException e) {
            System.out.println(SPACES + " Invalid input. Please provide a valid task index.");
        }
        System.out.println(DIVIDER + "\n");
    }

    private static void handleTodo(TaskList tasks, String input, String DIVIDER, String SPACES) {
        System.out.println(DIVIDER);
        System.out.println(SPACES + " Got it. I've added this task:");
        tasks.addTodo(input.substring(5));
        System.out.println(SPACES + " Now you have " + tasks.getTaskCount() + " tasks in the list.");
        System.out.println(DIVIDER + "\n");
    }

    private static void handleDeadline(TaskList tasks, String input, String DIVIDER, String SPACES) {
        System.out.println(DIVIDER);
        System.out.println(SPACES + " Got it. I've added this task:");
        try {
            String[] deadline = input.substring(9).split("/");
            tasks.addDeadline(deadline[0], deadline[1]);
            System.out.println(SPACES + " Now you have " + tasks.getTaskCount() + " tasks in the list.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(SPACES + " Invalid deadline format. Use: deadline <description> /by <time>");
        }
        System.out.println(DIVIDER + "\n");
    }

    private static void handleEvent(TaskList tasks, String input, String DIVIDER, String SPACES) {
        System.out.println(DIVIDER);
        System.out.println(SPACES + " Got it. I've added this task:");
        try {
            String[] deadline2 = input.substring(6).split("/");
            deadline2[1] = deadline2[1].substring(5);
            deadline2[2] = deadline2[2].substring(3);
            tasks.addEvent(deadline2[0], deadline2);
            System.out.println(SPACES + " Now you have " + tasks.getTaskCount() + " tasks in the list.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(SPACES + " Invalid event format. Use: event <description> /from <time> /to <time>");
        }
        System.out.println(DIVIDER + "\n");
    }

    private static void handleUnknownCommand(String DIVIDER, String SPACES) {
        System.out.println(DIVIDER);
        System.out.println(SPACES + " ☹ OOPS!!! I'm sorry, but I don't know what that means");
        System.out.println(DIVIDER + "\n");
    }
}
