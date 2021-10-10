package seedu.duke.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
        testList.addItem(new Item("HarryPotter", new BigDecimal("16.1"), new BigDecimal("25.12"), testList));
        testCommand = new DeleteCommand("HarryPotter", testList);
    }

    @Test
    public void deleteCommandExecute() {
        int numberOfItemsBeforeDeleting = testList.getSize();
        assertTrue(testList.contains("HarryPotter"));
        testCommand.execute(testList);
        int numberOfItemAfterDeleting = testList.getSize();
        assertTrue(numberOfItemAfterDeleting == numberOfItemsBeforeDeleting - 1);
        assertFalse(testList.contains("HarryPotter"));
    }
}
