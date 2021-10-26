package seedu.duke.command;

public class HelpCommand extends Command {

    private static final String COMMAND_LIST = "Here are the performable actions:\n"
            + "Words in UPPER_CASE are the parameters to be supplied by the user.\n"
            + "Items in square brackets [ ] are optional.\n"
            + "____________________________________________________________________________________________\n"
            + "Description: Command format\n"
            + "____________________________________________________________________________________________\n"
            + "1. Get help : help\n"
            + "2. Create new shelf: create shlv/SHELF_NAME\n"
            + "3. Remove existing shelf: remove shlv/SHELF_NAME\n"
            + "4. Add item: add n/NAME shlv/SHELF_NAME p/PURCHASE_PRICE s/SELLING_PRICE q/QUANTITY [r/REMARKS]\n"
            + "5. Delete item: delete shlv/SHELF_NAME i/INDEX\n"
            + "6. List items: list [shlv/SHELF_NAME]\n"
            + "7. Get information of item : get shlv/SHELF_NAME i/INDEX\n"
            + "8. Edit an item: edit shlv/SHELF_NAME i/INDEX p/PROPERTY v/VALUE \n"
            + "9. Sell an item: sell shlv/SHELF_NAME i/INDEX\n"
            + "10. Generate sales report: report t/TYPE [ym/YEAR-MONTH]\n"
            + "11. Exit program: bye\n"
            + "____________________________________________________________________________________________\n";

    /**
     * Executes help command.
     */
    public String execute() {
        System.out.println(COMMAND_LIST);
        return COMMAND_LIST;
    }
}
