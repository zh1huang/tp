package seedu.duke.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.exception.CommandException;
import seedu.duke.command.exception.EmptyListException;
import seedu.duke.model.Item;
import seedu.duke.model.Shelf;
import seedu.duke.model.exception.DuplicateItemException;
import seedu.duke.model.exception.DuplicateShelfException;
import seedu.duke.model.exception.IllegalArgumentException;
import seedu.duke.model.exception.ShelfNotExistException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.duke.command.TotalCostAndIncomeCommand.TOTAL_MONETARY_SUMMARY_MESSAGE_FORMAT;

class TotalCostAndIncomeCommandTest {
    private Shelf dummyShelf;
    private Shelf soldItemsTestShelf;
    private Command totalCostAndIncomeTestCommand;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() throws DuplicateShelfException, IllegalArgumentException {
        dummyShelf = new Shelf("testShelf");
        soldItemsTestShelf = new Shelf("soldItems");
        totalCostAndIncomeTestCommand = new TotalCostAndIncomeCommand();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() throws ShelfNotExistException {
        soldItemsTestShelf.deleteShelf();
        dummyShelf.deleteShelf();
        System.setOut(standardOut);
    }

    @Test
    public void execute_soldItemsAlreadyInList_showsNormally()
            throws IllegalArgumentException, DuplicateItemException, CommandException, ShelfNotExistException {

        String itemName1 = "Narnia";
        String itemPurchaseCost1 = "17.4";
        String itemSellingPrice1 = "25";
        String itemName2 = "Mechanical Pencil";
        String itemPurchaseCost2 = "0.70";
        String itemSellingPrice2 = "1.9";

        soldItemsTestShelf.addItem(new Item(itemName1, itemPurchaseCost1, itemSellingPrice1));
        soldItemsTestShelf.addItem(new Item(itemName2, itemPurchaseCost2, itemSellingPrice2));
        assertTrue(soldItemsTestShelf.contains("Narnia"));
        assertTrue(soldItemsTestShelf.contains("Mechanical Pencil"));
        totalCostAndIncomeTestCommand.execute();

        String expectedOutput = String.format(
            TOTAL_MONETARY_SUMMARY_MESSAGE_FORMAT, "18.10", "26.90", "8.80");

        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

}