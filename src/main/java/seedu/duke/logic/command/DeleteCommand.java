package seedu.duke.logic.command;

import seedu.duke.logic.command.exception.ItemNotExistCommandException;
import seedu.duke.logic.command.exception.ShelfNotExistCommandException;
import seedu.duke.model.Item;
import seedu.duke.model.Shelf;
import seedu.duke.model.ShelfList;
import seedu.duke.model.exception.DeniedAccessToShelfModelException;
import seedu.duke.model.exception.ItemNotExistModelException;
import seedu.duke.model.exception.ShelfNotExistModelException;

import java.util.logging.Level;
import java.util.logging.Logger;

//@@author haoyusimon
/**
 * The command that deletes a selected item.
 */
public class DeleteCommand extends Command {

    public static final String DELETE_ITEM_DATA_ARGS_FORMAT_STRING = "delete shlv/SHELF_NAME i/INDEX";
    public static final String DELETE_STRING = "delete";
    private static final String DELETE_COMPLETE_MESSAGE =
            "This item has been removed from the list."; //to be added to UI part later
    public static final String DELETED_ITEM_DETAILS_FORMAT = "Name: %s\nCost: %s\nPrice: %s\nRemarks: %s";
    public static final String MESSAGE_ITEM_NOT_EXIST = "Item with index %d does not exist";
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final String shelfName;
    private final int index;

    /**
     * The DeleteCommand constructor.
     *
     * @param shelfName the name of the shelf the item is on
     * @param index     the index number of the item in the shelf
     */
    public DeleteCommand(String shelfName, String index) {
        this.shelfName = shelfName;
        this.index = Integer.parseInt(index) - 1;
    }


    /**
     * Executes the delete operation.
     *
     * @throws ItemNotExistCommandException if the specified item does not exist
     * @throws ShelfNotExistCommandException if the specified shelf does not exist
     */
    public String execute() throws ItemNotExistCommandException, ShelfNotExistCommandException {
        try {
            Shelf selectedShelf = ShelfList
                    .getShelfList()
                    .getShelf(shelfName, true);
            int sizeBeforeDeleting = selectedShelf.getItemCount();
            Item selectedItem = selectedShelf.getItem(index);
            selectedShelf.deleteItem(selectedItem);
            int sizeAfterDeleting = selectedShelf.getItemCount();
            assert sizeBeforeDeleting - 1 == sizeAfterDeleting :
                    "After deleting an item the list size should decrease by 1";
            logger.log(Level.INFO, "DeleteCommand successfully executed.");
            String name = selectedItem.getName();
            String cost = selectedItem.getPurchaseCost();
            String price = selectedItem.getSellingPrice();
            String remarks = selectedItem.getRemarks();
            String deletedItemDetails = "\n"
                    + String.format(DELETED_ITEM_DETAILS_FORMAT, name, cost, price, remarks);
            return DELETE_COMPLETE_MESSAGE + deletedItemDetails;
        } catch (ItemNotExistModelException e) {
            logger.log(Level.WARNING, String.format("DeleteCommand failed to execute with error message %s",
                    e.getMessage()));
            throw new ItemNotExistCommandException(e.getMessage());
        } catch (ShelfNotExistModelException | DeniedAccessToShelfModelException e) {
            throw new ShelfNotExistCommandException(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            throw new ItemNotExistCommandException(String.format(MESSAGE_ITEM_NOT_EXIST, index + 1));
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
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand command = (DeleteCommand) other;
        return shelfName.equals(command.shelfName) && index == command.index;
    }
}
