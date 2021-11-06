package seedu.duke.model.exception;

//@@author yuejunfeng0909
public class DuplicateShelfModelException extends ModelException {

    public static final String MESSAGE_DUPLICATE_ITEM = "The shelf %s already exists";

    public DuplicateShelfModelException(String name) {
        super(String.format(MESSAGE_DUPLICATE_ITEM, name));
    }
}
