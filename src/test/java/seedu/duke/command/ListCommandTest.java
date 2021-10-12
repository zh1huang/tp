package seedu.duke.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.model.Item;
import seedu.duke.model.ItemContainer;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ListCommandTest {
    private ItemContainer testList;
    private Command testCommand;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() throws Exception {
        testList = new ItemContainer("test");
        new Item("HarryPotter", new BigDecimal("16.1"), new BigDecimal("25.12"), testList);
        new Item("LOTR", new BigDecimal("10.2"), new BigDecimal("15.7"), testList);
        //testList.addItem(new Item("HarryPotter", new BigDecimal("16.1"), new BigDecimal("25.12"), testList));
        //testList.addItem(new Item("LOTR", new BigDecimal("10.2"), new BigDecimal("15.7"), testList));
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
        String expected = "HarryPotter\nLOTR";
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    //todo for which no items in list

}
