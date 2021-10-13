package seedu.duke.command;

import seedu.duke.model.Item;
import seedu.duke.model.ItemContainer;

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
     */
    public void execute(ItemContainer list) {
        Item selectedItem = list.getItem(name);
        list.deleteItem(selectedItem);
        System.out.println(DELETE_COMPLETE_MESSAGE);
    }
}
