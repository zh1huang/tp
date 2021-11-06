package seedu.duke.model.exception;

//@@author yuejunfeng0909
public class ItemNotExistModelException extends ModelException {

    public static final String MESSAGE_ITEM_NOT_EXIST = "Item %s does not exist";

    public ItemNotExistModelException(String itemName) {
        super(String.format(MESSAGE_ITEM_NOT_EXIST, itemName));
    }
}
