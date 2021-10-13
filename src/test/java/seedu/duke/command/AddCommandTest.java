package seedu.duke.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.duke.model.Item;
import seedu.duke.model.ItemContainer;
import seedu.duke.model.exception.IllegalArgumentException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class AddCommandTest {
    private ItemContainer testList;
    private Command testCommand1;
    private Item testItem1;

    @BeforeEach
    public void setUp() {
        testList = new ItemContainer("test");
        testItem1 = new Item("HarryPotter", "16.1", "25.12");
    }

    @Test
    public void execute_emptyList_addsNormally() {
        testCommand1 = new AddCommand("HarryPotter", "16.1",
                "25.12");
        int numberOfItemsBeforeAdding = testList.getSize();
        testCommand1.execute(testList);
        assertTrue(testList.contains("HarryPotter"));
        int numberOfItemAfterAdding = testList.getSize();
        assertEquals(numberOfItemAfterAdding, numberOfItemsBeforeAdding + 1);
    }

    @Test
    public void execute_itemsWithSameNameInList_addsNormally() {
        testCommand1 = new AddCommand("HarryPotter", "16.1",
                "25.12");
        int numberOfItemsBeforeAdding = testList.getSize();
        testCommand1.execute(testList);
        assertTrue(testList.contains("HarryPotter"));
        int numberOfItemAfterAdding = testList.getSize();
        assertEquals(numberOfItemAfterAdding, numberOfItemsBeforeAdding + 1);
    }

    @Test
    public void execute_itemWithIllegalArgument_throwsIllegalArgumentException() {
        testCommand1 = new AddCommand("HarryPotter", "-16.1",
                "25.12");
        assertThrows(IllegalArgumentException.class, () -> testCommand1.execute(testList));
    }
}
