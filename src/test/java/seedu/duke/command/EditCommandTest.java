package seedu.duke.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


import seedu.duke.model.Item;
import seedu.duke.model.ItemContainer;

import java.math.BigDecimal;

public class EditCommandTest {
    private ItemContainer testList;
    private Command testCommand;

    @BeforeEach
    public void setUp() throws Exception {
        testList = new ItemContainer("test");
        testList.addItem(new Item("HarryPotter", "16.1", "25.12"));
        testCommand = new EditCommand("HarryPotter", "16.1", "20", testList);
    }

    @Test
    public void execute_oneItemAlreadyInList_editsNormally() {
        assertTrue(testList.contains("HarryPotter"));
        assertEquals(new BigDecimal("25.12"), testList.getItem("HarryPotter").getSellingPrice());
        int numberOfItemsBeforeEditing = testList.getSize();
        testCommand.execute(testList);
        int numberOfItemAfterEditing = testList.getSize();
        assertTrue(numberOfItemAfterEditing == numberOfItemsBeforeEditing);
        assertTrue(testList.contains("HarryPotter"));
        assertEquals(new BigDecimal("20"), testList.getItem("HarryPotter").getSellingPrice());
    }
}
