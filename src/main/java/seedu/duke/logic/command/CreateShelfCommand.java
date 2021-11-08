package seedu.duke.logic.command;

import seedu.duke.logic.command.exception.DeniedAccessToShelfCommandException;
import seedu.duke.logic.command.exception.DuplicateShelfCommandException;
import seedu.duke.logic.command.exception.IllegalArgumentCommandException;
import seedu.duke.model.ShelfList;
import seedu.duke.model.exception.DeniedAccessToShelfModelException;
import seedu.duke.model.exception.DuplicateShelfModelException;
import seedu.duke.model.exception.IllegalArgumentModelException;

//@@author haoyusimon
/**
 * Represents a command that creates a new shelf.
 */
public class CreateShelfCommand extends Command {

    public static final String CREATE_DATA_ARGS_FORMAT_STRING = "create shlv/SHELF_NAME";
    public static final String CREATE_STRING = "create";
    private static final String CREATE_COMPLETE_MESSAGE =
            "Shelf \"%s\" has been created.";
    private final String shelfName;

    /**
     * The CreateShelfCommand constructor.
     *
     * @param shelfName the name of the shelf to be created.
     */
    public CreateShelfCommand(String shelfName) {
        this.shelfName = shelfName;
    }

    /**
     * Create a new shelf with the specified name.
     *
     * @return A completed message as String
     * @throws DuplicateShelfCommandException when there is already a shelf with specified name
     * @throws IllegalArgumentCommandException when the name specified is invalid
     * @throws DeniedAccessToShelfCommandException when the user tries to access the soldItems shelf
     */
    @Override
    public String execute() throws DuplicateShelfCommandException,
            IllegalArgumentCommandException, DeniedAccessToShelfCommandException {
        try {
            ShelfList.getShelfList().addShelf(shelfName, true);
            return String.format(CREATE_COMPLETE_MESSAGE, shelfName);
        } catch (DuplicateShelfModelException e) {
            throw new DuplicateShelfCommandException(e.getMessage());
        } catch (IllegalArgumentModelException e) {
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
        if (!(other instanceof CreateShelfCommand)) {
            return false;
        }

        CreateShelfCommand command = (CreateShelfCommand) other;
        return shelfName.equals(command.shelfName);
    }
}
