package terry.command;

import terry.TaskList;
import terry.exception.TerryException;

public abstract class Command {
    public abstract void execute(TaskList taskList, String input) throws TerryException;
}
