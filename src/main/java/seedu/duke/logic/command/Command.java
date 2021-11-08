package seedu.duke.logic.command;

import seedu.duke.logic.command.exception.CommandException;
import seedu.duke.model.exception.ModelException;

//@@author haoyusimon
/**
 * The abstract command.
 */
public abstract class Command {

    /**
     * Abstract execute method.
     *
     * @throws CommandException if anything goes wrong
     */
    public abstract String execute() throws CommandException, ModelException;


    /**
     * Checks if exit command has been called.
     *
     * @return true if exit command is called
     */
    public boolean isExit() {
        return false;
    }
}

