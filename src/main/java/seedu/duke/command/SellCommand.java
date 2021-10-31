package seedu.duke.command;

import seedu.duke.model.Item;
import seedu.duke.model.ShelfList;
import seedu.duke.command.exception.ShelfNotExistException;
import seedu.duke.command.exception.ItemNotExistException;
import seedu.duke.salesmanager.SalesManager;

import java.util.regex.Pattern;

public class SellCommand extends Command {
    public static final String MESSAGE_ITEM_NOT_EXIST = "Item with index %d does not exist";
    public static final String SELL_DATA_ARGS_FORMAT_STRING = "Sell shlv/SHELF_NAME i/INDEX";
    public static final String SELL_STRING = "sell";
    public static final String PARSE_SELL_SUCCESS_MESSAGE_FORMAT = "shelfname: %s\nindex: %s\n";
    private static final String SELL_COMPLETE_MESSAGE =
            "This item has been sold."; //to be added to UI part later
    private final String shelfName;
    private final int index;

    public SellCommand(String shelfName, String index) {
        this.shelfName = shelfName;
        this.index = Integer.parseInt(index) - 1;
    }

    public String execute() throws ShelfNotExistException, ItemNotExistException {
        try {
            Item selectedItem = ShelfList
                    .getShelfList()
                    .getShelf(shelfName)
                    .getItem(index);
            SalesManager.getSalesManager().sell(selectedItem);
            return SELL_COMPLETE_MESSAGE;
        } catch (seedu.duke.model.exception.ShelfNotExistException e) {
            throw new ShelfNotExistException(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            throw new ItemNotExistException(String.format(MESSAGE_ITEM_NOT_EXIST, index + 1));
        } catch (seedu.duke.model.exception.ItemNotExistException e) {
            throw new ItemNotExistException(e.getMessage());
        }
    }
}
