public class Event extends Task{
    private String[] timePeriod;

    public Event(String name, String[] timePeriod) {
        super(name,"[E]");
        this.timePeriod = timePeriod;
    }

    @Override
    public String toString() {
        return super.toString() + "(from: " + timePeriod[1] + "to: " + timePeriod[2] + ")";
    }
}
