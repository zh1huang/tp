package seedu.duke.command;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import seedu.duke.command.exception.ItemNotExistException;
import seedu.duke.model.Item;
import seedu.duke.model.ItemContainer;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GetCommandTest {
    private ItemContainer testList;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private Command testCommand1;
    private Command testCommand2;

    @BeforeEach
    public void setUp() throws Exception {
        testList = new ItemContainer("test");
        testCommand1 = new GetCommand("HarryPotter");
        testCommand2 = new GetCommand("Mr Midnight");
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void execute_ItemInList_getsNormally() throws Exception {
        testList.addItem(new Item("HarryPotter", "16.1", "25.12"));
        assertTrue(testList.contains("HarryPotter"));
        testCommand1.execute(testList);
        String expected = "Here is the information of your item\n"
                + String.format(ItemContainer.ITEM_DESCRIPTION, "HarryPotter", "25.12", "16.1");
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @Test
    public void execute_emptyList_throwsItemNotExistException() {
        assertThrows(ItemNotExistException.class, () -> testCommand2.execute(testList));
    }

    @Test
    public void execute_noMatchedItemInList_throwItemNotExistException() throws Exception {
        testList.addItem(new Item("HarryPotter", "16.1", "25.12"));
        assertTrue(testList.contains("HarryPotter"));
        assertThrows(ItemNotExistException.class, () -> testCommand2.execute(testList));
    }
}
