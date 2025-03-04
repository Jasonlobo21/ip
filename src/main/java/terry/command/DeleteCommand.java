package terry.command;

import terry.Storage;
import terry.TaskList;
import terry.Ui;

import terry.exception.TerryException;

/**
 * Command to delete a task from the task list.
 */
public class DeleteCommand extends Command {

    /**
     * Executes the delete command to remove a task.
     *
     * @param tasks the current TaskList
     * @param input the user input containing the task number to delete
     * @throws TerryException if the input format is invalid or the task number is out of range
     */
    @Override
    public void execute(TaskList tasks, String input) throws TerryException {
        Ui.printDivider();
        String[] parts = input.split(" ");
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


        Ui.printWithSpaces(" Noted. I've removed this task:");
        Ui.printWithSpaces("  " + tasks.getTask(index - 1));

        tasks.deleteTask(index);
        Storage.rewriteTasksToFile(tasks);

        Ui.printWithSpaces(" Now you have " + tasks.getSize() + " tasks in the list.");
        Ui.printDivider();
    }
}
