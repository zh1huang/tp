package seedu.duke.ui;

//@@author yuejunfeng0909
public class MessageBubble {

    static final String EDGE_HORIZONTAL_SYMBOL = ".";
    static final String EDGE_VERTICAL_SYMBOL = ":";
    static final String EDGE_TAIL = "...";
    static int MAX_LINES = 1000;
    static int TERMINAL_WIDTH = 130;
    static int MAX_BUBBLE_WIDTH = 125;
    static int MAX_MESSAGE_LENGTH = MAX_BUBBLE_WIDTH - EDGE_TAIL.length() - 4;

    private final String[] messages;
    private int messagesCount;
    private int maxMessageLengthInBubble;
    private int bubbleWidth;

    /**
     * Convenience MessageBubble constructor.
     */
    public MessageBubble() {
        messages = new String[MAX_LINES];
        messagesCount = 0;
        maxMessageLengthInBubble = 0;
        bubbleWidth = 0;
    }

    public MessageBubble(String msg) {
        this();
        addMessage(msg);
    }

    /**
     * Print a message bubble with the given messages.
     *
     * @param msg messages to be printed
     */
    public static void printMessageBubble(String msg) {
        MessageBubble temp = new MessageBubble();
        temp.addMessage(msg);
        temp.printMessageBubble();
    }

    /**
     * Print the message bubble with all messages inside it.
     */
    public void printMessageBubble() {
        printTopEdge();
        printMessage(messages);
        printBottomEdge();
    }

    public int getMessagesCount() {
        return messagesCount;
    }

    /**
     * Add a new line, or multiple lines that are separated by "\n", to the MessageBubble.
     *
     * @param msg message(s) to be added
     */
    public void addMessage(String msg) {
        for (String line : msg.split("\n")) {
            if (line.length() < MAX_MESSAGE_LENGTH) {
                updateBubbleWidth(line);
                messages[messagesCount++] = line;
            } else {
                Wrapping autoWrap = new Wrapping(line, MAX_MESSAGE_LENGTH);
                while (!autoWrap.isEmpty()) {
                    String extracted = autoWrap.nextLine();
                    updateBubbleWidth(extracted);
                    messages[messagesCount++] = extracted;
                }
            }
        }
    }

    private void updateBubbleWidth(String msg) {
        if (msg.length() > maxMessageLengthInBubble) {
            maxMessageLengthInBubble = msg.length();
            bubbleWidth = maxMessageLengthInBubble + 4 + EDGE_TAIL.length();
        }
    }

    private void printTopEdge() {
        System.out.println(printSpacerBeforeBubble()
                + EDGE_HORIZONTAL_SYMBOL.repeat(bubbleWidth - EDGE_TAIL.length()));
    }

    private void printBottomEdge() {
        System.out.println(printSpacerBeforeBubble()
                + EDGE_HORIZONTAL_SYMBOL.repeat(bubbleWidth - EDGE_TAIL.length()) + EDGE_TAIL);
    }

    private String printSpacerBeforeBubble() {
        return " ".repeat(TERMINAL_WIDTH - bubbleWidth);
    }

    /**
     * Print the message with indentation.
     *
     * @param msg message to be printed
     */
    private void printMessage(String msg) {
        System.out.println(printSpacerBeforeBubble() + EDGE_VERTICAL_SYMBOL + " "
                + msg
                + " ".repeat(maxMessageLengthInBubble - msg.length()) + " " + EDGE_VERTICAL_SYMBOL);
    }

    /**
     * Print the messages each in one line, with indentation.
     *
     * @param messages messages to be printed
     */
    public void printMessage(String[] messages) {
        for (int i = 0; i < messagesCount; i++) {
            printMessage(messages[i]);
        }
    }
}
