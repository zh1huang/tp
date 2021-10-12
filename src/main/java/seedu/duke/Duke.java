package seedu.duke;

import seedu.duke.model.Item;
import seedu.duke.model.ItemContainer;
import seedu.duke.model.exception.InvalidFormat;

import java.math.BigDecimal;
import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static String userInputSample = "add n/Geronimo c/book p/$19 q/1";
    public static String LIST_userInputSample = "list c/all";
    public static ItemContainer container;


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");
        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());

        try {
            container = new ItemContainer("test");
        } catch (InvalidFormat e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                String input = in.nextLine();
                String output = Parser.parseCommand(input);
                System.out.println(output);
                new Item("HarryPotter", new BigDecimal("16.1"), new BigDecimal("25.12"), container);
            //todo implement exit command
            } catch (InvalidFormat e) {
                e.printStackTrace();
            }
        }
    }
}
