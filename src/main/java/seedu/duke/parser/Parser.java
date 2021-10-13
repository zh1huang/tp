package seedu.duke.parser;

import seedu.duke.command.AddCommand;
import seedu.duke.command.Command;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.EditCommand;
import seedu.duke.command.GetCommand;
import seedu.duke.command.ListCommand;
import seedu.duke.model.ItemContainer;
import seedu.duke.parser.exception.IllegalFormatException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Parser class adapted from
// https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java

/**
 * Parser Class. Manages parsing of user input for different commands.
 */
public class Parser {

    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    public static final Pattern ADD_ITEM_DATA_ARGS_FORMAT =
            Pattern.compile("n/(?<itemName>[^/]+)"
                    + " c/(?<category>[^/]+)"
                    + " p/[$](?<price>([0-9]+([.][0-9]{1,2})?))" //only accepts numbers or decimals in 1 or 2 d.p.
                    + " q/(?<quantity>[0-9]+)" // only accepts numbers, no decimals
                    + "( r/(?<remarks>[^/]+))?$"); // optional argument

    public static final Pattern DELETE_ITEM_DATA_ARGS_FORMAT =
            Pattern.compile("n/(?<itemName>[^/]+)");

    public static final Pattern LIST_ITEM_DATA_ARGS_FORMAT =
            Pattern.compile("(c/(?<category>[^/]+))?"); // optional argument category

    public static final Pattern GET_ITEM_DATA_ARGS_FORMAT =
            Pattern.compile("n/(?<itemName>[^/]+)"
                    + "( p/(?<property>[^/]+))?"); // optional argument property

    public static final Pattern EDIT_ITEM_DATA_ARGS_FORMAT =
            Pattern.compile("n/(?<itemName>[^/]+)"
                    + " p/(?<property>[^/]+)"
                    + " v/(?<value>[^/]+)"
                    + "( s/(?<showResult>[^/]+))?"); // optional argument showResult

    public static final String ADD_ITEM_DATA_ARGS_FORMAT_STRING =
            "add n/NAME c/CATEGORY p/PRICE q/QUANTITY [r/REMARKS]";
    public static final String DELETE_ITEM_DATA_ARGS_FORMAT_STRING = "delete n/NAME";
    public static final String LIST_ITEM_DATA_ARGS_FORMAT_STRING = "list [c/CATEGORY]";
    public static final String GET_ITEM_DATA_ARGS_FORMAT_STRING = "get n/NAME [p/PROPERTY]";
    public static final String EDIT_ITEM_DATA_ARGS_FORMAT_STRING =
            "edit n/NAME p/PROPERTY v/VALUE [s/SHOW_RESULT]";

    public static final String ADD_STRING = "add";
    public static final String DELETE_STRING = "delete";
    public static final String LIST_STRING = "list";
    public static final String GET_STRING = "get";
    public static final String EDIT_STRING = "edit";

    public static final String CORRECT_COMMAND_MESSAGE_STRING_FORMAT =
            "Input invalid command format.\nCorrect format: \n%s\n";
    public static final String PARSE_ADD_SUCCESS_MESSAGE_FORMAT = "name: %s\ncategory: %s\nprice: $%s\n"
            + "quantity: %s\nremarks: %s\n";
    public static final String PARSE_DELETE_SUCCESS_MESSAGE_FORMAT = "name: %s\n";
    public static final String PARSE_LIST_SUCCESS_MESSAGE_FORMAT = "category: %s\n";
    public static final String PARSE_GET_SUCCESS_MESSAGE_FORMAT = "itemName: %s\nproperty: %s\n";

    public static final String INVALID_COMMAND_MESSAGE_STRING = "Invalid command, please try again.";
    public static final String PARSE_SUCCESS_MESSAGE_STRING = "Parsed successful.\n";



