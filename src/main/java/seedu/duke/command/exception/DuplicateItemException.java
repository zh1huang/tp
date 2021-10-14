package seedu.duke.command.exception;

public class DuplicateItemException extends CommandException {

    public static final String MESSAGE_DUPLICATE_ITEM = "The item %s already exists";

    public DuplicateItemException(String nameOfDuplicateItem) {
        super(String.format(MESSAGE_DUPLICATE_ITEM, nameOfDuplicateItem));
    }
}
