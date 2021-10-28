package seedu.duke.salesmanager;

import seedu.duke.salesmanager.exception.EmptyListException;
import seedu.duke.model.Item;
import seedu.duke.model.Shelf;
import seedu.duke.model.ShelfList;
import seedu.duke.model.exception.ShelfNotExistException;
import seedu.duke.command.exception.IllegalArgumentException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Logger;

public class SalesReport {
    public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    private final String selectedDate;
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static final String TOTAL_MONETARY_SUMMARY_MESSAGE_FORMAT = "Total Purchase Cost: $ %s\n"
        + "Total Selling Price: $ %s\nTotal Profits: $ %s\nGross Profit Margin (in percent): %s";
    private static final String ITEM_INFO = "%o. %s (purchase cost: %s, selling price: %s)\n";
    private final String selectedEndDate;

    public SalesReport(String selectedDate, String selectedEndDate) {
        this.selectedDate = selectedDate;
        this.selectedEndDate = selectedEndDate;
    }

    /**
     * Generate a string to contain all sold item statistics.
     *
     * @return A String containing the sold items statistics
     * @throws EmptyListException If the soldItems shelf does not contain items
     */
    public String generateSoldItemStats()
        throws EmptyListException, IllegalArgumentException {
        SalesManager salesManager = SalesManager.getSalesManager();
        ArrayList<SoldItem> selectedSoldItems = salesManager.filterSoldItems(selectedDate, selectedEndDate);
        BigDecimal totalPurchaseCost = BigDecimal.ZERO;
        BigDecimal totalSellingPrice = BigDecimal.ZERO;
        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        for (int i = 0; i < selectedSoldItems.size(); i++) {
            Item selectedItem = selectedSoldItems.get(i);

            totalPurchaseCost = totalPurchaseCost.add(new BigDecimal(selectedItem.getPurchaseCost()));
            totalSellingPrice = totalSellingPrice.add(new BigDecimal(selectedItem.getSellingPrice()));
        }

        BigDecimal totalProfits = totalSellingPrice.subtract(totalPurchaseCost);
        BigDecimal grossProfitMargin = totalProfits.divide(totalSellingPrice, 2,
            RoundingMode.HALF_UP).multiply(ONE_HUNDRED);

        return String.format(TOTAL_MONETARY_SUMMARY_MESSAGE_FORMAT,
            decimalFormat.format(totalPurchaseCost), decimalFormat.format(totalSellingPrice),
            decimalFormat.format(totalProfits), decimalFormat.format(grossProfitMargin));
    }

    /**
     * Generate and gets every sold item details in the soldItems shelf.
     *
     * @return A String containing all sold item details
     */
    public String generateSoldItemDetails() throws IllegalArgumentException {
        SalesManager salesManager = SalesManager.getSalesManager();
        ArrayList<SoldItem> selectedSoldItems = salesManager.filterSoldItems(selectedDate, selectedEndDate);
        StringBuilder info = new StringBuilder();
        for (int i = 0; i < selectedSoldItems.size(); i++) {
            Item selectedItem = selectedSoldItems.get(i);
            int index = i + 1;
            info.append(String.format(ITEM_INFO, index,
                selectedItem.getName(), selectedItem.getPurchaseCost(), selectedItem.getSellingPrice()));
        } //todo: add remarks
        return info.toString().trim();
    }
}
