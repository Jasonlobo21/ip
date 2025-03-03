package terry.command;

import terry.TaskList;
import terry.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, String input) {
        Ui.printDivider();

        if (tasks.isEmpty()) {
            Ui.printWithSpaces("No tasks available.");
        } else {
            for (int i = 0; i < tasks.getSize(); i++) {
                Ui.printWithSpaces((i + 1) + ". " + tasks.getTask(i));
            }
        }
        Ui.printDivider();
    }
}
