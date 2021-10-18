package seedu.duke.command;

import seedu.duke.command.exception.ItemNotExistException;
import seedu.duke.model.Item;
import seedu.duke.model.Shelf;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GetCommand extends Command {

    private final String name;
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static final String GET_COMPLETE_MESSAGE = "Here is the information of your item\n";

    /**
     * Constructor for GetCommand.
     *
     * @param name the name of selected item
     */
    public GetCommand(String name) {
        this.name = name;
    }

    /**
     * Retrieves information of item from shelf.
     *
     * @param shelf the Shelf in which information of item is retrieved
     * @return information of item
     * @throws ItemNotExistException if specified item does not exist
     */
    private String getInfo(Shelf shelf) throws ItemNotExistException {
        try {
            int initialSize = shelf.getSize();
            Item selectedItem = shelf.getItem(name);
            assert initialSize == shelf.getSize();
            return "name: " + selectedItem.getName() + "\nselling price: " + selectedItem.getSellingPrice()
                    + "\npurchase cost: " + selectedItem.getPurchaseCost();
        } catch (seedu.duke.model.exception.ItemNotExistException e) {
            logger.log(Level.WARNING, "GetCommand failed to execute because item not in shelf");
            throw new ItemNotExistException(e.getMessage());
        }
    }

    /**
     * Executes the operation of retrieving information of specified item.
     *
     * @param shelf the Shelf that manipulates the item
     * @throws ItemNotExistException if specified item does not exist
     */
    public void execute(Shelf shelf) throws ItemNotExistException {
        String info = getInfo(shelf);
        System.out.println(GET_COMPLETE_MESSAGE + info);
        logger.log(Level.INFO, "GetCommand successfully executed");
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
        return name.equals(command.name);
    }
}
