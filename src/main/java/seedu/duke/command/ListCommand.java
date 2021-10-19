package seedu.duke.command;

import seedu.duke.command.exception.EmptyListException;
import seedu.duke.model.Item;
import seedu.duke.model.Shelf;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ListCommand extends Command {

    private static final String ITEM_INFO = "%o. %s (purchase cost: %s, selling price: %s)\n";
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static final String LIST_COMPLETE_MESSAGE = "Here is the list of items:\n";
    private static final String EMPTY_LIST_MESSAGE = "Shelf is empty";


    /**
     * Retrieves shelf of items from Shelf.
     *
     * @param shelf the Shelf in which list is retrieved
     * @return shelf of items
     */
    private String getList(Shelf shelf) {
        StringBuilder info = new StringBuilder();

        for (int i = 0; i < shelf.getSize();  i++) {
            Item selectedItem = shelf.getItem(i);
            int index = i + 1;
            info.append(String.format(ITEM_INFO, index,
                    selectedItem.getName(), selectedItem.getPurchaseCost(), selectedItem.getSellingPrice()));
        }
        return info.toString().trim();
    }

    /**
     * Executes the operation of listing all the items.
     *
     * @param shelf the Shelf to retrieve shelf of items
     */

    public void execute(Shelf shelf) throws EmptyListException {
        int initialSize = shelf.getSize();
        if (shelf.getSize() == 0) {
            logger.log(Level.WARNING, "ListCommand failed to execute because shelf is empty");
            throw new EmptyListException(EMPTY_LIST_MESSAGE);
        }

        String result = getList(shelf);
        assert initialSize == shelf.getSize() : "List size should not be changed";
        System.out.println(LIST_COMPLETE_MESSAGE + result);
        logger.log(Level.INFO, "ListCommand successfully executed");
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        return other instanceof ListCommand;
    }
}
