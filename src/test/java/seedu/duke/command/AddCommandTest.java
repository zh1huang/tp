package seedu.duke.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import seedu.duke.model.ItemContainer;


public class AddCommandTest {

    private ItemContainer testList;
    private Command testCommand;

    @BeforeEach
    public void setUp() {
        testList = new ItemContainer("test");
        testCommand = new AddCommand("HarryPotter", "16.1", "25.12");
    }

    @Test
    public void execute_emptyList_addsNormally() {
        int numberOfItemsBeforeAdding = testList.getSize();
        testCommand.execute(testList);
        assertTrue(testList.contains("HarryPotter"));
        int numberOfItemAfterAdding = testList.getSize();
        assertEquals(numberOfItemAfterAdding, numberOfItemsBeforeAdding + 1);
    }
}
