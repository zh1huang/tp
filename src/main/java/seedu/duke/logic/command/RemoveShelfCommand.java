package seedu.duke.logic.command;

import seedu.duke.logic.command.exception.DeniedAccessToShelfCommandException;
import seedu.duke.logic.command.exception.IllegalArgumentCommandException;
import seedu.duke.logic.command.exception.ShelfNotExistCommandException;
import seedu.duke.model.ShelfList;
import seedu.duke.model.exception.DeniedAccessToShelfModelException;
import seedu.duke.model.exception.ShelfNotExistModelException;

//@@author haoyusimon
/**
 * Represents the command that removes a specific shelf.
 */
public class RemoveShelfCommand extends Command {

    public static final String REMOVE_DATA_ARGS_FORMAT_STRING = "remove shlv/SHELF_NAME";
    public static final String REMOVE_STRING = "remove";
    public static final String PARSE_REMOVE_SUCCESS_MESSAGE_FORMAT = "shelfname: %s\n";
    private static final String REMOVE_COMPLETE_MESSAGE =
            "Shelf \"%s\" has been removed.";
    private static final String REMOVE_NON_EMPTY_SHELF_ERROR_MESSAGE =
            "Cannot remove shelf with items inside.";
    private final String shelfName;

    public RemoveShelfCommand(String shelfName) {
        this.shelfName = shelfName;
    }

    /**
     * Removes the specified shelf.
     *
     * @return the complete message as String
     * @throws ShelfNotExistCommandException       if the specified shelf does not exist
     * @throws IllegalArgumentCommandException     if the inputs are invalid
     * @throws DeniedAccessToShelfCommandException if the user tries to access to the soldItems shelf.
     */
    @Override
    public String execute() throws ShelfNotExistCommandException, IllegalArgumentCommandException,
            DeniedAccessToShelfCommandException {
        try {
            if (ShelfList.getShelfList().getShelf(shelfName, true).getItemCount() != 0) {
                throw new IllegalArgumentCommandException(REMOVE_NON_EMPTY_SHELF_ERROR_MESSAGE);
            }

            ShelfList.getShelfList().deleteShelf(shelfName);
            return String.format(REMOVE_COMPLETE_MESSAGE, shelfName);
        } catch (ShelfNotExistModelException e) {
            throw new ShelfNotExistCommandException(e.getMessage());
        } catch (IllegalArgumentCommandException e) {
            throw new IllegalArgumentCommandException(e.getMessage());
        } catch (DeniedAccessToShelfModelException e) {
            throw new DeniedAccessToShelfCommandException(e.getMessage());
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
        if (!(other instanceof RemoveShelfCommand)) {
            return false;
        }
        RemoveShelfCommand command = (RemoveShelfCommand) other;
        return shelfName.equals(command.shelfName);
    }
}
