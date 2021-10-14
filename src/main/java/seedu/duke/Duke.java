package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.model.ItemContainer;
import seedu.duke.model.exception.IllegalArgumentException;
import seedu.duke.parser.Parser;

import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
//    public static ItemContainer itemContainer;
//
//    {
//        try {
//            itemContainer = new ItemContainer("test");
//        } catch (IllegalArgumentException e) {
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");
        Scanner in = new Scanner(System.in);

//        while (true) {
//            try {
//                Parser p = new Parser();
//                String inputLine = in.nextLine();
//                if (inputLine.toLowerCase().equals("bye")) {
//                    break;
//                }
//                Command command = p.parseCommand(inputLine, itemContainer);
//                command.execute(itemContainer);
//
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        }
//        System.out.println("------------Done-----------\n");
        System.out.println("Hello " + in.nextLine());

    }
}
