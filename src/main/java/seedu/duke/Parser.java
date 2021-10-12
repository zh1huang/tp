package seedu.duke;

import seedu.duke.command.GetCommand;
import seedu.duke.command.ListCommand;
import seedu.duke.model.ItemContainer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Parser class adapted from
// https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java

/**
 * Parser Class. Manages parsing of user input for different commands.
 */
public class Parser {

    private ItemContainer items;

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

    public Parser(ItemContainer list) {
        items = list;
    }

    /**
     * Parses the User input line. Checks the user input line against the basic command format
     * and extracts the command word which is the first word in the user input line. After
     * extraction of the command word, pass the remaining user input line arguments to the
     * respective cases depending on the command word.
     *
     * @param userInputLine The user input Line.
     * @return A string indicating parse success or failure.
     */
    public static String parseCommand(String userInputLine) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInputLine.trim());

        /* Checks valid basic command format */
        if (!matcher.matches()) {
            return INVALID_COMMAND_MESSAGE_STRING;
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        String resultString = "";


        switch (commandWord) {
        case ADD_STRING:
            resultString = prepareAdd(arguments);
            break;

        case DELETE_STRING:
            resultString = prepareDelete(arguments);
            break;

        case LIST_STRING:
            resultString = prepareList(arguments);

            break;

        case GET_STRING:
            resultString = prepareGet(arguments);
            break;

        case EDIT_STRING:
            resultString = prepareEdit(arguments);
            break;

        default:
            return INVALID_COMMAND_MESSAGE_STRING;
        }

        return resultString;
    }

    /**
     * Parses add command arguments.
     *
     * @param arguments The additional arguments after command word.
     * @return A string indicating parse success or failure.
     */
    private static String prepareAdd(String arguments) {
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
    private static String prepareDelete(String arguments) {
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
    private static String prepareList(String arguments) {
        final Matcher matcher = LIST_ITEM_DATA_ARGS_FORMAT.matcher(arguments.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            return String.format(
                    CORRECT_COMMAND_MESSAGE_STRING_FORMAT, LIST_ITEM_DATA_ARGS_FORMAT_STRING);
        }

        try {
            //todo check category
            System.out.println(String.format(PARSE_LIST_SUCCESS_MESSAGE_FORMAT, matcher.group("category")));

            return PARSE_SUCCESS_MESSAGE_STRING;
        } catch (Exception e) {
            return (e.getMessage());
        }
    }

    /**
     * Parses get command arguments.
     *
     * @param arguments The additional arguments after command word.
     * @return A string indicating parse success or failure.
     */
    private static String prepareGet(String arguments) {
        final Matcher matcher = GET_ITEM_DATA_ARGS_FORMAT.matcher(arguments.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            return String.format(
                    CORRECT_COMMAND_MESSAGE_STRING_FORMAT, GET_ITEM_DATA_ARGS_FORMAT_STRING);
        }

        try {
            System.out.println(String.format(PARSE_GET_SUCCESS_MESSAGE_FORMAT,
                    matcher.group("itemName"), matcher.group("property")));

            String name = matcher.group("itemName");
            //GetCommand getcommand = new GetCommand(name, Duke.container);
            //getcommand.execute(Duke.container);

            return PARSE_SUCCESS_MESSAGE_STRING;
        } catch (Exception e) {
            return (e.getMessage());
        }
    }

    /**
     * Parses edit command arguments.
     *
     * @param arguments The additional arguments after command word.
     * @return A string indicating parse success or failure.
     */
    private static String prepareEdit(String arguments) {
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
