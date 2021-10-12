package seedu.duke.model;

public class ItemStub extends Item {

    private final String nameStub;

    public ItemStub(String name) {
        super("safe name", "1.0", "2.0");
        nameStub = name;
    }

    @Override
    public String getName() {
        return nameStub;
    }
}
