package terry.command;

import terry.Storage;
import terry.TaskList;
import terry.Ui;
import terry.exception.TerryException;

/**
 * Command to mark or unmark a task as done.
 */
public class MarkCommand extends Command {

    /**
     * Executes the mark/unmark command.
     * <p>
     * This command marks a task as done if the command is "mark", or marks it as not done if the command is "unmark".
     * It also updates the storage file after modification.
     * </p>
     *
     * @param tasks the current TaskList
     * @param input the user input containing the command and task number
     * @throws TerryException if the input format is invalid or the task number is out of range
     */
    @Override
    public void execute(TaskList tasks, String input) throws TerryException {
        Ui.printDivider();
        String[] parts = input.trim().split(" ");

        if (parts.length < 2) {
            throw new TerryException("use 'help' to see the correct format");
        }

        if (!parts[1].matches("\\d+")) {
            throw new TerryException("Insert a valid task number");
        }

        int index = Integer.parseInt(parts[1]);
        if (index < 1 || index > tasks.getSize()) {
            throw new TerryException("Task number not in range");
        }

        if (parts[0].equals("mark")) {
            Ui.printWithSpaces(" Nice! I've marked this task as done:");
            tasks.markTask(index);
        } else {
            Ui.printWithSpaces(" OK, I've marked this task as not done yet:");
            tasks.unmarkTask(index);
        }

        Storage.rewriteTasksToFile(tasks);

        Ui.printWithSpaces("  " + tasks.getTask(index - 1));
        Ui.printDivider();
    }
}
