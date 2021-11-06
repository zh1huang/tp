package seedu.duke.model.exception;

//@@author yuejunfeng0909
public class DuplicateItemModelException extends ModelException {

    public static final String MESSAGE_DUPLICATE_ITEM = "The item %s already exists";

    public DuplicateItemModelException(String nameOfDuplicateItem) {
        super(String.format(MESSAGE_DUPLICATE_ITEM, nameOfDuplicateItem));
    }
}
