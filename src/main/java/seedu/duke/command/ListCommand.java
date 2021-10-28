package seedu.duke.command;

import seedu.duke.command.exception.EmptyListException;
import seedu.duke.model.Item;
import seedu.duke.model.Shelf;
import seedu.duke.model.ShelfList;
import seedu.duke.model.exception.ShelfNotExistException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class ListCommand extends Command {

    public static final String LIST_ITEM_DATA_ARGS_FORMAT_STRING = "List [shlv/SHELF_NAME]";
    public static final String LIST_STRING = "list";
    public static final String PARSE_LIST_SUCCESS_MESSAGE_FORMAT = "shelfname: %s\n";
    private String shelfName = null;
    private final boolean toPrintAll;
    private static final String ITEM_INFO = "%d. %s (Cost: %s, Price: %s)\n";
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static final String LIST_COMPLETE_MESSAGE = "Here is the list of items:\n";
    private static final String EMPTY_LIST_MESSAGE = "Shelf is empty";

    /**
     * Constructor if ListCommand takes in shelfName as parameter.
     * ListCommand will only print out items in that specified shelf
     *
     * @param shelfName the name of shelf in which items are printed out
     */
    public ListCommand(String shelfName) {
        this.shelfName = shelfName;
        this.toPrintAll = false;
    }

    /**
     * Constructor if ListCommand does not take in optional parameters.
     */
    public ListCommand() {
        this.toPrintAll = true;
    }

    /**
     * Executes the list operation.
     *
     * @return Message string to be passed to UI
     * @throws ShelfNotExistException If the Shelf is not in the ShelfList
     * @throws EmptyListException If list is empty
     */
    public String execute() throws ShelfNotExistException, EmptyListException {
        String output = "";
        if (!toPrintAll) { //optional parameter entered so print that particular shelf
            try {
                Shelf selectedShelf = ShelfList
                        .getShelfList()
                        .getShelf(shelfName);
                if (selectedShelf.getSize() == 0) {
                    logger.log(Level.WARNING, "ListCommand failed to execute because shelf is empty");
                    throw new EmptyListException(EMPTY_LIST_MESSAGE);
                }

                System.out.print(LIST_COMPLETE_MESSAGE);
                output = getList(selectedShelf);

            } catch (seedu.duke.model.exception.ShelfNotExistException e) {
                logger.log(Level.WARNING, "GetCommand failed to execute because shelf does not exist");
                throw new ShelfNotExistException(e.getMessage());
            }
        } else {
            ArrayList<Shelf> shelves = ShelfList.getShelfList().getShelves();
            for (Shelf shelf: shelves) {
                String shelfName = shelf.getName();
                output += "-----[" + shelfName + "]-----:\n" + getList(shelf);
            }
        }
        return LIST_COMPLETE_MESSAGE + output;
    }

    /**
     * Gets the list of items from specified shelf.
     *
     * @param shelf Shelf to get list of items from
     */
    private String getList(Shelf shelf) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < shelf.getSize(); i++) {
            Item selectedItem = shelf.getItem(i);
            int index = i + 1;
            String name = selectedItem.getName();
            String cost = selectedItem.getPurchaseCost();
            String price = selectedItem.getSellingPrice();

            output.append(String.format(ITEM_INFO, index, name, cost, price));
            logger.log(Level.INFO, "ListCommand successfully executed");
        }
        return output.toString();
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
