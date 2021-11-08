package seedu.duke.logic.parser;

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
import seedu.duke.logic.command.PrintDummyIdCommand;
import seedu.duke.logic.command.RemoveShelfCommand;
import seedu.duke.logic.command.ReportCommand;
import seedu.duke.logic.command.SearchCommand;
import seedu.duke.logic.command.SellCommand;
import seedu.duke.model.exception.ItemNotExistModelException;
import seedu.duke.logic.parser.exception.IllegalFormatException;
import seedu.duke.logic.parser.exception.NoPropertyFoundException;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@@author t-l-xin-reused
// Parser class adapted from
// https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java
/**
 * Parser Class. Manages parsing of user input for different commands.
 */
public class Parser {

    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    public static final Pattern ADD_ITEM_DATA_ARGS_FORMAT =
            Pattern.compile("n/(?<itemName>[^/]+)"
                    + " shlv/(?<shelfName>[a-zA-Z0-9 _()-]+)"
                    + " p/(?<purchaseCost>([0-9]{1,4}([.][0-9]{1,2})?))"
                    //only accepts numbers or decimals in 1 or 2 d.p.
                    + " s/(?<sellingPrice>([0-9]{1,4}([.][0-9]{1,2})?))"
                    //only accepts numbers or decimals in 1 or 2 d.p.
                    + " q/(?<quantity>[0-9]{1,3})" // only accepts integers, between 1 to 3 digits
                    + "( r/(?<remarks>[^/]+))?$"); // optional argument

    public static final Pattern DELETE_ITEM_DATA_ARGS_FORMAT =
            Pattern.compile("shlv/(?<shelfName>[a-zA-Z0-9 _()-]+) i/(?<indexInShelf>[0-9]+)");

    public static final Pattern LIST_ITEM_DATA_ARGS_FORMAT =
            Pattern.compile("(shlv/(?<shelfName>[a-zA-Z0-9 _()-]+))?$"); // optional argument shelfName

    public static final Pattern SEARCH_ITEM_DATA_ARGS_FORMAT =
            Pattern.compile("k/(?<keyword>[a-zA-Z0-9 _()-]+)");

    public static final Pattern GET_ITEM_DATA_ARGS_FORMAT =
            Pattern.compile("shlv/(?<shelfName>[a-zA-Z0-9 _()-]+) i/(?<indexInShelf>[0-9]+)");

    public static final Pattern EDIT_ITEM_DATA_ARGS_FORMAT =
            Pattern.compile("shlv/(?<shelfName>[a-zA-Z0-9 _()-]+)"
                    + " i/(?<indexInShelf>[0-9]+)"
                    + "((( p/(?<property>(purchase cost|selling price)) v/(?<value>(([0-9]{1,4}([.][0-9]{1,2})?)))))|"
                    + "(( p/(?<remarksProperty>(remarks)) v/(?<remarksValue>[^/]+))))$"); //for remarks property

    public static final Pattern CREATE_SHELF_DATA_ARGS_FORMAT =
            Pattern.compile("shlv/(?<shelfName>[^/]+)");

    public static final Pattern REMOVE_SHELF_DATA_ARGS_FORMAT =
            Pattern.compile("shlv/(?<shelfName>[a-zA-Z0-9 _()-]+)");

    public static final Pattern SELL_ITEM_DATA_ARGS_FORMAT =
            Pattern.compile("id/(?<ID>[0-9a-f]{8}+)$"); //only hex characters are allowed

    public static final Pattern REPORT_DATA_ARGS_FORMAT =
            Pattern.compile("t/(?<type>(stats|items))"
                    + " ym/(?<startYearMonth>[0-9]{4}-[0-9]{2})"
                    + "( ym/(?<endYearMonth>[0-9]{4}-[0-9]{2}))?$"); // optional argument category

    public static final Pattern MARKUP_ITEM_DATA_ARGS_FORMAT =
            Pattern.compile("shlv/(?<shelfName>[a-zA-Z0-9 _()-]+) i/(?<indexInShelf>[0-9]+)"
                    + "( %/(?<percent>([0-9]{1,3}([.][0-9]{1,2})?)))?$");

    public static final String CORRECT_COMMAND_MESSAGE_STRING_FORMAT =
            "Input invalid command format.\nCorrect format: \n%s\n";

