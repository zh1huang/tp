package seedu.duke.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.duke.command.exception.CommandException;
import seedu.duke.command.exception.ItemNotExistException;
import seedu.duke.model.Shelf;
import seedu.duke.model.ShelfList;
import seedu.duke.model.exception.DuplicateShelfException;
import seedu.duke.model.exception.IllegalModelArgumentException;
import seedu.duke.model.exception.ShelfNotExistException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class SellCommandTest {

    private Shelf testList;
    private Command testCommand;
    private String itemID;

    @BeforeEach
    public void setUp() throws IllegalModelArgumentException,
            DuplicateShelfException, ShelfNotExistException, CommandException {
        ShelfList.getShelfList().resetShelfList();
        testList = new Shelf("test");
        Command addItemToSell = new AddCommand("HarryPotter", "16.1", "25.12",
                "1", "test", "");
        String output = addItemToSell.execute();
        int startingIndexOfID = output.indexOf("Its unique ID is: ") + "Its unique ID is: ".length() + 1;
        String extractedID = output.substring(startingIndexOfID);
        System.out.println(extractedID);
        itemID = extractedID;
    }

    @Test
    public void execute_itemWithInputIdExists_addsNormally() throws CommandException, ShelfNotExistException,
            IllegalModelArgumentException {
        assertTrue(testList.contains("HarryPotter"));
        testCommand = new SellCommand(itemID);
        int numberOfItemsBeforeSelling = testList.getItemCount();
        testCommand.execute();
        int numberOfItemAfterSelling = testList.getItemCount();
        assertEquals(numberOfItemAfterSelling, numberOfItemsBeforeSelling - 1);
        assertFalse(testList.contains("HarryPotter"));
        assertTrue(ShelfList.getShelfList().getShelf("soldItems").contains("HarryPotter"));
    }

    @Test
    public void execute_itemWithInputIdDoesNotExist_throwsItemNotExistException()
            throws CommandException, ShelfNotExistException,
            IllegalModelArgumentException {
        itemID = "XXXXXXXX";
        testCommand = new SellCommand(itemID);
        assertThrows(ItemNotExistException.class, () -> testCommand.execute());
    }
}
