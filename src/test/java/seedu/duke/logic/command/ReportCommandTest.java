package seedu.duke.logic.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.duke.logic.command.exception.CommandException;
import seedu.duke.logic.command.exception.NoTypeFoundCommandException;
import seedu.duke.model.Shelf;
import seedu.duke.model.ShelfList;
import seedu.duke.model.SoldItem;
import seedu.duke.model.exception.DuplicateShelfModelException;
import seedu.duke.model.exception.IllegalArgumentModelException;
import seedu.duke.model.exception.ModelException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class ReportCommandTest {
    private Shelf soldItems;
    private Command testCommand;

    @BeforeEach
    public void setUp() throws IllegalArgumentModelException, DuplicateShelfModelException {
        ShelfList.getShelfList().resetShelfList();
        soldItems = new Shelf("soldItems");
    }

    @AfterEach
    public void cleanUp() {
        ShelfList.getShelfList().resetShelfList();
    }

    @Test
    public void execute_invalidType_throwsNoTypeFoundCommandException() {
        testCommand = new ReportCommand("2021-11", "2021-12", "inValidType");
        assertThrows(NoTypeFoundCommandException.class, () -> testCommand.execute());
    }

    @Test
    public void execute_getItemsReport_returnsReportSuccessfully() throws ModelException, CommandException {
        String str = "2021-11-04 11:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime testItemDateTime1 = LocalDateTime.parse(str, formatter);
        SoldItem soldItem1 = new SoldItem("soldItem1", "11", "12", "",
                "XXXXXXX", testItemDateTime1);
        soldItems.addItem(soldItem1);
        testCommand = new ReportCommand("2021-11", "2021-12", "items");
        String outputLine1 =
                "No  |                    Item                 |  Cost   |  Price  |  Profit |      Sold Time    \n";
        String outputLine2 =
                "-------------------------------------------------------------------------------------------------\n";
        String outputLine3 =
                "1   | soldItem1                               | 11      | 12      | 1       |2021-11-04 11:30:00";
        String expectedOutput = outputLine1 + outputLine2 + outputLine3;
        assertTrue(testCommand.execute().equals(expectedOutput));
    }

    @Test
    public void execute_getStatsReport_returnsReportSuccessfully() throws ModelException, CommandException {
        String str = "2021-11-04 11:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime testItemDateTime1 = LocalDateTime.parse(str, formatter);
        SoldItem soldItem1 = new SoldItem("soldItem1", "11", "12", "",
                "XXXXXXXX", testItemDateTime1);
        soldItems.addItem(soldItem1);
        testCommand = new ReportCommand("2021-11", "2021-12", "stats");
        String expectedOutput = "Total Purchase Cost: $ 11.00\n"
                + "Total Selling Price: $ 12.00\n"
                + "Total Profits: $ 1.00\n"
                + "Gross Profit Margin (in percent): 8.33\n";
        assertTrue(testCommand.execute().equals(expectedOutput));
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        testCommand = new ReportCommand("2021-11", "2021-12", "items");
        Command sameCommand = testCommand;
        assertTrue(testCommand.equals(sameCommand));
    }

    @Test
    public void equals_anotherSameTestCommandObjectWithSameArguments_returnsTrue() {
        testCommand = new ReportCommand("2021-11", "2021-12", "items");
        Command anotherTestCommand = new ReportCommand("2021-11", "2021-12",
                "items");
        assertTrue(testCommand.equals(anotherTestCommand));
    }

    @Test
    public void equals_anotherSameTestCommandObjectWithDifferentStartDate_returnsFalse() {
        testCommand = new ReportCommand("2021-11", "2021-12", "items");
        Command anotherTestCommand = new ReportCommand("2021-12", "2021-12",
                "items");
        assertFalse(testCommand.equals(anotherTestCommand));
    }

    @Test
    public void equals_anotherSameTestCommandObjectWithDifferentEndDate_returnsFalse() {
        testCommand = new ReportCommand("2021-11", "2021-12", "items");
        Command anotherTestCommand = new ReportCommand("2021-11", "2022-12",
                "items");
        assertFalse(testCommand.equals(anotherTestCommand));
    }

    @Test
    public void equals_anotherSameTestCommandObjectWithDifferentTypereturnsFalse() {
        testCommand = new ReportCommand("2021-11", "2021-12", "items");
        Command anotherTestCommand = new ReportCommand("2021-11", "2021-12",
                "stats");
        assertFalse(testCommand.equals(anotherTestCommand));
    }

    @Test
    public void equals_null_returnsFalse() {
        testCommand = new ReportCommand("2021-11", "2021-12", "items");
        assertFalse(testCommand.equals(null));
    }

    @Test
    public void equals_notSameTypeWithTestCommand_returnsFalse() {
        testCommand = new ReportCommand("2021-11", "2021-12", "items");
        Command anotherCommand = new SellCommand("XXXXXXXX");
        assertFalse(testCommand.equals(anotherCommand));
    }

}
