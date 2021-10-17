package seedu.duke.model.exception;

public class DuplicateItemContainerException extends Exception {

    public static final String MESSAGE_DUPLICATE_ITEM = "The item container %s already exists";

    public DuplicateItemContainerException(String name) {
        super(String.format(MESSAGE_DUPLICATE_ITEM, name));
    }
}
