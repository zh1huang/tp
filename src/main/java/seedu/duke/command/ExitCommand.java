package seedu.duke.command;

import seedu.duke.model.Shelf;

public class ExitCommand extends Command {

    private static final String BYE_MESSAGE = "Bye! Hope to see you again!";

    /**
     * Override superclass Command to not perform any actions.
     *
     * @param list the Shelf in which exit command is executed
     */
    public void execute(Shelf list) {
        System.out.println(BYE_MESSAGE);
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
