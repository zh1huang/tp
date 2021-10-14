package seedu.duke.command;

import seedu.duke.command.exception.ItemNotExistException;
import seedu.duke.model.Item;
import seedu.duke.model.ItemContainer;

public class GetCommand extends Command {

    private final String name;

    /**
     * Constructor for GetCommand.
     *
     * @param name the name of selected item
     */
    public GetCommand(String name) {
        this.name = name;
    }

    /**
     * Executes the operation of getting the information the item.
     *
     * @param list the ItemContainer in which information of item is retrieved.
     * @throws ItemNotExistException if the selected item does not exist
     */
    public void execute(ItemContainer list) throws ItemNotExistException {
        try {
            int initialSize = list.getSize();
            Item selectedItem = list.getItem(name);
            System.out.printf((ItemContainer.ITEM_DESCRIPTION) + "%n",
                    selectedItem.getName(), selectedItem.getSellingPrice(), selectedItem.getPurchaseCost());
            assert initialSize == list.getSize() : "List size should not be changed";
        } catch (seedu.duke.model.exception.ItemNotExistException e) {
            throw new ItemNotExistException(e.getMessage());
        }
    }
}
