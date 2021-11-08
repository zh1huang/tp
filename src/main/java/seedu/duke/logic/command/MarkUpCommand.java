package seedu.duke.logic.command;

import seedu.duke.logic.command.exception.ItemNotExistCommandException;
import seedu.duke.model.exception.DeniedAccessToShelfModelException;
import seedu.duke.model.exception.ShelfNotExistModelException;
import seedu.duke.logic.command.sales.SalesMarkUp;

import java.util.logging.Level;
import java.util.logging.Logger;

//@@author t-l-xin
/**
 * The command that gets the markup information for a selected item.
 */
public class MarkUpCommand extends Command {

    public static final String MARKUP_DATA_ARGS_FORMAT_STRING = "markup shlv/SHELF_NAME i/INDEX [%/PERCENT]\n"
            + "Maximum percent: 999.99 (up to 2 dp)\n"
            + "Invalid percent e.g. \"5.\", \"-5\", \"5.000\", \"5000\"\n"
            + "Valid percent e.g. \"5.05\", \"5.6\", \"0.5\", \"50.50\"";
    public static final String MARKUP_STRING = "markup";
    private static final String MESSAGE_ITEM_NOT_EXIST = "Item with index %d does not exist";
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static final String MARKUP_COMMAND_SHELF_NOT_EXIST_LOGGING_MESSAGE_FORMAT =
            "MarkUpCommand can't find an existing shelf named: %s";
    private static final String MARKUP_COMMAND_INVALID_INDEX_LOGGING_MESSAGE_FORMAT =
            "MarkUpCommand can't find index %s in shelf";
    public static final String MARKUP_ON_SOLDITEMS_NOT_PERMITTED_MESSAGE = "Operation on SoldItems not permitted";

    private final String shelfName;
    private final int index;
    private String userRequestPercent;

    /**
     * MarkUpCommand constructor. Initialises shelf name, index, user requested percent for markup if not null.
     *
     * @param shelfName          Name of shelf
     * @param index              index in shelf
     * @param userRequestPercent user input percentage for markup
     */
    public MarkUpCommand(String shelfName, String index, String userRequestPercent) {
        this.shelfName = shelfName;
        this.index = Integer.parseInt(index) - 1;
        this.userRequestPercent = userRequestPercent;
    }

    /**
     * Executes the markup operation.
     *
     * @return A result string that contains all the markup information of a selected item
     * @throws ShelfNotExistModelException If Shelf does not
     * @throws ItemNotExistCommandException If Item at index does not exist
     */
    @Override
    public String execute() throws ShelfNotExistModelException,
            ItemNotExistCommandException, DeniedAccessToShelfModelException {
        StringBuilder resultString = new StringBuilder();
        try {

            SalesMarkUp salesMarkUp = new SalesMarkUp(shelfName, index, userRequestPercent);

            String currentItemInfo = salesMarkUp.getItemToMarkUpInfo();
            resultString.append(currentItemInfo);

            String currentItemMarkUpInfoString = salesMarkUp.getSelectedItemMarkUpInfo();
            resultString.append(currentItemMarkUpInfoString);

            if (userRequestPercent.equals("")) {
                String getEstimatedMarkUpInfoString = salesMarkUp.getEstimatedMarkUpInfo();
                resultString.append(getEstimatedMarkUpInfoString);
            } else {
                String getUserRequestMarkUpInfoString = salesMarkUp.getUserRequestMarkUpInfo();
                resultString.append(getUserRequestMarkUpInfoString);
            }

            logger.log(Level.INFO, "MarkUpCommand successfully executed.");
            return resultString.toString();

        } catch (ShelfNotExistModelException e) {
            logger.log(Level.WARNING, String.format(MARKUP_COMMAND_SHELF_NOT_EXIST_LOGGING_MESSAGE_FORMAT, shelfName));
            throw new ShelfNotExistModelException(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.WARNING, String.format(MARKUP_COMMAND_INVALID_INDEX_LOGGING_MESSAGE_FORMAT, index + 1));
            throw new ItemNotExistCommandException(String.format(MESSAGE_ITEM_NOT_EXIST, index + 1));
        }
    }

    /**
     * The overriding equal method to compare with other commands.
     *
     * @param other the other object to be compared with
     * @return true if two objects are the same, else false
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof MarkUpCommand)) {
            return false;
        }

        MarkUpCommand command = (MarkUpCommand) other;
        return shelfName.equals(command.shelfName)
                && index == command.index
                && userRequestPercent.equals(command.userRequestPercent);
    }

}
