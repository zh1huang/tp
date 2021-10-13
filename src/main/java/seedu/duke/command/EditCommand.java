package seedu.duke.command;

import seedu.duke.model.Item;
import seedu.duke.model.ItemContainer;

/**
 * The command that edits a selected item.
 */
public class EditCommand extends Command {
    private final String name;
    private final String purcaseCost;
    private final String sellingPrice;
    private static final String UPDATE_COMPLETE_MESSAGE = "This item has been updated.";
    //to be added to UI part later

    /**
     * The EditCommand constructor.
     *
     * @param name         the name of the selected item
     * @param purchaseCost the new cost of the item
     * @param sellingPrice the new price of the item
     */
    public EditCommand(String name, String purchaseCost, String sellingPrice) {
        this.name = name;
        this.purcaseCost = purchaseCost;
        this.sellingPrice = sellingPrice;
    }

    /**
     * Executes the update operation.
     *
     * @param list the itemContainer to remove the item from
     */
    public void execute(ItemContainer list) {
        Item selectedItem = list.getItem(name);
        selectedItem.setPurchaseCost(purcaseCost);
        selectedItem.setSellingPrice(sellingPrice);
        System.out.println(UPDATE_COMPLETE_MESSAGE);
    }
}
