package seedu.duke.model.exception;

public class SKUError extends Exception {
    String errorMessage;
    public SKUError (String message) {
        super(message);
        errorMessage = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
