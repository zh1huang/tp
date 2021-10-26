package seedu.duke.command;

import seedu.duke.command.exception.ShelfNotExistException;
import seedu.duke.model.Item;
import seedu.duke.model.Shelf;
import seedu.duke.command.exception.IllegalArgumentException;
import seedu.duke.command.exception.DuplicateItemException;
import seedu.duke.model.ShelfList;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The command that adds a new item to the list.
 */
public class AddCommand extends Command {
    private static final String ADD_COMPLETE_MESSAGE_SINGLE =
            "This item has been added to the list."; //to be added to UI part later
    private static final String ADD_COMPLETE_MESSAGE_MULTIPLE =
            " items have been added to the list."; //to be added to UI part later
    private final String name;
    private final String purchaseCost;
    private final String sellingPrice;
    private final int quantity;
    private final String shelfName;
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * AddCommand Constructor.
     *
     * @param name         the name of the new item
     * @param purchaseCost the cost of the item
     * @param sellingPrice the price of the item
     */
    public AddCommand(String name, String purchaseCost, String sellingPrice, String quantity, String shelfName) {
        this.name = name;
        this.purchaseCost = purchaseCost;
        this.sellingPrice = sellingPrice;
        this.quantity = Integer.parseInt(quantity);
        this.shelfName = shelfName;
    }

    /**
     * Executes the operation of adding the item to the list.
     *
     * @throws IllegalArgumentException if the input argument is wrong
     * @throws DuplicateItemException if exactly the same item is added to the list
     */
    @Override
    public String execute() throws IllegalArgumentException, DuplicateItemException,
            ShelfNotExistException {
        try {
            Shelf selectedShelf = ShelfList
                    .getShelfList()
                    .getShelf(shelfName);
            for (int i = 0; i < quantity; i++) {
                int sizeBeforeAdding = selectedShelf.getSize();
                Item newItem = new Item(name, purchaseCost, sellingPrice);
                selectedShelf.addItem(newItem);
                int sizeAfterAdding = selectedShelf.getSize();
                assert sizeBeforeAdding + 1 == sizeAfterAdding :
                        "After adding an item the list size should increase by 1";
                System.out.println(ADD_COMPLETE_MESSAGE_SINGLE);
                logger.log(Level.INFO, "AddCommand successfully executed.");
            }
            if (quantity > 1) {
                return quantity + ADD_COMPLETE_MESSAGE_MULTIPLE;
            } else {
                return ADD_COMPLETE_MESSAGE_SINGLE;
            }
        } catch (seedu.duke.model.exception.IllegalArgumentException e) {
            logger.log(Level.WARNING, String.format("AddCommand failed to execute with error message %s",
                    e.getMessage()));
            throw new IllegalArgumentException(e.getMessage());
        } catch (seedu.duke.model.exception.DuplicateItemException e) {
            logger.log(Level.WARNING, String.format("AddCommand failed to execute with error message %s",
                    e.getMessage()));
            throw new DuplicateItemException(e.getMessage());
        } catch (seedu.duke.model.exception.ShelfNotExistException e) {
            throw new ShelfNotExistException(e.getMessage());
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
        if (!(other instanceof AddCommand)) {
            return false;
        }

        AddCommand command = (AddCommand) other;
        return name.equals(command.name)
                && purchaseCost.equals(command.purchaseCost)
                && sellingPrice.equals(command.sellingPrice);
    }
}
