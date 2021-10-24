package seedu.duke.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import seedu.duke.model.ShelfList;
import seedu.duke.model.Item;
import seedu.duke.model.Shelf;
import seedu.duke.command.exception.ItemNotExistException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EditCommandTest {

    private Shelf testList;
    private Command testCommand;

    @BeforeEach
    public void setUp() throws Exception {
        ShelfList.getShelfList().resetShelfList();
        testList = new Shelf("test");
    }

    @Test
    public void execute_oneItemAlreadyInList_editsNormally() throws Exception {
        testList.addItem(new Item("HarryPotter", "16.1", "25.12"));
        testCommand = new EditCommand("HarryPotter", "price", "20", testList);
        assertTrue(testList.contains("HarryPotter"));
        assertEquals("25.12", testList.getItem("HarryPotter").getSellingPrice());
        int numberOfItemsBeforeEditing = testList.getSize();
        testCommand.execute();
        int numberOfItemAfterEditing = testList.getSize();
        assertEquals(numberOfItemAfterEditing, numberOfItemsBeforeEditing);
        assertTrue(testList.contains("HarryPotter"));
        assertEquals("20", testList.getItem("HarryPotter").getSellingPrice());
    }

    @Test
    public void execute_emptyList_throwsItemNotExistException() {
        testCommand = new EditCommand("HarryPotter", "price", "20", testList);
        assertThrows(ItemNotExistException.class, () -> testCommand.execute());
    }

    @Test
    public void execute_noMatchedItemInList_throwItemNotExistException() throws Exception {
        testList.addItem(new Item("HarryPotter", "16.1", "25.12"));
        testCommand = new EditCommand("HarryPotter2", "cost", "20", testList);
        assertThrows(ItemNotExistException.class, () -> testCommand.execute());
    }

}
