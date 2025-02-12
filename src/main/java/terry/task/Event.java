package terry.task;

public class Event extends Task {
    private String[] timePeriod;

    public Event(String name, String[] timePeriod) {
        super(name, "[E]");
        this.timePeriod = timePeriod;
    }

    @Override
    public String toString() {
        return String.format("%s (from: %sto: %s)", super.toString(), timePeriod[1], timePeriod[2]);
    }
}
