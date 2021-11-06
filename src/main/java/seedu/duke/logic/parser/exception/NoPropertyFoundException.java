package seedu.duke.logic.parser.exception;

public class NoPropertyFoundException extends Exception {

    public static final String MESSAGE_NO_PROPERTY_FOUND = "Property %s does not exist";

    public NoPropertyFoundException(String property) {
        super(String.format(MESSAGE_NO_PROPERTY_FOUND,
                property));
    }
}
