package terry;

import terry.command.AddTaskCommand;
import terry.command.DeleteCommand;
import terry.command.FindCommand;
import terry.command.ListCommand;
import terry.command.MarkCommand;
import terry.exception.TerryException;

/**
 * Parses user input and delegates commands to their respective handlers.
 */
public class Parser {

    /**
     * Processes the user input by determining the command and executing it.
     *
     * @param tasks the current TaskList
     * @param input the raw user input
     * @return false if the command is to exit the program, true otherwise
     */
    public static boolean handleInput(TaskList tasks, String input) {
        try {
            String command = input.split(" ")[0].toLowerCase();
            switch (command) {
            case "bye":
                return Ui.printGoodbye();
            case "list":
                new ListCommand().execute(tasks, input);
                return true;
            case "unmark":
            case "mark":
                new MarkCommand().execute(tasks, input);
                return true;
            case "todo":
            case "deadline":
            case "event":
                new AddTaskCommand().execute(tasks, input);
                return true;
            case "help":
                Ui.printHelp();
                return true;
            case "delete":
                new DeleteCommand().execute(tasks, input);
                return true;
            case "find":
                new FindCommand().execute(tasks, input);
                return true;
            default:
                Ui.printUnknownCommand();
                return true;
            }
        } catch (TerryException e) {
            System.out.println(e.getMessage());
            Ui.printDivider();
        }
        return true;
    }
}
