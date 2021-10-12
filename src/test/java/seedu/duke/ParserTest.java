package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

//import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.model.Item;
import seedu.duke.model.ItemContainer;

import java.math.BigDecimal;

// Parser Test class adapted from
// https://github.com/se-edu/addressbook-level2/blob/master/test/java/seedu/addressbook/parser/ParserTest.java
public class ParserTest {

    private Parser parser;

    @BeforeEach
    public void setUp() {
        parser = new Parser(new ItemContainer("test"));
    }


    @Test
    public void parse_EmptyInput_errorMessage() {
        final String emptyInput = "";
        assertEquals(parser.INVALID_COMMAND_MESSAGE_STRING, parser.parseCommand(emptyInput));
    }

    @Test
    public void parse_NotProgramCommand_errorMessage() {
        final String notProgramCommandInput = "blahdsdsh";
        assertEquals(parser.INVALID_COMMAND_MESSAGE_STRING, parser.parseCommand(notProgramCommandInput));
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
                parser.CORRECT_COMMAND_MESSAGE_STRING_FORMAT, parser.ADD_ITEM_DATA_ARGS_FORMAT_STRING);
        for (String input : inputs) {
            assertEquals(expectedErrorOutput, parser.parseCommand(input));
        }
    }

    @Test
    public void parse_addCommandValidArgs_errorMessage() {
        final String[] inputs = {
            "add n/Harry Potter 1 c/books p/$37 q/1",
            "add n/Pilot P100 c/stationary p/$1 q/1 r/Not many people bought this. Can consider a 50% discount."
        };
        for (String input : inputs) {
            assertEquals(parser.PARSE_SUCCESS_MESSAGE_STRING, parser.parseCommand(input));
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
                parser.CORRECT_COMMAND_MESSAGE_STRING_FORMAT, parser.DELETE_ITEM_DATA_ARGS_FORMAT_STRING);
        for (String input : inputs) {
            assertEquals(expectedErrorOutput, parser.parseCommand(input));
        }
    }

    @Test
    public void parse_deleteCommandValidArgs_errorMessage() {
        final String[] inputs = {
            "delete n/Alice in wonderland",
            "delete n/Stabilo colour pencil"
        };
        for (String input : inputs) {
            assertEquals(parser.PARSE_SUCCESS_MESSAGE_STRING, parser.parseCommand(input));
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
                parser.CORRECT_COMMAND_MESSAGE_STRING_FORMAT, parser.LIST_ITEM_DATA_ARGS_FORMAT_STRING);
        for (String input : inputs) {
            assertEquals(expectedErrorOutput, parser.parseCommand(input));
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
            assertEquals(parser.PARSE_SUCCESS_MESSAGE_STRING, parser.parseCommand(input));
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
                parser.CORRECT_COMMAND_MESSAGE_STRING_FORMAT, parser.GET_ITEM_DATA_ARGS_FORMAT_STRING);
        for (String input : inputs) {
            assertEquals(expectedErrorOutput, parser.parseCommand(input));
        }
    }

    @Test
    public void parse_getCommandValidArgs_errorMessage() {
        final String[] inputs = {
            "get n/Lord of theRings",
            "get n/Apples Never Fall p/quantity"
        };
        for (String input : inputs) {
            assertEquals(parser.PARSE_SUCCESS_MESSAGE_STRING, parser.parseCommand(input));
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
                parser.CORRECT_COMMAND_MESSAGE_STRING_FORMAT, parser.EDIT_ITEM_DATA_ARGS_FORMAT_STRING);
        for (String input : inputs) {
            assertEquals(expectedErrorOutput, parser.parseCommand(input));
        }
    }

    @Test
    public void parse_editCommandValidArgs_errorMessage() {
        final String[] inputs = {
            "edit n/Lord of the Rings p/price v/30",
            "edit n/Apples Never Fall p/quantity v/100 s/false",
            "edit n/Apples Never Fall p/quantity v/100 s/true"
        };
        for (String input : inputs) {
            assertEquals(parser.PARSE_SUCCESS_MESSAGE_STRING, parser.parseCommand(input));
        }
    }
}