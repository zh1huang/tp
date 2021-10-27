package seedu.duke.command;

import seedu.duke.command.exception.ShelfNotExistException;
import seedu.duke.model.Item;
import seedu.duke.model.Shelf;
import seedu.duke.command.exception.IllegalArgumentException;
import seedu.duke.command.exception.ItemNotExistException;
import seedu.duke.command.exception.NoPropertyFoundException;
import seedu.duke.model.ShelfList;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The command that edits a selected item.
 */
public class EditCommand extends Command {
    public static final String MESSAGE_ITEM_NOT_EXIST = "Item with index %d does not exist";
    private final String shelfName;
    private final int index;
    private final String selectedProperty;
    private final String newValue;
    private final String[] properties = {"cost", "price"};
    private static final String UPDATE_COMPLETE_MESSAGE = "This item has been updated.";
    //to be added to UI part later
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    /**
     * EditCommand constructor.
     * @param shelfName the name of the shelf where the item is on
     * @param index     the index of the item in the shelf
     * @param property the property to be changed
     * @param newValue the new value of the property
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
                    .getShelf(shelfName);
            int sizeBeforeEditing = selectedShelf.getSize();
            Item selectedItem = selectedShelf.getItem(index);
            if (selectedProperty.equals("cost")) {
                selectedItem.setPurchaseCost(newValue);

            } else {
                assert selectedProperty.equals("price") :
                        "All properties should have been listed";
                selectedItem.setSellingPrice(newValue);
            }
            int sizeAfterEditing = selectedShelf.getSize();
            assert sizeBeforeEditing == sizeAfterEditing :
                    "After editing an item the list size should remain unchanged";
            System.out.println(UPDATE_COMPLETE_MESSAGE);
            logger.log(Level.INFO, "EditCommand successfully executed.");
            return UPDATE_COMPLETE_MESSAGE;
        } catch (seedu.duke.model.exception.IllegalArgumentException e) {
            logger.log(Level.WARNING, String.format("EditCommand failed to execute with error message %s",
                    e.getMessage()));
            throw new IllegalArgumentException(e.getMessage());
        } catch (seedu.duke.model.exception.ShelfNotExistException e) {
            throw new ShelfNotExistException(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            throw new ItemNotExistException(String.format(MESSAGE_ITEM_NOT_EXIST, index));
        }
    }
}
