package seedu.duke.command;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import seedu.duke.command.exception.ItemNotExistException;
import seedu.duke.model.ShelfList;
import seedu.duke.model.Item;
import seedu.duke.model.Shelf;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GetCommandTest {

    private Shelf testList;
    private Command testCommand1;
    private Command testCommand2;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    public static final String ITEM_DESCRIPTION = "Name: %s\nCost: %s\nPrice: %s\nRemarks: %s";
    public static final String GET_COMPLETE_MESSAGE = "Here is the information of your item:\n";

    @BeforeEach
    public void setUp() throws Exception {
        ShelfList.getShelfList().resetShelfList();
        testList = new Shelf("test");
        testCommand1 = new GetCommand("test", "1");
        testCommand2 = new GetCommand("test", "3");
        //System.setOut(new PrintStream(outputStreamCaptor));
    }

    //@AfterEach
    //public void tearDown() {
    //    System.setOut(standardOut);
    //}

    @Test
    public void execute_ItemInList_getsNormally() throws Exception {
        testList.addItem(new Item("HarryPotter", "16.1", "25.12", "Hello"));
        assertTrue(testList.contains("HarryPotter"));
        //testCommand1.execute();
        String expected = String.format(GET_COMPLETE_MESSAGE + ITEM_DESCRIPTION,
                "HarryPotter", "16.1", "25.12", "Hello");
        assertEquals(expected, testCommand1.execute());
    }

    @Test
    public void execute_emptyList_throwsItemNotExistException() {
        assertThrows(ItemNotExistException.class, () -> testCommand2.execute());
    }

    @Test
    public void execute_noMatchedItemInList_throwItemNotExistException() throws Exception {
        testList.addItem(new Item("Hello", "25.12", "16.1", ""));
        assertTrue(testList.contains("Hello"));
        assertThrows(ItemNotExistException.class, () -> testCommand2.execute());
    }
}
