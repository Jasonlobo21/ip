package terry.command;

import terry.TaskList;

public abstract class Command {
    public abstract void execute(TaskList taskList);
}
