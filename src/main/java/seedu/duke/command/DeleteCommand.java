package seedu.duke.command;

import seedu.duke.model.Item;
import seedu.duke.model.ItemContainer;

/**
 * The command that deletes a selected item.
 */
public class DeleteCommand extends Command {
    private static final String DELETE_COMPLETE_MESSAGE =
            "This item has been removed from the list."; //to be added to UI part later
    private final Item selectedItem;

    /**
     * The DeleteCommand constructor.
     *
     * @param name the name of the item
     * @param list the itemContainer where the item is stored in
     */
    public DeleteCommand(String name, ItemContainer list) {
        this.selectedItem = list.getItem(name);
        // will add exception handling (cannot find item) once the the getItem() method adds exception
    }


    /**
     * Executes the delete operation.
     *
     * @param list the itemContainer to remove the item from
     */
    public void execute(ItemContainer list) {
        list.deleteItem(selectedItem);
        System.out.println(DELETE_COMPLETE_MESSAGE);
    }
}
