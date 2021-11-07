package seedu.duke.model.exception;

//@@author haoyusimon
public class DeniedAccessToShelfModelException extends ModelException {

    public static final String MESSAGE_SHELF_NOT_EXIST =
            "You are not allowed to change or read the \"%s\" shelf.";

    public DeniedAccessToShelfModelException(String itemContainerName) {
        super(String.format(MESSAGE_SHELF_NOT_EXIST, itemContainerName));
    }

}
