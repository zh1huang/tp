package seedu.duke.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.duke.command.AddCommand;
import seedu.duke.command.Command;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.EditCommand;
import seedu.duke.command.GetCommand;
import seedu.duke.command.ListCommand;
import seedu.duke.model.ShelfList;
import seedu.duke.model.Item;
import seedu.duke.model.Shelf;
import seedu.duke.model.exception.DuplicateShelfException;
import seedu.duke.model.exception.DuplicateItemException;
import seedu.duke.model.exception.IllegalArgumentException;
import seedu.duke.model.exception.ItemNotExistException;
import seedu.duke.parser.exception.NoPropertyFoundException;
import seedu.duke.parser.exception.IllegalFormatException;
import seedu.duke.parser.Parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Parser Test class adapted from
// https://github.com/se-edu/addressbook-level2/blob/master/test/java/seedu/addressbook/parser/ParserTest.java
public class ParserTest {

    public static final String PURCHASE_COST_PROPERTY_STRING = "purchaseCost";
    public static final String SELLING_PRICE_PROPERTY_STRING = "sellingPrice";

    public static final String ITEM_NAME_EXAMPLE_1 = "Geronimo";
    public static final String CATEGORY_EXAMPLE_1 = "books";
    public static final String PURCHASE_COST_EXAMPLE_1 = "25";
    public static final String SELLING_PRICE_EXAMPLE_1 = "30.99";
    public static final String VALUE_EXAMPLE_1 = "3.45";
    public static final String QUANTITY_EXAMPLE_1 = "1";
    public static final String REMARKS_EXAMPLE_1 = "Hello World!";

    public static final String ITEM_NAME_EXAMPLE_2 = "Mechanical Pencil";
    public static final String CATEGORY_EXAMPLE_2 = "Stationary";
    public static final String PURCHASE_COST_EXAMPLE_2 = "0.70";
    public static final String SELLING_PRICE_EXAMPLE_2 = "1.90";
    public static final String VALUE_EXAMPLE_2 = "3.4";
    public static final String QUANTITY_EXAMPLE_2 = "100";
    public static final String REMARKS_EXAMPLE_2 = "need restock!";

    public static final String ADD_STRING = "add";
    public static final String DELETE_STRING = "delete";
    public static final String LIST_STRING = "list";
    public static final String GET_STRING = "get";
    public static final String EDIT_STRING = "edit";

    public static final String WHITESPACE = "\\s";
    private Parser parser;
    private Shelf testShelf;

    @BeforeEach
    public void setUp() throws IllegalArgumentException, DuplicateShelfException {
        parser = new Parser();
        ShelfList.getShelfList().resetShelfList();
        testShelf = new Shelf("test");
    }

    @Test
    public void parse_EmptyInput_throwsIllegalFormatException() {
        final String emptyInput = "";
        assertThrows(IllegalFormatException.class, () -> parser.parseCommand(emptyInput, testShelf));
    }

    @Test
    public void parse_NotProgramCommand_throwsIllegalFormatException() {
        final String notProgramCommandInput = "blahdsdsh";
        assertThrows(IllegalFormatException.class, () -> parser.parseCommand(notProgramCommandInput, testShelf));
    }

    /*
     * Tests for add command ===============================================================
     */

