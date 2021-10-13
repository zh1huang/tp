package seedu.duke.command;

import seedu.duke.model.Item;
import seedu.duke.model.ItemContainer;
import seedu.duke.command.exception.ItemNotExistException;

/**
 * The command that deletes a selected item.
 */
public class DeleteCommand extends Command {
    private static final String DELETE_COMPLETE_MESSAGE =
            "This item has been removed from the list."; //to be added to UI part later
    private final String name;

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
    public void execute(ItemContainer list) throws ItemNotExistException {
        try {
            Item selectedItem = list.getItem(name);
            list.deleteItem(selectedItem);
            System.out.println(DELETE_COMPLETE_MESSAGE);
        } catch (seedu.duke.model.exception.ItemNotExistException e) {
            throw new ItemNotExistException(e.getMessage());
        }

    }
}
