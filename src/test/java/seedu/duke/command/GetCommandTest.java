package seedu.duke.command;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import seedu.duke.model.ContainerList;
import seedu.duke.model.Item;
import seedu.duke.model.ItemContainer;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetCommandTest {
    private ItemContainer testList;
    private Command testCommand;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() throws Exception {
        ContainerList.getContainerList().resetContainerList();
        testList = new ItemContainer("test");
        testList.addItem(new Item("HarryPotter", "16.1", "25.12"));
        testCommand = new GetCommand("HarryPotter");
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void execute_oneItemAlreadyInList_getsNormally() throws Exception {
        assertTrue(testList.contains("HarryPotter"));
        testCommand.execute(testList);
        String expected = String.format(ItemContainer.ITEM_DESCRIPTION, "HarryPotter", "25.12", "16.1");
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    //todo test for which no items in list

}
