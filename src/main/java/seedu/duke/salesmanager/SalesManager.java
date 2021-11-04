package seedu.duke.salesmanager;

import seedu.duke.command.exception.IllegalArgumentException;
import seedu.duke.model.Item;
import seedu.duke.model.Shelf;
import seedu.duke.model.ShelfList;
import seedu.duke.model.SoldItem;
import seedu.duke.model.exception.ItemNotExistException;
import seedu.duke.model.exception.ShelfNotExistException;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SalesManager {
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static SalesManager salesManager;
    private Shelf soldItems;

    private SalesManager() {
        ShelfList shelfList = ShelfList.getShelfList();
        try {
            soldItems = shelfList.getShelf("soldItems", false);
        } catch (ShelfNotExistException e) {
            try {
                soldItems = shelfList.addShelf("soldItems");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

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

    /**
     * Mark an item as sold.
     * This moves the item from its ItemContainer to the ItemContainer "soldItems"
     *
     * @param item The item to be marked as sold
     *
     * @return The SoldItem object that is stored in the ItemContainer "soldItems"
     *
     * @throws ItemNotExistException If the item does not belong to any ItemContainer
     */
    public SoldItem sell(Item item) throws ItemNotExistException {
        // todo check if already sold
        ShelfList.getShelfList().shelfOfItem(item).deleteItem(item);
        SoldItem temp;
        LocalDateTime saleTime = LocalDateTime.now();
        try {
            temp = new SoldItem(item.getName(), item.getPurchaseCost(), item.getSellingPrice(),
                    item.getRemarks(), item.getID(), saleTime);
            soldItems.addItem(temp);
            return temp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Filter out soldItems that are not sold within the input date.
     *
     * @param selectedStartDate the target date
     *
     * @return an ArrayList of SoldItems
     */
    public ArrayList<SoldItem> filterSoldItems(String selectedStartDate, String selectedEndDate)
        throws IllegalArgumentException {
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

    /**
     * Filters the list of sold items for items in the specific month.
     *
     * @param selectedStartDate Starting year-month string
     * @param dateTimeFormatter Defines DateTimeFormat to parse
     *
     * @return Arraylist of filtered sold items
     */
    private ArrayList<SoldItem> getFilteredListInSpecificMonth(String selectedStartDate,
                                                               DateTimeFormatter dateTimeFormatter)
        throws IllegalArgumentException {
        YearMonth yearMonthToSearch;
        ArrayList<SoldItem> filteredSoldItems = new ArrayList<>();
        try {
            yearMonthToSearch = YearMonth.parse(selectedStartDate, dateTimeFormatter);
            logger.log(Level.INFO, "Parse YearMonth success.");
        } catch (DateTimeParseException e) {
            logger.log(Level.WARNING, "Error parsing YearMonth.");
            throw new IllegalArgumentException("Invalid Year Month");
        }

        for (int i = 0; i < soldItems.getSize(); i++) {
            SoldItem selectedSoldItem = (SoldItem) soldItems.getItem(i);
            YearMonth itemSoldYearMonth = YearMonth.from(selectedSoldItem.getSaleTime());
            if (itemSoldYearMonth.equals(yearMonthToSearch)) {
                filteredSoldItems.add(selectedSoldItem);
            }
        }
        logger.log(Level.INFO, "Get filtered list in a specific month success.");
        return filteredSoldItems;
    }

    /**
     * Filters the list for items sold between the starting year-month to the ending year-month specified
     * by the user.
     *
     * @param selectedStartDate Starting year-month string
     * @param selectedEndDate   Ending year-month string
     * @param dateTimeFormatter Defines DateTimeFormat to parse
     *
     * @return Arraylist of filtered sold items
     */
    private ArrayList<SoldItem> getFilteredListWithinAPeriod(String selectedStartDate,
                                                             String selectedEndDate,
                                                             DateTimeFormatter dateTimeFormatter)
        throws IllegalArgumentException {
        YearMonth startYearMonthToSearch;
        YearMonth endYearMonthToSearch;

        ArrayList<SoldItem> filteredSoldItems = new ArrayList<>();
        try {
            startYearMonthToSearch = YearMonth.parse(selectedStartDate, dateTimeFormatter);
            endYearMonthToSearch = YearMonth.parse(selectedEndDate, dateTimeFormatter);
            logger.log(Level.INFO, "Parse Start & End YearMonth success.");
        } catch (DateTimeParseException e) {
            logger.log(Level.WARNING, "Error parsing Start & End YearMonth.");
            throw new IllegalArgumentException("Invalid Year Month");
        }

        if (endYearMonthToSearch.isBefore(startYearMonthToSearch)) {
            logger.log(Level.WARNING, "YearMonth parameters are swapped, Start YearMonth is after End YearMonth.");
            throw new IllegalArgumentException(
                "Invalid argument sequence, 2nd Year Month parameter is earlier than 1st Year Month parameter.\n"
                    + "Parameters are swapped.");
        }

        for (int i = 0; i < soldItems.getSize(); i++) {
            SoldItem selectedSoldItem = (SoldItem) soldItems.getItem(i);
            YearMonth itemSoldYearMonth = YearMonth.from(selectedSoldItem.getSaleTime());
            if (!itemSoldYearMonth.isBefore(startYearMonthToSearch)
                && !itemSoldYearMonth.isAfter(endYearMonthToSearch)) {
                filteredSoldItems.add(selectedSoldItem);
            }
        }

        logger.log(Level.INFO, "Get filtered list within a period success.");
        return filteredSoldItems;
    }


}
