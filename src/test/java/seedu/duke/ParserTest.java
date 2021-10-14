package seedu.duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.duke.command.Command;
import seedu.duke.model.Item;
import seedu.duke.model.ItemContainer;
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

    public static final String WHITESPACE = " ";
    private Parser parser;
    private ItemContainer list;

    @BeforeEach
    public void setUp() throws IllegalArgumentException {
        parser = new Parser();
        list = new ItemContainer("test");
    }

    @Test
    public void parse_EmptyInput_throwsIllegalFormatException() {
        final String emptyInput = "";
        assertThrows(IllegalFormatException.class, () -> parser.parseCommand(emptyInput, list));
    }

    @Test
    public void parse_NotProgramCommand_throwsIllegalFormatException() {
        final String notProgramCommandInput = "blahdsdsh";
        assertThrows(IllegalFormatException.class, () -> parser.parseCommand(notProgramCommandInput, list));
    }

    /*
     * Tests for add command ===============================================================
     */

    @Test
    public void parse_addCommandInvalidArgs_throwsIllegalFormatException() {
        //throws IllegalFormatException in parser package: todo decide whether to merge exceptions into one package
        String addString = "add";
        String itemName = "c/books";
        String purchasePrice = "p/$25";
        String sellingPrice = "p/$30.99";
        String quantity = "q/1";

        final String[] inputs = {
            addString,
            addString + WHITESPACE + itemName + WHITESPACE + quantity,
            addString + WHITESPACE + purchasePrice + WHITESPACE + itemName,
            addString + WHITESPACE + quantity + WHITESPACE + purchasePrice,
            addString + WHITESPACE + itemName + WHITESPACE  + purchasePrice + WHITESPACE + sellingPrice,
            addString + WHITESPACE + itemName + WHITESPACE  + purchasePrice + WHITESPACE + quantity
        };

        for (String input : inputs) {
            assertThrows(IllegalFormatException.class, () -> parser.parseCommand(input, list));
        }
    }

    @Test
    public void parse_addCommandValidArgs_returnsAddCommand() {

        final String[] inputs = {
            "add n/Harry Potter 1 c/books p/$37 s/$50 q/1",
            "add n/Pilot P100 c/stationary p/$37 s/$50 q/1 r/Not many people bought this. Consider a 50% discount."
        };
        for (String input : inputs) {
            //placeholder for now: todo equals method for comparing classes
            assertTrue(true);
        }
    }

    /*
     * Tests for delete command ===============================================================
     */

    @Test
    public void parse_deleteCommandInvalidArgs_throwsIllegalFormatException() {
        final String[] inputs = {
            "delete ",
            "delete p/$37",
            "delete q/37"
        };

        //list.addItem(new Item("name", "12.55", "13.55"));
        for (String input : inputs) {
            assertThrows(IllegalFormatException.class, () -> parser.parseCommand(input, list));
        }
    }

    @Test
    public void parse_deleteCommandValidArgs_returnsDeleteCommand() throws IllegalFormatException, IllegalArgumentException,
            DuplicateItemException, ItemNotExistException, NoPropertyFoundException {
        final String[] inputs = {
            "delete n/Alice in wonderland",
            "delete n/Stabilo colour pencil"
        };
        list.addItem(new Item("name", "12.55", "13.55"));
        for (String input : inputs) {
            Command expected = parser.parseCommand(input, list);
            assertEquals(expected, expected); //placeholder for now: todo equals method for comparing classes
        }
    }

    /*
     * Tests for list command ===============================================================
     */


    @Test
    public void parse_listCommandInvalidArgs_throwsIllegalFormatException() {
        final String[] inputs = {
            "list p/223",
            "list p/dme",
            "list r/idmwk "
        };

        for (String input : inputs) {
            assertThrows(IllegalFormatException.class, () -> parser.parseCommand(input, list));
        }
    }

    @Test
    public void parse_listCommandValidArgs_returnsListCommand() throws IllegalFormatException,
            ItemNotExistException, NoPropertyFoundException {
        final String[] inputs = {
            "list",
            "list c/all",
            "list c/stationary "
        };

        for (String input : inputs) {
            Command expected = parser.parseCommand(input, list);
            assertEquals(expected, expected); //placeholder for now: todo equals method for comparing classes
        }
    }

    /*
     * Tests for get command ===============================================================
     */

    @Test
    public void parse_getCommandInvalidArgs_throwsIllegalFormatException() {
        final String[] inputs = {
            "get ",
            "get p/quantity"
        };

        for (String input : inputs) {
            assertThrows(IllegalFormatException.class, () -> parser.parseCommand(input, list));
        }
    }

    @Test
    public void parse_getCommandValidArgs_returnsGetCommand() throws IllegalFormatException,
            ItemNotExistException, NoPropertyFoundException {
        final String[] inputs = {
            "get n/Lord of the Rings",
            "get n/Apples Never Fall p/quantity"
        };
        for (String input : inputs) {
            Command expected = parser.parseCommand(input, list);
            assertEquals(expected, expected); //placeholder for now: todo equals method for comparing classes
        }
    }

    /*
     * Tests for edit command ===============================================================
     */

    @Test
    public void parse_editCommandInvalidArgs_throwsIllegalFormatException() throws IllegalArgumentException, DuplicateItemException {
        final String[] inputs = {
            "edit ",
            "edit n/Apples Never Fall ",
            "edit v/hahaha s/true"
        };

        //list.addItem(new Item("name", "12.55", "13.55"));
        for (String input : inputs) {
            //placeholder for now: todo equals method for comparing classes
            assertThrows(IllegalFormatException.class, () -> parser.parseCommand(input, list));
        }
    }

    @Test
    public void parse_editCommandValidArgs_returnsEditCommand() throws
        IllegalArgumentException, DuplicateItemException {
        final String[] inputs = {
            "edit n/Lord of the Rings p/price v/30",
            "edit n/Apples Never Fall p/quantity v/100 s/false",
            "edit n/Apples Never Fall p/quantity v/100 s/true"
        };
        list.addItem(new Item("name", "12.55", "13.55"));
        for (String input : inputs) {
            assertTrue(true); //placeholder for now: todo equals method for comparing classes
        }
    }

}