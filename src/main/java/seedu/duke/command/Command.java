package seedu.duke.command;

import seedu.duke.command.exception.CommandException;
import seedu.duke.model.Shelf;
import seedu.duke.model.exception.IllegalArgumentException;
import seedu.duke.model.exception.ShelfNotExistException;

/**
 * The abstract command.
 */
public abstract class Command {

    /**
     * Abstract execute method.
     *
     * @throws CommandException if anything goes wrong
     */
    public abstract String execute() throws CommandException, ShelfNotExistException, IllegalArgumentException;


    /**
     * Checks if exit command has been called.
     *
     * @return true if exit command is called
     */
    public boolean isExit() {
        return false;
    }
}

