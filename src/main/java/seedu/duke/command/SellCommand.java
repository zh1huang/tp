package seedu.duke.command;

import seedu.duke.model.Item;
import seedu.duke.model.ShelfList;
import seedu.duke.command.exception.ShelfNotExistException;
import seedu.duke.command.exception.ItemNotExistException;
import seedu.duke.salesmanager.SalesManager;

public class SellCommand extends Command {
    public static final String MESSAGE_ITEM_NOT_EXIST = "Item with index %s does not exist";
    private static final String SELL_COMPLETE_MESSAGE =
            "This item has been sold."; //to be added to UI part later
    private final String shelfName;
    private final String index;

    public SellCommand(String shelfName, String index) {
        this.shelfName = shelfName;
        this.index = Integer.toString(Integer.parseInt(index) - 1);
    }

    public String execute() throws ShelfNotExistException, ItemNotExistException {
        try {
            Item selectedItem = ShelfList
                    .getShelfList()
                    .getShelf(shelfName)
                    .getItem(Integer.parseInt(index));
            SalesManager.getSalesManager().sell(selectedItem);
            System.out.println(SELL_COMPLETE_MESSAGE);
            return SELL_COMPLETE_MESSAGE;
        } catch (seedu.duke.model.exception.ShelfNotExistException e) {
            throw new ShelfNotExistException(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            throw new ItemNotExistException(String.format(MESSAGE_ITEM_NOT_EXIST, index));
        } catch (seedu.duke.model.exception.ItemNotExistException e) {
            throw new ItemNotExistException(e.getMessage());
        }
    }
}
