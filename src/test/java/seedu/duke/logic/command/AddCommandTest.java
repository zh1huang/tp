package seedu.duke.logic.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.logic.command.exception.CommandException;
import seedu.duke.logic.command.exception.ExceedsShelfSizeLimitCommandException;
import seedu.duke.logic.command.exception.IllegalArgumentCommandException;
import seedu.duke.logic.command.exception.ShelfNotExistCommandException;
import seedu.duke.model.Shelf;
import seedu.duke.model.ShelfList;
import seedu.duke.model.exception.DuplicateShelfModelException;
import seedu.duke.model.exception.IllegalArgumentModelException;
import seedu.duke.model.exception.ModelException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class AddCommandTest {
    private static final String PRICE_WARNING =
            "\nWarning: \nYour price of selling is not higher than your purchase cost. "
                    + "\nMake sure you did not type wrongly.";
    private static final String ZERO_PRICE_WARNING =
            "\nWarning: \nYour selling price and/or your purchase cost is 0. "
                    + "\nMake sure you did not type wrongly.";

    private Shelf testList;
    private Command testCommand;

    @BeforeEach
    public void setUp() throws IllegalArgumentModelException, DuplicateShelfModelException {
        ShelfList.getShelfList().resetShelfList();
        testList = new Shelf("test");
    }

    @Test
    public void execute_addSingleItemToEmptyList_addsNormally() throws CommandException, ModelException {
        testCommand = new AddCommand("HarryPotter", "16.1", "25.12", "1", "test", "");
        int numberOfItemsBeforeAdding = testList.getItemCount();
        testCommand.execute();
        assertTrue(testList.contains("HarryPotter"));
        int numberOfItemAfterAdding = testList.getItemCount();
        assertEquals(numberOfItemAfterAdding, numberOfItemsBeforeAdding + 1);
    }

    @Test
    public void execute_addSingleItemWithItemsWithSameNameInList_addsNormally() throws CommandException,
            ModelException {
        testCommand = new AddCommand("HarryPotter", "16.1", "25.12", "1", "test", "");
        int numberOfItemsBeforeAdding = testList.getItemCount();
        testCommand.execute();
        assertTrue(testList.contains("HarryPotter"));
        int numberOfItemAfterAdding = testList.getItemCount();
        assertEquals(numberOfItemAfterAdding, numberOfItemsBeforeAdding + 1);
    }

    @Test
    public void execute_addMultipleItemsWithItemsWithSameNameInList_addsNormally() throws CommandException,
            ModelException {
        testCommand = new AddCommand("HarryPotter", "16.1", "25.12", "10", "test", "");
        int numberOfItemsBeforeAdding = testList.getItemCount();
        testCommand.execute();
        assertTrue(testList.contains("HarryPotter"));
        int numberOfItemAfterAdding = testList.getItemCount();
        assertEquals(numberOfItemAfterAdding, numberOfItemsBeforeAdding + 10);
    }

    @Test
    public void execute_addSingleItemWithIllegalArgument_throwsIllegalArgumentException() {
        testCommand = new AddCommand("HarryPotter", "-16.1",
                "25.12", "1", "test", "");
        assertThrows(IllegalArgumentCommandException.class, () -> testCommand.execute());
    }

    @Test
    public void execute_addMultipleItemsWithIllegalArgument_throwsIllegalArgumentException() {
        testCommand = new AddCommand("HarryPotter", "-16.1",
                "25.12", "10", "test", "");
        assertThrows(IllegalArgumentCommandException.class, () -> testCommand.execute());
    }

    @Test
    public void execute_exceedsMaxLimit_throwsExceedsShelfSizeLimitException() {
        testCommand = new AddCommand("HarryPotter", "16.1",
                "25.12", "1000", "test", "");
        assertThrows(ExceedsShelfSizeLimitCommandException.class, () -> testCommand.execute());
    }

    @Test
    public void execute_addSingleItemWithNegativeProfit_addsNormallyWithWarning() throws
            ModelException, CommandException {
        testCommand = new AddCommand("HarryPotter", "16.1", "15.12",
                "1", "test", "");
        int numberOfItemsBeforeAdding = testList.getItemCount();
        String completeMessage = testCommand.execute();
        assertTrue(testList.contains("HarryPotter"));
        int numberOfItemAfterAdding = testList.getItemCount();
        assertEquals(numberOfItemAfterAdding, numberOfItemsBeforeAdding + 1);
        assertTrue(completeMessage.contains(PRICE_WARNING));
    }

    @Test
    public void execute_addMultipleItemsWithNegativeProfit_addsNormallyWithWarning() throws
            ModelException, CommandException {
        testCommand = new AddCommand("HarryPotter", "16.1", "15.12",
                "10", "test", "");
        int numberOfItemsBeforeAdding = testList.getItemCount();
        String completeMessage = testCommand.execute();
        assertTrue(testList.contains("HarryPotter"));
        int numberOfItemAfterAdding = testList.getItemCount();
        assertEquals(numberOfItemAfterAdding, numberOfItemsBeforeAdding + 10);
        assertTrue(completeMessage.contains(PRICE_WARNING));
    }

    @Test
    public void execute_addSingleItemWithZeroPrice_addsNormallyWithWarning() throws
            ModelException, CommandException {
        testCommand = new AddCommand("HarryPotter", "16.1", "0",
                "1", "test", "");
        int numberOfItemsBeforeAdding = testList.getItemCount();
        String completeMessage = testCommand.execute();
        assertTrue(testList.contains("HarryPotter"));
        int numberOfItemAfterAdding = testList.getItemCount();
        assertEquals(numberOfItemAfterAdding, numberOfItemsBeforeAdding + 1);
        assertTrue(completeMessage.contains(ZERO_PRICE_WARNING));
    }

    @Test
    public void execute_addMultipleItemsWithZeroPrice_addsNormallyWithWarning() throws
            ModelException, CommandException {
        testCommand = new AddCommand("HarryPotter", "16.1", "0",
                "10", "test", "");
        int numberOfItemsBeforeAdding = testList.getItemCount();
        String completeMessage = testCommand.execute();
        assertTrue(testList.contains("HarryPotter"));
        int numberOfItemAfterAdding = testList.getItemCount();
        assertEquals(numberOfItemAfterAdding, numberOfItemsBeforeAdding + 10);
        assertTrue(completeMessage.contains(ZERO_PRICE_WARNING));
    }

    @Test
    public void execute_addSingleItemToANonExistingShelf_throwsShelfNotExistException() {
        testCommand = new AddCommand("HarryPotter", "16.1",
                "25.12", "1", "nonExistShelf", "");
        assertThrows(ShelfNotExistCommandException.class, () -> testCommand.execute());
    }

    @Test
    public void execute_addMultipleItemsToANonExistingShelf_throwsShelfNotExistException() {
        testCommand = new AddCommand("HarryPotter", "16.1",
                "25.12", "10", "nonExistShelf", "");
        assertThrows(ShelfNotExistCommandException.class, () -> testCommand.execute());
    }

    @Test
    public void execute_addZeroItem_throwsIllegalArgumentException() {
        testCommand = new AddCommand("HarryPotter", "16.1",
                "25.12", "0", "test", "");
        assertThrows(IllegalArgumentCommandException.class, () -> testCommand.execute());
    }

    @Test
    public void execute_addSingleItemWithZeroCost_addsNormallyWithWarning() throws
            ModelException, CommandException {
        testCommand = new AddCommand("HarryPotter", "0", "16.1",
                "1", "test", "");
        int numberOfItemsBeforeAdding = testList.getItemCount();
        String completeMessage = testCommand.execute();
        assertTrue(testList.contains("HarryPotter"));
        int numberOfItemAfterAdding = testList.getItemCount();
        assertEquals(numberOfItemAfterAdding, numberOfItemsBeforeAdding + 1);
        assertTrue(completeMessage.contains(ZERO_PRICE_WARNING));
    }

    @Test
    public void execute_addMultipleItemsWithZeroCost_addsNormallyWithWarning() throws
            ModelException, CommandException {
        testCommand = new AddCommand("HarryPotter", "0", "16.1",
                "10", "test", "");
        int numberOfItemsBeforeAdding = testList.getItemCount();
        String completeMessage = testCommand.execute();
        assertTrue(testList.contains("HarryPotter"));
        int numberOfItemAfterAdding = testList.getItemCount();
        assertEquals(numberOfItemAfterAdding, numberOfItemsBeforeAdding + 10);
        assertTrue(completeMessage.contains(ZERO_PRICE_WARNING));
    }

    @Test
    public void execute_addMultipleItemsToEmptyList_addsNormally() throws CommandException, ModelException {
        testCommand = new AddCommand("HarryPotter", "16.1", "25.12",
                "10", "test", "");
        int numberOfItemsBeforeAdding = testList.getItemCount();
        testCommand.execute();
        assertTrue(testList.contains("HarryPotter"));
        int numberOfItemAfterAdding = testList.getItemCount();
        assertEquals(numberOfItemAfterAdding, numberOfItemsBeforeAdding + 10);
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        testCommand = new AddCommand("HarryPotter", "16.1",
                "25.12", "10", "test", "");
        Command sameCommand = testCommand;
        assertTrue(testCommand.equals(sameCommand));
    }

    @Test
    public void equals_anotherSameTestCommandObjectWithSameArguments_returnsTrue() {
        testCommand = new AddCommand("HarryPotter", "16.1",
                "25.12", "10", "test", "");
        Command anotherTestCommand = new AddCommand("HarryPotter", "16.1",
                "25.12", "10", "test", "");
        assertTrue(testCommand.equals(anotherTestCommand));
    }

    @Test
    public void equals_anotherSameTestCommandObjectWithDifferentName_returnsFalse() {
        testCommand = new AddCommand("HarryPotter", "16.1",
                "25.12", "10", "test", "");
        Command anotherTestCommand = new AddCommand("Harry The Potter", "16.1",
                "25.12", "10", "test", "");
        assertFalse(testCommand.equals(anotherTestCommand));
    }

    @Test
    public void equals_anotherSameTestCommandObjectWithDifferentCost_returnsFalse() {
        testCommand = new AddCommand("HarryPotter", "16.1",
                "25.12", "10", "test", "");
        Command anotherTestCommand = new AddCommand("HarryPotter", "16",
                "25.12", "10", "test", "");
        assertFalse(testCommand.equals(anotherTestCommand));
    }

    @Test
    public void equals_anotherSameTestCommandObjectWithDifferentPrice_returnsFalse() {
        testCommand = new AddCommand("HarryPotter", "16.1",
                "25.12", "10", "test", "");
        Command anotherTestCommand = new AddCommand("HarryPotter", "16.1",
                "25", "10", "test", "");
        assertFalse(testCommand.equals(anotherTestCommand));
    }

    @Test
    public void equals_anotherSameTestCommandObjectWithDifferentQuantity_returnsFalse() {
        testCommand = new AddCommand("HarryPotter", "16.1",
                "25.12", "10", "test", "");
        Command anotherTestCommand = new AddCommand("HarryPotter", "16.1",
                "25.12", "11", "test", "");
        assertFalse(testCommand.equals(anotherTestCommand));
    }

    @Test
    public void equals_anotherSameTestCommandObjectWithDifferentShelfName_returnsFalse() {
        testCommand = new AddCommand("HarryPotter", "16.1",
                "25.12", "10", "test", "");
        Command anotherTestCommand = new AddCommand("HarryPotter", "16.1",
                "25.12", "10", "test1", "");
        assertFalse(testCommand.equals(anotherTestCommand));
    }

    @Test
    public void equals_anotherSameTestCommandObjectWithDifferentRemarks_returnsFalse() {
        testCommand = new AddCommand("HarryPotter", "16.1",
                "25.12", "10", "test", "");
        Command anotherTestCommand = new AddCommand("HarryPotter", "16.1",
                "25.12", "10", "test", "some remarks");
        assertFalse(testCommand.equals(anotherTestCommand));
    }

    @Test
    public void equals_null_returnsFalse() {
        testCommand = new AddCommand("HarryPotter", "16.1",
                "25.12", "10", "test", "");
        assertFalse(testCommand.equals(null));
    }

    @Test
    public void equals_notSameTypeWithTestCommand_returnsFalse() {
        testCommand = new AddCommand("HarryPotter", "16.1",
                "25.12", "10", "test", "");
        Command anotherCommand = new SellCommand("XXXXXXXX");
        assertFalse(testCommand.equals(anotherCommand));
    }


}
