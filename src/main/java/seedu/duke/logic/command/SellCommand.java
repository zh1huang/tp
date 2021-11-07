package seedu.duke.logic.command;

import seedu.duke.logic.command.exception.ItemNotExistCommandException;
import seedu.duke.logic.command.exception.ShelfNotExistCommandException;
import seedu.duke.model.Item;
import seedu.duke.model.ShelfList;
import seedu.duke.logic.command.sales.SalesManager;
import seedu.duke.model.exception.ItemNotExistModelException;

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

    public String execute() throws ShelfNotExistCommandException, ItemNotExistCommandException {
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
        } catch (ItemNotExistModelException e) {
            throw new ItemNotExistCommandException(e.getMessage());
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof SellCommand)) {
            return false;
        }

        SellCommand command = (SellCommand) other;
        return itemID.equals(command.itemID);
    }
}
