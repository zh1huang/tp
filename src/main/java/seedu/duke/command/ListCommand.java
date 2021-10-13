package seedu.duke.command;

import seedu.duke.model.ItemContainer;

public class ListCommand extends Command {

    /**
     * Executes the operation of listing all the items.
     *
     * @param list the ItemContainer to retrieve list of items
     */
    public void execute(ItemContainer list) {
        int initialSize = list.getSize();
        System.out.println(list);
        assert initialSize == list.getSize() : "List size should not be changed";
    }
}
