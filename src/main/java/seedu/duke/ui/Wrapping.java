package seedu.duke.ui;

import java.util.ArrayList;

//@@author yuejunfeng0909
public class Wrapping {

    private final String originalMessage;
    private final int lineLimit;
    private ArrayList<String> lines;

    public Wrapping(String message, int lineLimit) {
        lines = new ArrayList<>();
        originalMessage = message;
        this.lineLimit = lineLimit;
        autoWrap();
    }

    private void autoWrap() {
        String temp = originalMessage;
        outerLoop:
        while (!temp.isBlank()) {
            // check if longer than line limit
            if (temp.length() <= lineLimit) {
                lines.add(temp);
                break;
            }
            for (int i = lineLimit; i >= 0; i--) {
                if (temp.charAt(i) == ' ') {
                    lines.add(temp.substring(0, i));
                    temp = temp.substring(i + 1);
                    continue outerLoop;
                }
            }
            // if cannot find space, hard wrap
            lines.add(temp.substring(0, lineLimit - 1) + "-");
            temp = temp.substring(lineLimit - 1);
        }
    }

    public String nextLine() {
        if (lines.size() == 0) {
            return "";
        }
        return lines.remove(0);
    }

    public boolean isEmpty() {
        return lines.isEmpty();
    }

    public static String restrictMessageLength(String message, int length) {
        if (message.length() <= length) {
            return message + " ".repeat(length - message.length());
        }
        return message.substring(0, length - 3) + "...";
    }
}
