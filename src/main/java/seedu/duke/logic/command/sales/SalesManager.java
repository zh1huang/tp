package seedu.duke.logic.command.sales;

import seedu.duke.logic.command.exception.IllegalArgumentCommandException;
import seedu.duke.model.Item;
import seedu.duke.model.Shelf;
import seedu.duke.model.ShelfList;
import seedu.duke.model.SoldItem;
import seedu.duke.model.exception.ItemNotExistModelException;
import seedu.duke.model.exception.ShelfNotExistModelException;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SalesManager {

    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static final String YEAR_MONTH_ARGUMENTS_SWAPPED_EXCEPTION_MESSAGE = "Invalid argument sequence,"
            + " 2nd Year Month parameter is earlier than 1st Year Month parameter.\nParameters are swapped.";
    public static final String INVALID_YEAR_MONTH_MESSAGE = "Invalid Year Month";
    public static final String ERROR_PARSING_START_END_YEAR_MONTH_LOGGING_MESSAGE =
            "Error parsing Start & End YearMonth.";
    public static final String PARSE_YEAR_MONTH_SUCCESS_LOGGING_MESSAGE = "Parse Start & End YearMonth success.";
    public static final String YEAR_MONTH_PARAMETERS_SWAPPED_LOGGING_MESSAGE =
            "YearMonth parameters are swapped, Start YearMonth is after End YearMonth.";
    public static final String GET_FILTERED_LIST_WITHIN_A_PERIOD_SUCCESS_LOGGING_MESSAGE =
            "Get filtered list within a period success.";
    private static SalesManager salesManager;

    /**
     * Retrieves the global SalesManager.
     *
     * @return The global SalesManager instance
     */
    public static SalesManager getSalesManager() {
        if (salesManager == null) {
            salesManager = new SalesManager();
        }
        return salesManager;
    }

    private Shelf getSoldItemsShelf() {
        try {
            return ShelfList.getShelfList().getShelf("soldItems");
        } catch (ShelfNotExistModelException e) {
            logger.log(Level.INFO, "Initialize soldItems shelf");
            try {
                return ShelfList.getShelfList().addShelf("soldItems");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Mark an item as sold.
     * This moves the item from its ItemContainer to the ItemContainer "soldItems"
     *
     * @param item The item to be marked as sold
     * @return The SoldItem object that is stored in the ItemContainer "soldItems"
     * @throws ItemNotExistModelException If the item does not belong to any ItemContainer
     */
    public SoldItem sell(Item item) throws ItemNotExistModelException {
        // todo check if already sold
        ShelfList.getShelfList().shelfOfItem(item).deleteItem(item);
        SoldItem temp;
        LocalDateTime saleTime = LocalDateTime.now();
        try {
            temp = new SoldItem(item.getName(), item.getPurchaseCost(), item.getSellingPrice(),
                    item.getRemarks(), item.getID(), saleTime);
            getSoldItemsShelf().addItem(temp);
            return temp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //@@author t-l-xin
    /**
     * Filter out soldItems that are not sold within the input date.
     *
     * @param selectedStartDate the target date
     * @return an ArrayList of SoldItems
     */
    public ArrayList<SoldItem> filterSoldItems(String selectedStartDate, String selectedEndDate)
            throws IllegalArgumentCommandException {
        ArrayList<SoldItem> filteredSoldItems;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM");

        if (selectedEndDate.equals("")) {
            filteredSoldItems = getFilteredListInSpecificMonth(selectedStartDate, dateTimeFormatter);
            logger.log(Level.INFO, "Get Filtered List in a specific month of year success.");

        } else {
            filteredSoldItems = getFilteredListWithinAPeriod(selectedStartDate,
                    selectedEndDate, dateTimeFormatter);
            logger.log(Level.INFO, "Get Filtered List within a period success.");
        }

        return filteredSoldItems;
    }

    //@@author t-l-xin
    /**
     * Filters the list of sold items for items in the specific month.
     *
     * @param selectedStartDate Starting year-month string
     * @param dateTimeFormatter Defines DateTimeFormat to parse
     * @return Arraylist of filtered sold items
     */
    private ArrayList<SoldItem> getFilteredListInSpecificMonth(
            String selectedStartDate, DateTimeFormatter dateTimeFormatter) throws IllegalArgumentCommandException {

        YearMonth yearMonthToSearch;
        ArrayList<SoldItem> filteredSoldItems = new ArrayList<>();
        try {
            yearMonthToSearch = YearMonth.parse(selectedStartDate, dateTimeFormatter);
            logger.log(Level.INFO, "Parse YearMonth success.");
        } catch (DateTimeParseException e) {
            logger.log(Level.WARNING, "Error parsing YearMonth.");
            throw new IllegalArgumentCommandException(INVALID_YEAR_MONTH_MESSAGE);
        }

        for (int i = 0; i < getSoldItemsShelf().getItemCount(); i++) {
            SoldItem selectedSoldItem = (SoldItem) getSoldItemsShelf().getItem(i);
            YearMonth itemSoldYearMonth = YearMonth.from(selectedSoldItem.getSaleTime());
            if (itemSoldYearMonth.equals(yearMonthToSearch)) {
                filteredSoldItems.add(selectedSoldItem);
            }
        }
        logger.log(Level.INFO, "Get filtered list in a specific month success.");
        return filteredSoldItems;
    }

    //@@author t-l-xin
    /**
     * Filters the list for items sold between the starting year-month to the ending year-month specified
     * by the user.
     *
     * @param selectedStartDate Starting year-month string
     * @param selectedEndDate   Ending year-month string
     * @param dateTimeFormatter Defines DateTimeFormat to parse
     * @return Arraylist of filtered sold items
     */
    private ArrayList<SoldItem> getFilteredListWithinAPeriod(
            String selectedStartDate, String selectedEndDate, DateTimeFormatter dateTimeFormatter)
            throws IllegalArgumentCommandException {
        YearMonth startYearMonthToSearch;
        YearMonth endYearMonthToSearch;

        ArrayList<SoldItem> filteredSoldItems = new ArrayList<>();
        try {
            startYearMonthToSearch = YearMonth.parse(selectedStartDate, dateTimeFormatter);
            endYearMonthToSearch = YearMonth.parse(selectedEndDate, dateTimeFormatter);
            logger.log(Level.INFO, PARSE_YEAR_MONTH_SUCCESS_LOGGING_MESSAGE);
        } catch (DateTimeParseException e) {
            logger.log(Level.WARNING, ERROR_PARSING_START_END_YEAR_MONTH_LOGGING_MESSAGE);
            throw new IllegalArgumentCommandException(INVALID_YEAR_MONTH_MESSAGE);
        }

        if (endYearMonthToSearch.isBefore(startYearMonthToSearch)) {
            logger.log(Level.WARNING, YEAR_MONTH_PARAMETERS_SWAPPED_LOGGING_MESSAGE);
            throw new IllegalArgumentCommandException(YEAR_MONTH_ARGUMENTS_SWAPPED_EXCEPTION_MESSAGE);
        }

        for (int i = 0; i < getSoldItemsShelf().getItemCount(); i++) {
            SoldItem selectedSoldItem = (SoldItem) getSoldItemsShelf().getItem(i);
            YearMonth itemSoldYearMonth = YearMonth.from(selectedSoldItem.getSaleTime());
            if (!itemSoldYearMonth.isBefore(startYearMonthToSearch)
                    && !itemSoldYearMonth.isAfter(endYearMonthToSearch)) {
                filteredSoldItems.add(selectedSoldItem);
            }
        }

        logger.log(Level.INFO, GET_FILTERED_LIST_WITHIN_A_PERIOD_SUCCESS_LOGGING_MESSAGE);
        return filteredSoldItems;
    }

}
