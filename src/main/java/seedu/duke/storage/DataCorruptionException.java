package seedu.duke.storage;

import seedu.duke.model.ShelfList;
import seedu.duke.model.exception.DuplicateItemException;
import seedu.duke.model.exception.DuplicateShelfException;
import seedu.duke.model.exception.IllegalModelArgumentException;

//@@author yuejunfeng0909
public class DataCorruptionException extends Exception {
    DataCorruptionException() {
        super("Data was corrupted.");
        // load sample data
        Storage storage = Storage.getStorageManager();
        ShelfList shelfList = ShelfList.getShelfList();
        shelfList.resetShelfList();
        try {
            storage.loadFromJson(storage.sampleData());
        } catch (DuplicateShelfException | DuplicateItemException | IllegalModelArgumentException ex) {
            ex.printStackTrace();
        }
    }
}
