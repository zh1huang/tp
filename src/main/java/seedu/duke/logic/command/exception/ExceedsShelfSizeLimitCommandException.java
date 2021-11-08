package seedu.duke.logic.command.exception;

//@@author haoyusimon
public class ExceedsShelfSizeLimitCommandException extends CommandException {

    public static final String MESSAGE_EXCEEDS_LIMIT =
            "Adding items is unsuccessful because you have exceeded the size limit of this shelf.\n"
                    + "At most 999 items are allowed for each shelf. Current number of items in this shelf: %d\n"
                    + "Please delete items from this shelf or use a new shelf.";

    public ExceedsShelfSizeLimitCommandException(int currentSize) {
        super(String.format(MESSAGE_EXCEEDS_LIMIT,
                currentSize));
    }

    public ExceedsShelfSizeLimitCommandException(String errorMessage) {
        super(errorMessage);
    }
}