    public static final String PARSE_SUCCESS_MESSAGE_STRING = "Parsed successful.\n";
    public static final String INVALID_COMMAND_MESSAGE_STRING =
            "Invalid command, please try again.\nType \"help\" for commands available.\n";

    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * Parses the User input line. Checks the user input line against the basic command format
     * and extracts the command word which is the first word in the user input line. After
     * extraction of the command word, pass the remaining user input line arguments to the
     * respective cases depending on the command word.
     *
     * @param userInputLine The user input Line
     * @return Command object depending on the command type
     * @throws IllegalFormatException     If user input line does not match the respective command format
     * @throws ItemNotExistModelException If item name not found in the container
     * @throws NoPropertyFoundException   If edit command operation cannot find the associated property specified
     *                                    by the user
     */
    public Command parseCommand(String userInputLine) throws IllegalFormatException,
            ItemNotExistModelException, NoPropertyFoundException {
        logger.log(Level.INFO, "Parsing Start...");
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInputLine.trim());

        /* Checks valid basic command format */
        if (!matcher.matches()) {
            logger.log(Level.WARNING, "Does not match Basic Command Format");
            throw new IllegalFormatException(INVALID_COMMAND_MESSAGE_STRING);
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {
        case AddCommand.ADD_STRING:
            return prepareAdd(arguments);

        case DeleteCommand.DELETE_STRING:
            return prepareDelete(arguments);

        case ListCommand.LIST_STRING:
            return prepareList(arguments);

        case SearchCommand.SEARCH_STRING:
            return prepareSearch(arguments);

        case GetCommand.GET_STRING:
            return prepareGet(arguments);

        case EditCommand.EDIT_STRING:
            return prepareEdit(arguments);

        case HelpCommand.HELP_STRING:
            return prepareHelp(arguments);

        case ExitCommand.BYE_STRING:
            return prepareBye(arguments);

        case ReportCommand.REPORT_STRING:
            return prepareReport(arguments);

        case SellCommand.SELL_STRING:
            return prepareSell(arguments);

        case CreateShelfCommand.CREATE_STRING:
            return prepareCreateShelf(arguments);

        case RemoveShelfCommand.REMOVE_STRING:
            return prepareRemoveShelf(arguments);

        case MarkUpCommand.MARKUP_STRING:
            return prepareMarkUp(arguments);

        case PrintDummyIdCommand.DUMMY_ID_STRING:
            return new PrintDummyIdCommand();

        default:
            throw new IllegalFormatException(INVALID_COMMAND_MESSAGE_STRING);
        }

    }

    /**
     * Parses add command arguments.
     *
     * @param arguments The additional arguments after command word
     * @return AddCommand object
     * @throws IllegalFormatException If the input format is wrong
     */
    private Command prepareAdd(String arguments) throws IllegalFormatException {
        final Matcher matcher = ADD_ITEM_DATA_ARGS_FORMAT.matcher(arguments.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            logger.log(Level.WARNING, "Does not match Add Command Format");
            throw new IllegalFormatException(String.format(CORRECT_COMMAND_MESSAGE_STRING_FORMAT,
                    AddCommand.ADD_ITEM_DATA_ARGS_FORMAT_STRING));
        }
        String itemName = matcher.group("itemName");
        String shelfName = matcher.group("shelfName");
        String purchaseCost = matcher.group("purchaseCost");
        String sellingPrice = matcher.group("sellingPrice");
        String quantity = matcher.group("quantity");
        String remarks = matcher.group("remarks");

        Command addCommand;
        addCommand = new AddCommand(itemName, purchaseCost, sellingPrice,
                quantity, shelfName, Objects.requireNonNullElse(remarks, ""));
        logger.log(Level.INFO, "AddCommand parse success.");
        return addCommand;
    }

    /**
     * Parses delete command arguments.
     *
     * @param arguments The additional arguments after command word
     * @return DeleteCommand object
     * @throws IllegalFormatException If the input format is wrong
     */
    private Command prepareDelete(String arguments) throws IllegalFormatException {
        final Matcher matcher = DELETE_ITEM_DATA_ARGS_FORMAT.matcher(arguments.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            logger.log(Level.WARNING, "Does not match Delete Command Format");
            throw new IllegalFormatException(String.format(
                    CORRECT_COMMAND_MESSAGE_STRING_FORMAT, DeleteCommand.DELETE_ITEM_DATA_ARGS_FORMAT_STRING));
        }

        String shelfName = matcher.group("shelfName");
        String indexInShelf = matcher.group("indexInShelf");

        Command deleteCommand = new DeleteCommand(shelfName, indexInShelf);
        logger.log(Level.INFO, "DeleteCommand parse success.");
        return deleteCommand;
    }

