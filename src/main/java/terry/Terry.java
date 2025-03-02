package terry;

import terry.task.*;
import java.util.Scanner;

public class Terry {
    public static TaskList tasks = new TaskList();

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
