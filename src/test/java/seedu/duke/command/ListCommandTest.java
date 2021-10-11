package seedu.duke.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import seedu.duke.model.Item;
import seedu.duke.model.ItemContainer;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

public class ListCommandTest {
    private ItemContainer testList;
    private Command testCommand;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() throws Exception {
        testList = new ItemContainer("test");
        testList.addItem(new Item("HarryPotter", new BigDecimal("16.1"), new BigDecimal("25.12"), testList));
        testList.addItem(new Item("LOTR", new BigDecimal("10.2"), new BigDecimal("15.7"), testList));
        testCommand = new GetCommand("HarryPotter", testList);
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }



}
