package seedu.duke.storage;

import seedu.duke.model.ShelfList;
import seedu.duke.model.exception.DuplicateItemException;
import seedu.duke.model.exception.DuplicateShelfException;
import seedu.duke.model.exception.IllegalArgumentException;

public class DataCorruptionException extends Exception {
    DataCorruptionException() {
        super("Data was corrupted.");
        // load sample data
        Storage storage = Storage.getStorageManager();
        ShelfList shelfList = ShelfList.getShelfList();
        shelfList.resetShelfList();
        try {
            storage.loadFromJson(storage.sampleData());
        } catch (DuplicateShelfException | DuplicateItemException | IllegalArgumentException ex) {
            ex.printStackTrace();
        }
    }
}
