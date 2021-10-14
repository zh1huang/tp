package seedu.duke.command;

import seedu.duke.command.exception.CommandException;
import seedu.duke.model.ItemContainer;
import seedu.duke.model.exception.DuplicateItemException;

/**
 * The abstract command.
 */
public abstract class Command {

    /**
     * Abstract execute method.
     *
     * @param list the ItemContainer that manipulates the item
     * @throws CommandException if anything goes wrong
     */
    public abstract void execute(ItemContainer list) throws CommandException;
}

