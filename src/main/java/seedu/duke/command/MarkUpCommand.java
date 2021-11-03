package seedu.duke.command;

import seedu.duke.command.exception.ItemNotExistException;
import seedu.duke.model.exception.ShelfNotExistException;
import seedu.duke.salesmanager.SalesMarkUp;

public class MarkUpCommand extends Command {
    public static final String MARKUP_DATA_ARGS_FORMAT_STRING = "markup shlv/SHELF_NAME i/INDEX [%/PERCENT]";
    public static final String MARKUP_STRING = "markup";

    private static final String MESSAGE_ITEM_NOT_EXIST = "Item with index %d does not exist";

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

    @Override
    public String execute() throws ShelfNotExistException, ItemNotExistException {
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

            return resultString.toString();

        } catch (ShelfNotExistException e) {
            throw new ShelfNotExistException(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            throw new ItemNotExistException(String.format(MESSAGE_ITEM_NOT_EXIST, index));
        }
    }

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
