package seedu.duke.logic.command.exception;

public class DuplicateItemCommandException extends CommandException {

    public DuplicateItemCommandException(String errorMessage) {
        super(errorMessage);
    }
}
