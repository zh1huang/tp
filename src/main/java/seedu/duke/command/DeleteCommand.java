package seedu.duke.command;

import seedu.duke.model.Item;
import seedu.duke.model.Shelf;
import seedu.duke.command.exception.ItemNotExistException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The command that deletes a selected item.
 */
public class DeleteCommand extends Command {
    private static final String DELETE_COMPLETE_MESSAGE =
            "This item has been removed from the list."; //to be added to UI part later
    private final String name;
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * The DeleteCommand constructor.
     *
     * @param name the name of the item
     */
    public DeleteCommand(String name) {
        this.name = name;
    }


    /**
     * Executes the delete operation.
     *
     * @param list the itemContainer to remove the item from
     * @throws ItemNotExistException if the specified item does not exist
     */
    public void execute(Shelf list) throws ItemNotExistException {
        try {
            int sizeBeforeDeleting = list.getSize();
            Item selectedItem = list.getItem(name);
            list.deleteItem(selectedItem);
            int sizeAfterDeleting = list.getSize();
            assert sizeBeforeDeleting - 1 == sizeAfterDeleting :
                    "After deleting an item the list size should decrease by 1";
            System.out.println(DELETE_COMPLETE_MESSAGE);
            logger.log(Level.INFO, "DeleteCommand successfully executed.");
        } catch (seedu.duke.model.exception.ItemNotExistException e) {
            logger.log(Level.WARNING, String.format("DeleteCommand failed to execute with error message %s",
                    e.getMessage()));
            throw new ItemNotExistException(e.getMessage());
        }

    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand command = (DeleteCommand) other;
        return name.equals(command.name);
    }
}
