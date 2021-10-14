package seedu.duke.command.exception;

public abstract class CommandException extends Exception {
    public CommandException(String message) {
        super(message);
    }
}
