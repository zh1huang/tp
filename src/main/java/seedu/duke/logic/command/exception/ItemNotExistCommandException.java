package seedu.duke.logic.command.exception;

//@@author haoyusimon
public class ItemNotExistCommandException extends CommandException {

    public ItemNotExistCommandException(String errorMessage) {
        super(errorMessage);
    }
}
