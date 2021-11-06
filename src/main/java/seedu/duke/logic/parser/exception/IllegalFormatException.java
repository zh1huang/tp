package seedu.duke.logic.parser.exception;

import seedu.duke.ui.PredefinedMessages;

public class IllegalFormatException extends Exception {

    public IllegalFormatException(String message) {
        super(message + PredefinedMessages.GENERAL_INVALID_COMMAND_NOTES_STRING);
    }
}
