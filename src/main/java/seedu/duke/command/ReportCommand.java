package seedu.duke.command;

import seedu.duke.command.exception.NoTypeFoundException;
import seedu.duke.model.exception.IllegalArgumentException;
import seedu.duke.salesmanager.SalesReport;
import seedu.duke.command.exception.EmptyListException;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReportCommand extends Command {
    private final String[] types = {"stats", "items"};
    private final String selectedStartDate;
    private final String selectedEndDate;
    private final String reportType;
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public ReportCommand(String selectedStartDate, String selectedEndDate, String reportType) {
        this.selectedStartDate = selectedStartDate;
        this.selectedEndDate = selectedEndDate;
        this.reportType = reportType;
    }

    public String execute() throws NoTypeFoundException, EmptyListException, IllegalArgumentException {
        boolean isType = Arrays.asList(types).contains(reportType);
        if (!isType) {
            logger.log(Level.WARNING, "ReportCommand can't find the given type.");
            throw new NoTypeFoundException(reportType);
        }
        try {
            if (reportType.equals("stats")) {
                assert reportType.equals("items") :
                    "All types should have been listed";
                SalesReport newSalesReport = new SalesReport(selectedStartDate, selectedEndDate);
                logger.log(Level.INFO, "ReportCommand successfully executed.");
                return newSalesReport.generateSoldItemStats();
            } else {
                SalesReport newSalesReport = new SalesReport(selectedStartDate, selectedEndDate);
                logger.log(Level.INFO, "ReportCommand successfully executed.");
                return newSalesReport.generateSoldItemDetails();
            }
        } catch (seedu.duke.salesmanager.exception.EmptyListException e) {
            throw new EmptyListException(e.getMessage());
        }

    }
}
