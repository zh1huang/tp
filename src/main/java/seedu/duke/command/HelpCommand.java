package seedu.duke.command;

import seedu.duke.model.Shelf;

public class HelpCommand extends Command {

    private static final String commandList = "Here are the performable actions:\n"
            + "Words in UPPER_CASE are the parameters to be supplied by the user.\n"
            + "Items in square brackets [ ] are optional.\n"
            + "____________________________________________________________________________________________\n"
            + "Description: Command format\n"
            + "____________________________________________________________________________________________\n"
            + "1. Get help : help\n"
            + "2. Add item: add n/NAME c/CATEGORY p/PURCHASE_PRICE s/SELLING_PRICE q/QUANTITY [r/REMARKS]\n"
            + "3. Delete item: delete n/NAME\n"
            + "4. List items: list [c/CATEGORY]\n"
            + "5. Get information of item : get n/NAME [p/PROPERTY]\n"
            + "6. Edit an item: edit n/NAME p/PROPERTY v/VALUE [s/SHOWRESULT]\n"
            + "7. Generate sales report: report t/PERIOD\n"
            + "8. Exit program: bye\n"
            + "____________________________________________________________________________________________\n";

    /**
     * Executes help command.
     *
     * @param list the Shelf in which HelpCommand is executed
     */
    public void execute(Shelf list) {
        System.out.println(commandList);
    }
}
