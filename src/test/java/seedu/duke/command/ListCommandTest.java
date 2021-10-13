package seedu.duke.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.model.Item;
import seedu.duke.model.ItemContainer;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListCommandTest {
    private ItemContainer testList;
    private Command testCommand;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() throws Exception {
        testList = new ItemContainer("test");
        testList.addItem(new Item("HarryPotter", "16.1", "25.12"));
        testList.addItem(new Item("LOTR", "10.2", "15.7"));
        testCommand = new ListCommand();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void execute_itemsAlreadyInList_listsNormally() {
        assertTrue(testList.contains("HarryPotter"));
        assertTrue(testList.contains("LOTR"));
        testCommand.execute(testList);
        String expected = "1. HarryPotter\n2. LOTR";
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    //todo for which no items in list

}
