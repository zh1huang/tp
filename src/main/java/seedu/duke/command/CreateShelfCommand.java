package seedu.duke.command;

import seedu.duke.command.exception.CommandException;
import seedu.duke.command.exception.DuplicateShelfException;
import seedu.duke.command.exception.IllegalArgumentException;
import seedu.duke.model.ShelfList;

import java.util.regex.Pattern;

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
        } catch (seedu.duke.model.exception.DuplicateShelfException e) {
            throw new DuplicateShelfException(e.getMessage());
        } catch (seedu.duke.model.exception.IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