    /**
     * Parses list command arguments.
     *
     * @param arguments The additional arguments after command word
     * @return ListCommand object
     * @throws IllegalFormatException If the input format is wrong
     */
    private Command prepareList(String arguments) throws IllegalFormatException {
        final Matcher matcher = LIST_ITEM_DATA_ARGS_FORMAT.matcher(arguments.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            logger.log(Level.WARNING, "Does not match List Command Format");
            throw new IllegalFormatException(String.format(
                    CORRECT_COMMAND_MESSAGE_STRING_FORMAT, ListCommand.LIST_ITEM_DATA_ARGS_FORMAT_STRING));
        }

        String shelfName = matcher.group("shelfName");

        Command listCommand;
        if (shelfName == null) {
            listCommand = new ListCommand();
        } else {
            listCommand = new ListCommand(shelfName);
        }

        logger.log(Level.INFO, "ListCommand parse success.");
        return listCommand;
    }

    private Command prepareSearch(String arguments) throws IllegalFormatException {
        final Matcher matcher = SEARCH_ITEM_DATA_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            logger.log(Level.WARNING, "Does not match Search Command Format");
            throw new IllegalFormatException(String.format(
                    CORRECT_COMMAND_MESSAGE_STRING_FORMAT, SearchCommand.SEARCH_ITEM_DATA_ARGS_FORMAT));
        }

        String keyword = matcher.group("keyword");
        SearchCommand searchCommand = new SearchCommand(keyword);

