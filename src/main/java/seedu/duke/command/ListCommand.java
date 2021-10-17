package seedu.duke.command;

import seedu.duke.command.exception.EmptyListException;
import seedu.duke.model.Item;
import seedu.duke.model.ItemContainer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ListCommand extends Command {

    private static final String ITEM_INFO = "%o. %s (purchase cost: %s, selling price: %s)\n";
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * Retrieves list of items from ItemContainer.
     *
     * @param list the ItemContainer in which list is retrieved
     * @return list of items
     */
    private String getList(ItemContainer list) throws EmptyListException {
        StringBuilder info = new StringBuilder();
        if (list.getSize() == 0) {
            logger.log(Level.WARNING, "ListCommand failed to execute because list is empty");
            throw new EmptyListException("List is empty");
        }

        for (int i = 0; i < list.getSize();  i++) {
            Item selectedItem = list.getItem(i);
            int index = i + 1;
            info.append(String.format(ITEM_INFO, index,
                    selectedItem.getName(), selectedItem.getPurchaseCost(), selectedItem.getSellingPrice()));
        }
        return info.toString().trim();
    }

    /**
     * Executes the operation of listing all the items.
     *
     * @param list the ItemContainer to retrieve list of items
     */
    public void execute(ItemContainer list) throws EmptyListException {
        int initialSize = list.getSize();
        String result = getList(list);
        assert initialSize == list.getSize() : "List size should not be changed";
        System.out.println(result);
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
