package seedu.duke.logic.command.exception;

//@@author haoyusimon
public abstract class CommandException extends Exception {

    public CommandException(String message) {
        super(message);
    }
}
