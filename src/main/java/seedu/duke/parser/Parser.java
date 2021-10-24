package seedu.duke.parser;

import seedu.duke.command.AddCommand;
import seedu.duke.command.Command;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.EditCommand;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.GetCommand;
import seedu.duke.command.HelpCommand;
import seedu.duke.command.ListCommand;
import seedu.duke.model.Shelf;
import seedu.duke.model.exception.ItemNotExistException;
import seedu.duke.parser.exception.NoPropertyFoundException;
import seedu.duke.parser.exception.IllegalFormatException;

import java.util.logging.Level;
import java.util.logging.Logger;
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
                    + " p/(?<purchaseCost>([0-9]+([.][0-9]{1,2})?))"
                    //only accepts numbers or decimals in 1 or 2 d.p.
                    + " s/(?<sellingPrice>([0-9]+([.][0-9]{1,2})?))"
                    //only accepts numbers or decimals in 1 or 2 d.p.
                    + " q/(?<quantity>[0-9]+)" // only accepts numbers, no decimals
                    + "( r/(?<remarks>[^/]+))?$"); // optional argument

    public static final Pattern DELETE_ITEM_DATA_ARGS_FORMAT =
            Pattern.compile("n/(?<itemName>[^/]+)"
                    + " q/(?<quantity>[0-9]+)"); // only accepts numbers, no decimals

    public static final Pattern LIST_ITEM_DATA_ARGS_FORMAT =
            Pattern.compile("(c/(?<category>[^/]+))?"); // optional argument category

    public static final Pattern GET_ITEM_DATA_ARGS_FORMAT =
            Pattern.compile("n/(?<itemName>[^/]+)"
                    + "( p/(?<property>[^/]+))?"); // optional argument property

    public static final Pattern EDIT_ITEM_DATA_ARGS_FORMAT =
            Pattern.compile("n/(?<itemName>[^/]+)"
                    + " p/(?<property>[^/]+)"
                    + " v/(?<value>([0-9]+([.][0-9]{1,2})?))"
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
    public static final String BYE_STRING = "bye";
    public static final String HELP_STRING = "help";

    public static final String INVALID_BYE_COMMAND = "Error: Type 'bye' without additional parameters to exit";
    public static final String INVALID_HELP_COMMAND = "Error: Type 'help' without additional parameters";

    public static final String CORRECT_COMMAND_MESSAGE_STRING_FORMAT =
            "Input invalid command format.\nCorrect format: \n%s\n";
    public static final String PARSE_ADD_SUCCESS_MESSAGE_FORMAT = "name: %s\ncategory: %s\nprice: $%s\n"
            + "quantity: %s\nremarks: %s\n";
    public static final String PARSE_DELETE_SUCCESS_MESSAGE_FORMAT = "name: %s\n";
    public static final String PARSE_LIST_SUCCESS_MESSAGE_FORMAT = "category: %s\n";
    public static final String PARSE_GET_SUCCESS_MESSAGE_FORMAT = "itemName: %s\nproperty: %s\n";

    public static final String PARSE_SUCCESS_MESSAGE_STRING = "Parsed successful.\n";
    public static final String INVALID_COMMAND_MESSAGE_STRING = "Invalid command, please try again.";

    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * Parses the User input line. Checks the user input line against the basic command format
     * and extracts the command word which is the first word in the user input line. After
     * extraction of the command word, pass the remaining user input line arguments to the
     * respective cases depending on the command word.
     *
     * @param userInputLine The user input Line
     * @param shelf         The itemContainer used to prepare the command
     * @return Command object depending on the command type
     * @throws IllegalFormatException   If user input line does not match the respective command format
     * @throws ItemNotExistException    If item name not found in the container
     * @throws NoPropertyFoundException If edit command operation cannot find the associated property specified
     *                                  by the user
     */
    public Command parseCommand(String userInputLine, Shelf shelf) throws IllegalFormatException,
            ItemNotExistException, NoPropertyFoundException {
        logger.log(Level.INFO, "Parsing Start...");
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInputLine.trim());

        /* Checks valid basic command format */
        if (!matcher.matches()) {
            logger.log(Level.WARNING, "Does not match Basic Command Format");
            throw new IllegalFormatException(INVALID_COMMAND_MESSAGE_STRING);
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        Command command;

        switch (commandWord) {
        case ADD_STRING:
            command = prepareAdd(arguments, shelf);
            break;

        case DELETE_STRING:
            command = prepareDelete(arguments, shelf);
            break;

        case LIST_STRING:
            command = prepareList(arguments, shelf);
            break;

        case GET_STRING:
            command = prepareGet(arguments, shelf);
            break;

        case EDIT_STRING:
            command = prepareEdit(arguments, shelf);
            break;

        case HELP_STRING:
            command = prepareHelp(arguments);
            break;

        case BYE_STRING:
            return prepareBye(arguments);

        default:
            throw new IllegalFormatException(INVALID_COMMAND_MESSAGE_STRING);
        }

        return command;
    }

    /**
     * Parses bye command.
     *
     * @param arguments The additional arguments after command word, should be none
     * @return ByeCommand object
     * @throws IllegalFormatException if exists extra argument after bye
     */
    private Command prepareBye(String arguments) throws IllegalFormatException {
        if (!arguments.isEmpty()) {
            throw new IllegalFormatException(INVALID_BYE_COMMAND);
        }
        return new ExitCommand();
    }

    /**
     * Parses help command.
     *
     * @param arguments The additional arguments after command word, should be none
     * @return HelpCommand object
     * @throws IllegalFormatException if exists extra argument after bye
     */
    private Command prepareHelp(String arguments) throws IllegalFormatException {
        if (!arguments.isEmpty()) {
            throw new IllegalFormatException(INVALID_HELP_COMMAND);
        }
        return new HelpCommand();
    }

    /**
     * Parses add command arguments.
     *
     * @param arguments The additional arguments after command word.
     * @return AddCommand object.
     * @throws IllegalFormatException If the input format is wrong.
     */
    private Command prepareAdd(String arguments, Shelf shelf) throws IllegalFormatException {
        final Matcher matcher = ADD_ITEM_DATA_ARGS_FORMAT.matcher(arguments.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            logger.log(Level.WARNING, "Does not match Add Command Format");
            throw new IllegalFormatException(String.format(
                    CORRECT_COMMAND_MESSAGE_STRING_FORMAT, ADD_ITEM_DATA_ARGS_FORMAT_STRING));
        }
        String itemName = matcher.group("itemName");
        String purchaseCost = matcher.group("purchaseCost");
        String sellingPrice = matcher.group("sellingPrice");
        int quantity = Integer.parseInt(matcher.group("quantity"));
        Command addCommand = new AddCommand(itemName, purchaseCost, sellingPrice, quantity, shelf);
        assert addCommand.getClass() == AddCommand.class : "Add should return AddCommand\n";
        logger.log(Level.INFO, "AddCommand parse success.");
        return addCommand;
    }

    /**
     * Parses delete command arguments.
     *
     * @param arguments The additional arguments after command word.
     * @return DeleteCommand object.
     * @throws IllegalFormatException If the input format is wrong.
     */
    private Command prepareDelete(String arguments, Shelf shelf) throws IllegalFormatException {
        final Matcher matcher = DELETE_ITEM_DATA_ARGS_FORMAT.matcher(arguments.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            logger.log(Level.WARNING, "Does not match Delete Command Format");
            throw new IllegalFormatException(String.format(
                    CORRECT_COMMAND_MESSAGE_STRING_FORMAT, DELETE_ITEM_DATA_ARGS_FORMAT_STRING));
        }
        String itemName = matcher.group("itemName");
        int quantity = Integer.parseInt(matcher.group("quantity"));
        Command deleteCommand = new DeleteCommand(itemName, quantity, shelf);
        assert deleteCommand.getClass() == DeleteCommand.class : "Delete should return DeleteCommand\n";
        logger.log(Level.INFO, "DeleteCommand parse success.");
        return deleteCommand;
    }

    /**
     * Parses list command arguments.
     *
     * @param arguments The additional arguments after command word.
     * @return ListCommand object.
     * @throws IllegalFormatException If the input format is wrong.
     */
    private Command prepareList(String arguments, Shelf shelf) throws IllegalFormatException {
        final Matcher matcher = LIST_ITEM_DATA_ARGS_FORMAT.matcher(arguments.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            logger.log(Level.WARNING, "Does not match List Command Format");
            throw new IllegalFormatException(String.format(
                    CORRECT_COMMAND_MESSAGE_STRING_FORMAT, LIST_ITEM_DATA_ARGS_FORMAT_STRING));
        }
        Command listCommand = new ListCommand(shelf);
        assert listCommand.getClass() == ListCommand.class : "List should return ListCommand\n";
        logger.log(Level.INFO, "ListCommand parse success.");
        return listCommand;
    }

    /**
     * Parses get command arguments.
     *
     * @param arguments The additional arguments after command word.
     * @return GetCommand object.
     * @throws IllegalFormatException If the input format is wrong.
     */
    private Command prepareGet(String arguments, Shelf shelf) throws IllegalFormatException {
        final Matcher matcher = GET_ITEM_DATA_ARGS_FORMAT.matcher(arguments.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            logger.log(Level.WARNING, "Does not match Get Command Format");
            throw new IllegalFormatException(String.format(
                    CORRECT_COMMAND_MESSAGE_STRING_FORMAT, GET_ITEM_DATA_ARGS_FORMAT_STRING));
        }

        String itemName = matcher.group("itemName");

        Command getCommand = new GetCommand(itemName, shelf);
        assert getCommand.getClass() == GetCommand.class : "Get should return GetCommand\n";
        logger.log(Level.INFO, "GetCommand parse success.");
        return getCommand;
    }

    /**
     * Parses edit command arguments.
     *
     * @param arguments The additional arguments after command word.
     * @return EditCommand object.
     * @throws IllegalFormatException   If the input format is wrong.
     * @throws ItemNotExistException    If the item cannot be found from the container.
     * @throws NoPropertyFoundException If the associated item property cannot be found.
     */
    private Command prepareEdit(String arguments, Shelf shelf) throws IllegalFormatException,
            ItemNotExistException, NoPropertyFoundException {
        final Matcher matcher = EDIT_ITEM_DATA_ARGS_FORMAT.matcher(arguments.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            logger.log(Level.WARNING, "Does not match Edit Command Format");
            throw new IllegalFormatException(String.format(
                    CORRECT_COMMAND_MESSAGE_STRING_FORMAT, EDIT_ITEM_DATA_ARGS_FORMAT_STRING));
        }
        String itemName = matcher.group("itemName");
        String selectedProperty = matcher.group("property");
        String newValue = matcher.group("value");
        return new EditCommand(itemName, selectedProperty, newValue, shelf);

    }

    /**
     * Creates EditCommand Object.
     *
     * @param itemName Name of item to be edited.
     * @param updatedPurchaseCost New Purchase Cost.
     * @param updatedSellingPrice New Selling Price.
     * @return EditCommand object.
     */
    private Command formEditCommand(String itemName, String updatedPurchaseCost, String updatedSellingPrice,
                                    Shelf shelf) {
        Command editCommand = new EditCommand(itemName, updatedPurchaseCost, updatedSellingPrice, shelf);
        assert editCommand.getClass() == EditCommand.class : "Edit should return EditCommand\n";
        logger.log(Level.INFO, "EditCommand parse success.");

        return editCommand;
    }
}
