package seedu.duke.command;

import seedu.duke.model.Item;
import seedu.duke.model.ItemContainer;

public class GetCommand extends Command {

    private final Item selectedItem;

    public GetCommand(String name, ItemContainer list) {
        this.selectedItem = list.getItem(name);
    }

    /**
     * Executes the operation of getting the information the item.
     *
     * @param list the ItemContainer to get the information
     */
    public void execute(ItemContainer list) {
        String output = list.getDescription(selectedItem);
        System.out.println(output);
    }
}
