package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.model.Shelf;
import seedu.duke.parser.Parser;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Duke {

    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String HELLO_MESSAGE = "Hello from\n" + LOGO + "What is your name?";
    private static final String HELP_PROMPT_MESSAGE = "Enter 'help' for the list of available commands";

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) throws Exception {
        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        logger.setLevel(Level.WARNING);

        Shelf warehouse = new Shelf("warehouse");

        System.out.println(HELLO_MESSAGE);
        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine() + ", what can I do for you?");
        System.out.println(HELP_PROMPT_MESSAGE);
        String input;
        Parser parser = new Parser();

        boolean isExit = false;
        while (!isExit) {
            input = in.nextLine();
            try {
                Command command = parser.parseCommand(input, warehouse);
                command.execute(); // todo remove execute input argument because unnecessary.
                isExit = command.isExit();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
