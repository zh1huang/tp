package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.model.Shelf;
import seedu.duke.model.ShelfList;
import seedu.duke.parser.Parser;
import seedu.duke.storage.Storage;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Duke {

    private static final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
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

        Storage storage = new Storage();
        try {
            storage.loadData();
        } catch (Exception e) {
            // todo warn user cannot load data/ initializing data
        }

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
                Command command = parser.parseCommand(input, ShelfList.getShelfList().getShelf("warehouse"));
                // todo remove execute input argument because unnecessary.
                command.execute(ShelfList.getShelfList().getShelf("warehouse"));
                isExit = command.isExit();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            storage.saveData();
        }
    }
}
