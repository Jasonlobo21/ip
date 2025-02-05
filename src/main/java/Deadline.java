public class Deadline extends Task {
    private String dueDate;

    public Deadline(String name, String dueDate) {
        super(name,"[D]");
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return super.toString() + "(by: " + dueDate + ")";
    }
}
