package terry.command;

import terry.Storage;
import terry.TaskList;
import terry.Ui;
import terry.exception.TerryException;

public class MarkCommand extends Command {

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

        if(parts[0].equals("mark")) {
            Ui.printWithSpaces("Nice! I've marked this task as done:");
            tasks.markTask(index);
        } else {
            Ui.printWithSpaces("OK, I've marked this task as not done yet:");
            tasks.unmarkTask(index);
        }

        Storage.rewriteTasksToFile(tasks);

        Ui.printWithSpaces("  " + tasks.getTask(index - 1));
        Ui.printDivider();
    }
}
