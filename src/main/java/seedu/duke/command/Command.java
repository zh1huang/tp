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
    public abstract void execute(Shelf list) throws CommandException;
}

