package seedu.duke.command;

import seedu.duke.command.exception.ItemNotExistException;
import seedu.duke.model.Item;
import seedu.duke.model.Shelf;
import seedu.duke.model.ShelfList;
import seedu.duke.model.exception.ShelfNotExistException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GetCommand extends Command {

    private final int index;
    private final String shelfName;
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static final String GET_COMPLETE_MESSAGE = "Here is the information of your item:\n";
    public static final String MESSAGE_ITEM_NOT_EXIST = "Item with index %s does not exist";
    public static final String GET_OUTPUT = "Name: %s\nCost: %s\nPrice: %s\nRemarks:%s";

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
     * @throws ShelfNotExistException If the Shelf is not in the ShelfList
     * @throws ItemNotExistException If empty does not exist in the shelf
     */
    public String execute() throws ShelfNotExistException, ItemNotExistException {
        String output= "";
        try {
            Shelf selectedShelf = ShelfList
                    .getShelfList()
                    .getShelf(shelfName);
            int initialSize = selectedShelf.getSize();
            Item selectedItem = selectedShelf.getItem(index);
            assert initialSize == selectedShelf.getSize()
                    : "After getting the list size should remain constant";

            String name = selectedItem.getName();
            String cost = selectedItem.getPurchaseCost();
            String price = selectedItem.getSellingPrice();
            String remarks = selectedItem.getRemark();

            output = String.format(GET_OUTPUT, name, cost, price, remarks);
            logger.log(Level.INFO, "GetCommand successfully executed");
        } catch (seedu.duke.model.exception.ShelfNotExistException e) {
            logger.log(Level.WARNING, "GetCommand failed to execute because shelf does not exist");
            throw new ShelfNotExistException(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.WARNING, "GetCommand failed to execute because item not in shelf");
            throw new ItemNotExistException(String.format(MESSAGE_ITEM_NOT_EXIST, index));
        }

        logger.log(Level.INFO, "GetCommand successfully executed");
        return GET_COMPLETE_MESSAGE + output;
    }

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
        return shelfName.equals(command.shelfName);
    }
}
