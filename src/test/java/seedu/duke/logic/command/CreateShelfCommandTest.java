package seedu.duke.logic.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.duke.logic.command.exception.CommandException;
import seedu.duke.logic.command.exception.IllegalArgumentCommandException;
import seedu.duke.model.Shelf;
import seedu.duke.model.ShelfList;
import seedu.duke.model.exception.DuplicateShelfModelException;
import seedu.duke.model.exception.IllegalArgumentModelException;
import seedu.duke.model.exception.ModelException;
import seedu.duke.model.exception.ShelfNotExistModelException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CreateShelfCommandTest {

    private Shelf testList;
    private Command testCommand;

    @BeforeEach
    public void setUp() throws IllegalArgumentModelException, DuplicateShelfModelException,
            ShelfNotExistModelException, CommandException {
        ShelfList.getShelfList().resetShelfList();
    }

    @Test
    public void execute_emptyShelfWithInputNameExists_RemovesNormally() throws CommandException, ModelException {
        assertFalse(ShelfList.getShelfList().existShelf("test"));
        testCommand = new CreateShelfCommand("test");
        int numberOfShelvesBeforeCreating = ShelfList.getShelfList().getNumberOfShelves();
        testCommand.execute();
        int numberOfShelvesAfterCreating = ShelfList.getShelfList().getNumberOfShelves();
        assertTrue(ShelfList.getShelfList().existShelf("test"));
        assertEquals(numberOfShelvesAfterCreating, numberOfShelvesBeforeCreating + 1);
    }

    @Test
    public void execute_ShelfWithInvalidName_throwsIllegalArgumentException() {
        testCommand = new CreateShelfCommand("");
        assertThrows(IllegalArgumentCommandException.class, () -> testCommand.execute());
    }


    @Test
    public void equals_sameObject_returnsTrue() {
        testCommand = new CreateShelfCommand("test");
        Command sameCommand = testCommand;
        assertTrue(testCommand.equals(sameCommand));
    }

    @Test
    public void equals_anotherSameTestCommandObjectWithSameShelfName_returnsTrue() {
        testCommand = new CreateShelfCommand("test");
        Command anotherTestCommand = new CreateShelfCommand("test");
        assertTrue(testCommand.equals(anotherTestCommand));
    }

    @Test
    public void equals_null_returnsFalse() {
        testCommand = new CreateShelfCommand("test");
        assertFalse(testCommand.equals(null));
    }

    @Test
    public void equals_notSameTypeWithTestCommand_returnsFalse() {
        testCommand = new CreateShelfCommand("test");
        Command anotherCommand = new SellCommand("XXXXXXXX");
        assertFalse(testCommand.equals(anotherCommand));
    }


}
