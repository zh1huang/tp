package seedu.duke.logic.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.logic.command.exception.CommandException;
import seedu.duke.logic.command.exception.ItemNotExistCommandException;
import seedu.duke.model.Item;
import seedu.duke.model.Shelf;
import seedu.duke.model.ShelfList;
import seedu.duke.model.exception.DeniedAccessToShelfModelException;
import seedu.duke.model.exception.ModelException;
import seedu.duke.model.exception.ShelfNotExistModelException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MarkUpCommandTest {

    private Command testCommand;

    @BeforeEach
    public void setUp() throws Exception {
        ShelfList.getShelfList().resetShelfList();
        Shelf testShelf = new Shelf("test");
        testShelf.addItem(new Item("Harry Potter", "16.1", "25.12", ""));
        testShelf.addItem(new Item("Narnia", "10.2", "15.7", ""));
    }

    @Test
    public void execute_ItemInList_executesNormally()
            throws ModelException, CommandException, IllegalArgumentException {

        testCommand = new MarkUpCommand("test", "1", "");
        String expectedOutput1 = "Item: Harry Potter\nCost: 16.1, Price: 25.12\nAmount Difference: 9.02\n"
            + "Current Mark Up: 56.02%\nmarkup: 0%, increase: $0.00, Final price: $16.10\n"
            + "markup: 20%, increase: $3.22, Final price: $19.32\n"
            + "markup: 40%, increase: $6.44, Final price: $22.54\n"
            + "markup: 60%, increase: $9.66, Final price: $25.76\n"
            + "markup: 80%, increase: $12.88, Final price: $28.98\n"
            + "markup: 100%, increase: $16.10, Final price: $32.20\n";
        assertEquals(expectedOutput1, testCommand.execute());
        testCommand = new MarkUpCommand("test", "1", "6.7");
        String expectedOutput2 = "Item: Harry Potter\nCost: 16.1, Price: 25.12\nAmount Difference: 9.02\n"
            + "Current Mark Up: 56.02%\nmarkup: 6.7%, increase: $1.08, Final price: $17.18\n";
        assertEquals(expectedOutput2, testCommand.execute());
    }

    @Test
    public void execute_ItemInListButUserRequestMarkUpPercentMoreThanOneHundred_executesNormallyWithWarningMessage()
            throws ModelException, CommandException, IllegalArgumentException {

        testCommand = new MarkUpCommand("test", "1", "103.7");
        String expectedOutput2 = "Item: Harry Potter\nCost: 16.1, Price: 25.12\nAmount Difference: 9.02\n"
            + "Current Mark Up: 56.02%\nmarkup: 103.7%, increase: $16.70, Final price: $32.80\n"
            + "!!!WARNING: NOT recommended to set a percentage > 100 to $32.80.\n"
            + "This is to keep the price of the item reasonable";
        assertEquals(expectedOutput2, testCommand.execute());
    }

    @Test
    public void execute_ItemInSoldItemList_throwsDeniedAccessToShelfModelException()
            throws IllegalArgumentException {
        testCommand = new MarkUpCommand("soldItems", "1", "");
        assertThrows(DeniedAccessToShelfModelException.class, () -> testCommand.execute());
    }

    @Test
    public void execute_ShelfNotExist_throwShelfNotExistModelException() {
        testCommand = new MarkUpCommand("book1", "1", "");
        assertThrows(ShelfNotExistModelException.class, () -> testCommand.execute());
    }

    @Test
    public void execute_NoItemInList_throwItemNotExistCommandException() {
        testCommand = new MarkUpCommand("test", "10", "");
        assertThrows(ItemNotExistCommandException.class, () -> testCommand.execute());
    }
}