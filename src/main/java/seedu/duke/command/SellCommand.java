package seedu.duke.command;

import seedu.duke.command.exception.ItemNotExistException;
import seedu.duke.command.exception.ShelfNotExistException;
import seedu.duke.model.Item;
import seedu.duke.model.ShelfList;
import seedu.duke.salesmanager.SalesManager;

public class SellCommand extends Command {
    public static final String SELL_DATA_ARGS_FORMAT_STRING = "sell id/itemID";
    public static final String SELL_STRING = "sell";
    private static final String SELL_COMPLETE_MESSAGE =
            "This item has been sold."; //to be added to UI part later
    public static final String SOLD_ITEM_DETAILS_FORMAT = "Name: %s\nCost: %s\nPrice: %s\nRemarks: %s";
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
            String name = selectedItem.getName();
            String cost = selectedItem.getPurchaseCost();
            String price = selectedItem.getSellingPrice();
            String remarks = selectedItem.getRemarks();
            String deletedItemDetails = "\n"
                    + String.format(SOLD_ITEM_DETAILS_FORMAT, name, cost, price, remarks);
            return SELL_COMPLETE_MESSAGE + deletedItemDetails;
        } catch (seedu.duke.model.exception.ItemNotExistException e) {
            throw new ItemNotExistException(e.getMessage());
        }
    }
}
