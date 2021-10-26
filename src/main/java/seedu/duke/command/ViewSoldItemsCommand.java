package seedu.duke.command;

import seedu.duke.salesmanager.SalesReport;

public class ViewSoldItemsCommand extends Command {
    private final String selectedDate;

    public ViewSoldItemsCommand(String selectedDate) {
        this.selectedDate = selectedDate;
    }

    public String execute() {
        SalesReport newSalesReport = new SalesReport(selectedDate);
        return newSalesReport.generateSoldItemDetails();
    }
}
