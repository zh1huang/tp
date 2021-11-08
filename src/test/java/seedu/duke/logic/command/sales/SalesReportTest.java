package seedu.duke.logic.command.sales;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.logic.command.exception.CommandException;
import seedu.duke.logic.command.exception.IllegalArgumentCommandException;
import seedu.duke.model.Shelf;
import seedu.duke.model.ShelfList;
import seedu.duke.model.SoldItem;
import seedu.duke.model.exception.DuplicateItemModelException;
import seedu.duke.model.exception.DuplicateShelfModelException;
import seedu.duke.model.exception.IllegalArgumentModelException;
import seedu.duke.model.exception.ModelException;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SalesReportTest {

    @BeforeEach
    public void setUp() throws ModelException, CommandException {
        ShelfList.getShelfList().resetShelfList();
    }

    @Test
    public void generateSoldItemStats_soldItemsNotEmpty_generatesStatsSuccessfully() throws
            DuplicateShelfModelException, IllegalArgumentModelException, DuplicateItemModelException,
            IllegalArgumentCommandException {
        Shelf soldItems = new Shelf("soldItems");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String time1 = "2021-11-04 11:30";
        LocalDateTime testItemDateTime1 = LocalDateTime.parse(time1, formatter);
        SoldItem soldItem1 = new SoldItem("soldItem1", "11", "12", "",
                "11111111", testItemDateTime1);
        String time2 = "2021-09-04 11:30";
        LocalDateTime testItemDateTime2 = LocalDateTime.parse(time2, formatter);
        SoldItem soldItem2 = new SoldItem("soldItem2", "11", "12", "",
                "11111112", testItemDateTime2);
        soldItems.addItem(soldItem1);
        soldItems.addItem(soldItem2);
        String expectedOutput = "Total Purchase Cost: $ 11.00\n"
                + "Total Selling Price: $ 12.00\n"
                + "Total Profits: $ 1.00\n"
                + "Gross Profit Margin (in percent): 8.33\n";
        System.out.println(new SalesReport("2021-10", "2021-11").generateSoldItemStats());
        assertEquals(expectedOutput,
                new SalesReport("2021-10", "2021-11").generateSoldItemStats());
    }

    @Test
    public void generateSoldItemDetails_soldItemsNotEmpty_generatesDetailsSuccessfully() throws
            DuplicateShelfModelException, IllegalArgumentModelException, DuplicateItemModelException,
            IllegalArgumentCommandException {
        Shelf soldItems = new Shelf("soldItems");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String time1 = "2021-11-04 11:30";
        LocalDateTime testItemDateTime1 = LocalDateTime.parse(time1, formatter);
        SoldItem soldItem1 = new SoldItem("soldItem1", "11", "12", "",
                "11111111", testItemDateTime1);
        String time2 = "2021-09-04 11:30";
        LocalDateTime testItemDateTime2 = LocalDateTime.parse(time2, formatter);
        SoldItem soldItem2 = new SoldItem("soldItem2", "11", "12", "",
                "11111112", testItemDateTime2);
        soldItems.addItem(soldItem1);
        soldItems.addItem(soldItem2);
        String expectedOutput =
                "No  |                    Item                 |  Cost   |  Price  |  Profit |      Sold Time    \n"
                + "-------------------------------------------------------------------------------------------------\n"
                + "1   | soldItem1                               | 11      | 12      | 1       |2021-11-04 11:30:00";
        System.out.println(new SalesReport("2021-10", "2021-11").generateSoldItemDetails());
        assertEquals(expectedOutput,
                new SalesReport("2021-10", "2021-11").generateSoldItemDetails());
    }
}
