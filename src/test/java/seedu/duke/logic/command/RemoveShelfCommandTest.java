package seedu.duke.logic.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.duke.logic.command.exception.CommandException;
import seedu.duke.logic.command.exception.IllegalArgumentCommandException;
import seedu.duke.logic.command.exception.ShelfNotExistCommandException;
import seedu.duke.model.Item;
import seedu.duke.model.Shelf;
import seedu.duke.model.ShelfList;
import seedu.duke.model.exception.DuplicateItemModelException;
import seedu.duke.model.exception.DuplicateShelfModelException;
import seedu.duke.model.exception.IllegalArgumentModelException;
import seedu.duke.model.exception.ShelfNotExistModelException;
import seedu.duke.model.exception.ModelException;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class RemoveShelfCommandTest {

    private Shelf testList;
    private Command testCommand;

    @BeforeEach
    public void setUp() throws IllegalArgumentModelException, DuplicateShelfModelException,
            ShelfNotExistModelException, CommandException {
        ShelfList.getShelfList().resetShelfList();
        testList = new Shelf("test");
    }

    @Test
    public void execute_emptyShelfWithInputNameExists_RemovesNormally() throws CommandException, ModelException {
        assertTrue(ShelfList.getShelfList().existShelf("test"));
        testCommand = new RemoveShelfCommand("test");
        int numberOfShelvesBeforeRemoving = ShelfList.getShelfList().getNumberOfShelves();
        testCommand.execute();
        int numberOfShelvesAfterRemoving = ShelfList.getShelfList().getNumberOfShelves();
        assertFalse(ShelfList.getShelfList().existShelf("test"));
        assertEquals(numberOfShelvesAfterRemoving, numberOfShelvesBeforeRemoving - 1);
    }

    @Test
    public void execute_ShelfWithInputNameDoesNotExist_throwsShelfNotExistException() {
        testCommand = new RemoveShelfCommand("nameWithNoMatchingShelf");
        assertThrows(ShelfNotExistCommandException.class, () -> testCommand.execute());
    }

    @Test
    public void execute_nonEmptyShelfWithInputNameExists_throwsIllegalArgumentException() throws
            IllegalArgumentModelException, DuplicateItemModelException {
        testList.addItem(new Item("HarryPotter", "16.1", "25.12", ""));
        testCommand = new RemoveShelfCommand("test");
        assertThrows(IllegalArgumentCommandException.class, () -> testCommand.execute());
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        testCommand = new RemoveShelfCommand("test");
        Command sameCommand = testCommand;
        assertTrue(testCommand.equals(sameCommand));
    }

    @Test
    public void equals_anotherSameTestCommandObjectWithSameShelfName_returnsTrue() {
        testCommand = new RemoveShelfCommand("test");
        Command anotherTestCommand = new RemoveShelfCommand("test");
        assertTrue(testCommand.equals(anotherTestCommand));
    }

    @Test
    public void equals_null_returnsFalse() {
        testCommand = new RemoveShelfCommand("test");
        assertFalse(testCommand.equals(null));
    }

    @Test
    public void equals_notTestCommand_returnsFalse() {
        testCommand = new RemoveShelfCommand("test");
        Command anotherCommand = new SellCommand("XXXXXXXX");
        assertFalse(testCommand.equals(anotherCommand));
    }


}
