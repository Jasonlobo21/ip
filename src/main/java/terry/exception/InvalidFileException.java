package terry.exception;

public class InvalidFileException extends TerryException {
    public InvalidFileException() {
        super("Invalid file format. Please provide a valid file path ☹");
    }
}
