package seedu.duke.logic.command;

import seedu.duke.logic.command.exception.DuplicateItemCommandException;
import seedu.duke.logic.command.exception.ExceedsShelfSizeLimitCommandException;
import seedu.duke.logic.command.exception.IllegalArgumentCommandException;
import seedu.duke.logic.command.exception.ShelfNotExistCommandException;
import seedu.duke.model.Item;
import seedu.duke.model.Shelf;
import seedu.duke.model.ShelfList;
import seedu.duke.model.exception.DuplicateItemModelException;
import seedu.duke.model.exception.IllegalArgumentModelException;
import seedu.duke.model.exception.ShelfNotExistModelException;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The command that adds a new item to the list.
 */
public class AddCommand extends Command {
    public static final String ADD_ITEM_DATA_ARGS_FORMAT_STRING =
            "add n/NAME shlv/SHELF_NAME p/PURCHASE_COST s/SELLING_PRICE q/QUANTITY [r/REMARKS]\n"
                    + "(Purchase cost and selling price must be non-negative numbers. "
                    + "Quantity must be non-negative integers.)\n"
                    + "Valid cost/price input e.g \"5.4\", \"5.00\", \"12.00\"\n"
                    + "Invalid cost/price input e.g. \"5.\", \"45.\", \"-5.0\", \"10000.000\"\n"
                    + "Max cost/price: 9999.99 (up to 2 dp)\n"
                    + "Max quantity: 999";
    public static final String ADD_STRING = "add";
    public static final String PARSE_ADD_SUCCESS_MESSAGE_FORMAT = "name: %s\nshelfname: %s\ncost: $%s\n"
            + "price: %s\nquantity: %s\nremarks: %s\n";
    private static final String ADD_COMPLETE_MESSAGE_SINGLE =
            "This item has been added to the list. Its unique ID is: \n";
    private static final String ADD_COMPLETE_MESSAGE_MULTIPLE =
            " items have been added to the list. Use Get command to view their unique IDs.";
    private static final String PRICE_WARNING =
            "\nWarning: \nYour price of selling is not higher than your purchase cost. "
                    + "\nMake sure you did not type wrongly.";
    private static final String ZERO_PRICE_WARNING =
            "\nWarning: \nYour selling price and/or your purchase cost is 0. "
                    + "\nMake sure you did not type wrongly.";
    private static final int SHELF_SIZE_LIMIT = 999;
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final String name;
    private final String purchaseCost;
    private final String sellingPrice;
    private final int quantity;
    private final String shelfName;
    private final String remarks;


    /**
     * The AddCommand constructor.
     *
     * @param name the name of the item
     * @param purchaseCost the purchase cost of the item
     * @param sellingPrice the selling price of the item
     * @param quantity number of the identical items to be added
     * @param shelfName the name of the shelf to be added to
     * @param remarks additional remarks for the item
     */
    public AddCommand(String name, String purchaseCost, String sellingPrice, String quantity,
                      String shelfName, String remarks) {
        this.name = name;
        this.purchaseCost = purchaseCost;
        this.sellingPrice = sellingPrice;
        this.quantity = Integer.parseInt(quantity);
        this.shelfName = shelfName;
        this.remarks = remarks;
    }

    /**
     * Executes the operation of adding the item to the shelf.
     *
     * @throws IllegalArgumentCommandException if the input argument is wrong
     * @throws DuplicateItemCommandException   if exactly the same item is added to the list
     */
    @Override
    public String execute() throws IllegalArgumentCommandException, DuplicateItemCommandException,
            ShelfNotExistCommandException, ExceedsShelfSizeLimitCommandException {
        try {
            String itemID = "";
            Shelf selectedShelf = ShelfList
                    .getShelfList()
                    .getShelf(shelfName, true);
            int currentSize = selectedShelf.getItemCount();
            int expectedSize = currentSize + quantity;
            if (expectedSize > SHELF_SIZE_LIMIT) {
                throw new ExceedsShelfSizeLimitCommandException(currentSize);
            }
            for (int i = 0; i < quantity; i++) {
                int sizeBeforeAdding = selectedShelf.getItemCount();
                Item newItem = new Item(name, purchaseCost, sellingPrice, remarks);
                selectedShelf.addItem(newItem);
                int sizeAfterAdding = selectedShelf.getItemCount();
                assert sizeBeforeAdding + 1 == sizeAfterAdding :
                        "After adding an item the list size should increase by 1";
                itemID = newItem.getID();
                logger.log(Level.INFO, "AddCommand successfully executed.");
            }
            boolean hasNegativeProfit = (new BigDecimal(sellingPrice).compareTo(new BigDecimal(purchaseCost)) == -1);
            double cost = Double.parseDouble(purchaseCost.trim());
            double price = Double.parseDouble(sellingPrice.trim());
            boolean hasZeroPriceOrCost = (cost == 0 || price == 0);
            if (quantity > 1) {
                if (hasZeroPriceOrCost) {
                    return quantity + ADD_COMPLETE_MESSAGE_MULTIPLE + ZERO_PRICE_WARNING;
                }
                if (hasNegativeProfit) {
                    return quantity + ADD_COMPLETE_MESSAGE_MULTIPLE + PRICE_WARNING;
                } else {
                    return quantity + ADD_COMPLETE_MESSAGE_MULTIPLE;
                }
            } else if (quantity == 1) {
                assert !itemID.equals("") : "An item must have an auto-generated ID!";
                if (hasZeroPriceOrCost) {
                    return ADD_COMPLETE_MESSAGE_SINGLE + itemID + ZERO_PRICE_WARNING;
                }
                if (hasNegativeProfit) {
                    return ADD_COMPLETE_MESSAGE_SINGLE + itemID + PRICE_WARNING;
                } else {
                    return ADD_COMPLETE_MESSAGE_SINGLE + itemID;
                }
            } else {
                throw new IllegalArgumentCommandException("Item's quantity cannot be 0");
            }

        } catch (IllegalArgumentModelException e) {
            logger.log(Level.WARNING, String.format("AddCommand failed to execute with error message %s",
                    e.getMessage()));
            throw new IllegalArgumentCommandException(e.getMessage());
        } catch (DuplicateItemModelException e) {
            logger.log(Level.WARNING, String.format("AddCommand failed to execute with error message %s",
                    e.getMessage()));
            throw new DuplicateItemCommandException(e.getMessage());
        } catch (ShelfNotExistModelException e) {
            throw new ShelfNotExistCommandException(e.getMessage());
        } catch (ExceedsShelfSizeLimitCommandException e) {
            throw new ExceedsShelfSizeLimitCommandException(e.getMessage());
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
                && sellingPrice.equals(command.sellingPrice)
                && quantity == command.quantity
                && shelfName.equals(command.shelfName)
                && remarks.equals(command.remarks);
    }
}
