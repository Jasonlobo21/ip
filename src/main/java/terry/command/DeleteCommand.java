package terry.command;

import terry.Storage;
import terry.TaskList;
import terry.Ui;

import terry.exception.TerryException;

public class DeleteCommand extends Command {

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

        tasks.deleteTask(index);
        Storage.rewriteTasksToFile(tasks);

        Ui.printWithSpaces("Noted. I've removed this task:");
        Ui.printWithSpaces("  " + tasks.getTask(index - 1));
        Ui.printWithSpaces("Now you have " + tasks.getSize() + " tasks in the list.");
        Ui.printDivider();
    }

}
