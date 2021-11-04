package seedu.duke.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.exception.CommandException;
import seedu.duke.command.exception.ItemNotExistException;
import seedu.duke.model.Item;
import seedu.duke.model.Shelf;
import seedu.duke.model.ShelfList;
import seedu.duke.model.exception.DuplicateItemException;
import seedu.duke.model.exception.DuplicateShelfException;
import seedu.duke.model.exception.IllegalModelArgumentException;
import seedu.duke.model.exception.ShelfNotExistException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteCommandTest {

    private Shelf testList;
    private Command testCommand1;
    private Command testCommand2;
    private Item testItem1;
    private Item testItem2;

    @BeforeEach
    public void setUp() throws IllegalModelArgumentException, DuplicateShelfException {
        ShelfList.getShelfList().resetShelfList();
        testList = new Shelf("test");
        testItem1 = new Item("HarryPotter", "16.1", "25.12", "");
        testItem2 = new Item("HarryPotter", "16.1", "25.12", "");
        testCommand1 = new DeleteCommand("test", "1");
        testCommand2 = new DeleteCommand("test", "1");
    }

    @Test
    public void execute_oneItemAlreadyInList_deletesNormally()
            throws CommandException, DuplicateItemException, ShelfNotExistException, IllegalModelArgumentException {

        testList.addItem(testItem1);
        int numberOfItemsBeforeDeleting = testList.getSize();
        assertTrue(testList.contains("HarryPotter"));
        testCommand1.execute();
        int numberOfItemAfterDeleting = testList.getSize();
        assertEquals(numberOfItemAfterDeleting, numberOfItemsBeforeDeleting - 1);
        assertFalse(testList.contains("HarryPotter"));
    }

    @Test
    public void execute_emptyList_throwsItemNotExitException() {
        assertThrows(ItemNotExistException.class, () -> testCommand1.execute());
    }

    @Test
    public void execute_itemsWithSameNameInList_deletesNormally()
            throws CommandException, DuplicateItemException, ShelfNotExistException, IllegalModelArgumentException {

        testList.addItem(testItem1);
        testList.addItem(testItem2);
        int numberOfItemsBeforeDeleting = testList.getSize();
        testCommand1.execute();
        int numberOfItemAfterDeleting = testList.getSize();
        assertEquals(numberOfItemAfterDeleting, numberOfItemsBeforeDeleting - 1);
    }

}
