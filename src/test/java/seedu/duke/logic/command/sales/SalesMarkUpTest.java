package seedu.duke.logic.command.sales;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.model.Item;
import seedu.duke.model.Shelf;
import seedu.duke.model.ShelfList;
import seedu.duke.model.exception.DeniedAccessToShelfModelException;
import seedu.duke.model.exception.DuplicateItemModelException;
import seedu.duke.model.exception.DuplicateShelfModelException;
import seedu.duke.model.exception.IllegalArgumentModelException;
import seedu.duke.model.exception.ShelfNotExistModelException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.logic.command.sales.SalesMarkUp.CURRENT_ITEM_MARKUP_MESSAGE_FORMAT;
import static seedu.duke.logic.command.sales.SalesMarkUp.ESTIMATED_MARKUP_MESSAGE_FORMAT;
import static seedu.duke.logic.command.sales.SalesMarkUp.ITEM_NAME_MESSAGE_FORMAT;
import static seedu.duke.logic.command.sales.SalesMarkUp.WARNING_CURRENT_MARKUP_NEGATIVE_MESSAGE;
import static seedu.duke.logic.command.sales.SalesMarkUp.WARNING_LARGE_PERCENT_MESSAGE_FORMAT;

class SalesMarkUpTest {
    public static final String TEST_SHELF_NAME = "test";
    private Shelf testShelf;

    @BeforeEach
    public void setUp() throws IllegalArgumentException, DuplicateShelfModelException, DuplicateItemModelException,
            IllegalArgumentModelException {
        ShelfList.getShelfList().resetShelfList();
        testShelf = new Shelf(TEST_SHELF_NAME);
        testShelf.addItem(new Item("Harry Potter", "16.1", "25.12", ""));
        testShelf.addItem(new Item("Narnia", "10.2", "15.7", ""));
        testShelf.addItem(new Item("The red riding hood", "6.2", "5.7", ""));
    }

    @Test
    public void getItemToMarkUpInfo_itemInShelf_executesNormally()
            throws ShelfNotExistModelException, DeniedAccessToShelfModelException {
        SalesMarkUp salesMarkUpTest = new SalesMarkUp(TEST_SHELF_NAME, 0, "86.05");
        String expectedOutput = String.format(ITEM_NAME_MESSAGE_FORMAT, "Harry Potter", "16.1", "25.12");
        assertEquals(expectedOutput, salesMarkUpTest.getItemToMarkUpInfo());
    }

    @Test
    public void getSelectedItemMarkUpInfo_itemInShelf_executesNormally()
            throws ShelfNotExistModelException, DeniedAccessToShelfModelException {
        SalesMarkUp salesMarkUpTest = new SalesMarkUp(TEST_SHELF_NAME, 1, "86.05");
        String expectedOutput = String.format(CURRENT_ITEM_MARKUP_MESSAGE_FORMAT, "5.5", "53.92");
        assertEquals(expectedOutput, salesMarkUpTest.getSelectedItemMarkUpInfo());
    }

    @Test
    public void getSelectedItemMarkUpInfo_itemCostMoreThanPrice_executesNormallyWithMarkUpNegativeWarning()
            throws ShelfNotExistModelException, DeniedAccessToShelfModelException {
        SalesMarkUp salesMarkUpTest = new SalesMarkUp(TEST_SHELF_NAME, 2, "");
        String expectedOutput = WARNING_CURRENT_MARKUP_NEGATIVE_MESSAGE;
        expectedOutput += String.format(CURRENT_ITEM_MARKUP_MESSAGE_FORMAT, "-0.5", "-8.06");
        assertEquals(expectedOutput, salesMarkUpTest.getSelectedItemMarkUpInfo());
    }

    @Test
    public void getUserRequestMarkUpInfo_itemInShelf_executesNormally()
            throws ShelfNotExistModelException, DeniedAccessToShelfModelException {
        SalesMarkUp salesMarkUpTest = new SalesMarkUp(TEST_SHELF_NAME, 1, "86.05");
        String expectedOutput = String.format(ESTIMATED_MARKUP_MESSAGE_FORMAT, "86.05", "8.78", "18.98");
        assertEquals(expectedOutput, salesMarkUpTest.getUserRequestMarkUpInfo());
    }

    @Test
    public void getUserRequestMarkUpInfo_UserRequestMarkUpPercentMorethanOneHundred_executesNormallyWithWarning()
            throws ShelfNotExistModelException, DeniedAccessToShelfModelException {
        SalesMarkUp salesMarkUpTest = new SalesMarkUp(TEST_SHELF_NAME, 1, "150.65");
        String expectedOutput = String.format(ESTIMATED_MARKUP_MESSAGE_FORMAT, "150.65", "15.37", "25.57");
        String warningString = String.format(WARNING_LARGE_PERCENT_MESSAGE_FORMAT, "25.57");
        expectedOutput += warningString;
        assertEquals(expectedOutput, salesMarkUpTest.getUserRequestMarkUpInfo());
    }

    @Test
    public void getEstimatedMarkUpInfo_itemInShelf_executesNormally()
            throws ShelfNotExistModelException, DeniedAccessToShelfModelException {
        SalesMarkUp salesMarkUpTest = new SalesMarkUp(TEST_SHELF_NAME, 1, "");
        String expectedOutput = "markup: 0%, increase: $0.00, Final price: $10.20\n"
            + "markup: 20%, increase: $2.04, Final price: $12.24\n"
            + "markup: 40%, increase: $4.08, Final price: $14.28\n"
            + "markup: 60%, increase: $6.12, Final price: $16.32\n"
            + "markup: 80%, increase: $8.16, Final price: $18.36\n"
            + "markup: 100%, increase: $10.20, Final price: $20.40\n";
        assertEquals(expectedOutput, salesMarkUpTest.getEstimatedMarkUpInfo());
    }
}