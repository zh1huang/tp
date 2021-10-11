package seedu.duke.model.exception;

public class DuplicateItemException extends RuntimeException {

    public static final String MESSAGE_DUPLICATE_ITEM = "The item %s already exists";

    public DuplicateItemException(String nameOfDuplicateItem) {
        super(String.format(MESSAGE_DUPLICATE_ITEM, nameOfDuplicateItem));
    }
}
