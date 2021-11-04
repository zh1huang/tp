package seedu.duke.ui;

//@@author yuejunfeng0909
public class DukePredefinedMessages {

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
