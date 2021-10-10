package seedu.duke.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import seedu.duke.model.ItemContainer;

import java.math.BigDecimal;

public class AddCommandTest {
    private ItemContainer testList;
    private Command testCommand;

    @BeforeEach
    public void setUp() throws Exception {
        testList = new ItemContainer("test");
        testCommand = new AddCommand("HarryPotter", new BigDecimal("16.1"), new BigDecimal("25.12"), testList);
    }

    @Test
    public void addCommandExecute() {
        int numberOfItemsBeforeAdding = testList.getSize();
        testCommand.execute(testList);
        assertTrue(testList.contains("HarryPotter"));
        int numberOfItemAfterAdding = testList.getSize();
        assertTrue(numberOfItemAfterAdding == numberOfItemsBeforeAdding + 1);
    }
}
