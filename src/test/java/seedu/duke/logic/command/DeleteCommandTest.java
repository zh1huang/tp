package seedu.duke.logic.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.logic.command.exception.CommandException;
import seedu.duke.logic.command.exception.ItemNotExistCommandException;
import seedu.duke.model.Item;
import seedu.duke.model.Shelf;
import seedu.duke.model.ShelfList;
import seedu.duke.model.exception.DuplicateShelfModelException;
import seedu.duke.model.exception.IllegalArgumentModelException;
import seedu.duke.model.exception.ModelException;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteCommandTest {

    private Shelf testList;
    private Command testCommand1;
    private Command testCommand2;
    private Item testItem1;
    private Item testItem2;

    @BeforeEach
    public void setUp() throws IllegalArgumentModelException, DuplicateShelfModelException {
        ShelfList.getShelfList().resetShelfList();
        testList = new Shelf("test");
        testItem1 = new Item("HarryPotter", "16.1", "25.12", "");
        testItem2 = new Item("HarryPotter", "16.1", "25.12", "");
        testCommand1 = new DeleteCommand("test", "1");
        testCommand2 = new DeleteCommand("test", "1");
    }

    @Test
    public void execute_oneItemAlreadyInList_deletesNormally()
            throws CommandException, ModelException {

        testList.addItem(testItem1);
        int numberOfItemsBeforeDeleting = testList.getItemCount();
        assertTrue(testList.contains("HarryPotter"));
        testCommand1.execute();
        int numberOfItemAfterDeleting = testList.getItemCount();
        assertEquals(numberOfItemAfterDeleting, numberOfItemsBeforeDeleting - 1);
        assertFalse(testList.contains("HarryPotter"));
    }

    @Test
    public void execute_emptyList_throwsItemNotExitException() {
        assertThrows(ItemNotExistCommandException.class, () -> testCommand1.execute());
    }

    @Test
    public void execute_itemsWithSameNameInList_deletesNormally()
            throws CommandException, ModelException {

        testList.addItem(testItem1);
        testList.addItem(testItem2);
        int numberOfItemsBeforeDeleting = testList.getItemCount();
        testCommand1.execute();
        int numberOfItemAfterDeleting = testList.getItemCount();
        assertEquals(numberOfItemAfterDeleting, numberOfItemsBeforeDeleting - 1);
    }

}
