package seedu.duke.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import seedu.duke.model.Item;
import seedu.duke.model.ItemContainer;

import java.math.BigDecimal;

public class DeleteCommandTest {
    private ItemContainer testList;
    private Command testCommand;

    @BeforeEach
    public void setUp() throws Exception {
        testList = new ItemContainer("test");
        testList.addItem(new Item("HarryPotter", "16.1", "25.12"));
        testCommand = new DeleteCommand("HarryPotter", testList);
    }

    @Test
    public void execute_oneItemAlreadyInList_deletesNormally() {
        int numberOfItemsBeforeDeleting = testList.getSize();
        assertTrue(testList.contains("HarryPotter"));
        testCommand.execute(testList);
        int numberOfItemAfterDeleting = testList.getSize();
        assertEquals(numberOfItemAfterDeleting, numberOfItemsBeforeDeleting - 1);
        assertFalse(testList.contains("HarryPotter"));
    }
}