        logger.log(Level.INFO, "SearchCommand parse success.");
        return searchCommand;
    }

    /**
     * Parses get command arguments.
     *
     * @param arguments The additional arguments after command word
     * @return GetCommand object
     * @throws IllegalFormatException If the input format is wrong
     */
    private Command prepareGet(String arguments) throws IllegalFormatException {
        final Matcher matcher = GET_ITEM_DATA_ARGS_FORMAT.matcher(arguments.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            logger.log(Level.WARNING, "Does not match Get Command Format");
            throw new IllegalFormatException(String.format(
                    CORRECT_COMMAND_MESSAGE_STRING_FORMAT, GetCommand.GET_ITEM_DATA_ARGS_FORMAT_STRING));
        }

        String shelfName = matcher.group("shelfName");
        String indexInShelf = matcher.group("indexInShelf");

        Command getCommand = new GetCommand(shelfName, indexInShelf);
        logger.log(Level.INFO, "GetCommand parse success.");
        return getCommand;
    }

    /**
     * Parses edit command arguments.
     *
     * @param arguments The additional arguments after command word
     * @return EditCommand object
     * @throws IllegalFormatException     If the input format is wrong
     */
    private Command prepareEdit(String arguments) throws IllegalFormatException {
        final Matcher matcher = EDIT_ITEM_DATA_ARGS_FORMAT.matcher(arguments.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            logger.log(Level.WARNING, "Does not match Edit Command Format");
            throw new IllegalFormatException(String.format(
                    CORRECT_COMMAND_MESSAGE_STRING_FORMAT, EditCommand.EDIT_ITEM_DATA_ARGS_FORMAT_STRING));
        }
        String shelfName = matcher.group("shelfName");
        String indexInShelf = matcher.group("indexInShelf");
        String selectedProperty = matcher.group("property");
        String newValue = matcher.group("value");

        String selectedRemarksProperty = matcher.group("remarksProperty");
        String newRemarksValue = matcher.group("remarksValue");

        Command editCommand;
        if (selectedProperty == null) {
            editCommand = new EditCommand(shelfName, indexInShelf, selectedRemarksProperty, newRemarksValue);
        } else {
            editCommand = new EditCommand(shelfName, indexInShelf, selectedProperty, newValue);
        }

        logger.log(Level.INFO, "EditCommand parse success.");
        return editCommand;
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
            throw new IllegalFormatException(HelpCommand.INVALID_HELP_COMMAND);
        }
        return new HelpCommand();
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
            throw new IllegalFormatException(ExitCommand.INVALID_BYE_COMMAND);
        }
        return new ExitCommand();
    }

    /**
     * Parses report command arguments.
     *
     * @param arguments The additional arguments after command word.
     * @return ReportCommand object
     * @throws IllegalFormatException If the input format is wrong
     */
    private Command prepareReport(String arguments) throws IllegalFormatException {
        final Matcher matcher = REPORT_DATA_ARGS_FORMAT.matcher(arguments.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            logger.log(Level.WARNING, "Does not match Report Command Format");
            throw new IllegalFormatException(String.format(
                    CORRECT_COMMAND_MESSAGE_STRING_FORMAT, ReportCommand.REPORT_DATA_ARGS_FORMAT_STRING));
        }

        String type = matcher.group("type");
        String startYearMonth = matcher.group("startYearMonth");
        String endYearMonth = matcher.group("endYearMonth");

        Command reportCommand;

        reportCommand = new ReportCommand(startYearMonth, Objects.requireNonNullElse(endYearMonth, ""), type);

        logger.log(Level.INFO, "ReportCommand parse success.");
        return reportCommand;
    }

    /**
     * Parses sell command arguments.
     *
     * @param arguments The additional arguments after command word.
     * @return SellCommand object
     * @throws IllegalFormatException If the input format is wrong
     */
    private Command prepareSell(String arguments) throws IllegalFormatException {
        final Matcher matcher = SELL_ITEM_DATA_ARGS_FORMAT.matcher(arguments.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            logger.log(Level.WARNING, "Does not match Sell Command Format");
            throw new IllegalFormatException(String.format(
                    CORRECT_COMMAND_MESSAGE_STRING_FORMAT, SellCommand.SELL_DATA_ARGS_FORMAT_STRING));
        }

        String itemID = matcher.group("ID");

        Command sellCommand = new SellCommand(itemID);
        logger.log(Level.INFO, "SellCommand parse success.");
        return sellCommand;
    }

    /**
     * Parses create shelf command arguments.
     *
     * @param arguments The additional arguments after command word.
     * @return CreateShelfCommand object
     * @throws IllegalFormatException If the input format is wrong
     */
    private Command prepareCreateShelf(String arguments) throws IllegalFormatException {
        final Matcher matcher = CREATE_SHELF_DATA_ARGS_FORMAT.matcher(arguments.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            logger.log(Level.WARNING, "Does not match Create Shelf Command Format");
            throw new IllegalFormatException(String.format(
                    CORRECT_COMMAND_MESSAGE_STRING_FORMAT, CreateShelfCommand.CREATE_DATA_ARGS_FORMAT_STRING));
        }

        String shelfName = matcher.group("shelfName");

        Command createShelfCommand = new CreateShelfCommand(shelfName);
        logger.log(Level.INFO, "CreateShelfCommand parse success.");
        return createShelfCommand;
    }

    /**
     * Parses remove shelf command arguments.
     *
     * @param arguments The additional arguments after command word.
     * @return RemoveShelfCommand object
     * @throws IllegalFormatException If the input format is wrong
     */
    private Command prepareRemoveShelf(String arguments) throws IllegalFormatException {
        final Matcher matcher = REMOVE_SHELF_DATA_ARGS_FORMAT.matcher(arguments.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            logger.log(Level.WARNING, "Does not match Remove Shelf Command Format");
            throw new IllegalFormatException(String.format(
                    CORRECT_COMMAND_MESSAGE_STRING_FORMAT, RemoveShelfCommand.REMOVE_DATA_ARGS_FORMAT_STRING));
        }

        String shelfName = matcher.group("shelfName");

        Command removeShelfCommand = new RemoveShelfCommand(shelfName);
        logger.log(Level.INFO, "RemoveShelfCommand parse success.");
        return removeShelfCommand;
    }

    /**
     * Parses mark up command arguments.
     *
     * @param arguments The additional arguments after command word.
     * @return MarkUpCommand object
     * @throws IllegalFormatException If arguments do not follow input format specified
     */
    private Command prepareMarkUp(String arguments) throws IllegalFormatException {
        final Matcher matcher = MARKUP_ITEM_DATA_ARGS_FORMAT.matcher(arguments.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            logger.log(Level.WARNING, "Does not match Sell Command Format");
            throw new IllegalFormatException(String.format(
                    CORRECT_COMMAND_MESSAGE_STRING_FORMAT, MarkUpCommand.MARKUP_DATA_ARGS_FORMAT_STRING));
        }

        String shelfName = matcher.group("shelfName");
        String indexInShelf = matcher.group("indexInShelf");
        String userRequestPercent = matcher.group("percent");

        Command markUpCommand;
        markUpCommand = new MarkUpCommand(shelfName, indexInShelf, Objects.requireNonNullElse(userRequestPercent, ""));
        logger.log(Level.INFO, "MarkUpCommand parse success.");
        return markUpCommand;
    }
}
