package seedu.duke.command;

import seedu.duke.command.exception.IllegalArgumentException;
import seedu.duke.command.exception.ItemNotExistException;
import seedu.duke.command.exception.NoPropertyFoundException;
import seedu.duke.command.exception.ShelfNotExistException;
import seedu.duke.model.Item;
import seedu.duke.model.Shelf;
import seedu.duke.model.ShelfList;
import seedu.duke.model.exception.IllegalModelArgumentException;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The command that edits a selected item.
 */
public class EditCommand extends Command {
    public static final String MESSAGE_ITEM_NOT_EXIST = "Item with index %d does not exist";
    public static final String EDIT_ITEM_DATA_ARGS_FORMAT_STRING =
            "edit shlv/SHELF_NAME i/INDEX p/PROPERTY v/VALUE";
    public static final String EDIT_STRING = "edit";
    public static final String PARSE_EDIT_SUCCESS_MESSAGE_FORMAT =
            "shelfname: %s\nindex: %s\nproperty: %s\nvalue: %s\n";
    public static final String EDIT_ITEM_DETAILS_FORMAT = "Name: %s\nCost: %s\nPrice: %s\nRemarks: %s";
    private static final String UPDATE_COMPLETE_MESSAGE = "This item has been updated.";
    //to be added to UI part later
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
     * @throws ItemNotExistException    when cannot find any item with the name
     * @throws NullPointerException     when the name specified is null
     * @throws IllegalArgumentException when the argument is invalid
     */
    public String execute() throws ItemNotExistException,
            NullPointerException, IllegalArgumentException,
            NoPropertyFoundException, ShelfNotExistException {
        boolean isProperty = Arrays.asList(properties).contains(selectedProperty);
        if (!isProperty) {
            logger.log(Level.WARNING, "EditCommand can't find item property.");
            throw new NoPropertyFoundException(selectedProperty);
        }
        try {
            Shelf selectedShelf = ShelfList
                    .getShelfList()
                    .getShelf(shelfName, true);
            int sizeBeforeEditing = selectedShelf.getSize();
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
            int sizeAfterEditing = selectedShelf.getSize();
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
        } catch (IllegalModelArgumentException e) {
            logger.log(Level.WARNING, String.format("EditCommand failed to execute with error message %s",
                    e.getMessage()));
            throw new IllegalArgumentException(e.getMessage());
        } catch (seedu.duke.model.exception.ShelfNotExistException e) {
            throw new ShelfNotExistException(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            throw new ItemNotExistException(String.format(MESSAGE_ITEM_NOT_EXIST, index + 1));
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
        if (!(other instanceof EditCommand)) {
            return false;
        }

        EditCommand command = (EditCommand) other;
        return shelfName.equals(command.shelfName) && index == command.index
            && selectedProperty.equals(command.selectedProperty) && newValue.equals(command.newValue);
    }
}
