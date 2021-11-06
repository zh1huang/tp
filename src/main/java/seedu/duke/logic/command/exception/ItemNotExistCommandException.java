package seedu.duke.logic.command.exception;

public class ItemNotExistCommandException extends CommandException {

    public ItemNotExistCommandException(String errorMessage) {
        super(errorMessage);
    }
}
