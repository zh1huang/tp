package seedu.duke.logic.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.duke.logic.command.exception.CommandException;
import seedu.duke.logic.command.exception.ItemNotExistCommandException;
import seedu.duke.model.Shelf;
import seedu.duke.model.ShelfList;
import seedu.duke.model.exception.DuplicateShelfModelException;
import seedu.duke.model.exception.IllegalArgumentModelException;
import seedu.duke.model.exception.ModelException;
import seedu.duke.model.exception.ShelfNotExistModelException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class SellCommandTest {

    private Shelf testList;
    private Command testCommand;
    private String itemID;

    @BeforeEach
    public void setUp() throws ModelException, CommandException {
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
    public void execute_itemWithInputIdExists_addsNormally() throws CommandException, ModelException {
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
            throws CommandException, ShelfNotExistModelException,
            IllegalArgumentModelException {
        itemID = "XXXXXXXX";
        testCommand = new SellCommand(itemID);
        assertThrows(ItemNotExistCommandException.class, () -> testCommand.execute());
    }
}
