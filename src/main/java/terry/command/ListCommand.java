package terry.command;

import terry.TaskList;
import terry.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList) {
        Ui.printDivider();
        taskList.listTasks();
        Ui.printDivider();
    }
}
