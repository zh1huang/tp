package seedu.duke.model.exception;

//@@author yuejunfeng0909
public class ShelfNotExistException extends Exception {

    public static final String MESSAGE_ITEM_NOT_EXIST =
            "The shelf \"%s\" does not exist. Please create a new shelf first by keying \"create shlv/SHELF_NAME\"";

    public ShelfNotExistException(String itemContainerName) {
        super(String.format(MESSAGE_ITEM_NOT_EXIST, itemContainerName));
    }

}
