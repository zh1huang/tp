package seedu.duke.logic.command;

import seedu.duke.logic.command.exception.EmptyListCommandException;
import seedu.duke.logic.command.exception.IllegalArgumentCommandException;
import seedu.duke.logic.command.exception.NoTypeFoundCommandException;
import seedu.duke.logic.command.sales.SalesReport;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReportCommand extends Command {

    public static final String REPORT_DATA_ARGS_FORMAT_STRING =
            "report t/TYPE ym/START-YEAR-MONTH [ym/END-YEAR-MONTH]\n"
                    + "Only 2 report types:\n"
                    + "1. \"t/stats\" - to show sales statistics\n"
                    + "2. \"t/items\" - to show items sold\n"
                    + "ym/YEAR-MONTH should follow input ym/YYYY-MM\n"
                    + "E.g. Jan 2021 is represented as ym/2021-01.\n";
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

    public String execute() throws NoTypeFoundCommandException,
            EmptyListCommandException, IllegalArgumentCommandException {
        boolean isType = Arrays.asList(types).contains(reportType);
        if (!isType) {
            logger.log(Level.WARNING, "ReportCommand can't find the given type.");
            throw new NoTypeFoundCommandException(reportType);
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
        } catch (seedu.duke.logic.command.sales.exception.EmptyListException e) {
            throw new EmptyListCommandException(e.getMessage());
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
