package seedu.duke.salesmanager;

import seedu.duke.model.Item;
import seedu.duke.model.Shelf;
import seedu.duke.model.ShelfList;
import seedu.duke.model.exception.DuplicateShelfException;
import seedu.duke.model.exception.IllegalArgumentException;
import seedu.duke.model.exception.ItemNotExistException;
import seedu.duke.model.exception.ShelfNotExistException;

import java.time.LocalDateTime;
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
     * @param selectedDate the target date
     * @return an ArrayList of SoldItems
     */
    public ArrayList<SoldItem> filterSoldItems(String selectedDate) {
        ArrayList<SoldItem> filteredSoldItems = new ArrayList<>();
        int selectedYear = Integer.parseInt(selectedDate.split("-")[0]);
        int selectedMonth = Integer.parseInt(selectedDate.split("-")[1]);
        for (int i = 0; i < soldItems.getSize();  i++) {
            SoldItem selectedSoldItem = (SoldItem) soldItems.getItem(i); //todo: check if can use cast
            int yearOfItem = selectedSoldItem.getSaleTime().getYear();
            int monthOfItem = selectedSoldItem.getSaleTime().getMonthValue();
            boolean isWithinSelectedYear = (yearOfItem == selectedYear);
            boolean isWithinSelectedMonth = (monthOfItem == selectedMonth);
            if (isWithinSelectedYear && isWithinSelectedMonth) {
                filteredSoldItems.add(selectedSoldItem);
            }
        }
        return filteredSoldItems; //todo: check if can use arraylist here
    }

}