    /**
     * Parses the User input line. Checks the user input line against the basic command format
     * and extracts the command word which is the first word in the user input line. After
     * extraction of the command word, pass the remaining user input line arguments to the
     * respective cases depending on the command word.
     *
     * @param userInputLine The user input Line.
     * @return A string indicating parse success or failure.
     */
    public Command parseCommand(String userInputLine, ItemContainer list) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInputLine.trim());

        /* Checks valid basic command format */
        if (!matcher.matches()) {
            throw new IllegalFormatException(INVALID_COMMAND_MESSAGE_STRING);
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        String resultString = "";
        Command command;

        switch (commandWord) {
        case ADD_STRING:
            resultString = prepareAdd(arguments);
            command = new AddCommand("name", "12.55", "13.55"); //placeholders for now
            break;

        case DELETE_STRING:
            resultString = prepareDelete(arguments);
            command = new DeleteCommand("name", list); //placeholders for now
            break;

        case LIST_STRING:
            command = prepareList(arguments);
            break;

        case GET_STRING:
            command = prepareGet(arguments, list);
            break;

        case EDIT_STRING:
            resultString = prepareEdit(arguments);
            command = new EditCommand("name", "12.55", "13.55", list); //placeholders for now
            break;

        default:
            throw new IllegalFormatException(INVALID_COMMAND_MESSAGE_STRING);
        }
        return command;
    }

    /**
     * Parses add command arguments.
     *
     * @param arguments The additional arguments after command word.
     * @return A string indicating parse success or failure.
     */
    private String prepareAdd(String arguments) {
        final Matcher matcher = ADD_ITEM_DATA_ARGS_FORMAT.matcher(arguments.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            return String.format(
                    CORRECT_COMMAND_MESSAGE_STRING_FORMAT, ADD_ITEM_DATA_ARGS_FORMAT_STRING);
        }

        try {
            System.out.println(String.format(PARSE_ADD_SUCCESS_MESSAGE_FORMAT,
                    matcher.group("itemName"), matcher.group("category"),
                    matcher.group("price"), matcher.group("quantity"), matcher.group("remarks")));
            return PARSE_SUCCESS_MESSAGE_STRING;

        } catch (Exception e) {
            return String.format(e.getMessage());
        }
    }

    /**
     * Parses delete command arguments.
     *
     * @param arguments The additional arguments after command word.
     * @return A string indicating parse success or failure.
     */
    private String prepareDelete(String arguments) {
        final Matcher matcher = DELETE_ITEM_DATA_ARGS_FORMAT.matcher(arguments.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            return String.format(
                    CORRECT_COMMAND_MESSAGE_STRING_FORMAT, DELETE_ITEM_DATA_ARGS_FORMAT_STRING);
        }

        try {
            System.out.println(String.format(PARSE_DELETE_SUCCESS_MESSAGE_FORMAT, matcher.group("itemName")));
            return PARSE_SUCCESS_MESSAGE_STRING;
        } catch (Exception e) {
            return (e.getMessage());
        }
    }

    /**
     * Parses list command arguments.
     *
     * @param arguments The additional arguments after command word.
     * @return A string indicating parse success or failure.
     */
    private Command prepareList(String arguments) {
        final Matcher matcher = LIST_ITEM_DATA_ARGS_FORMAT.matcher(arguments.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            throw new IllegalFormatException(String.format(
                    CORRECT_COMMAND_MESSAGE_STRING_FORMAT, LIST_ITEM_DATA_ARGS_FORMAT_STRING));
        }

        return new ListCommand();
    }

    /**
     * Parses get command arguments.
     *
     * @param arguments The additional arguments after command word.
     * @return A string indicating parse success or failure.
     */
    private Command prepareGet(String arguments, ItemContainer list) {
        final Matcher matcher = GET_ITEM_DATA_ARGS_FORMAT.matcher(arguments.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            throw new IllegalFormatException(String.format(
                    CORRECT_COMMAND_MESSAGE_STRING_FORMAT, GET_ITEM_DATA_ARGS_FORMAT_STRING));
        }

        return new GetCommand(matcher.group("itemName"));
    }

    /**
     * Parses edit command arguments.
     *
     * @param arguments The additional arguments after command word.
     * @return A string indicating parse success or failure.
     */
    private String prepareEdit(String arguments) {
        final Matcher matcher = EDIT_ITEM_DATA_ARGS_FORMAT.matcher(arguments.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            return String.format(
                    CORRECT_COMMAND_MESSAGE_STRING_FORMAT, EDIT_ITEM_DATA_ARGS_FORMAT_STRING);
        }

        try {
            System.out.println(String.format("itemName: %s\nproperty: %s\nvalue: %s\nshow result: %s\n",
                    matcher.group("itemName"), matcher.group("property"),
                    matcher.group("value"), matcher.group("showResult")));
            return PARSE_SUCCESS_MESSAGE_STRING;
        } catch (Exception e) {
            return (e.getMessage());
        }
    }
}
