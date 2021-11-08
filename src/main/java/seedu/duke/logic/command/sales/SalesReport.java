package seedu.duke.logic.command.sales;

import seedu.duke.logic.command.exception.IllegalArgumentCommandException;
import seedu.duke.model.Item;
import seedu.duke.model.SoldItem;
import seedu.duke.ui.Wrapping;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SalesReport {

    public static final String TOTAL_MONETARY_SUMMARY_MESSAGE_FORMAT = "Total Purchase Cost: $ %s\n"
            + "Total Selling Price: $ %s\nTotal Profits: $ %s\nGross Profit Margin (in percent): %s\n";
    public static final String NEGATIVE_PROFIT_WARNING_MESSAGE =
            "!!! WARNING:\nNegative profit,\nplease ensure that future items are priced more than purchase cost.\n";
    public static final String NO_SOLD_ITEMS_BETWEEN_MONTHS_MESSAGE_FORMAT = "No sold items in between %s and %s\n";
    public static final String NO_SOLD_ITEMS_IN_THE_MONTH_MESSAGE_FORMAT = "No sold items in the month of %s\n";
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static final String HEADER =
            "  No  |                    Item                 |  Cost   |  Price  |  Profit |      Sold Time    \n";
    private static final String BORDER =
            "-------------------------------------------------------------------------------------------------\n";
    private static final int INDEX_TABLE_LENGTH = 4;
    private static final int ITEM_TABLE_LENGTH = 40;
    private static final int COST_TABLE_LENGTH = 8;
    private static final int PRICE_TABLE_LENGTH = 8;
    private static final int PROFIT_TABLE_LENGTH = 8;
    private static final String ITEM_INFO = "%s| %s| %s| %s| %s|%s\n";

    private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
    private static final int FOUR_DECIMAL_POINT = 4;
    private static final int INTEGER_VALUE_ONE = 1;
    private static final int INTEGER_VALUE_MINUS_ONE = -1;
    private static final int INTEGER_VALUE_ZERO = 0;
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
     * @throws IllegalArgumentCommandException if the selected dates are not valid
     */
    public String generateSoldItemStats()
            throws IllegalArgumentCommandException {
        SalesManager salesManager = SalesManager.getSalesManager();
        String stringToReturn = "";
        ArrayList<SoldItem> selectedSoldItems = salesManager.filterSoldItems(selectedDate, selectedEndDate);
        if (selectedSoldItems.size() != INTEGER_VALUE_ZERO) {
            stringToReturn = getSalesStatisticsString(selectedSoldItems);
        } else {
            stringToReturn += getEmptySoldItemInMonthMessage(selectedDate, selectedEndDate);
        }
        logger.log(Level.INFO, "Generate Sold Items Statistics success.");
        return stringToReturn;
    }

    //@@author t-l-xin
    private String getSalesStatisticsString(ArrayList<SoldItem> selectedSoldItems) {

        assert selectedSoldItems.size() != INTEGER_VALUE_ZERO;
        BigDecimal totalPurchaseCost = BigDecimal.ZERO;
        BigDecimal totalSellingPrice = BigDecimal.ZERO;
        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        for (int i = 0; i < selectedSoldItems.size(); i++) {

            Item selectedItem = selectedSoldItems.get(i);
            totalPurchaseCost = totalPurchaseCost.add(new BigDecimal(selectedItem.getPurchaseCost()));
            totalSellingPrice = totalSellingPrice.add(new BigDecimal(selectedItem.getSellingPrice()));
        }

        BigDecimal totalProfits = totalSellingPrice.subtract(totalPurchaseCost);
        BigDecimal grossProfitMargin = totalProfits.divide(totalSellingPrice, FOUR_DECIMAL_POINT,
                RoundingMode.HALF_UP).multiply(ONE_HUNDRED);
        assert grossProfitMargin != null;

        String stringToReturn = String.format(TOTAL_MONETARY_SUMMARY_MESSAGE_FORMAT,
                decimalFormat.format(totalPurchaseCost), decimalFormat.format(totalSellingPrice),
                decimalFormat.format(totalProfits), decimalFormat.format(grossProfitMargin));

        if (grossProfitMargin.compareTo(BigDecimal.ZERO) == INTEGER_VALUE_MINUS_ONE) {
            stringToReturn += NEGATIVE_PROFIT_WARNING_MESSAGE;
        }

        logger.log(Level.INFO, "Get Sales Statistics success.");
        return stringToReturn;
    }

    //@@author t-l-xin
    /**
     * Generate and gets every sold item details in the soldItems shelf.
     *
     * @return A String containing all sold item details
     * @throws IllegalArgumentCommandException if the selected dates are not valid
     */
    public String generateSoldItemDetails() throws IllegalArgumentCommandException {
        SalesManager salesManager = SalesManager.getSalesManager();
        ArrayList<SoldItem> selectedSoldItems = salesManager.filterSoldItems(selectedDate, selectedEndDate);
        StringBuilder info = new StringBuilder();
        if (selectedSoldItems.size() != INTEGER_VALUE_ZERO) {
            String soldItemsDetailsToAppend = getSoldItemsDetailsString(selectedSoldItems);
            info.append(soldItemsDetailsToAppend);
        } else {
            assert selectedSoldItems.size() == 0 : "Should have no selected SoldItems";
            String emptySoldItemInMonthMessage = getEmptySoldItemInMonthMessage(selectedDate, selectedEndDate);
            info.append(emptySoldItemInMonthMessage);
        }
        logger.log(Level.INFO, "Get Sold Item Details success.");
        return info.toString().trim();
    }

    //@@author haoyusimon
    private String getSoldItemsDetailsString(ArrayList<SoldItem> selectedSoldItems) {
        assert selectedSoldItems.size() != 0;
        StringBuilder details = new StringBuilder();
        details.append(HEADER);
        details.append(BORDER);
        for (int i = 0; i < selectedSoldItems.size(); i++) {
            SoldItem selectedSoldItem = selectedSoldItems.get(i);
            int index = i + 1;
            String indexAsString = Wrapping.restrictMessageLength(Integer.toString(index), INDEX_TABLE_LENGTH);
            String name = selectedSoldItem.getName();
            name = Wrapping.restrictMessageLength(name, ITEM_TABLE_LENGTH);
            String cost = selectedSoldItem.getPurchaseCost();
            String wrappedCost = Wrapping.restrictMessageLength(cost, COST_TABLE_LENGTH);
            String price = selectedSoldItem.getSellingPrice();
            String wrappedPrice = Wrapping.restrictMessageLength(price, PRICE_TABLE_LENGTH);
            BigDecimal profit = new BigDecimal(price).subtract(new BigDecimal(cost));
            String profitAsString = profit.toString();
            profitAsString = Wrapping.restrictMessageLength(profitAsString, PROFIT_TABLE_LENGTH);
            DateTimeFormatter saleTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String saleTime = selectedSoldItem.getSaleTime().format(saleTimeFormatter);
            details.append(String.format(ITEM_INFO, indexAsString, name,
                    wrappedCost, wrappedPrice, profitAsString, saleTime));
        } //todo: add remarks
        return details.toString().trim();
    }

    //@@author t-l-xin
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
