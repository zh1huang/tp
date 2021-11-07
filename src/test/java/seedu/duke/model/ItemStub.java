package seedu.duke.model;

import seedu.duke.model.exception.IllegalArgumentModelException;

//@@author yuejunfeng0909
public class ItemStub extends Item {

    private final String nameStub;
    public static final String dummyID = "11111111";

    public ItemStub(String name) throws IllegalArgumentModelException {
        super("safe name", "1.0", "2.0", "");
        nameStub = name;
        setID(dummyID);
    }

    @Override
    public String getName() {
        return nameStub;
    }
}
