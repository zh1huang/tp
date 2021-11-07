package seedu.duke.logic.parser;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.logic.command.AddCommand;
import seedu.duke.logic.command.Command;
import seedu.duke.logic.command.CreateShelfCommand;
import seedu.duke.logic.command.DeleteCommand;
import seedu.duke.logic.command.EditCommand;
import seedu.duke.logic.command.ExitCommand;
import seedu.duke.logic.command.GetCommand;
import seedu.duke.logic.command.HelpCommand;
import seedu.duke.logic.command.ListCommand;
import seedu.duke.logic.command.MarkUpCommand;
import seedu.duke.logic.command.RemoveShelfCommand;
import seedu.duke.logic.command.ReportCommand;
import seedu.duke.logic.command.SellCommand;
import seedu.duke.logic.command.exception.CommandException;
import seedu.duke.model.Shelf;
import seedu.duke.model.ShelfList;

import seedu.duke.logic.parser.exception.IllegalFormatException;
import seedu.duke.logic.parser.exception.NoPropertyFoundException;
import seedu.duke.model.exception.DuplicateShelfModelException;
import seedu.duke.model.exception.IllegalArgumentModelException;
import seedu.duke.model.exception.ModelException;
import seedu.duke.model.exception.ShelfNotExistModelException;
import seedu.duke.model.exception.ItemNotExistModelException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.duke.logic.command.AddCommand.ADD_STRING;
import static seedu.duke.logic.command.DeleteCommand.DELETE_STRING;
import static seedu.duke.logic.command.EditCommand.EDIT_STRING;
import static seedu.duke.logic.command.GetCommand.GET_STRING;
import static seedu.duke.logic.command.ListCommand.LIST_STRING;
import static seedu.duke.logic.command.MarkUpCommand.MARKUP_STRING;
import static seedu.duke.logic.command.ReportCommand.REPORT_STRING;
import static seedu.duke.logic.command.SellCommand.SELL_STRING;


// Parser Test class adapted from
// https://github.com/se-edu/addressbook-level2/blob/master/test/java/seedu/addressbook/parser/ParserTest.java
public class ParserTest {

    public static final String PURCHASE_COST_PROPERTY_STRING = "purchase cost";
    public static final String SELLING_PRICE_PROPERTY_STRING = "selling price";

    public static final String ITEM_NAME_EXAMPLE_1 = "Geronimo";
    public static final String SHELF_NAME_EXAMPLE_1 = "book1";
    public static final String PURCHASE_COST_EXAMPLE_1 = "25";
    public static final String SELLING_PRICE_EXAMPLE_1 = "30.99";
    public static final String VALUE_EXAMPLE_1 = "3.45";
    public static final String QUANTITY_EXAMPLE_1 = "1";
    public static final String REMARKS_EXAMPLE_1 = "Hello World!";

    public static final String ITEM_NAME_EXAMPLE_2 = "Mechanical Pencil";
    public static final String SHELF_NAME_EXAMPLE_2 = "stationary1";
    public static final String PURCHASE_COST_EXAMPLE_2 = "0.70";
    public static final String SELLING_PRICE_EXAMPLE_2 = "1.90";
    public static final String VALUE_EXAMPLE_2 = "3.4";
    public static final String QUANTITY_EXAMPLE_2 = "2";
    public static final String REMARKS_EXAMPLE_2 = "need restock!";
    public static final String INDEX_1_STRING = "1";

    public static final String REPORT_ITEMS_STRING = "items";
    private static final String REPORT_STATS_STRING = "stats";
    public static final String USER_REQUEST_PERCENT_EXAMPLE = "5.60";
    private Parser parser;
    private Shelf soldItems;

