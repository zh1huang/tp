package seedu.duke.command;

import seedu.duke.model.Item;
import seedu.duke.model.ItemContainer;

/**
 * The command that edits a selected item.
 */
public class EditCommand extends Command {
    private final Item selectedItem;
    private final Item updatedItem;
    private static final String UPDATE_COMPLETE_MESSAGE = "This item has been updated."; //to be added to UI part later


    /**
     * The EditCommand constructor.
     *
     * @param name         the name of the selected item
     * @param purchaseCost the new cost of the item
     * @param sellingPrice the new price of the item
     * @param list         the itemContainer where the selected item is stored in
     */
    public EditCommand(String name, String purchaseCost, String sellingPrice, ItemContainer list) {
        this.selectedItem = list.getItem(name);
        this.updatedItem = new Item(name, purchaseCost, sellingPrice);
    }

    /**
     * Executes the update operation.
     *
     * @param list the itemContainer to remove the item from
     */
    public void execute(ItemContainer list) {
        list.updateItem(selectedItem, updatedItem);
        System.out.println(UPDATE_COMPLETE_MESSAGE);
    }
}
