package seedu.duke.ui;

//@@author yuejunfeng0909
public class DukePredefinedMessages {

    public static final String GENERAL_INVALID_COMMAND_NOTES_STRING =
            "_".repeat(111) + "\nCheck the following:\n"
            + "1. Words in UPPER_CASE are the parameters to be supplied by the user\n"
            + "2. Parameters in [] is optional & can only be specified ONCE.\n"
            + "3. For Report Command that require date input ym/YEAR-MONTH should follow input ym/YYYY-MM\n"
            + "E.g. Jan 2021 is represented as ym/2021-01.\n"
            + "4. SHELF_NAME & NAME parameters cannot contain special characters e.g.!@#$%^&*[]{}+=`~<>?,./|\\\n"
            + "5. Do not add extra parameters to commands unless specified in user guide.\n"
            + "6. Do not leave trailing spaces at the end of the command.\n"
            + "7. Program input is case-sensitive, and parameter input sequence cannot be reordered.\n"
            + "8. Program only takes in input from english character set (US-ASCII), "
            + "no input of other languages are accepted.\n"
            + "9. INDEX always starts from 1\n"
            + "For more details refer to User Guide, under Usage Section\n";

    /**
     * Print the welcome message with Duke LOGO in a message bubble.
     */
    public static void printWelcomeMessage() {
        final String dukeLogo
                = "  _____ _      _____              _____ _          _  __\n"
                + " / ____| |    |_   _|            / ____| |        | |/ _|\n"
                + "| |    | |      | |_   _____ _ _| (___ | |__   ___| | |_\n"
                + "| |    | |      | \\ \\ / / _ \\ '__\\___ \\| '_ \\ / _ \\ |  _|\n"
                + "| |____| |____ _| |\\ V /  __/ |  ____) | | | |  __/ | | \n"
                + " \\_____|______|_____\\_/ \\___|_| |_____/|_| |_|\\___|_|_| \n";
        MessageBubble.printMessageBubble("Hello from\n" + dukeLogo + "What can I do for you?");
    }

    /**
     * Print the farewell message in a message bubble.
     */
    public static void printByeMessage() {
        MessageBubble.printMessageBubble("Bye. Hope to see you again soon!");
    }


}
