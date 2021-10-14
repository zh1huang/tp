package seedu.duke.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.duke.command.exception.CommandException;
import seedu.duke.model.Item;
import seedu.duke.model.ItemContainer;
import seedu.duke.command.exception.ItemNotExistException;
import seedu.duke.model.exception.DuplicateItemException;
import seedu.duke.model.exception.IllegalArgumentException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DeleteCommandTest {

    private ItemContainer testList;
    private Command testCommand1;
    private Command testCommand2;
    private Item testItem1;
    private Item testItem2;

    @BeforeEach
    public void setUp() throws IllegalArgumentException {
        testList = new ItemContainer("test");
        testItem1 = new Item("HarryPotter", "16.1", "25.12");
        testItem2 = new Item("HarryPotter", "16.1", "25.12");
        testCommand1 = new DeleteCommand("HarryPotter");
        testCommand2 = new DeleteCommand("TYS");
    }

    @Test
    public void execute_oneItemAlreadyInList_deletesNormally() throws CommandException, DuplicateItemException {
        testList.addItem(testItem1);
        int numberOfItemsBeforeDeleting = testList.getSize();
        assertTrue(testList.contains("HarryPotter"));
        testCommand1.execute(testList);
        int numberOfItemAfterDeleting = testList.getSize();
        assertEquals(numberOfItemAfterDeleting, numberOfItemsBeforeDeleting - 1);
        assertFalse(testList.contains("HarryPotter"));
    }

    @Test
    public void execute_emptyList_throwsItemNotExitException() {
        assertThrows(ItemNotExistException.class, () -> testCommand1.execute(testList));
    }

    @Test
    public void execute_noMatchedItemInList_throwsItemNotExitException() throws DuplicateItemException {
        testList.addItem(testItem1);
        assertThrows(ItemNotExistException.class, () -> testCommand2.execute(testList));
    }

    @Test
    public void execute_itemsWithSameNameInList_deletesNormally() throws CommandException, DuplicateItemException {
        testList.addItem(testItem1);
        testList.addItem(testItem2);
        int numberOfItemsBeforeDeleting = testList.getSize();
        testCommand1.execute(testList);
        int numberOfItemAfterDeleting = testList.getSize();
        assertEquals(numberOfItemAfterDeleting, numberOfItemsBeforeDeleting - 1);
    }

}
