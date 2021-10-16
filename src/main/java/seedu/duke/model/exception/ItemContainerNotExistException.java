package seedu.duke.model.exception;

public class ItemContainerNotExistException extends Exception {

    public static final String MESSAGE_ITEM_NOT_EXIST = "The item container %s does not exist";

    public ItemContainerNotExistException(String itemContainerName) {
        super(String.format(MESSAGE_ITEM_NOT_EXIST, itemContainerName));
    }

}
