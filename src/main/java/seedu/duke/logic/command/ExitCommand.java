package seedu.duke.logic.command;

//@@author zh1huang
public class ExitCommand extends Command {

    public static final String BYE_STRING = "bye";
    public static final String INVALID_BYE_COMMAND = "Error: Type 'bye' without additional parameters to exit\n";
    public static final String BYE_MESSAGE = "See you next time";

    /**
     * Override superclass Command to not perform any actions.
     */
    public String execute() {
        return BYE_MESSAGE;
    }

    /**
     * Checks if exit command is called.
     *
     * @return true if exit command called
     */
    public boolean isExit() {
        return true;
    }
}
