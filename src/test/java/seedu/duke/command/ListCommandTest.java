package seedu.duke.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.duke.model.Item;
import seedu.duke.model.Shelf;
import seedu.duke.model.ShelfList;
import seedu.duke.model.exception.ShelfNotExistException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ListCommandTest {
    private Command testCommand;
    private Command testCommand1;
    private static final String LINE =
            "-----------------------------------------------------------------------------------------------------\n";
    private static final String HEADER =
            "   No    |                        Item                        |   Cost    |   Price   | Qty  | Remark\n";
    private static final String LIST_MESSAGE = "Here is the list of items:\n";
    private static final String TAB = "   \n";

    @BeforeEach
    public void setUp() {
        ShelfList.getShelfList().resetShelfList();
        testCommand = new ListCommand("test");
        testCommand1 = new ListCommand();
    }

    @Test
    public void execute_itemsAlreadyInShelf_listsNormally() throws Exception {
        Shelf testShelf = new Shelf("test");
        testShelf.addItem(new Item("HarryPotter", "16.1", "25.12", ""));
        testShelf.addItem(new Item("LOTR", "10.2", "15.7", ""));
        assertTrue(testShelf.contains("HarryPotter"));
        assertTrue(testShelf.contains("LOTR"));
        String expected = "Here is the list of items:\n"
                + HEADER + LINE
                + " 001     | HarryPotter                                        | 16.1      | 25.12     | 1    |   x"
                + TAB
                + " 002     | LOTR                                               | 10.2      | 15.7      | 1    |   x"
                + TAB;
        assertEquals(expected, testCommand.execute());
    }

    @Test
    public void execute_multipleItemsInShelves_listsNormally() throws Exception {
        Shelf testShelf1 = new Shelf("test");
        for (int i = 0; i < 5; i++) {
            testShelf1.addItem(new Item("Harry", "9999.99", "9999.99", "expensive"));
        }
        String expected = LIST_MESSAGE + HEADER + LINE
                + " 001-005 | Harry                                              | 9999.99   | 9999.99   | 5    |   o"
                + TAB;

        assertEquals(expected, testCommand.execute());
    }

    @Test
    public void execute_itemsInMultipleShelves_listsNormally() throws Exception {
        Shelf testShelf = new Shelf("test");
        Shelf testShelf1 = new Shelf("test1");
        for (int i = 0; i < 5; i++) {
            testShelf.addItem(new Item("Harry", "9999.99", "9999.99", "expensive"));
        }
        testShelf.addItem(new Item("LOTR", "10.2", "15.7", ""));

        for (int i = 0; i < 10; i++) {
            testShelf1.addItem(new Item("HarryPotter", "16.1", "25.12", ""));
        }
        testShelf1.addItem(new Item("Geronimo", "12.31", "25.23", "favourite"));
        String expected = LIST_MESSAGE
                + "[test]:\n"
                + HEADER + LINE
                + " 001-005 | Harry                                              | 9999.99   | 9999.99   | 5    |   o"
                + TAB
                + " 006     | LOTR                                               | 10.2      | 15.7      | 1    |   x"
                + TAB
                + "[test1]:\n"
                + HEADER + LINE
                + " 001     | Geronimo                                           | 12.31     | 25.23     | 1    |   o"
                + TAB
                + " 002-011 | HarryPotter                                        | 16.1      | 25.12     | 10   |   x"
                + TAB;
        assertEquals(expected, testCommand1.execute());
    }

    @Test
    public void execute_addItemsToNonExistentShelf_throwsShelfNotExistException() {
        assertThrows(ShelfNotExistException.class, () -> testCommand.execute());
    }
}