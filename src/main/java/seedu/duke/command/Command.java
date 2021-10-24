package seedu.duke.command;

import seedu.duke.command.exception.CommandException;
import seedu.duke.model.Shelf;

/**
 * The abstract command.
 */
public abstract class Command {

    /**
     * Abstract execute method.
     *
     * @param list the Shelf that manipulates the item
     * @throws CommandException if anything goes wrong
     */
    public abstract void execute() throws CommandException;


    /**
     * Checks if exit command has been called.
     *
     * @return true if exit command is called
     */
    public boolean isExit() {
        return false;
    }
}

