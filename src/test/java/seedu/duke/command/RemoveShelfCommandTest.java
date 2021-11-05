package seedu.duke.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.duke.command.exception.CommandException;
import seedu.duke.command.exception.IllegalArgumentException;
import seedu.duke.model.Item;
import seedu.duke.model.Shelf;
import seedu.duke.model.ShelfList;
import seedu.duke.model.exception.DuplicateItemException;
import seedu.duke.model.exception.DuplicateShelfException;
import seedu.duke.model.exception.IllegalModelArgumentException;
import seedu.duke.model.exception.ShelfNotExistException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class RemoveShelfCommandTest {

    private Shelf testList;
    private Command testCommand;

    @BeforeEach
    public void setUp() throws IllegalModelArgumentException, DuplicateShelfException,
            ShelfNotExistException, CommandException {
        ShelfList.getShelfList().resetShelfList();
        testList = new Shelf("test");
    }

    @Test
    public void execute_emptyShelfWithInputNameExists_RemovesNormally() throws CommandException, ShelfNotExistException,
            IllegalModelArgumentException {
        assertTrue(ShelfList.getShelfList().existShelf("test"));
        testCommand = new RemoveShelfCommand("test");
        int numberOfShelvesBeforeRemoving = ShelfList.getShelfList().getNumberOfShelves();
        testCommand.execute();
        int numberOfShelvesAfterRemoving = ShelfList.getShelfList().getNumberOfShelves();
        assertFalse(ShelfList.getShelfList().existShelf("test"));
        assertEquals(numberOfShelvesAfterRemoving, numberOfShelvesBeforeRemoving - 1);
    }

    @Test
    public void execute_ShelfWithInputNameDoesNotExist_throwsShelfNotExistException() throws
            CommandException, ShelfNotExistException, IllegalModelArgumentException {
        testCommand = new RemoveShelfCommand("nameWithNoMatchingShelf");
        assertThrows(seedu.duke.command.exception.ShelfNotExistException.class, () -> testCommand.execute());
    }

    @Test
    public void execute_nonEmptyShelfWithInputNameExists_throwsIllegalArgumentException() throws CommandException,
            ShelfNotExistException, IllegalModelArgumentException, DuplicateItemException {
        testList.addItem(new Item("HarryPotter", "16.1", "25.12", ""));
        testCommand = new RemoveShelfCommand("test");
        assertThrows(IllegalArgumentException.class, () -> testCommand.execute());
    }

}
