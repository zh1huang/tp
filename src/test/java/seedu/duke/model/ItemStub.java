package seedu.duke.model;

import seedu.duke.model.exception.IllegalModelArgumentException;

public class ItemStub extends Item {

    private final String nameStub;

    public ItemStub(String name) throws IllegalModelArgumentException {
        super("safe name", "1.0", "2.0", "");
        nameStub = name;
    }

    @Override
    public String getName() {
        return nameStub;
    }
}
