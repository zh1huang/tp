package seedu.duke.model.exception;

public class NoCommandFoundException extends RuntimeException {
    public static final String MESSAGE_COMMAND_NOT_FOUND = "Item %s does not exist";
    public NoCommandFoundException() {
        super(MESSAGE_COMMAND_NOT_FOUND);
    }
}
