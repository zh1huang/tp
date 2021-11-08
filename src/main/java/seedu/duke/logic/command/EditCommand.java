package seedu.duke.logic.command;

import seedu.duke.logic.command.exception.IllegalArgumentCommandException;
import seedu.duke.logic.command.exception.ItemNotExistCommandException;
import seedu.duke.logic.command.exception.NoPropertyFoundCommandException;
import seedu.duke.logic.command.exception.ShelfNotExistCommandException;
import seedu.duke.logic.command.exception.DeniedAccessToShelfCommandException;
import seedu.duke.model.Item;
import seedu.duke.model.Shelf;
import seedu.duke.model.ShelfList;
import seedu.duke.model.exception.DeniedAccessToShelfModelException;
import seedu.duke.model.exception.IllegalArgumentModelException;
import seedu.duke.model.exception.ShelfNotExistModelException;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author haoyusimon
/**
 * The command that edits a selected item.
 */
public class EditCommand extends Command {

    public static final String MESSAGE_ITEM_NOT_EXIST = "Item with index %d does not exist";
    public static final String EDIT_ITEM_DATA_ARGS_FORMAT_STRING =
            "edit shlv/SHELF_NAME i/INDEX p/PROPERTY v/VALUE\n"
                    + "Only 3 types of p/PROPERTY\n"
                    + "1. \"p/purchase cost\" - to edit purchase cost\n"
                    + "2. \"p/selling price\" - to edit selling price\n"
                    + "   Valid cost/price input e.g \"5.4\", \"5.00\", \"12.00\"\n"
                    + "   Invalid unreasonable wrong price input e.g. \"5.\", \"-5.0\", \"10000.000\"\n"
                    + "   Max cost/price: 9999.99 (up to 2 dp)\n"
                    + "3. \"p/remarks\" - to edit remarks\n"
                    + "   Remarks property accepts any alphanumeric input";
    public static final String EDIT_STRING = "edit";
    public static final String EDIT_ITEM_DETAILS_FORMAT = "Name: %s\nCost: %s\nPrice: %s\nRemarks: %s";
    private static final String UPDATE_COMPLETE_MESSAGE = "This item has been updated.";
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final String shelfName;
    private final int index;
    private final String selectedProperty;
    private final String newValue;
    private final String[] properties = {"purchase cost", "selling price", "remarks"};


    /**
     * EditCommand constructor.
     *
     * @param shelfName the name of the shelf where the item is on
     * @param index     the index of the item in the shelf
     * @param property  the property to be changed
     * @param newValue  the new value of the property
     */
    public EditCommand(String shelfName, String index, String property, String newValue) {
        this.shelfName = shelfName;
        this.index = Integer.parseInt(index) - 1;
        this.selectedProperty = property;
        this.newValue = newValue;
    }

    /**
     * Executes the update operation.
     *
     * @return completed message as String.
     * @throws ItemNotExistCommandException        when cannot find any item with the name
     * @throws NullPointerException                when the name specified is null
     * @throws IllegalArgumentCommandException     when the argument is invalid
     * @throws NoPropertyFoundCommandException     when the input property is invalid
     * @throws ShelfNotExistCommandException       if the shelf specified does not exist
     * @throws DeniedAccessToShelfCommandException if the user attrempts to access the soldItems shelf
     */
    public String execute() throws ItemNotExistCommandException,
            NullPointerException, IllegalArgumentCommandException,
            NoPropertyFoundCommandException, ShelfNotExistCommandException, DeniedAccessToShelfCommandException {
        boolean isProperty = Arrays.asList(properties).contains(selectedProperty);
        if (!isProperty) {
            logger.log(Level.WARNING, "EditCommand can't find item property.");
            throw new NoPropertyFoundCommandException(selectedProperty);
        }
        try {
            Shelf selectedShelf = ShelfList
                    .getShelfList()
                    .getShelf(shelfName, true);
            int sizeBeforeEditing = selectedShelf.getItemCount();
            Item selectedItem = selectedShelf.getItem(index);
            if (selectedProperty.equals("purchase cost")) {
                selectedItem.setPurchaseCost(newValue);

            } else if (selectedProperty.equals("selling price")) {
                selectedItem.setSellingPrice(newValue);
            } else {
                assert selectedProperty.equals("remarks") :
                        "All properties should have been listed";
                selectedItem.setRemarks(newValue);
            }
            int sizeAfterEditing = selectedShelf.getItemCount();
            assert sizeBeforeEditing == sizeAfterEditing :
                    "After editing an item the list size should remain unchanged";
            logger.log(Level.INFO, "EditCommand successfully executed.");
            String name = selectedItem.getName();
            String cost = selectedItem.getPurchaseCost();
            String price = selectedItem.getSellingPrice();
            String remarks = selectedItem.getRemarks();
            String deletedItemDetails = "\n"
                    + String.format(EDIT_ITEM_DETAILS_FORMAT, name, cost, price, remarks);
            return UPDATE_COMPLETE_MESSAGE + deletedItemDetails;
        } catch (IllegalArgumentModelException e) {
            logger.log(Level.WARNING, String.format("EditCommand failed to execute with error message %s",
                    e.getMessage()));
            throw new IllegalArgumentCommandException(e.getMessage());
        } catch (ShelfNotExistModelException e) {
            throw new ShelfNotExistCommandException(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            throw new ItemNotExistCommandException(String.format(MESSAGE_ITEM_NOT_EXIST, index + 1));
        } catch (DeniedAccessToShelfModelException e) {
            throw new DeniedAccessToShelfCommandException(e.getMessage());
        }
    }

    /**
     * The overriding equal method to compare with other commands.
     *
     * @param other the other object to be compared with
     * @return true if two objects are the same, else false
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof EditCommand)) {
            return false;
        }

        EditCommand command = (EditCommand) other;
        return shelfName.equals(command.shelfName) && index == command.index
                && selectedProperty.equals(command.selectedProperty) && newValue.equals(command.newValue);
    }
}