    /**
    @Test
    public void parse_addCommandInvalidArgs_throwsIllegalFormatException() {
        //throws IllegalFormatException in parser package: todo decide whether to merge exceptions into one package

        final String[] inputs = {
            ADD_STRING,
            ADD_STRING + WHITESPACE + CATEGORY_EXAMPLE_1 + WHITESPACE + QUANTITY_EXAMPLE_1,
            ADD_STRING + WHITESPACE + PURCHASE_COST_EXAMPLE_1 + WHITESPACE + CATEGORY_EXAMPLE_1,
            ADD_STRING + WHITESPACE + QUANTITY_EXAMPLE_1 + WHITESPACE + PURCHASE_COST_EXAMPLE_1,
            ADD_STRING + WHITESPACE + CATEGORY_EXAMPLE_1 + WHITESPACE + PURCHASE_COST_EXAMPLE_1
                + WHITESPACE + SELLING_PRICE_EXAMPLE_1,
            ADD_STRING + WHITESPACE + CATEGORY_EXAMPLE_1 + WHITESPACE + PURCHASE_COST_EXAMPLE_1
                + WHITESPACE + QUANTITY_EXAMPLE_1
        };

        for (String input : inputs) {
            assertThrows(IllegalFormatException.class, () -> parser.parseCommand(input, testShelf));
        }
    }

    @Test
    public void parse_addCommandValidArgs_returnsAddCommand() throws ItemNotExistException,
        NoPropertyFoundException, IllegalFormatException {

        String input1 = ADD_STRING + " n/" + ITEM_NAME_EXAMPLE_1 + " c/" + CATEGORY_EXAMPLE_1 + " p/"
            + PURCHASE_COST_EXAMPLE_1 + " s/" + SELLING_PRICE_EXAMPLE_1 + " q/" + QUANTITY_EXAMPLE_1;

        AddCommand expectedCommand1 = new AddCommand(ITEM_NAME_EXAMPLE_1,
            PURCHASE_COST_EXAMPLE_1, SELLING_PRICE_EXAMPLE_1, QUANTITY_EXAMPLE_1, testShelf);

        assertEquals(expectedCommand1, parser.parseCommand(input1, testShelf));

        String input2 = ADD_STRING + " n/" + ITEM_NAME_EXAMPLE_2 + " c/" + CATEGORY_EXAMPLE_2 + " p/"
            + PURCHASE_COST_EXAMPLE_2 + " s/" + SELLING_PRICE_EXAMPLE_2 + " q/" + QUANTITY_EXAMPLE_2;

        AddCommand expectedCommand2 = new AddCommand(ITEM_NAME_EXAMPLE_2,
            PURCHASE_COST_EXAMPLE_2, SELLING_PRICE_EXAMPLE_2, QUANTITY_EXAMPLE_1, testShelf);

        assertEquals(expectedCommand2, parser.parseCommand(input2, testShelf));
    }
    */

    /*
     * Tests for delete command ===============================================================
     */

    /**
    @Test
    public void parse_deleteCommandInvalidArgs_throwsIllegalFormatException() {
        final String[] inputs = {
            "delete ",
            "delete p/37",
            "delete q/37"
        };

        for (String input : inputs) {
            assertThrows(IllegalFormatException.class, () -> parser.parseCommand(input, testShelf));
        }
    }

    @Test
    public void parse_deleteCommandValidArgs_returnsDeleteCommand() throws IllegalFormatException,
        ItemNotExistException, NoPropertyFoundException {

        String input1 = DELETE_STRING + " n/" + ITEM_NAME_EXAMPLE_1 + " q/" + QUANTITY_EXAMPLE_1;

        Command expectedCommand1 = new DeleteCommand(ITEM_NAME_EXAMPLE_1, QUANTITY_EXAMPLE_1, testShelf);
        assertEquals(expectedCommand1, parser.parseCommand(input1, testShelf));

        String input2 = DELETE_STRING + " n/" + ITEM_NAME_EXAMPLE_2 + " q/" + QUANTITY_EXAMPLE_2;

        Command expectedCommand2 = new DeleteCommand(ITEM_NAME_EXAMPLE_2, QUANTITY_EXAMPLE_2, testShelf);
        assertEquals(expectedCommand2, parser.parseCommand(input2, testShelf));
    }
    */
    /*
     * Tests for list command ===============================================================
     */

