package seedu.duke;

import seedu.duke.command.AddCommand;
import seedu.duke.command.Command;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.EditCommand;
import seedu.duke.model.ItemContainer;
import seedu.duke.model.exception.IllegalFormatException;
import seedu.duke.model.exception.NoCommandFoundException;
import seedu.duke.model.exception.NoPropertyFoundException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Parser class adapted from
// https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java

/**
 * Parser Class. Manages parsing of user input for different commands.
 */
public class Parser {
    private final ItemContainer list;

    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    public static final Pattern ADD_ITEM_DATA_ARGS_FORMAT =
            Pattern.compile("n/(?<itemName>[^/]+)"
                    + " c/(?<category>[^/]+)"
                    + " p/[$](?<purchaseCost>([0-9]+([.][0-9]{1,2})?))" //only accepts numbers or decimals in 1 or 2 d.p.
                    + " s/[$](?<sellingPrice>([0-9]+([.][0-9]{1,2})?))" //only accepts numbers or decimals in 1 or 2 d.p.
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
            "add n/NAME c/CATEGORY p/purchaseCOST s/SELLINGPRICE q/QUANTITY [r/REMARKS]";
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
    public static final String PARSE_ADD_SUCCESS_MESSAGE_FORMAT = "name: %s\ncategory: %s\n"
            + "purchaseCost: $%s\nSellingPrice: $%s\nquantity: %s\nremarks: %s\n";
    public static final String PARSE_DELETE_SUCCESS_MESSAGE_FORMAT = "name: %s\n";
    public static final String PARSE_LIST_SUCCESS_MESSAGE_FORMAT = "category: %s\n";
    public static final String PARSE_GET_SUCCESS_MESSAGE_FORMAT = "itemName: %s\nproperty: %s\n";

    public static final String INVALID_COMMAND_MESSAGE_STRING = "Invalid command, please try again.";
    public static final String PARSE_SUCCESS_MESSAGE_STRING = "Parsed successful.\n";

    public Parser(ItemContainer list) {
        this.list = list;
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
        Command newCommand;

        switch (commandWord) {
        case ADD_STRING:
            newCommand = prepareAdd(arguments);
            break;

        case DELETE_STRING:
            newCommand= prepareDelete(arguments);
            break;

        case LIST_STRING:
            newCommand = prepareList(arguments);
            break;

        case GET_STRING:
            newCommand = prepareGet(arguments);
            break;

        case EDIT_STRING:
            newCommand = prepareEdit(arguments);
            break;

        default:
            throw new NoCommandFoundException();
        }
    }

    /**
     * Parses add command arguments.
     *
     * @param arguments The additional arguments after command word.
     * @return A string indicating parse success or failure.
     */
    private Command prepareAdd(String arguments) {
        final Matcher matcher = ADD_ITEM_DATA_ARGS_FORMAT.matcher(arguments.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            throw new IllegalFormatException(String.format(
                    CORRECT_COMMAND_MESSAGE_STRING_FORMAT, ADD_ITEM_DATA_ARGS_FORMAT_STRING));
//            return String.format(
//                    CORRECT_COMMAND_MESSAGE_STRING_FORMAT, ADD_ITEM_DATA_ARGS_FORMAT_STRING);
        }

//       try {
//        System.out.println(String.format(PARSE_ADD_SUCCESS_MESSAGE_FORMAT,
//                matcher.group("itemName"), matcher.group("category"),
//                matcher.group("price"), matcher.group("quantity"), matcher.group("remarks")));
        String itemName = matcher.group("itemName");

        return new AddCommand(, matcher.group("purchaseCost"),
                matcher.group("sellingPrice"));

//        } catch (Exception e) {
//            return String.format(e.getMessage());
//        }
    }

    /**
     * Parses delete command arguments.
     *
     * @param arguments The additional arguments after command word.
     * @return A string indicating parse success or failure.
     */
    private Command prepareDelete(String arguments) {
        final Matcher matcher = DELETE_ITEM_DATA_ARGS_FORMAT.matcher(arguments.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            throw new IllegalFormatException(String.format(
                    CORRECT_COMMAND_MESSAGE_STRING_FORMAT, DELETE_ITEM_DATA_ARGS_FORMAT_STRING));
//            return String.format(
//                    CORRECT_COMMAND_MESSAGE_STRING_FORMAT, DELETE_ITEM_DATA_ARGS_FORMAT_STRING);
        }

//        try {
//            System.out.println(String.format(PARSE_DELETE_SUCCESS_MESSAGE_FORMAT, matcher.group("itemName")));
//            return PARSE_SUCCESS_MESSAGE_STRING;
//        } catch (Exception e) {
//            return (e.getMessage());
//        }
       return new DeleteCommand(matcher.group("itemName"), list) ;
    }

    /**
     * Parses list command arguments.
     *
     * @param arguments The additional arguments after command word.
     * @return A string indicating parse success or failure.
     */
    private String prepareList(String arguments) {
        final Matcher matcher = LIST_ITEM_DATA_ARGS_FORMAT.matcher(arguments.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            return String.format(
                    CORRECT_COMMAND_MESSAGE_STRING_FORMAT, LIST_ITEM_DATA_ARGS_FORMAT_STRING);
        }

        try {
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
    private String prepareGet(String arguments) {
        final Matcher matcher = GET_ITEM_DATA_ARGS_FORMAT.matcher(arguments.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            return String.format(
                    CORRECT_COMMAND_MESSAGE_STRING_FORMAT, GET_ITEM_DATA_ARGS_FORMAT_STRING);
        }

        try {
            System.out.println(String.format(PARSE_GET_SUCCESS_MESSAGE_FORMAT,
                    matcher.group("itemName"), matcher.group("property")));
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
    private String prepareEdit(String arguments) {
        final Matcher matcher = EDIT_ITEM_DATA_ARGS_FORMAT.matcher(arguments.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            throw new IllegalFormatException(String.format(
                    CORRECT_COMMAND_MESSAGE_STRING_FORMAT, EDIT_ITEM_DATA_ARGS_FORMAT_STRING));
//            return String.format(
//                    CORRECT_COMMAND_MESSAGE_STRING_FORMAT, EDIT_ITEM_DATA_ARGS_FORMAT_STRING);
        }
        String itemName = matcher.group("itemName");
        String selectedProperty = matcher.group("property");

        if (selectedProperty.equals("purchaseCost")) {
            String updatedPurchaseCost = selectedProperty;
            String updatedSellingPrice = list.getItem(itemName).getSellingPrice();
            return new EditCommand(itemName, updatedPurchaseCost, updatedSellingPrice, list);
        } else if (selectedProperty.equals("sellingPrice")) {
            String updatedPurchaseCost = list.getItem(itemName).getPurchaseCost();
            String updatedSellingPrice = selectedProperty;
            return new EditCommand(itemName, updatedPurchaseCost, updatedSellingPrice, list);
        } else {
            throw new NoPropertyFoundException(selectedProperty);
        }
//        try {
//            System.out.println(String.format("itemName: %s\nproperty: %s\nvalue: %s\nshow result: %s\n",
//                    matcher.group("itemName"), matcher.group("property"),
//                    matcher.group("value"), matcher.group("showResult")));
//            return PARSE_SUCCESS_MESSAGE_STRING;
//        } catch (Exception e) {
//            return (e.getMessage());
//        }
    }
}
