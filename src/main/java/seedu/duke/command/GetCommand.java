package seedu.duke.command;

import seedu.duke.model.Item;
import seedu.duke.model.ItemContainer;

public class GetCommand extends Command {

    private final Item selectedItem;

    /**
     * Constructor for GetCommand.
     *
     * @param name the name of selected item
     * @param list the itemContainer where the selected item is stored in
     */
    public GetCommand(String name, ItemContainer list) {
        this.selectedItem = list.getItem(name);
    }

    /**
     * Executes the operation of getting the information the item.
     *
     * @param list the ItemContainer in which information of item is retrieved.
     */
    public void execute(ItemContainer list) {
        String output = list.getDescription(selectedItem);
        System.out.println(output);
    }
}
