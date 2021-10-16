package seedu.duke.ui;

public class DukePredefinedMessages {

    /**
     * Print the welcome message with Duke LOGO in a message bubble.
     */
    public static void printWelcomeMessage() {
        final String dukeLogo = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        MessageBubble.printMessageBubble("Hello from\n" + dukeLogo + "What can I do for you?");
    }

    /**
     * Print the farewell message in a message bubble.
     */
    public static void printByeMessage() {
        MessageBubble.printMessageBubble("Bye. Hope to see you again soon!");
    }
}
