package seedu.duke.command;

import seedu.duke.model.ItemContainer;

public class ListCommand extends Command{

    @Override
    public void execute(ItemContainer list) {
        list.printItemContainer();
    }

}
