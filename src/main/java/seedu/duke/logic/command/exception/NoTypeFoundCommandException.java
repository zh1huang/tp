package seedu.duke.logic.command.exception;

//@@author haoyusimon
public class NoTypeFoundCommandException extends CommandException {

    public static final String MESSAGE_NO_TYPE_FOUND = "Type %s does not exist";

    public NoTypeFoundCommandException(String property) {
        super(String.format(MESSAGE_NO_TYPE_FOUND,
                property));
    }
}
