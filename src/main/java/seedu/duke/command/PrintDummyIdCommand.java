package seedu.duke.command;

import seedu.duke.command.exception.CommandException;
import seedu.duke.model.Item;
import seedu.duke.model.exception.IllegalModelArgumentException;
import seedu.duke.model.exception.ShelfNotExistException;

public class PrintDummyIdCommand extends Command {
    public static final String DUMMY_ID_STRING = "dummyid";

    @Override
    public String execute() throws CommandException, ShelfNotExistException, IllegalModelArgumentException {
        Item.setPrintDummyID(true);
        return "Dummy ID will be printed instead of actual ID";
    }
}
