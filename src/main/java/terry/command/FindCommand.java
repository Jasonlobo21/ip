package terry.command;

import terry.TaskList;
import terry.Ui;
import terry.exception.TerryException;

/**
 * Command to search for tasks that contain a specified keyword.
 */
public class FindCommand extends Command {

    /**
     * Executes the find command to search and display matching tasks.
     *
     * @param tasks the current TaskList
     * @param input the user input containing the keyword to search for
     * @throws TerryException if the input format is invalid
     */
    @Override
    public void execute(TaskList tasks, String input) throws TerryException {
        Ui.printDivider();
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            throw new TerryException("use 'help' to see the correct format");
        }

        Ui.printWithSpaces("Here are the matching tasks in your list: ");

        boolean notFound = true;
        for (int i = 0; i < tasks.getSize(); i++) {
            if (tasks.getTask(i).getName().contains(parts[1])) {
                notFound = false;
                Ui.printListedFormat(tasks, i);
            }
        }

        if (notFound) {
            Ui.printWithSpaces("No matching '" + parts[1] +  "' tasks found.");
        }
        Ui.printDivider();
    }
}
