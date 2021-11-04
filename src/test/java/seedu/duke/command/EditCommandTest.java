package seedu.duke.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.exception.ItemNotExistException;
import seedu.duke.model.Item;
import seedu.duke.model.Shelf;
import seedu.duke.model.ShelfList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        testList.addItem(new Item("HarryPotter", "16.1", "25.12", ""));
        testCommand = new EditCommand("test", "1", "selling price", "20");
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
        testCommand = new EditCommand("test", "1", "selling price", "20");
        assertThrows(ItemNotExistException.class, () -> testCommand.execute());
    }

}
