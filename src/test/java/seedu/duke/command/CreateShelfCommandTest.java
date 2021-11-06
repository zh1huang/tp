package seedu.duke.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.duke.command.exception.CommandException;
import seedu.duke.model.Shelf;
import seedu.duke.model.ShelfList;
import seedu.duke.model.exception.DuplicateShelfException;
import seedu.duke.model.exception.IllegalModelArgumentException;
import seedu.duke.model.exception.ShelfNotExistException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CreateShelfCommandTest {

    private Shelf testList;
    private Command testCommand;

    @BeforeEach
    public void setUp() throws IllegalModelArgumentException, DuplicateShelfException,
            ShelfNotExistException, CommandException {
        ShelfList.getShelfList().resetShelfList();
    }

    @Test
    public void execute_emptyShelfWithInputNameExists_RemovesNormally() throws CommandException, ShelfNotExistException,
            IllegalModelArgumentException {
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
        assertThrows(seedu.duke.command.exception.IllegalArgumentException.class, () -> testCommand.execute());
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
    public void equals_notTestCommand_returnsFalse() {
        testCommand = new CreateShelfCommand("test");
        Command anotherCommand = new SellCommand("XXXXXXXX");
        assertFalse(testCommand.equals(anotherCommand));
    }


}
