package seedu.duke.command;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import seedu.duke.model.Item;
import seedu.duke.model.ItemContainer;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GetCommandTest {
    private ItemContainer testList;
    private Command testCommand;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() throws Exception {
        testList = new ItemContainer("test");
        testList.addItem(new Item("HarryPotter", new BigDecimal("16.1"), new BigDecimal("25.12"), testList));
        testCommand = new GetCommand("HarryPotter", testList);
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void getCommandExecute() {
        assertTrue(testList.contains("HarryPotter"));
        testCommand.execute(testList);
        String expected = "name: HarryPotter\n selling price: 25.12\n purchase cost: 16.1";
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }
}
