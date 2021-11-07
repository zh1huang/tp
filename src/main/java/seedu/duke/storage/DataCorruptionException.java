package seedu.duke.storage;

import seedu.duke.model.ShelfList;
import seedu.duke.model.exception.DeniedAccessToShelfModelException;
import seedu.duke.model.exception.DuplicateItemModelException;
import seedu.duke.model.exception.DuplicateShelfModelException;
import seedu.duke.model.exception.IllegalArgumentModelException;

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
        } catch (DuplicateShelfModelException | DuplicateItemModelException | IllegalArgumentModelException
                | DeniedAccessToShelfModelException ex) {
            ex.printStackTrace();
        }
    }
}
