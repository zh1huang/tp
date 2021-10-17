package seedu.duke.salesmanager;

import seedu.duke.model.ContainerList;
import seedu.duke.model.Item;
import seedu.duke.model.ItemContainer;
import seedu.duke.model.exception.DuplicateItemException;
import seedu.duke.model.exception.IllegalArgumentException;
import seedu.duke.model.exception.ItemContainerNotExistException;
import seedu.duke.model.exception.ItemNotExistException;

import java.time.LocalDateTime;

public class SalesManager {

    private static SalesManager salesManager;
    private ItemContainer soldItems;

    private SalesManager() {
        ContainerList containerList = ContainerList.getContainerList();
        try {
            soldItems = containerList.getContainer("soldItems");
        } catch (ItemContainerNotExistException e) {
            try {
                soldItems = containerList.addContainer("soldItems");
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
        ContainerList.getContainerList().containerOfItem(item).deleteItem(item);
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
