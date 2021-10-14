package seedu.duke.parser;

import seedu.duke.command.AddCommand;
import seedu.duke.command.Command;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.EditCommand;
import seedu.duke.command.GetCommand;
import seedu.duke.command.ListCommand;
import seedu.duke.model.ItemContainer;
import seedu.duke.model.exception.ItemNotExistException;
import seedu.duke.parser.exception.NoPropertyFoundException;
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
                    + " p/[$](?<purchaseCost>([0-9]+([.][0-9]{1,2})?))"
                    //only accepts numbers or decimals in 1 or 2 d.p.
                    + " s/[$](?<sellingPrice>([0-9]+([.][0-9]{1,2})?))"
                    //only accepts numbers or decimals in 1 or 2 d.p.
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
                    + " v/[$](?<value>([0-9]+([.][0-9]{1,2})?))"
                    + "( s/(?<showResult>[^/]+))?"); // optional argument showResult

    public static final String ADD_ITEM_DATA_ARGS_FORMAT_STRING =
            "add n/NAME c/CATEGORY p/PURCHASE_COST s/SELLING_PRICE q/QUANTITY [r/REMARKS]";
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
     * @param userInputLine The user input Line
     * @param list The itemContainer used to prepare the command
     * @return A string indicating parse success or failure
     */
    public Command parseCommand(String userInputLine, ItemContainer list) throws IllegalFormatException,
            ItemNotExistException, NoPropertyFoundException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInputLine.trim());

        /* Checks valid basic command format */
        if (!matcher.matches()) {
            throw new IllegalFormatException(INVALID_COMMAND_MESSAGE_STRING);
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        //String resultString = "";
        Command command;

        switch (commandWord) {
        case ADD_STRING:
            command = prepareAdd(arguments);
            break;

        case DELETE_STRING:
            command = prepareDelete(arguments);
            break;

        case LIST_STRING:
            command = prepareList(arguments);
            break;

        case GET_STRING:
            command = prepareGet(arguments, list);
            break;

        case EDIT_STRING:
            command = prepareEdit(arguments, list);
            break;


        default:
            throw new IllegalFormatException(INVALID_COMMAND_MESSAGE_STRING);
        }
        return command;
    }

    /**
     * Parses add command arguments.
     *
     * @param arguments The additional arguments after command word
     * @return A string indicating parse success or failure
     * @throws IllegalFormatException when the input format is wrong
     */
    private Command prepareAdd(String arguments) throws IllegalFormatException {
        final Matcher matcher = ADD_ITEM_DATA_ARGS_FORMAT.matcher(arguments.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            throw new IllegalFormatException(String.format(
                    CORRECT_COMMAND_MESSAGE_STRING_FORMAT, ADD_ITEM_DATA_ARGS_FORMAT_STRING));
        }
        String itemName = matcher.group("itemName");
        String purchaseCost = matcher.group("purchaseCost");
        String sellingPrice = matcher.group("sellingPrice");
        return new AddCommand(itemName, purchaseCost, sellingPrice);
    }

    /**
     * Parses delete command arguments.
     *
     * @param arguments The additional arguments after command word
     * @return A string indicating parse success or failure
     * @throws IllegalFormatException when the input format is wrong
     */
    private Command prepareDelete(String arguments) throws IllegalFormatException {
        final Matcher matcher = DELETE_ITEM_DATA_ARGS_FORMAT.matcher(arguments.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            throw new IllegalFormatException(String.format(
                    CORRECT_COMMAND_MESSAGE_STRING_FORMAT, DELETE_ITEM_DATA_ARGS_FORMAT_STRING));
        }
        String itemName = matcher.group("itemName");
        return new DeleteCommand(itemName);
    }

    /**
     * Parses list command arguments.
     *
     * @param arguments The additional arguments after command word.
     * @return A string indicating parse success or failure.
     * @throws IllegalFormatException when the input format is wrong
     */
    private Command prepareList(String arguments) throws IllegalFormatException {
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
     * @throws IllegalFormatException when the input format is wrong
     */
    private Command prepareGet(String arguments, ItemContainer list) throws IllegalFormatException {
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
     * @throws IllegalFormatException when the input format is wrong
     */
    private Command prepareEdit(String arguments, ItemContainer list) throws IllegalFormatException,
        ItemNotExistException, NoPropertyFoundException {
        final Matcher matcher = EDIT_ITEM_DATA_ARGS_FORMAT.matcher(arguments.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            throw new IllegalFormatException(String.format(
                CORRECT_COMMAND_MESSAGE_STRING_FORMAT, EDIT_ITEM_DATA_ARGS_FORMAT_STRING));
        }
        String itemName = matcher.group("itemName");
        String selectedProperty = matcher.group("property");
        String newValue = matcher.group("value");

        if (selectedProperty.equals("purchaseCost")) {
            String updatedPurchaseCost = newValue;
            String updatedSellingPrice = list.getItem(itemName).getSellingPrice();
            return new EditCommand(itemName, updatedPurchaseCost, updatedSellingPrice);
        } else if (selectedProperty.equals("sellingPrice")) {
            String updatedPurchaseCost = list.getItem(itemName).getPurchaseCost();
            String updatedSellingPrice = newValue;
            return new EditCommand(itemName, updatedPurchaseCost, updatedSellingPrice);
        } else {
            throw new NoPropertyFoundException(selectedProperty);
        }
    }
}
