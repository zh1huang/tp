package seedu.duke.command;

import seedu.duke.command.exception.ShelfNotExistException;
import seedu.duke.model.ShelfList;

public class RemoveShelfCommand extends Command {
    private static final String CREATE_COMPLETE_MESSAGE =
            "This shelf has been created.";
    private final String shelfName;

    public RemoveShelfCommand(String shelfName) {
        this.shelfName = shelfName;
    }

    @Override
    public String execute() throws ShelfNotExistException {
        try {
            ShelfList.getShelfList().deleteShelf(shelfName);
            return CREATE_COMPLETE_MESSAGE;
        } catch (seedu.duke.model.exception.ShelfNotExistException e) {
            throw new ShelfNotExistException(e.getMessage());
        }
    }
}
