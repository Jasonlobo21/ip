package terry.command;

import terry.Storage;
import terry.TaskList;
import terry.Ui;
import terry.exception.TerryException;
import terry.task.Deadline;
import terry.task.Event;
import terry.task.Task;
import terry.task.Todo;

/**
 * Command to add a new task (todo, deadline, or event) to the task list.
 */
public class AddTaskCommand extends Command {

    /**
     * Executes the add task command.
     * <p>
     * This method parses the input, creates the appropriate task type, adds it to the task list,
     * updates storage, and prints the confirmation message.
     * </p>
     *
     * @param tasks the current TaskList
     * @param input the user input containing task details
     * @throws TerryException if the input format is invalid
     */
    @Override
    public void execute(TaskList tasks, String input) throws TerryException {
        Ui.printDivider();
        Task newTask = null;
        String[] parts = input.trim().split(" ");

        switch (parts[0]) {
        case "todo":
            if (parts.length < 2) {
                throw new TerryException("Invalid todo command (e.g., todo run)");
            }

            newTask = new Todo(input.substring(5));
            break;
        case "deadline":
            if (!input.contains(" /by")) {
                throw new TerryException("Invalid deadline command (e.g., deadline run /by 9pm)");
            }

            String[] deadline = input.substring(9).split("/by");
            if (deadline.length < 2) {
                throw new TerryException("Invalid deadline command (e.g., deadline run /by 9pm)");
            }

            newTask = new Deadline(deadline[0].trim(), deadline[1]);
            break;
        case "event":
            if (!input.contains("/from") || !input.contains("/to")) {
                throw new TerryException("Invalid event command (e.g., event run /from 7pm /to 9pm)");
            }

            String[] event = input.substring(6).split("/");
            event[1] = event[1].substring(4);
            event[2] = event[2].substring(2);
            if (event[1].isEmpty() || event[2].isEmpty()) {
                throw new TerryException("Invalid event command (e.g., event run /from 7pm /to 9pm)");
            }

            newTask = new Event(event[0].trim(), event[1].trim(), event[2].trim());
            break;
        default:
            throw new TerryException("Invalid type of task");
        }

        tasks.addTask(newTask);
        Storage.appendTaskToFile(newTask);

        Ui.printWithSpaces(" Got it. I've added this task:");
        Ui.printWithSpaces("   " + tasks.getTask(tasks.getSize() - 1));
        Ui.printWithSpaces(" Now you have " + tasks.getSize() + " tasks in the list.");
        Ui.printDivider();
    }
}
