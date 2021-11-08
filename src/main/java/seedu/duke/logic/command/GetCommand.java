package seedu.duke.logic.command;

import seedu.duke.logic.command.exception.DeniedAccessToShelfCommandException;
import seedu.duke.logic.command.exception.ItemNotExistCommandException;
import seedu.duke.model.Item;
import seedu.duke.model.Shelf;
import seedu.duke.model.ShelfList;
import seedu.duke.model.exception.DeniedAccessToShelfModelException;
import seedu.duke.model.exception.ShelfNotExistModelException;

import java.util.logging.Level;
import java.util.logging.Logger;

//@@author zh1huang
public class GetCommand extends Command {

    public static final String MESSAGE_ITEM_NOT_EXIST = "Item with index %s does not exist";
    public static final String GET_OUTPUT = "Name: %s\nCost: %s\nPrice: %s\nID: %s\nRemarks: %s";
    public static final String GET_ITEM_DATA_ARGS_FORMAT_STRING = "get shlv/SHELF_NAME i/INDEX";
    public static final String GET_STRING = "get";
    public static final String GET_COMPLETE_MESSAGE = "Here is the information of your item:\n";
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final int index;
    private final String shelfName;

    /**
     * Constructor for GetCommand.
     *
     * @param shelfName the name of selected shelf
     */
    public GetCommand(String shelfName, String index) {
        this.index = Integer.parseInt(index) - 1;
        this.shelfName = shelfName;
    }

    /**
     * Executes the get command.
     *
     * @return Message string to be passed to UI
     * @throws ShelfNotExistModelException  If the Shelf is not in the ShelfList
     * @throws ItemNotExistCommandException If empty does not exist in the shelf
     */
    public String execute() throws ShelfNotExistModelException, ItemNotExistCommandException,
            DeniedAccessToShelfCommandException {
        String output = "";
        try {
            Shelf selectedShelf = ShelfList
                    .getShelfList()
                    .getShelf(shelfName, true);
            int initialSize = selectedShelf.getItemCount();
            Item selectedItem = selectedShelf.getItem(index);
            assert initialSize == selectedShelf.getItemCount()
                    : "After getting the list size should remain constant";
            output = getInfo(selectedItem);
            logger.log(Level.INFO, "GetCommand successfully executed");
        } catch (ShelfNotExistModelException e) {
            logger.log(Level.WARNING, "GetCommand failed to execute because shelf does not exist");
            throw new ShelfNotExistModelException(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.WARNING, "GetCommand failed to execute because item not in shelf");
            throw new ItemNotExistCommandException(String.format(MESSAGE_ITEM_NOT_EXIST, index + 1));
        } catch (DeniedAccessToShelfModelException e) {
            throw new DeniedAccessToShelfCommandException(e.getMessage());
        }

        logger.log(Level.INFO, "GetCommand successfully executed");
        return GET_COMPLETE_MESSAGE + output;
    }

    /**
     * Gets the information of selected item.
     *
     * @param selectedItem item that information is to be retrieved
     * @return String of information of selected item
     */
    private String getInfo(Item selectedItem) {
        String name = selectedItem.getName();
        String cost = selectedItem.getPurchaseCost();
        String price = selectedItem.getSellingPrice();
        String remarks = selectedItem.getRemarks();
        String id = selectedItem.getID();
        return String.format(GET_OUTPUT, name, cost, price, id, remarks);
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
        if (!(other instanceof GetCommand)) {
            return false;
        }

        GetCommand command = (GetCommand) other;
        return shelfName.equals(command.shelfName)
                && index == command.index;
    }
}
