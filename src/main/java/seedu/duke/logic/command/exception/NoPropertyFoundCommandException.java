package seedu.duke.logic.command.exception;

//@@author haoyusimon
public class NoPropertyFoundCommandException extends CommandException {

    public static final String MESSAGE_NO_PROPERTY_FOUND = "Property %s does not exist";

    public NoPropertyFoundCommandException(String property) {
        super(String.format(MESSAGE_NO_PROPERTY_FOUND,
                property));
    }
}
