package seedu.duke.command;

import seedu.duke.command.exception.ShelfNotExistException;
import seedu.duke.model.ShelfList;

public class RemoveShelfCommand extends Command {
    private static final String REMOVE_COMPLETE_MESSAGE =
            "This shelf %s has been deleted.";
    private final String shelfName;

    public RemoveShelfCommand(String shelfName) {
        this.shelfName = shelfName;
    }

    @Override
    public String execute() throws ShelfNotExistException {
        try {
            ShelfList.getShelfList().deleteShelf(shelfName);
            return String.format(REMOVE_COMPLETE_MESSAGE, shelfName);
        } catch (seedu.duke.model.exception.ShelfNotExistException e) {
            throw new ShelfNotExistException(e.getMessage());
        }
    }
}
