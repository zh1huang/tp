package seedu.duke.model;

import seedu.duke.model.exception.IllegalArgumentException;

public class ItemStub extends Item {

    private final String nameStub;

    public ItemStub(String name) throws IllegalArgumentException {
        super("safe name", "1.0", "2.0", "");
        nameStub = name;
    }

    @Override
    public String getName() {
        return nameStub;
    }
}
