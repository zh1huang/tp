package seedu.duke.command;

import seedu.duke.model.Item;
import seedu.duke.model.ItemContainer;

import java.math.BigDecimal;

/**
 * The command that adds a new item to the list.
 */
public class AddCommand extends Command {
    private static final String ADD_COMPLETE_MESSAGE =
            "This item has been added to the list."; //to be added to UI part later
    private final Item newItem;

    /**
     * AddCommand Constructor.
     * @param name the name of the new item
     * @param purchaseCost the cost of the item
     * @param sellingPrice the price of the item
     */
    public AddCommand(String name, String purchaseCost, String sellingPrice) {
        this.newItem = new Item(name, purchaseCost, sellingPrice);
    }

    /**
     * Executes the operation of adding the item to the list.
     * @param list the itemContainer to remove the item from
     */
    @Override
    public void execute(ItemContainer list) {
        list.addItem(newItem);
        System.out.println(ADD_COMPLETE_MESSAGE);
    }
}
