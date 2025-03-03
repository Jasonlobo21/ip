package terry;

import java.util.Scanner;

/**
 * The main entry point of the Terry application.
 * <p>
 * This class initializes the task list, loads tasks from storage, and starts the user
 * input processing loop.
 * </p>
 */
public class Terry {

    /**
     * The list of tasks managed by the application.
     */
    public static TaskList tasks = new TaskList();

    /**
     * The main method that starts the Terry application.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {

        Ui.printGreeting();
        Storage.loadTasks(tasks);
        Scanner in = new Scanner(System.in);

        boolean isLooping = true;
        while (isLooping){
            isLooping = Parser.handleInput(tasks, in.nextLine());
        }
    }
}
