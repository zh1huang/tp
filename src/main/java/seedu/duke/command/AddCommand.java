package seedu.duke.command;

import seedu.duke.model.Item;
import seedu.duke.model.ItemContainer;
import seedu.duke.command.exception.IllegalArgumentException;
import seedu.duke.command.exception.DuplicateItemException;

/**
 * The command that adds a new item to the list.
 */
public class AddCommand extends Command {
    private static final String ADD_COMPLETE_MESSAGE =
            "This item has been added to the list."; //to be added to UI part later
    private final String name;
    private final String purchaseCost;
    private final String sellingPrice;

    /**
     * AddCommand Constructor.
     *
     * @param name         the name of the new item
     * @param purchaseCost the cost of the item
     * @param sellingPrice the price of the item
     */
    public AddCommand(String name, String purchaseCost, String sellingPrice) {
        this.name = name;
        this.purchaseCost = purchaseCost;
        this.sellingPrice = sellingPrice;
    }

    /**
     * Executes the operation of adding the item to the list.
     *
     * @param list the itemContainer to remove the item from
     * @throws IllegalArgumentException if the input argument is wrong
     */
    @Override
    public void execute(ItemContainer list) throws IllegalArgumentException, DuplicateItemException {
        try {
            Item newItem = new Item(name, purchaseCost, sellingPrice);
            list.addItem(newItem);
            System.out.println(ADD_COMPLETE_MESSAGE);
        } catch (seedu.duke.model.exception.IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (seedu.duke.model.exception.DuplicateItemException e) {
            throw new DuplicateItemException(e.getMessage());
        }

    }
}