    /**
    @Test
    public void parse_listCommandInvalidArgs_throwsIllegalFormatException() {
        final String[] inputs = {
            "list p/223",
            "list p/dme",
            "list r/idmwk "
        };

        for (String input : inputs) {
            assertThrows(IllegalFormatException.class, () -> parser.parseCommand(input, testShelf));
        }
    }

    @Test
    public void parse_listCommandValidArgs_returnsListCommand() throws IllegalFormatException,
        ItemNotExistException, NoPropertyFoundException {

        Command expectedCommand = new ListCommand(testShelf);
        assertEquals(expectedCommand, parser.parseCommand(LIST_STRING, testShelf));
    }
    */

    /*
     * Tests for get command ===============================================================
     */

    /**
    @Test
    public void parse_getCommandInvalidArgs_throwsIllegalFormatException() {
        final String[] inputs = {
            "get ",
            "get p/quantity"
        };

        for (String input : inputs) {
            assertThrows(IllegalFormatException.class, () -> parser.parseCommand(input, testShelf));
        }
    }

    @Test
    public void parse_getCommandValidArgs_returnsGetCommand() throws IllegalFormatException,
        ItemNotExistException, NoPropertyFoundException {
        String input1 = GET_STRING + " n/" + ITEM_NAME_EXAMPLE_1;

        Command expectedCommand1 = new GetCommand(ITEM_NAME_EXAMPLE_1, testShelf);
        assertEquals(expectedCommand1, parser.parseCommand(input1, testShelf));

        String input2 = GET_STRING + " n/" + ITEM_NAME_EXAMPLE_2;

        Command expectedCommand2 = new GetCommand(ITEM_NAME_EXAMPLE_2, testShelf);
        assertEquals(expectedCommand2, parser.parseCommand(input2, testShelf));
    }
    */

    /*
     * Tests for edit command ===============================================================
     */

    /*
    @Test
    public void parse_editCommandInvalidArgs_throwsIllegalFormatException() throws
        IllegalArgumentException, DuplicateItemException {
        final String[] inputs = {
            "edit ",
            "edit n/Apples Never Fall ",
            "edit v/hahaha s/true"
        };

        for (String input : inputs) {
            assertThrows(IllegalFormatException.class, () -> parser.parseCommand(input, testShelf));
        }
    }

    @Test
    public void parse_editCommandValidArgs_returnsEditCommand() throws
        ItemNotExistException, NoPropertyFoundException, IllegalFormatException,
        IllegalArgumentException, DuplicateItemException {

        // add item to container first
        Item newItem1 = new Item(ITEM_NAME_EXAMPLE_1, PURCHASE_COST_EXAMPLE_1, SELLING_PRICE_EXAMPLE_1);
        Item newItem2 = new Item(ITEM_NAME_EXAMPLE_2, PURCHASE_COST_EXAMPLE_2, SELLING_PRICE_EXAMPLE_2);
        testShelf.addItem(newItem1);
        testShelf.addItem(newItem2);

        // Test edit Purchase Cost
        String input1 = EDIT_STRING + " n/" + ITEM_NAME_EXAMPLE_1 + " p/" + PURCHASE_COST_PROPERTY_STRING
            + " v/" + VALUE_EXAMPLE_1;
        Command expectedCommand1 = new EditCommand(ITEM_NAME_EXAMPLE_1, PURCHASE_COST_PROPERTY_STRING, VALUE_EXAMPLE_1,
                testShelf);
        assertEquals(expectedCommand1.getClass(), parser.parseCommand(input1, testShelf).getClass()); //todo remove getclass

        // Test edit Selling Price
        String input2 = EDIT_STRING + " n/" + ITEM_NAME_EXAMPLE_2 + " p/" + SELLING_PRICE_PROPERTY_STRING
            + " v/" + VALUE_EXAMPLE_2;
        Command expectedCommand2 = new EditCommand(ITEM_NAME_EXAMPLE_2, SELLING_PRICE_PROPERTY_STRING, VALUE_EXAMPLE_2,
                testShelf);
        assertEquals(expectedCommand2.getClass(), parser.parseCommand(input2, testShelf).getClass()); //todo remove getclass
    }
    */

}