package terry.command;

import terry.TaskList;
import terry.exception.TerryException;

/**
 * An abstract command that can be executed.
 * <p>
 * All specific command types must extend this class and implement the execute method.
 * </p>
 */
public abstract class Command {

    /**
     * Executes the command with the given task list and input.
     *
     * @param taskList the TaskList to operate on
     * @param input    the raw user input for the command
     * @throws TerryException if an error occurs during command execution
     */
    public abstract void execute(TaskList taskList, String input) throws TerryException;
}
