package seedu.duke.logic.command.sales;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.duke.logic.command.exception.CommandException;
import seedu.duke.logic.command.exception.IllegalArgumentCommandException;

import seedu.duke.model.Item;
import seedu.duke.model.Shelf;
import seedu.duke.model.ShelfList;
import seedu.duke.model.SoldItem;
import seedu.duke.model.exception.DuplicateItemModelException;
import seedu.duke.model.exception.IllegalArgumentModelException;
import seedu.duke.model.exception.ItemNotExistModelException;
import seedu.duke.model.exception.ShelfNotExistModelException;
import seedu.duke.model.exception.ModelException;
import seedu.duke.model.exception.DuplicateShelfModelException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;



public class SalesManagerTest {
    private Shelf testList;

    @BeforeEach
    public void setUp() throws ModelException, CommandException {
        ShelfList.getShelfList().resetShelfList();
        testList = new Shelf("test");
    }

    @Test
    public void sell_itemExistsInShelf_sellsSuccessfully() throws DuplicateItemModelException,
            IllegalArgumentModelException, ItemNotExistModelException, ShelfNotExistModelException {
        Item testItem = new Item("HarryPotter", "16.1",
                "25.12", "", "11111111");
        testList.addItem(testItem);
        assertTrue(testList.contains("HarryPotter"));
        int numberOfItemsBeforeSelling = testList.getItemCount();
        SalesManager.getSalesManager().sell(testItem);
        assertTrue(ShelfList.getShelfList().getShelf("soldItems").contains("HarryPotter"));
        int numberOfItemAfterSelling = testList.getItemCount();
        assertEquals(numberOfItemAfterSelling, numberOfItemsBeforeSelling - 1);
        assertFalse(testList.contains("HarryPotter"));
    }

    @Test
    public void filterSoldItems_legalArgumentsOnlyStartDate_getsSuccessfully() throws DuplicateShelfModelException,
            IllegalArgumentModelException, DuplicateItemModelException, IllegalArgumentCommandException {
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
        ArrayList<SoldItem> expectedOutput = new ArrayList<>();
        expectedOutput.add(soldItem1);
        assertEquals(expectedOutput, SalesManager.getSalesManager().filterSoldItems("2021-11",
                ""));
    }

    @Test
    public void filterSoldItems_iLLegalArgumentsAndWithinSpecificMonth_throwsIllegalArgumentCommandException() {
        assertThrows(IllegalArgumentCommandException.class,
            () -> SalesManager.getSalesManager().filterSoldItems("2021-1", ""));
    }

    @Test
    public void filterSoldItems_iLLegalArgumentsAndWithinAPeriod_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentCommandException.class,
            () -> SalesManager.getSalesManager().filterSoldItems("2021-1", "2021-2"));
    }

}
