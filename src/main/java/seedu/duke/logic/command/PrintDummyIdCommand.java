package seedu.duke.logic.command;

import seedu.duke.logic.command.exception.CommandException;
import seedu.duke.model.Item;
import seedu.duke.model.exception.IllegalArgumentModelException;
import seedu.duke.model.exception.ShelfNotExistModelException;

//@@author yuejunfeng0909
public class PrintDummyIdCommand extends Command {

    public static final String DUMMY_ID_STRING = "dummyid";

    @Override
    public String execute() throws CommandException, ShelfNotExistModelException, IllegalArgumentModelException {
        Item.setPrintDummyID(true);
        return "Dummy ID will be printed instead of actual ID";
    }
}
