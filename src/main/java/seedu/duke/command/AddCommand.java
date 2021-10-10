package seedu.duke.command;

import java.math.BigDecimal;

public class AddCommand extends Command {
    private final ItemContainer list;
    private final Item newItem;

    public AddCommand(String name, BigDecimal purchaseCost, BigDecimal sellingPrice, ItemContainer list) {
        this.newItem = new Item(name, purchaseCost, sellingPrice, list);
        this.list = list;
    }

    public void execute() {
        
    }
}
