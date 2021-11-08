package seedu.duke.logic.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.logic.command.exception.ItemNotExistCommandException;
import seedu.duke.model.Item;
import seedu.duke.model.Shelf;
import seedu.duke.model.ShelfList;
import seedu.duke.model.exception.ShelfNotExistModelException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GetCommandTest {

    private Shelf testList;
    private Command testCommand;
    private Command testCommand1;
    private Command testCommand2;
    private Command testCommand3;

    @BeforeEach
    public void setUp() throws Exception {
        ShelfList.getShelfList().resetShelfList();
        testList = new Shelf("test");
        testCommand1 = new GetCommand("test", "1");
        testCommand2 = new GetCommand("test", "3");
        testCommand3 = new GetCommand("test","001");
    }


    @Test
    public void execute_ItemInList_getsNormally() throws Exception {
        Item.setPrintDummyID(true);
        testList.addItem(new Item("HarryPotter", "16.1", "25.12", "Hello", Item.DUMMY_ID));
        assertTrue(testList.contains("HarryPotter"));
        String expected = String.format(GetCommand.GET_COMPLETE_MESSAGE + GetCommand.GET_OUTPUT,
                "HarryPotter", "16.1", "25.12", Item.DUMMY_ID, "Hello");
        assertEquals(expected, testCommand1.execute());
        assertEquals(expected, testCommand3.execute());
    }

    @Test
    public void execute_emptyList_throwsItemNotExistCommandException() {
        assertThrows(ItemNotExistCommandException.class, () -> testCommand2.execute());
    }

    @Test
    public void execute_nonExistentShelf_throwsShelfNotExistModelException() {
        Command testCommand5 = new GetCommand("non","1");
        assertThrows(ShelfNotExistModelException.class, testCommand5::execute);
    }

    @Test
    public void execute_noMatchedItemInList_throwsItemNotExistCommandException() throws Exception {
        testList.addItem(new Item("Hello", "25.12", "16.1", ""));
        assertTrue(testList.contains("Hello"));
        assertThrows(ItemNotExistCommandException.class, () -> testCommand2.execute());
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        testCommand = new GetCommand("test","1");
        Command sameCommand = testCommand;
        assertEquals(testCommand, sameCommand);
    }

    @Test
    public void equals_differentObjectSameArguments_returnsTrue() {
        testCommand = new GetCommand("test","1");
        Command anotherCommand = new GetCommand("test", "1");
        assertEquals(testCommand, anotherCommand);
    }

    @Test
    public void equals_null_returnFalse() {
        testCommand = new GetCommand("test", "1");
        assertNotEquals(testCommand, null);
    }

    @Test
    public void equals_differentShelf_returnsFalse() {
        testCommand = new GetCommand("test", "1");
        Command anotherCommand = new GetCommand("different", "1");
        assertNotEquals(testCommand, anotherCommand);
    }

    @Test
    public void equals_differentIndex_returnsFalse() {
        testCommand = new GetCommand("test", "1");
        Command anotherCommand = new GetCommand("test", "2");
        assertNotEquals(testCommand, anotherCommand);
    }

}
