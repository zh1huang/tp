package seedu.duke.command.exception;

public class ItemNotExistException extends CommandException {

    public ItemNotExistException(String errorMessage) {
        super(errorMessage);
    }
}
