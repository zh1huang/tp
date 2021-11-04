package seedu.duke.salesmanager;

import seedu.duke.command.exception.IllegalArgumentException;
import seedu.duke.model.Item;
import seedu.duke.model.SoldItem;
import seedu.duke.salesmanager.exception.EmptyListException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SalesReport {
    public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
    public static final String TOTAL_MONETARY_SUMMARY_MESSAGE_FORMAT = "Total Purchase Cost: $ %s\n"
        + "Total Selling Price: $ %s\nTotal Profits: $ %s\nGross Profit Margin (in percent): %s\n";
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static final String ITEM_INFO = "%o. %s (purchase cost: %s, selling price: %s)\n";
    public static final String NEGATIVE_PROFIT_WARNING_MESSAGE =
        "!!! WARNING:\nNegative profit,\nplease ensure that future items are priced more than purchase cost.\n";
    public static final String NO_SOLD_ITEMS_BETWEEN_MONTHS_MESSAGE_FORMAT = "No sold items in between %s and %s\n";
    public static final String NO_SOLD_ITEMS_IN_THE_MONTH_MESSAGE_FORMAT = "No sold items in the month of %s\n";
    private final String selectedDate;
    private final String selectedEndDate;

    public SalesReport(String selectedDate, String selectedEndDate) {
        this.selectedDate = selectedDate;
        this.selectedEndDate = selectedEndDate;
    }

    /**
     * Generate a string to contain all sold item statistics.
     *
     * @return A String containing the sold items statistics
     *
     * @throws EmptyListException If the soldItems shelf does not contain items
     */
    public String generateSoldItemStats()
        throws EmptyListException, IllegalArgumentException {
        SalesManager salesManager = SalesManager.getSalesManager();
        String stringToReturn = "";
        ArrayList<SoldItem> selectedSoldItems = salesManager.filterSoldItems(selectedDate, selectedEndDate);
        if (selectedSoldItems.size() != 0) {
            stringToReturn = getSalesStatisticsString(selectedSoldItems);
        } else {
            stringToReturn += getEmptySoldItemInMonthMessage(selectedDate, selectedEndDate);
        }
        logger.log(Level.INFO, "Generate Sold Items Statistics success.");
        return stringToReturn;
    }

    private String getSalesStatisticsString(ArrayList<SoldItem> selectedSoldItems) {

        assert selectedSoldItems.size() != 0;
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
        assert grossProfitMargin != null;

        String stringToReturn = String.format(TOTAL_MONETARY_SUMMARY_MESSAGE_FORMAT,
            decimalFormat.format(totalPurchaseCost), decimalFormat.format(totalSellingPrice),
            decimalFormat.format(totalProfits), decimalFormat.format(grossProfitMargin));

        if (grossProfitMargin.compareTo(BigDecimal.ZERO) == -1) {
            stringToReturn += NEGATIVE_PROFIT_WARNING_MESSAGE;
        }

        logger.log(Level.INFO, "Get Sales Statistics success.");
        return stringToReturn;
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
        if (selectedSoldItems.size() != 0) {
            String soldItemsDetailsToAppend = getSoldItemsDetailsString(selectedSoldItems);
            info.append(soldItemsDetailsToAppend);
        } else {
            assert selectedSoldItems.size() == 0;
            String emptySoldItemInMonthMessage = getEmptySoldItemInMonthMessage(selectedDate, selectedEndDate);
            info.append(emptySoldItemInMonthMessage);
        }
        logger.log(Level.INFO, "Get Sold Item Details success.");
        return info.toString().trim();
    }

    private String getSoldItemsDetailsString(ArrayList<SoldItem> selectedSoldItems) {
        String soldItemDetailsString = "";
        assert selectedSoldItems.size() != 0;
        for (int i = 0; i < selectedSoldItems.size(); i++) {
            Item selectedItem = selectedSoldItems.get(i);
            int index = i + 1;
            soldItemDetailsString += String.format(ITEM_INFO, index,
                selectedItem.getName(), selectedItem.getPurchaseCost(), selectedItem.getSellingPrice());
        } //todo: add remarks
        return soldItemDetailsString;
    }

    private String getEmptySoldItemInMonthMessage(String selectedDate, String selectedEndDate) {
        String emptySoldItemInPeriodString;

        if (selectedEndDate.equals("")) {
            emptySoldItemInPeriodString = String.format(NO_SOLD_ITEMS_IN_THE_MONTH_MESSAGE_FORMAT, selectedDate);
            logger.log(Level.INFO, "Get No Sold Items in month string success.");
        } else {
            emptySoldItemInPeriodString = String.format(
                NO_SOLD_ITEMS_BETWEEN_MONTHS_MESSAGE_FORMAT, selectedDate, selectedEndDate);
            logger.log(Level.INFO, "Get No Sold Items in a period string success.");
        }

        return emptySoldItemInPeriodString;
    }
}
