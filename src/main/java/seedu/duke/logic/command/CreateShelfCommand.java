package seedu.duke.logic.command;

import seedu.duke.logic.command.exception.CommandException;
import seedu.duke.logic.command.exception.DuplicateShelfCommandException;
import seedu.duke.logic.command.exception.IllegalArgumentCommandException;
import seedu.duke.model.ShelfList;
import seedu.duke.model.exception.DuplicateShelfModelException;
import seedu.duke.model.exception.IllegalArgumentModelException;

public class CreateShelfCommand extends Command {

    public static final String CREATE_DATA_ARGS_FORMAT_STRING = "create shlv/SHELF_NAME";
    public static final String CREATE_STRING = "create";
    public static final String PARSE_CREATE_SUCCESS_MESSAGE_FORMAT = "shelfname: %s\n";
    private static final String CREATE_COMPLETE_MESSAGE =
            "Shelf \"%s\" has been created.";
    private final String shelfName;

    public CreateShelfCommand(String shelfName) {
        this.shelfName = shelfName;
    }

    @Override
    public String execute() throws CommandException {
        try {
            ShelfList.getShelfList().addShelf(shelfName);
            return String.format(CREATE_COMPLETE_MESSAGE, shelfName);
        } catch (DuplicateShelfModelException e) {
            throw new DuplicateShelfCommandException(e.getMessage());
        } catch (IllegalArgumentModelException e) {
            throw new IllegalArgumentCommandException(e.getMessage());
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
        if (!(other instanceof CreateShelfCommand)) {
            return false;
        }

        CreateShelfCommand command = (CreateShelfCommand) other;
        return shelfName.equals(command.shelfName);
    }
}
