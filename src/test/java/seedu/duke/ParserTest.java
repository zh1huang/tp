package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.Command;
import seedu.duke.model.Item;
import seedu.duke.model.ItemContainer;
import seedu.duke.model.exception.IllegalFormatException;


// Parser Test class adapted from
// https://github.com/se-edu/addressbook-level2/blob/master/test/java/seedu/addressbook/parser/ParserTest.java
public class ParserTest {

    private Parser parser;
    private ItemContainer list;

    @BeforeEach
    public void setUp() {
        parser = new Parser();
        list = new ItemContainer("test");
    }


    @Test
    public void parse_EmptyInput_errorMessage() {
        final String emptyInput = "";
        assertThrows(IllegalFormatException.class, () -> parser.parseCommand(emptyInput, list));
    }

    @Test
    public void parse_NotProgramCommand_errorMessage() {
        final String notProgramCommandInput = "blahdsdsh";
        assertThrows(IllegalFormatException.class, () -> parser.parseCommand(notProgramCommandInput, list));
    }

    /*
     * Tests for add command ===============================================================
     */

    @Test
    public void parse_addCommandInvalidArgs_errorMessage() {
        final String[] inputs = {
            "add ",
            "add c/books p/$37 q/1",
            "add n/Harry Potter 1 c/books "
        };

        final String expectedErrorOutput = String.format(
            Parser.CORRECT_COMMAND_MESSAGE_STRING_FORMAT, Parser.ADD_ITEM_DATA_ARGS_FORMAT_STRING);
        for (String input : inputs) {
            Command expected = parser.parseCommand(input, list);
            assertEquals(expected, expected); //placeholder for now: todo equals method for comparing classes
        }
    }

    @Test
    public void parse_addCommandValidArgs_errorMessage() {
        final String[] inputs = {
            "add n/Harry Potter 1 c/books p/$37 q/1",
            "add n/Pilot P100 c/stationary p/$1 q/1 r/Not many people bought this. Can consider a 50% discount."
        };
        for (String input : inputs) {
            Command expected = parser.parseCommand(input, list);
            assertEquals(expected, expected); //placeholder for now: todo equals method for comparing classes
        }
    }

    /*
     * Tests for delete command ===============================================================
     */

    @Test
    public void parse_deleteCommandInvalidArgs_errorMessage() {
        final String[] inputs = {
            "delete ",
            "delete p/$37",
            "delete q/37"
        };

        final String expectedErrorOutput = String.format(
            Parser.CORRECT_COMMAND_MESSAGE_STRING_FORMAT, Parser.DELETE_ITEM_DATA_ARGS_FORMAT_STRING);
        list.addItem(new Item("name", "12.55", "13.55"));
        for (String input : inputs) {
            Command expected = parser.parseCommand(input, list);
            assertEquals(expected, expected); //placeholder for now: todo equals method for comparing classes
        }
    }

    @Test
    public void parse_deleteCommandValidArgs_errorMessage() {
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
    public void parse_listCommandInvalidArgs_errorMessage() {
        final String[] inputs = {
            "list p/223",
            "list p/dme",
            "list r/idmwk "
        };

        final String expectedErrorOutput = String.format(
            Parser.CORRECT_COMMAND_MESSAGE_STRING_FORMAT, Parser.LIST_ITEM_DATA_ARGS_FORMAT_STRING);
        for (String input : inputs) {
            assertThrows(IllegalFormatException.class, () -> parser.parseCommand(input, list));
        }
    }

    @Test
    public void parse_listCommandValidArgs_errorMessage() {
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
    public void parse_getCommandInvalidArgs_errorMessage() {
        final String[] inputs = {
            "get ",
            "get p/quantity"
        };

        final String expectedErrorOutput = String.format(
            Parser.CORRECT_COMMAND_MESSAGE_STRING_FORMAT, Parser.GET_ITEM_DATA_ARGS_FORMAT_STRING);
        for (String input : inputs) {
            assertThrows(IllegalFormatException.class, () -> parser.parseCommand(input, list));
        }
    }

    @Test
    public void parse_getCommandValidArgs_errorMessage() {
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
    public void parse_editCommandInvalidArgs_errorMessage() {
        final String[] inputs = {
            "edit ",
            "edit n/Apples Never Fall ",
            "edit v/hahaha s/true"
        };

        final String expectedErrorOutput = String.format(
            Parser.CORRECT_COMMAND_MESSAGE_STRING_FORMAT, Parser.EDIT_ITEM_DATA_ARGS_FORMAT_STRING);
        list.addItem(new Item("name", "12.55", "13.55"));
        for (String input : inputs) {
            Command expected = parser.parseCommand(input, list);
            assertEquals(expected, expected); //placeholder for now: todo equals method for comparing classes
        }
    }

    @Test
    public void parse_editCommandValidArgs_errorMessage() {
        final String[] inputs = {
            "edit n/Lord of the Rings p/price v/30",
            "edit n/Apples Never Fall p/quantity v/100 s/false",
            "edit n/Apples Never Fall p/quantity v/100 s/true"
        };
        list.addItem(new Item("name", "12.55", "13.55"));
        for (String input : inputs) {
            Command expected = parser.parseCommand(input, list);
            assertEquals(expected, expected); //placeholder for now: todo equals method for comparing classes
        }
    }
}