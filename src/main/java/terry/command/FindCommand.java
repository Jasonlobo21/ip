package terry.command;

import terry.TaskList;
import terry.Ui;
import terry.exception.TerryException;

public class FindCommand extends Command {

    @Override
    public void execute(TaskList tasks, String input) throws TerryException {
        Ui.printDivider();
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            throw new TerryException("use 'help' to see the correct format");
        }

        Ui.printWithSpaces("Here are the matching tasks in your list: ");

        for (int i = 0; i < tasks.getSize(); i++) {
            if (tasks.getTask(i).getName().contains(parts[1])) {
                Ui.printListedFormat(tasks, i);
            }
        }
        Ui.printDivider();
    }
}
