package seedu.duke.command;

import seedu.duke.model.Item;
import seedu.duke.model.ItemContainer;

public class ListCommand extends Command {

    /**
     * Retrieves list of items from ItemContainer.
     *
     * @param list the ItemContainer in which lsit is retrieved
     * @return list of items
     */
    private String getList(ItemContainer list) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < list.getSize();  i++) {
            Item selectedItem = list.getItem(i);
            result.append(i + 1).append(". ")
                    .append(selectedItem.getName())
                    .append(" (purchase cost: ")
                    .append(selectedItem.getPurchaseCost())
                    .append(", selling price: ")
                    .append(selectedItem.getSellingPrice())
                    .append(")")
                    .append("\n");
        }

        return result.toString().trim();
    }

    /**
     * Executes the operation of listing all the items.
     *
     * @param list the ItemContainer to retrieve list of items
     */
    public void execute(ItemContainer list) {
        int initialSize = list.getSize();
        String result = getList(list);
        System.out.println(result);
        assert initialSize == list.getSize() : "List size should not be changed";
    }
}
