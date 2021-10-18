package seedu.duke.salesmanager;

import seedu.duke.model.Item;
import seedu.duke.model.Shelf;
import seedu.duke.model.ShelfList;
import seedu.duke.model.exception.ItemNotExistException;
import seedu.duke.model.exception.ShelfNotExistException;

import java.time.LocalDateTime;

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
            temp = new SoldItem(item.getName(), item.getPurchaseCost(), item.getSellingPrice(), saleTime);
            soldItems.addItem(temp);
            return temp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
