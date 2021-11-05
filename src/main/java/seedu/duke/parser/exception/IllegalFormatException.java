package seedu.duke.parser.exception;

import seedu.duke.ui.DukePredefinedMessages;

public class IllegalFormatException extends Exception {
    public IllegalFormatException(String message) {
        super(message + DukePredefinedMessages.GENERAL_INVALID_COMMAND_NOTES_STRING);
    }
}
