package seedu.duke.command;

import seedu.duke.command.exception.EmptyListException;
import seedu.duke.model.Item;
import seedu.duke.model.Shelf;
import seedu.duke.model.ShelfList;
import seedu.duke.model.exception.ShelfNotExistException;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.logging.Logger;

public class TotalCostAndIncomeCommand extends Command {

    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static final String TOTAL_MONETARY_SUMMARY_MESSAGE_FORMAT = "Total Purchase Cost: %s\n"
        + "Total Selling Price: %s\nTotal Profits: %s";
    public static final String SOLD_ITEM_SHELF_NOT_EXIST_MESSAGE = "None of the items have been sold.";
    private Shelf soldItemsShelf;

    @Override
    public void execute(Shelf list) throws EmptyListException {
        try {
            checkSoldItemShelfExist();
        } catch (ShelfNotExistException e) {
            throw new EmptyListException(SOLD_ITEM_SHELF_NOT_EXIST_MESSAGE);
        }

        BigDecimal totalPurchaseCost = BigDecimal.ZERO;
        BigDecimal totalSellingPrice = BigDecimal.ZERO;
        DecimalFormat df = new DecimalFormat("0.00");

        for (int i = 0; i < soldItemsShelf.getSize(); i++) {
            Item selectedItem = soldItemsShelf.getItem(i);

            totalPurchaseCost = totalPurchaseCost.add(new BigDecimal(selectedItem.getPurchaseCost()));
            totalSellingPrice = totalSellingPrice.add(new BigDecimal(selectedItem.getSellingPrice()));
        }

        BigDecimal totalProfits = totalSellingPrice.subtract(totalPurchaseCost);

        System.out.println(String.format(TOTAL_MONETARY_SUMMARY_MESSAGE_FORMAT,
            df.format(totalPurchaseCost), df.format(totalSellingPrice),
            df.format(totalProfits)));

    }

    /**
     * Method is temporary placeholder to get soldItemsShelf.
     * Original method to be implemented in SalesManager.
     */
    public void checkSoldItemShelfExist() throws ShelfNotExistException {
        //SalesManager salesManager = SalesManager.getSalesManager();
        ShelfList shelfList = ShelfList.getShelfList();
        soldItemsShelf = shelfList.getShelf("soldItems");
    }
}
