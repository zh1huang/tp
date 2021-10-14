package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.model.ItemContainer;
import seedu.duke.parser.Parser;
import seedu.duke.parser.exception.IllegalFormatException;
import seedu.duke.parser.exception.NoPropertyFoundException;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Duke {

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) throws Exception {

        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        logger.setLevel(Level.WARNING);

        ItemContainer warehouse = new ItemContainer("warehouse");


        String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");
        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine() + ", what can I do for you?");
        String input = in.nextLine();
        Parser parser = new Parser();
        while (!input.trim().equals("bye")) {

            Command command = parser.parseCommand(input, warehouse);
            command.execute(warehouse); // todo remove execute input argument because unnecessary.

            // prepare for next input
            input = in.nextLine();
        }
        System.out.println("See you next time");
    }
}
