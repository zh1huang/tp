package seedu.duke.ui;

//@@author yuejunfeng0909
public class PredefinedMessages {

    public static final String GENERAL_INVALID_COMMAND_NOTES_STRING =
            "_".repeat(111) + "\nCheck the following:\n"
                    + "1. Please strictly adhere to the input formats of each command. "
                    + "You can refer to the User Guide for more details.\n"
                    + "2. Words in UPPER_CASE are the parameters to be supplied by the user. "
                    + "Only US-ASCII characters are allowed.\n"
                    + "3. Parameters in [] are optional & can only be specified ONCE.\n"
                    + "4. Command input is case-sensitive.\n"
                    + "5. SHELF_NAME & NAME & KEYWORD parameters cannot contain special characters "
                    + "e.g.!@#$%^&*[]{}+=`~<>?,./|\\\n";

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
