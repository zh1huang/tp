package seedu.duke.salesmanager;

import seedu.duke.model.Item;
import seedu.duke.model.Shelf;
import seedu.duke.model.ShelfList;
import seedu.duke.model.exception.DuplicateShelfException;
import seedu.duke.model.exception.IllegalArgumentException;
import seedu.duke.model.exception.ItemNotExistException;
import seedu.duke.model.exception.ShelfNotExistException;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SalesManager {

    private static SalesManager salesManager;
    private Shelf soldItems;

    private SalesManager() {
        ShelfList shelfList = ShelfList.getShelfList();
        try {
            soldItems = shelfList.getShelf("soldItems");
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
     * @return The SoldItem object that is stored in the ItemContainer "soldItems"
     * @throws ItemNotExistException If the item does not belong to any ItemContainer
     */
    public SoldItem sell(Item item) throws ItemNotExistException {
        // todo check if already sold
        ShelfList.getShelfList().shelfOfItem(item).deleteItem(item);
        SoldItem temp;
        LocalDateTime saleTime = LocalDateTime.now();
        try {
            temp = new SoldItem(item.getName(), item.getPurchaseCost(), item.getSellingPrice(),
                item.getRemarks(), saleTime);
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
     * @return an ArrayList of SoldItems
     */
    public ArrayList<SoldItem> filterSoldItems(String selectedStartDate, String selectedEndDate) {
        ArrayList<SoldItem> filteredSoldItems = new ArrayList<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM");

        if (selectedEndDate == null) {
            filteredSoldItems = getFilteredListInSpecificMonth(selectedStartDate, dateTimeFormatter);
        } else {
            filteredSoldItems = getFilteredListWithinAPeriod(selectedStartDate,
                selectedEndDate, dateTimeFormatter);
        }

        return filteredSoldItems; //todo: check if can use arraylist here
    }

    /**
     * Filters the list of sold items for items in the specific month.
     *
     * @param selectedStartDate Starting year-month string
     * @param dateTimeFormatter Defines DateTimeFormat to parse
     * @return Arraylist of filtered sold items
     */
    private ArrayList<SoldItem> getFilteredListInSpecificMonth(String selectedStartDate,
                                                               DateTimeFormatter dateTimeFormatter) {

        ArrayList<SoldItem> filteredSoldItems = new ArrayList<>();
        YearMonth yearMonthToSearch = YearMonth.parse(selectedStartDate, dateTimeFormatter);
        for (int i = 0; i < soldItems.getSize(); i++) {
            SoldItem selectedSoldItem = (SoldItem) soldItems.getItem(i);
            YearMonth itemSoldYearMonth = YearMonth.from(selectedSoldItem.getSaleTime());
            if (itemSoldYearMonth.equals(yearMonthToSearch)) {
                filteredSoldItems.add(selectedSoldItem);
            }
        }
        return filteredSoldItems;
    }

    /**
     * Filters the list for items sold between the starting year-month to the ending year-month specified
     * by the user.
     *
     * @param selectedStartDate Starting year-month string
     * @param selectedEndDate Ending year-month string
     * @param dateTimeFormatter Defines DateTimeFormat to parse
     * @return Arraylist of filtered sold items
     */
    private ArrayList<SoldItem> getFilteredListWithinAPeriod(String selectedStartDate,
        String selectedEndDate, DateTimeFormatter dateTimeFormatter) {

        ArrayList<SoldItem> filteredSoldItems = new ArrayList<>();
        YearMonth startYearMonthToSearch = YearMonth.parse(selectedStartDate, dateTimeFormatter);
        YearMonth endYearMonthToSearch = YearMonth.parse(selectedEndDate, dateTimeFormatter);
        for (int i = 0; i < soldItems.getSize(); i++) {
            SoldItem selectedSoldItem = (SoldItem) soldItems.getItem(i);
            YearMonth itemSoldYearMonth = YearMonth.from(selectedSoldItem.getSaleTime());
            if (!itemSoldYearMonth.isBefore(startYearMonthToSearch)
                && !itemSoldYearMonth.isAfter(endYearMonthToSearch)) {
                filteredSoldItems.add(selectedSoldItem);
            }
        }
        return filteredSoldItems;
    }


}
