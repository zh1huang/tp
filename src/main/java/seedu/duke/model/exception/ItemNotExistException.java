package seedu.duke.model.exception;

//@@author yuejunfeng0909
public class ItemNotExistException extends Exception {

    public static final String MESSAGE_ITEM_NOT_EXIST = "Item %s does not exist";

    public ItemNotExistException(String itemName) {
        super(String.format(MESSAGE_ITEM_NOT_EXIST, itemName));
    }
}
