package seedu.duke.command;

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
     */
    public void execute(ItemContainer list) {
        Item selectedItem = list.getItem(name);
        String output = list.getDescription(selectedItem);
        System.out.println(output);
    }
}
