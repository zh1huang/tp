package seedu.duke.command;

import seedu.duke.command.exception.ItemNotExistException;
import seedu.duke.command.exception.ShelfNotExistException;
import seedu.duke.model.Item;
import seedu.duke.model.ShelfList;
import seedu.duke.salesmanager.SalesManager;

public class SellCommand extends Command {
    public static final String SELL_DATA_ARGS_FORMAT_STRING = "Sell id/itemID";
    public static final String SELL_STRING = "sell";
    private static final String SELL_COMPLETE_MESSAGE =
            "This item has been sold."; //to be added to UI part later
    private final String itemID;

    public SellCommand(String itemID) {
        this.itemID = itemID;
    }

    public String execute() throws ShelfNotExistException, ItemNotExistException {
        try {
            Item selectedItem = ShelfList
                    .getShelfList()
                    .getItem(itemID);
            SalesManager.getSalesManager().sell(selectedItem);
            return SELL_COMPLETE_MESSAGE;
        } catch (seedu.duke.model.exception.ItemNotExistException e) {
            throw new ItemNotExistException(e.getMessage());
        }
    }
}