    @BeforeEach
    public void setUp() throws IllegalArgumentModelException, DuplicateShelfModelException {
        parser = new Parser();
        ShelfList.getShelfList().resetShelfList();
        Command newShelf1Command = new CreateShelfCommand(SHELF_NAME_EXAMPLE_1);
        Command newShelf2Command = new CreateShelfCommand(SHELF_NAME_EXAMPLE_2);
        Command newSoldItemsShelfCommand = new CreateShelfCommand("soldItems");
        try {
            newShelf1Command.execute();
            newShelf2Command.execute();
            newSoldItemsShelfCommand.execute();
        } catch (CommandException | ModelException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void tearDown() {

    }

    @Test
    public void parse_EmptyInput_throwsIllegalFormatException() {
        final String[] emptyInputs = {"", "\n", " ", "\n \n"};
        for (String emptyInput : emptyInputs) {
            assertThrows(IllegalFormatException.class, () -> parser.parseCommand(emptyInput));
        }
    }

    @Test
    public void parse_NotProgramCommand_throwsIllegalFormatException() {
        final String notProgramCommandInput = "blahdsdsh";
        assertThrows(IllegalFormatException.class, () -> parser.parseCommand(notProgramCommandInput));
    }

    /*
     * Tests for 0 argument commands =========================================================
     */

    @Test
    public void parse_helpCommand_parsedCorrectly() throws ItemNotExistModelException,
        NoPropertyFoundException, IllegalFormatException {
        final String input = "help";
        assertEquals(HelpCommand.class, parser.parseCommand(input).getClass());
    }

    @Test
    public void parse_ExitCommand_parsedCorrectly() throws ItemNotExistModelException,
        NoPropertyFoundException, IllegalFormatException {
        final String input = "bye";
        assertEquals(ExitCommand.class, parser.parseCommand(input).getClass());
    }

    /*
     * Tests for create command ===============================================================
     */
    @Test
    public void parse_createCommandInvalidArgs_throwsIllegalFormatException() {
        final String[] inputs = {
            "create",
            "create shelf/nonexistentshelf",
            "create shlv/book1 shlv/book1",
        };

        for (String input : inputs) {
            assertThrows(IllegalFormatException.class, () -> parser.parseCommand(input));
        }
    }

    @Test
    public void parse_createCommandValidArgs_returnCreateShelfCommand() throws ItemNotExistModelException,
        NoPropertyFoundException, IllegalFormatException {
        String input = "create shlv/book2";
        Command createShelfCommand = new CreateShelfCommand("book2");
        assertEquals(createShelfCommand, parser.parseCommand(input));
    }

    /*
     * Tests for remove command ===============================================================
     */

    @Test
    public void parse_removeCommandInvalidArgs_throwsIllegalFormatException() {
        final String[] inputs = {
            "remove",
            "remove shelf/existentshelf",
            "remove shlv/book1 shlv/book1",
        };

        for (String input : inputs) {
            assertThrows(IllegalFormatException.class, () -> parser.parseCommand(input));
        }
    }

    @Test
    public void parse_removeCommandValidArgs_returnRemoveShelfCommand() throws ItemNotExistModelException,
        NoPropertyFoundException, IllegalFormatException {
        addExtraShelves();
        String input = "remove shlv/book2";
        Command removeShelfCommand = new RemoveShelfCommand("book2");
        assertEquals(removeShelfCommand, parser.parseCommand(input));
    }

    /*
     * Tests for add command ===============================================================
     */

    @Test
    public void parse_addCommandInvalidArgs_throwsIllegalFormatException() {

        final String[] inputs = {
            "add",
            "add shlv/book1 p/23.99 s/39.99 q/1",
            "add n/Harry Potter 2 p/24.99 s/40.99 q/1",
            "add n/Harry Potter 3 shlv/book1 s/41.99 q/1",
            "add n/Harry Potter 4 shlv/book1 p/26.99 q/1",
            "add n/Harry Potter 5 shlv/book1 p/26.99 s/41.99",
            "add n/Harry Potter 5 shlv/book1 p/26000.99 s/41.99 q/6 r/This is a popular book!"
        };

        for (String input : inputs) {
            assertThrows(IllegalFormatException.class, () -> parser.parseCommand(input));
        }
    }


    @Test
    public void parse_addCommandValidArgs_returnsAddCommand() throws ItemNotExistModelException,
        NoPropertyFoundException, IllegalFormatException {

        String input1 = ADD_STRING + " n/" + ITEM_NAME_EXAMPLE_1 + " shlv/" + SHELF_NAME_EXAMPLE_1 + " p/"
            + PURCHASE_COST_EXAMPLE_1 + " s/" + SELLING_PRICE_EXAMPLE_1 + " q/" + QUANTITY_EXAMPLE_1;

        AddCommand expectedCommand1 = new AddCommand(ITEM_NAME_EXAMPLE_1, PURCHASE_COST_EXAMPLE_1,
            SELLING_PRICE_EXAMPLE_1, QUANTITY_EXAMPLE_1, SHELF_NAME_EXAMPLE_1, "");

        assertEquals(expectedCommand1, parser.parseCommand(input1));

        String input2 = ADD_STRING + " n/" + ITEM_NAME_EXAMPLE_2 + " shlv/" + SHELF_NAME_EXAMPLE_2 + " p/"
            + PURCHASE_COST_EXAMPLE_2 + " s/" + SELLING_PRICE_EXAMPLE_2 + " q/" + QUANTITY_EXAMPLE_1
            + " r/" + REMARKS_EXAMPLE_2;

        AddCommand expectedCommand2 = new AddCommand(ITEM_NAME_EXAMPLE_2, PURCHASE_COST_EXAMPLE_2,
            SELLING_PRICE_EXAMPLE_2, QUANTITY_EXAMPLE_1, SHELF_NAME_EXAMPLE_2, REMARKS_EXAMPLE_2);

        assertEquals(expectedCommand2, parser.parseCommand(input2));
    }

    /*
     * Tests for delete command ===============================================================
     */

    @Test
    public void parse_deleteCommandInvalidArgs_throwsIllegalFormatException() {
        final String[] inputs = {
            "delete ",
            "delete shlv/book1",
            "delete i/37"
        };

        for (String input : inputs) {
            assertThrows(IllegalFormatException.class, () -> parser.parseCommand(input));
        }
    }

    @Test
    public void parse_deleteCommandValidArgs_returnsDeleteCommand() throws IllegalFormatException,
            ItemNotExistModelException, NoPropertyFoundException {

        addExampleItemsToShelf();

        String input1 = DELETE_STRING + " shlv/" + SHELF_NAME_EXAMPLE_1 + " i/" + INDEX_1_STRING;

        Command expectedCommand1 = new DeleteCommand(SHELF_NAME_EXAMPLE_1, INDEX_1_STRING);
        assertEquals(expectedCommand1, parser.parseCommand(input1));

        String input2 = DELETE_STRING + " shlv/" + SHELF_NAME_EXAMPLE_2 + " i/" + INDEX_1_STRING;

        Command expectedCommand2 = new DeleteCommand(SHELF_NAME_EXAMPLE_2, INDEX_1_STRING);
        assertEquals(expectedCommand2, parser.parseCommand(input2));
    }


    /*
     * Tests for list command ===============================================================
     */

    @Test
    public void parse_listCommandInvalidArgs_throwsIllegalFormatException() {
        final String[] inputs = {
            "list p/223",
            "list shlv/",
            "list shl/book1"
        };

        for (String input : inputs) {
            assertThrows(IllegalFormatException.class, () -> parser.parseCommand(input));
        }
    }

    @Test
    public void parse_listCommandValidArgs_returnsListCommand() throws IllegalFormatException,
            ItemNotExistModelException, NoPropertyFoundException {

        String input1 = LIST_STRING;
        Command expectedCommand1 = new ListCommand();
        assertEquals(expectedCommand1, parser.parseCommand(input1));

        String input2 = LIST_STRING + " shlv/" + SHELF_NAME_EXAMPLE_1;
        Command expectedCommand2 = new ListCommand(SHELF_NAME_EXAMPLE_1);
        assertEquals(expectedCommand2, parser.parseCommand(input2));
    }

    /*
     * Tests for get command ===============================================================
     */

    @Test
    public void parse_getCommandInvalidArgs_throwsIllegalFormatException() {
        final String[] inputs = {
            "get",
            "get shlv/book1",
            "get i/1"
        };

        for (String input : inputs) {
            assertThrows(IllegalFormatException.class, () -> parser.parseCommand(input));
        }
    }

    @Test
    public void parse_getCommandValidArgs_returnsGetCommand() throws IllegalFormatException,
            ItemNotExistModelException, NoPropertyFoundException {

        addExampleItemsToShelf();

        String input1 = GET_STRING + " shlv/" + SHELF_NAME_EXAMPLE_1 + " i/" + INDEX_1_STRING;

        Command expectedCommand1 = new GetCommand(SHELF_NAME_EXAMPLE_1, INDEX_1_STRING);
        assertEquals(expectedCommand1, parser.parseCommand(input1));

        String input2 = GET_STRING + " shlv/" + SHELF_NAME_EXAMPLE_2 + " i/" + INDEX_1_STRING;

        Command expectedCommand2 = new GetCommand(SHELF_NAME_EXAMPLE_2, INDEX_1_STRING);
        assertEquals(expectedCommand2, parser.parseCommand(input2));
    }


    /*
     * Tests for edit command ===============================================================
     */

    @Test
    public void parse_editCommandInvalidArgs_throwsIllegalFormatException() {
        final String[] inputs = {
            "edit ",
            "edit i/2 p/cost v/15.80",
            "edit shlv/book1 p/cost v/15.60",
            "edit shlv/book1 i/2 v/15.60",
            "edit shlv/book1 i/2 p/cost",
            "edit shlv/book1 i/2 p/prices v/15.60"
        };

        for (String input : inputs) {
            assertThrows(IllegalFormatException.class, () -> parser.parseCommand(input));
        }
    }

    @Test
    public void parse_editCommandValidArgs_returnsEditCommand() throws
            ItemNotExistModelException, NoPropertyFoundException, IllegalFormatException {

        addExampleItemsToShelf();
        // Test edit Purchase Cost
        String input1 = EDIT_STRING + " shlv/" + SHELF_NAME_EXAMPLE_1 + " i/" + INDEX_1_STRING + " p/"
            + PURCHASE_COST_PROPERTY_STRING + " v/" + VALUE_EXAMPLE_1;
        Command expectedCommand1 = new EditCommand(SHELF_NAME_EXAMPLE_1, INDEX_1_STRING,
            PURCHASE_COST_PROPERTY_STRING, VALUE_EXAMPLE_1);
        assertEquals(expectedCommand1, parser.parseCommand(input1));

        // Test edit Selling Price
        String input2 = EDIT_STRING + " shlv/" + SHELF_NAME_EXAMPLE_2 + " i/" + INDEX_1_STRING + " p/"
            + SELLING_PRICE_PROPERTY_STRING + " v/" + VALUE_EXAMPLE_2;
        Command expectedCommand2 = new EditCommand(SHELF_NAME_EXAMPLE_2, INDEX_1_STRING,
            SELLING_PRICE_PROPERTY_STRING, VALUE_EXAMPLE_2);
        assertEquals(expectedCommand2, parser.parseCommand(input2));
    }

    /*
     * Tests for report command ===============================================================
     */
    @Test
    public void parse_reportCommandInvalidArgs_throwsIllegalFormatException() {
        final String[] inputs = {
            "report ",
            "report t/stat ym/2021-10",
            "report t/item ym/2021-10",
            "report t/stats ym/21-10",
            "report t/items ym/",
            "report t/stats ym/2021-10 ym/2021-",
            "report t/stats ym/2021-10 ym/-10",
            "report t/stats ym/2021-10 ym/2021-1Oct"
        };

        for (String input : inputs) {
            assertThrows(IllegalFormatException.class, () -> parser.parseCommand(input));
        }
    }

    @Test
    public void parse_reportCommandValidArgs_returnReportCommand() throws
            ItemNotExistModelException, NoPropertyFoundException, IllegalFormatException {

        String input1 = REPORT_STRING + " t/" + REPORT_ITEMS_STRING + " ym/" + "2021-10";
        ReportCommand reportCommand1 = new ReportCommand(
            "2021-10", "", REPORT_ITEMS_STRING);
        assertEquals(reportCommand1, parser.parseCommand(input1));

        String input2 = REPORT_STRING + " t/" + REPORT_STATS_STRING + " ym/" + "2021-05" + " ym/" + "2021-11";
        ReportCommand reportCommand2 = new ReportCommand(
            "2021-05", "2021-11", REPORT_STATS_STRING);
        assertEquals(reportCommand2, parser.parseCommand(input2));
    }

    /*
     * Tests for sell command ===============================================================
     */
    @Test
    public void parse_sellCommandInvalidArgs_throwsIllegalFormatException() {
        final String[] inputs = {
            "sell ",
            "sell id/1",
            "sell id/1234567",
        };

        for (String input : inputs) {
            assertThrows(IllegalFormatException.class, () -> parser.parseCommand(input));
        }

    }

    @Test
    public void parse_sellCommandValidArgs_returnSellCommand() throws
            ItemNotExistModelException, NoPropertyFoundException, IllegalFormatException {
        String input = SELL_STRING + " id/" + "12345abc";
        SellCommand sellCommand = new SellCommand("12345abc");
        assertEquals(sellCommand, parser.parseCommand(input));
    }

    /*
     * Tests for markup command ===============================================================
     */
    @Test
    public void parse_markupCommandInvalidArgs_throwsIllegalFormatException() {
        final String[] inputs = {
            "markup ",
            "markup i/1",
            "markup shlv/book1",
            "markup shlv/book2/ i/1",
            "markup shlv/book1$   i/1/2"
        };

        for (String input : inputs) {
            assertThrows(IllegalFormatException.class, () -> parser.parseCommand(input));
        }
    }

    @Test
    public void parse_markupCommandValidArgs_returnMarkupCommand() throws
            ItemNotExistModelException, NoPropertyFoundException, IllegalFormatException {

        addExampleItemsToShelf();
        String input1 = MARKUP_STRING + " shlv/" + SHELF_NAME_EXAMPLE_1 + " i/" + INDEX_1_STRING;

        Command expectedCommand1 = new MarkUpCommand(SHELF_NAME_EXAMPLE_1, INDEX_1_STRING, "");
        assertEquals(expectedCommand1, parser.parseCommand(input1));

        String input2 = MARKUP_STRING + " shlv/" + SHELF_NAME_EXAMPLE_2 + " i/" + INDEX_1_STRING + " %/"
            + USER_REQUEST_PERCENT_EXAMPLE;

        Command expectedCommand2 = new MarkUpCommand(
            SHELF_NAME_EXAMPLE_2, INDEX_1_STRING, USER_REQUEST_PERCENT_EXAMPLE);
        assertEquals(expectedCommand2, parser.parseCommand(input2));
    }

    /*
     * Utility methods ===============================================================
     */

    private void addExtraShelves() {
        Command createShelfCommand = new CreateShelfCommand("book2");
        try {
            createShelfCommand.execute();
        } catch (CommandException | ModelException e) {
            e.printStackTrace();
        }
    }

    private void addExampleItemsToShelf() {
        Command addCommand1 = new AddCommand(ITEM_NAME_EXAMPLE_1, PURCHASE_COST_EXAMPLE_1,
            SELLING_PRICE_EXAMPLE_1, QUANTITY_EXAMPLE_1, SHELF_NAME_EXAMPLE_1, "");
        Command addCommand2 = new AddCommand(ITEM_NAME_EXAMPLE_2, PURCHASE_COST_EXAMPLE_2,
            SELLING_PRICE_EXAMPLE_2, QUANTITY_EXAMPLE_1, SHELF_NAME_EXAMPLE_2, "");

        try {
            addCommand1.execute();
            addCommand2.execute();
        } catch (CommandException | ModelException e) {
            e.printStackTrace();
        }
    }

}