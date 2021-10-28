package seedu.duke.command.exception;

public class NoTypeFoundException extends CommandException {

    public static final String MESSAGE_NO_TYPE_FOUND = "Type %s does not exist";

    public NoTypeFoundException(String property) {
        super(String.format(MESSAGE_NO_TYPE_FOUND,
                property));
    }
}
