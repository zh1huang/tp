package seedu.duke.logic.command.exception;

public abstract class CommandException extends Exception {
    public CommandException(String message) {
        super(message);
    }
}
