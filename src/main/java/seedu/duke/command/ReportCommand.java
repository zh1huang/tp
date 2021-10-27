package seedu.duke.command;

import seedu.duke.salesmanager.SalesReport;

public class ReportCommand extends Command {
    private final String selectedDate;
    private final String reportType;

    public ReportCommand (String selectedDate, String reportType) {
        this.selectedDate = selectedDate;
        this.reportType = reportType;
    }

    public String execute() {
        SalesReport newSalesReport = new SalesReport(selectedDate);
        return newSalesReport.generateSoldItemDetails();
    }
}
