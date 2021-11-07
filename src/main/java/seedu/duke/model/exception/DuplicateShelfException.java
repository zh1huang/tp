package seedu.duke.model.exception;

//@@author yuejunfeng0909
public class DuplicateShelfException extends Exception {

    public static final String MESSAGE_DUPLICATE_ITEM = "The shelf %s already exists";

    public DuplicateShelfException(String name) {
        super(String.format(MESSAGE_DUPLICATE_ITEM, name));
    }
}
