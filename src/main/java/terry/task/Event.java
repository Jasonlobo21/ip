package terry.task;

public class Event extends Task {
    private String from;
    private String to;

    public Event(String name, String from, String to) {
        super(name, "[E]");
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)", super.toString(), from, to);
    }
}
