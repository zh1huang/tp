package seedu.duke.command;

import seedu.duke.model.Item;
import seedu.duke.model.ItemContainer;

import java.math.BigDecimal;

public class GetCommand extends Command {

    private final Item selectedItem;
    //private final String name;

    public GetCommand(String name, ItemContainer list) {
        this.selectedItem = list.getItem(name);
        //this.name = selectedItem.getName();
    }

    /**
     * Executes the operation of adding the item to the list.
     *
     * @param list the ItemContainer to get the information
     */
    public void execute(ItemContainer list) {
        String output = list.getDescription(selectedItem);
        System.out.println(output);
    }
}
