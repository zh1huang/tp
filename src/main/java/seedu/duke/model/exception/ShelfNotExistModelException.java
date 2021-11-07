package seedu.duke.model.exception;

//@@author yuejunfeng0909
public class ShelfNotExistModelException extends ModelException {

    public static final String MESSAGE_SHELF_NOT_EXIST =
            "The shelf \"%s\" does not exist. Please create a new shelf first by keying \"create shlv/SHELF_NAME\"";

    public ShelfNotExistModelException(String itemContainerName) {
        super(String.format(MESSAGE_SHELF_NOT_EXIST, itemContainerName));
    }

}
