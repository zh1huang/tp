package seedu.duke.command;

import seedu.duke.command.exception.EmptyListException;
import seedu.duke.command.exception.IllegalArgumentException;
import seedu.duke.command.exception.NoTypeFoundException;
import seedu.duke.salesmanager.SalesReport;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReportCommand extends Command {
    public static final String REPORT_DATA_ARGS_FORMAT_STRING = "report t/TYPE ym/YEAR-MONTH [ym/YEAR-MONTH]";
    public static final String REPORT_STRING = "report";
    public static final String PARSE_REPORT_SUCCESS_MESSAGE_FORMAT = "type: %s\nstart date: %s\nend date: %s\n";
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final String[] types = {"stats", "items"};
    private final String selectedStartDate;
    private final String selectedEndDate;
    private final String reportType;

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
                SalesReport newSalesReport = new SalesReport(selectedStartDate, selectedEndDate);
                logger.log(Level.INFO, "ReportCommand successfully executed.");
                return newSalesReport.generateSoldItemStats();
            } else {
                assert reportType.equals("items") :
                        "All types should have been listed";
                SalesReport newSalesReport = new SalesReport(selectedStartDate, selectedEndDate);
                logger.log(Level.INFO, "ReportCommand successfully executed.");
                return newSalesReport.generateSoldItemDetails();
            }
        } catch (seedu.duke.salesmanager.exception.EmptyListException e) {
            throw new EmptyListException(e.getMessage());
        }

    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof ReportCommand)) {
            return false;
        }

        ReportCommand command = (ReportCommand) other;
        return selectedStartDate.equals(command.selectedStartDate)
            && selectedEndDate.equals(command.selectedEndDate)
            && reportType.equals(command.reportType);
    }

}
