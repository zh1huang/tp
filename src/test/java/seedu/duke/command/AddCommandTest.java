package seedu.duke.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.exception.CommandException;
import seedu.duke.command.exception.IllegalArgumentException;
import seedu.duke.model.Shelf;
import seedu.duke.model.ShelfList;
import seedu.duke.model.exception.DuplicateShelfException;
import seedu.duke.model.exception.IllegalModelArgumentException;
import seedu.duke.model.exception.ShelfNotExistException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class AddCommandTest {

    private Shelf testList;
    private Command testCommand1;

    @BeforeEach
    public void setUp() throws IllegalModelArgumentException, DuplicateShelfException {
        ShelfList.getShelfList().resetShelfList();
        testList = new Shelf("test");
    }

    @Test
    public void execute_emptyList_addsNormally() throws CommandException, ShelfNotExistException,
            IllegalModelArgumentException {
        testCommand1 = new AddCommand("HarryPotter", "16.1",
                "25.12", "1", "test", "");
        int numberOfItemsBeforeAdding = testList.getSize();
        testCommand1.execute();
        assertTrue(testList.contains("HarryPotter"));
        int numberOfItemAfterAdding = testList.getSize();
        assertEquals(numberOfItemAfterAdding, numberOfItemsBeforeAdding + 1);
    }

    @Test
    public void execute_itemsWithSameNameInList_addsNormally() throws CommandException,
            ShelfNotExistException, IllegalModelArgumentException {
        testCommand1 = new AddCommand("HarryPotter", "16.1",
                "25.12", "1", "test", "");
        int numberOfItemsBeforeAdding = testList.getSize();
        testCommand1.execute();
        assertTrue(testList.contains("HarryPotter"));
        int numberOfItemAfterAdding = testList.getSize();
        assertEquals(numberOfItemAfterAdding, numberOfItemsBeforeAdding + 1);
    }

    @Test
    public void execute_itemWithIllegalArgument_throwsIllegalArgumentException() {
        testCommand1 = new AddCommand("HarryPotter", "-16.1",
                "25.12", "1", "test", "");
        assertThrows(IllegalArgumentException.class, () -> testCommand1.execute());
    }
}
