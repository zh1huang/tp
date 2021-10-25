package seedu.duke.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.duke.command.exception.CommandException;
import seedu.duke.model.ShelfList;
import seedu.duke.model.Shelf;
import seedu.duke.command.exception.IllegalArgumentException;
import seedu.duke.model.exception.DuplicateShelfException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class AddCommandTest {

    private Shelf testList;
    private Command testCommand1;

    @BeforeEach
    public void setUp() throws seedu.duke.model.exception.IllegalArgumentException, DuplicateShelfException {
        ShelfList.getShelfList().resetShelfList();
        testList = new Shelf("test");
    }

    @Test
    public void execute_emptyList_addsNormally() throws CommandException {
        testCommand1 = new AddCommand("HarryPotter", "16.1",
                "25.12", "1", testList);
        int numberOfItemsBeforeAdding = testList.getSize();
        testCommand1.execute();
        assertTrue(testList.contains("HarryPotter"));
        int numberOfItemAfterAdding = testList.getSize();
        assertEquals(numberOfItemAfterAdding, numberOfItemsBeforeAdding + 1);
    }

    @Test
    public void execute_itemsWithSameNameInList_addsNormally() throws CommandException {
        testCommand1 = new AddCommand("HarryPotter", "16.1",
                "25.12", "1", testList);
        int numberOfItemsBeforeAdding = testList.getSize();
        testCommand1.execute();
        assertTrue(testList.contains("HarryPotter"));
        int numberOfItemAfterAdding = testList.getSize();
        assertEquals(numberOfItemAfterAdding, numberOfItemsBeforeAdding + 1);
    }

    @Test
    public void execute_itemWithIllegalArgument_throwsIllegalArgumentException() {
        testCommand1 = new AddCommand("HarryPotter", "-16.1",
                "25.12", "1", testList);
        assertThrows(IllegalArgumentException.class, () -> testCommand1.execute());
    }
}
