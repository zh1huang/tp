package seedu.duke.command;

import seedu.duke.model.ItemContainer;

public class ListCommand extends Command {

    public void execute(ItemContainer list) {
        String output = list.printItemContainer();
        System.out.println(output);
    }
}
