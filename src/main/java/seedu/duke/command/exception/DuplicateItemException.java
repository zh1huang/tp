package seedu.duke.command.exception;

public class DuplicateItemException extends CommandException {

    public DuplicateItemException(String errorMessage) {
        super(errorMessage);
    }
}
