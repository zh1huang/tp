package seedu.duke.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.exception.EmptyListException;
import seedu.duke.model.Item;
import seedu.duke.model.ItemContainer;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ListCommandTest {
    private ItemContainer testList;
    private Command testCommand;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() throws Exception {
        testList = new ItemContainer("test");
        testCommand = new ListCommand();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void execute_itemsAlreadyInList_listsNormally() throws Exception {
        testList.addItem(new Item("HarryPotter", "16.1", "25.12"));
        testList.addItem(new Item("LOTR", "10.2", "15.7"));
        assertTrue(testList.contains("HarryPotter"));
        assertTrue(testList.contains("LOTR"));
        testCommand.execute(testList);
        String expected = "Here is the list of items:\n"
                + "1. HarryPotter (purchase cost: 16.1, selling price: 25.12)\n"
                + "2. LOTR (purchase cost: 10.2, selling price: 15.7)";
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @Test
    public void execute_noItemsInList_throwsEmptyListException() {
        assertThrows(EmptyListException.class, () -> testCommand.execute(testList));
    }
}
