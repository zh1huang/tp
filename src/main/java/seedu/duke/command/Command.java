package seedu.duke.command;

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
     */
    public abstract void execute(ItemContainer list) throws Exception;
}

