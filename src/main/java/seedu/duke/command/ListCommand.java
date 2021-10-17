package seedu.duke.command;

import seedu.duke.model.Item;
import seedu.duke.model.Shelf;

public class ListCommand extends Command {

    private static final String ITEM_INFO = "%o. %s (purchase cost: %s, selling price: %s)\n";

    /**
     * Retrieves list of items from Shelf.
     *
     * @param list the Shelf in which lsit is retrieved
     * @return list of items
     */
    private String getList(Shelf list) {
        String info = "";
        for (int i = 0; i < list.getSize();  i++) {
            Item selectedItem = list.getItem(i);
            int index = i + 1;
            info += String.format(ITEM_INFO, index,
                    selectedItem.getName(), selectedItem.getPurchaseCost(), selectedItem.getSellingPrice());
        }
        return info.trim();
    }

    /**
     * Executes the operation of listing all the items.
     *
     * @param list the Shelf to retrieve list of items
     */
    public void execute(Shelf list) {
        int initialSize = list.getSize();
        String result = getList(list);
        System.out.println(result);
        assert initialSize == list.getSize() : "List size should not be changed";
    }
}
