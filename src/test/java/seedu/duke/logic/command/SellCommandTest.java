package seedu.duke.logic.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.duke.logic.command.exception.CommandException;
import seedu.duke.logic.command.exception.ItemNotExistCommandException;
import seedu.duke.logic.command.sales.SalesReport;
import seedu.duke.model.Shelf;
import seedu.duke.model.ShelfList;
import seedu.duke.model.exception.ModelException;

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
        itemID = extractedID;
    }

    @AfterEach
    public void cleanUp() {
        ShelfList.getShelfList().resetShelfList();
    }

    @Test
    public void execute_itemWithInputIdExists_sellsNormally() throws CommandException, ModelException {
        assertTrue(testList.contains("HarryPotter"));
        testCommand = new SellCommand(itemID);
        int numberOfItemsBeforeSelling = testList.getItemCount();
        testCommand.execute();
        assertTrue(ShelfList.getShelfList().getShelf("soldItems").contains("HarryPotter"));
        int numberOfItemAfterSelling = testList.getItemCount();
        assertEquals(numberOfItemAfterSelling, numberOfItemsBeforeSelling - 1);
        assertFalse(testList.contains("HarryPotter"));
    }


    @Test
    public void execute_itemWithInputIdDoesNotExist_throwsItemNotExistCommandException() {
        itemID = "XXXXXXXX";
        testCommand = new SellCommand(itemID);
        assertThrows(ItemNotExistCommandException.class, () -> testCommand.execute());
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        itemID = "XXXXXXXX";
        testCommand = new SellCommand(itemID);
        Command sameCommand = testCommand;
        assertTrue(testCommand.equals(sameCommand));
    }

    @Test
    public void equals_anotherSameCommandObjectWithSameArgument_returnsTrue() {
        itemID = "XXXXXXXX";
        testCommand = new SellCommand(itemID);
        Command anotherTestCommand = new SellCommand(itemID);
        assertTrue(testCommand.equals(anotherTestCommand));
    }

    @Test
    public void equals_null_returnsFalse() {
        itemID = "XXXXXXXX";
        testCommand = new SellCommand(itemID);
        assertFalse(testCommand.equals(null));
    }

    @Test
    public void equals_notSameTypeWithTestCommand_returnsFalse() {
        itemID = "XXXXXXXX";
        testCommand = new SellCommand(itemID);
        Command anotherCommand = new CreateShelfCommand("randomName");
        assertFalse(testCommand.equals(anotherCommand));
    }
}
