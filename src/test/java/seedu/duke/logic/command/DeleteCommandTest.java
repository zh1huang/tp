package seedu.duke.logic.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.logic.command.exception.CommandException;
import seedu.duke.logic.command.exception.ItemNotExistCommandException;
import seedu.duke.logic.command.exception.ShelfNotExistCommandException;
import seedu.duke.model.Item;
import seedu.duke.model.Shelf;
import seedu.duke.model.ShelfList;
import seedu.duke.model.exception.DuplicateShelfModelException;
import seedu.duke.model.exception.IllegalArgumentModelException;
import seedu.duke.model.exception.ModelException;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteCommandTest {

    private Shelf testList;
    private Command testCommand;
    private Item testItem1;
    private Item testItem2;

    @BeforeEach
    public void setUp() throws IllegalArgumentModelException, DuplicateShelfModelException {
        ShelfList.getShelfList().resetShelfList();
        testList = new Shelf("test");
    }

    @Test
    public void execute_oneItemAlreadyInList_deletesNormally()
            throws CommandException, ModelException {
        testItem1 = new Item("HarryPotter", "16.1", "25.12", "");
        testCommand = new DeleteCommand("test", "1");
        testList.addItem(testItem1);
        int numberOfItemsBeforeDeleting = testList.getItemCount();
        assertTrue(testList.contains("HarryPotter"));
        testCommand.execute();
        int numberOfItemAfterDeleting = testList.getItemCount();
        assertEquals(numberOfItemAfterDeleting, numberOfItemsBeforeDeleting - 1);
        assertFalse(testList.contains("HarryPotter"));
    }

    @Test
    public void execute_emptyList_throwsItemNotExitException() {
        testCommand = new DeleteCommand("test", "1");
        assertThrows(ItemNotExistCommandException.class, () -> testCommand.execute());
    }

    @Test
    public void execute_MultipleItemsInList_deletesNormally()
            throws CommandException, ModelException {
        testItem1 = new Item("HarryPotter", "16.1", "25.12", "");
        testItem2 = new Item("HarryPotter", "16.1", "25.12", "");
        testCommand = new DeleteCommand("test", "1");
        testList.addItem(testItem1);
        testList.addItem(testItem2);
        int numberOfItemsBeforeDeleting = testList.getItemCount();
        testCommand.execute();
        int numberOfItemAfterDeleting = testList.getItemCount();
        assertEquals(numberOfItemAfterDeleting, numberOfItemsBeforeDeleting - 1);
    }

    @Test
    public void execute_indexExceedsLimit_throwItemNotExistCommandException()
            throws CommandException, ModelException {
        testItem1 = new Item("HarryPotter", "16.1", "25.12", "");
        testCommand = new DeleteCommand("test", "2");
        testList.addItem(testItem1);
        assertThrows(ItemNotExistCommandException.class, () -> testCommand.execute());
    }

    @Test
    public void execute_specifiedShelfNotExist_throwShelfNotExistCommandException()
            throws CommandException, ModelException {
        testItem1 = new Item("HarryPotter", "16.1", "25.12", "");
        testCommand = new DeleteCommand("anotherShelf", "1");
        testList.addItem(testItem1);
        assertThrows(ShelfNotExistCommandException.class, () -> testCommand.execute());
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        testCommand = new DeleteCommand("test", "1");
        Command sameCommand = testCommand;
        assertTrue(testCommand.equals(sameCommand));
    }

    @Test
    public void equals_anotherSameTestCommandObjectWithArguments_returnsTrue() {
        testCommand = new DeleteCommand("test", "1");
        Command anotherCommand = new DeleteCommand("test", "1");
        assertTrue(testCommand.equals(anotherCommand));
    }

    @Test
    public void equals_null_returnsFalse() {
        testCommand = new DeleteCommand("test", "1");
        assertFalse(testCommand.equals(null));
    }

    @Test
    public void equals_notSameTypeWithTestCommand_returnsFalse() {
        testCommand = new DeleteCommand("test", "1");
        Command anotherCommand = new SellCommand("XXXXXXXX");
        assertFalse(testCommand.equals(anotherCommand));
    }

    @Test
    public void equals_anotherSameCommandObjectWithDifferentShelfName_returnsFalse() {
        testCommand = new DeleteCommand("test", "1");
        Command anotherCommand = new DeleteCommand("test2", "1");
        assertFalse(testCommand.equals(anotherCommand));
    }

    @Test
    public void equals_anotherSameCommandObjectWithDifferentIndex_returnsFalse() {
        testCommand = new DeleteCommand("test", "1");
        Command anotherCommand = new DeleteCommand("test", "2");
        assertFalse(testCommand.equals(anotherCommand));
    }
}
