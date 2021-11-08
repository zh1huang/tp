package seedu.duke.logic.command;

//@@author zh1huang
public class HelpCommand extends Command {

    public static final String HELP_STRING = "help";
    public static final String INVALID_HELP_COMMAND = "Error: Type 'help' without additional parameters\n";
    public static final String COMMAND_LIST = "Here are the performable actions:\n"
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
            + "7. Search item: search k/KEYWORD\n"
            + "8. Get information of item : get shlv/SHELF_NAME i/INDEX\n"
            + "9. Edit an item: edit shlv/SHELF_NAME i/INDEX p/PROPERTY v/VALUE \n"
            + "10. Sell an item: sell id/ITEM_ID\n"
            + "11. Markup price of item: markup shlv/SHELF_NAME i/INDEX [%/PERCENT_MARKUP]\n"
            + "12. Generate sales report: report t/CONTENT_TYPE ym/START-YEAR-MONTH [ym/END-YEAR-MONTH]\n"
            + "13. Exit program: bye\n";

    /**
     * Executes help command.
     */
    public String execute() {
        return COMMAND_LIST;
    }
}
