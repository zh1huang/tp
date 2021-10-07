package seedu.duke;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Parser class adapted from https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java
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

    public static final String INVALID_COMMAND_MESSAGE_STRING = "Invalid command, please try again.";
    public static final String CORRECT_COMMAND_MESSAGE_STRING_FORMAT =
            "Input invalid command format.\nCorrect format: \n%s\n";

    /**
     * Parses the User input line.
     *
     * @param userInputLine The user input Line.
     */
    public static void parseCommand(String userInputLine){
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInputLine.trim());
        if (!matcher.matches()) {
            System.out.println(INVALID_COMMAND_MESSAGE_STRING);
            return;
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {

        case ADD_STRING:
            prepareAdd(arguments);
            break;

        case DELETE_STRING:
            prepareDelete(arguments);
            break;

        case LIST_STRING:
            prepareList(arguments);
            break;

        case GET_STRING:
            prepareGet(arguments);
            break;

        case EDIT_STRING:
            prepareEdit(arguments);
            break;

        default:
            System.out.println(INVALID_COMMAND_MESSAGE_STRING);
        }
    }

    /**
     * Parses add command arguments.
     *
     * @param arguments The additional arguments after command word.
     */
    private static void prepareAdd(String arguments) {
        final Matcher matcher = ADD_ITEM_DATA_ARGS_FORMAT.matcher(arguments.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            System.out.println(String.format(
                    CORRECT_COMMAND_MESSAGE_STRING_FORMAT, ADD_ITEM_DATA_ARGS_FORMAT_STRING));
            return;
        }

        try {
            System.out.println(String.format("name: %s", matcher.group("itemName")));
            System.out.println(String.format("category: %s", matcher.group("category")));
            System.out.println(String.format("price: $%s", matcher.group("price")));
            System.out.println(String.format("quantity: %s", matcher.group("quantity")));
            System.out.println(String.format("remarks: %s", matcher.group("remarks")));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Parses delete command arguments.
     *
     * @param arguments The additional arguments after command word.
     */
    private static void prepareDelete(String arguments) {
        final Matcher matcher = DELETE_ITEM_DATA_ARGS_FORMAT.matcher(arguments.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            System.out.println(String.format(
                    CORRECT_COMMAND_MESSAGE_STRING_FORMAT, DELETE_ITEM_DATA_ARGS_FORMAT_STRING));
            return;
        }

        try {
            System.out.println(String.format("name: %s", matcher.group("itemName")));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Parses list command arguments.
     *
     * @param arguments The additional arguments after command word.
     */
    private static void prepareList(String arguments) {
        final Matcher matcher = LIST_ITEM_DATA_ARGS_FORMAT.matcher(arguments.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            System.out.println(String.format(
                    CORRECT_COMMAND_MESSAGE_STRING_FORMAT, LIST_ITEM_DATA_ARGS_FORMAT_STRING));
            return;
        }

        try {
            System.out.println(String.format("category: %s", matcher.group("category")));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Parses get command arguments.
     *
     * @param arguments The additional arguments after command word.
     */
    private static void prepareGet(String arguments) {
        final Matcher matcher = GET_ITEM_DATA_ARGS_FORMAT.matcher(arguments.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            System.out.println(String.format(
                    CORRECT_COMMAND_MESSAGE_STRING_FORMAT, GET_ITEM_DATA_ARGS_FORMAT_STRING));
            return;
        }

        try {
            System.out.println(String.format("name: %s", matcher.group("itemName")));
            System.out.println(String.format("property: %s", matcher.group("property")));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Parses edit command arguments.
     *
     * @param arguments The additional arguments after command word.
     */
    private static void prepareEdit(String arguments) {
        final Matcher matcher = EDIT_ITEM_DATA_ARGS_FORMAT.matcher(arguments.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            System.out.println(String.format(
                    CORRECT_COMMAND_MESSAGE_STRING_FORMAT, EDIT_ITEM_DATA_ARGS_FORMAT_STRING));
            return;
        }

        try {
            System.out.println(String.format("name: %s", matcher.group("itemName")));
            System.out.println(String.format("property: %s", matcher.group("property")));
            System.out.println(String.format("value: %s", matcher.group("value")));
            System.out.println(String.format("show result: %s", matcher.group("showResult")));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
