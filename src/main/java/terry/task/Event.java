package terry.task;

/**
 * Represents an event task with a start and end time.
 */
public class Event extends Task {

    /** The start time of the event. */
    private String from;

    /** The end time of the event. */
    private String to;

    /**
     * Constructs an Event task with the specified name, start time, and end time.
     *
     * @param name the name or description of the event
     * @param from the start time of the event
     * @param to   the end time of the event
     */
    public Event(String name, String from, String to) {
        super(name, "[E]");
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start time of the event.
     *
     * @return the start time as a String
     */
    public String getFrom() {
        return from;
    }

    /**
     * Returns the end time of the event.
     *
     * @return the end time as a String
     */
    public String getTo() {
        return to;
    }

    /**
     * Returns the string representation of the event task.
     *
     * @return the formatted string including task type, status, name, start and end time
     */
    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)", super.toString(), from, to);
    }
}
