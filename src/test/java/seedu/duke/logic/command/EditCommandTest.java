package seedu.duke.logic.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.logic.command.exception.IllegalArgumentCommandException;
import seedu.duke.logic.command.exception.ItemNotExistCommandException;
import seedu.duke.logic.command.exception.NoPropertyFoundCommandException;
import seedu.duke.logic.command.exception.ShelfNotExistCommandException;
import seedu.duke.model.Item;
import seedu.duke.model.Shelf;
import seedu.duke.model.ShelfList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class EditCommandTest {

    private Shelf testList;
    private Command testCommand;

    @BeforeEach
    public void setUp() throws Exception {
        ShelfList.getShelfList().resetShelfList();
        testList = new Shelf("test");
    }

    @Test
    public void execute_editSellingPrice_editsNormally() throws Exception {
        testList.addItem(new Item("HarryPotter", "16.1", "25.12", ""));
        testCommand = new EditCommand("test", "1", "selling price", "20");
        assertTrue(testList.contains("HarryPotter"));
        assertEquals("25.12", testList.getItem("HarryPotter").getSellingPrice());
        int numberOfItemsBeforeEditing = testList.getItemCount();
        testCommand.execute();
        int numberOfItemAfterEditing = testList.getItemCount();
        assertEquals(numberOfItemAfterEditing, numberOfItemsBeforeEditing);
        assertTrue(testList.contains("HarryPotter"));
        assertEquals("20", testList.getItem("HarryPotter").getSellingPrice());
    }

    @Test
    public void execute_editPurchaseCost_editsNormally() throws Exception {
        testList.addItem(new Item("HarryPotter", "16.1", "25.12", ""));
        testCommand = new EditCommand("test", "1", "purchase cost", "20");
        assertTrue(testList.contains("HarryPotter"));
        assertEquals("16.1", testList.getItem("HarryPotter").getPurchaseCost());
        int numberOfItemsBeforeEditing = testList.getItemCount();
        testCommand.execute();
        int numberOfItemAfterEditing = testList.getItemCount();
        assertEquals(numberOfItemAfterEditing, numberOfItemsBeforeEditing);
        assertTrue(testList.contains("HarryPotter"));
        assertEquals("20", testList.getItem("HarryPotter").getPurchaseCost());
    }

    @Test
    public void execute_editRemarks_editsNormally() throws Exception {
        testList.addItem(new Item("HarryPotter", "16.1", "25.12", "Remarks1"));
        testCommand = new EditCommand("test", "1", "remarks", "Remarks2");
        assertTrue(testList.contains("HarryPotter"));
        assertEquals("Remarks1", testList.getItem("HarryPotter").getRemarks());
        int numberOfItemsBeforeEditing = testList.getItemCount();
        testCommand.execute();
        int numberOfItemAfterEditing = testList.getItemCount();
        assertEquals(numberOfItemAfterEditing, numberOfItemsBeforeEditing);
        assertTrue(testList.contains("HarryPotter"));
        assertEquals("Remarks2", testList.getItem("HarryPotter").getRemarks());
    }



    @Test
    public void execute_emptyList_throwsItemNotExistCommandException() {
        testCommand = new EditCommand("test", "1",
                "selling price", "20");
        assertThrows(ItemNotExistCommandException.class, () -> testCommand.execute());
    }

    @Test
    public void execute_invalidNewValue_throwsIllegalArgumentCommandException() throws Exception {
        testList.addItem(new Item("HarryPotter", "16.1", "25.12", ""));
        testCommand = new EditCommand("test", "1", "selling price", "-20");
        assertThrows(IllegalArgumentCommandException.class, () -> testCommand.execute());
    }

    @Test
    public void execute_nonExistShelfName_throwsShelfNotExistCommandException() throws Exception {
        testList.addItem(new Item("HarryPotter", "16.1", "25.12", ""));
        testCommand = new EditCommand("test2", "1", "selling price", "20");
        assertThrows(ShelfNotExistCommandException.class, () -> testCommand.execute());
    }

    @Test
    public void execute_invalidProperty_throwsNoPropertyFoundCommandException() throws Exception {
        testList.addItem(new Item("HarryPotter", "16.1", "25.12", ""));
        testCommand = new EditCommand("test", "1", "random property", "20");
        assertThrows(NoPropertyFoundCommandException.class, () -> testCommand.execute());
    }

    @Test
    public void execute_invalidIndex_throwsItemNotExistCommandException() throws Exception {
        testList.addItem(new Item("HarryPotter", "16.1", "25.12", ""));
        testCommand = new EditCommand("test", "2", "selling price", "20");
        assertThrows(ItemNotExistCommandException.class, () -> testCommand.execute());
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        testCommand = new EditCommand("test", "1",
                "selling price", "20");
        Command sameCommand = testCommand;
        assertTrue(testCommand.equals(sameCommand));
    }

    @Test
    public void equals_anotherSameTestCommandObjectWithArguments_returnsTrue() {
        testCommand = new EditCommand("test", "1",
                "selling price", "20");
        Command anotherCommand = new EditCommand("test", "1",
                "selling price", "20");
        assertTrue(testCommand.equals(anotherCommand));
    }

    @Test
    public void equals_null_returnsFalse() {
        testCommand = new EditCommand("test","1",
                "selling price", "20");
        assertFalse(testCommand.equals(null));
    }

    @Test
    public void equals_notSameTypeWithTestCommand_returnsFalse() {
        testCommand = new EditCommand("test", "1",
                "selling price", "20");
        Command anotherCommand = new SellCommand("XXXXXXXX");
        assertFalse(testCommand.equals(anotherCommand));
    }

    @Test
    public void equals_anotherSameCommandObjectWithDifferentShelfName_returnsFalse() {
        testCommand = new EditCommand("test", "1",
                "selling price", "20");
        Command anotherCommand = new EditCommand("test1", "1",
                "selling price", "20");
        assertFalse(testCommand.equals(anotherCommand));
    }

    @Test
    public void equals_anotherSameCommandObjectWithDifferentIndex_returnsFalse() {
        testCommand = new EditCommand("test", "1",
                "selling price", "20");
        Command anotherCommand = new EditCommand("test", "2",
                "selling price", "20");
        assertFalse(testCommand.equals(anotherCommand));
    }

    @Test
    public void equals_anotherSameCommandObjectWithDifferentProperty_returnsFalse() {
        testCommand = new EditCommand("test", "1",
                "selling price", "20");
        Command anotherCommand = new EditCommand("test", "1",
                "purchase cost", "20");
        assertFalse(testCommand.equals(anotherCommand));
    }

    @Test
    public void equals_anotherSameCommandObjectWithDifferentNewValue_returnsFalse() {
        testCommand = new EditCommand("test", "1",
                "selling price", "20");
        Command anotherCommand = new EditCommand("test", "1",
                "selling price", "25");
        assertFalse(testCommand.equals(anotherCommand));
    }
}
