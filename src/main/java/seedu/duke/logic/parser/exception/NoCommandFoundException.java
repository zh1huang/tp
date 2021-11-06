package seedu.duke.logic.parser.exception;

public class NoCommandFoundException extends Exception {

    public static final String MESSAGE_COMMAND_NOT_FOUND = "Item %s does not exist";

    public NoCommandFoundException() {
        super(MESSAGE_COMMAND_NOT_FOUND);
    }
}
