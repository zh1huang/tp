package seedu.duke.model.exception;

//@@author yuejunfeng0909
public class DuplicateItemException extends Exception {

    public static final String MESSAGE_DUPLICATE_ITEM = "The item %s already exists";

    public DuplicateItemException(String nameOfDuplicateItem) {
        super(String.format(MESSAGE_DUPLICATE_ITEM, nameOfDuplicateItem));
    }
}
