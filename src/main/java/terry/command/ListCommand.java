package terry.command;

import terry.TaskList;
import terry.Ui;

/**
 * Command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command to display all tasks.
     *
     * @param tasks the current TaskList
     * @param input the user input (not used for listing)
     */
    @Override
    public void execute(TaskList tasks, String input) {
        Ui.printDivider();

        if (tasks.isEmpty()) {
            Ui.printWithSpaces("No tasks available.");
        } else {
            for (int i = 0; i < tasks.getSize(); i++) {
                Ui.printListedFormat(tasks, i);
            }
        }
        Ui.printDivider();
    }
}
