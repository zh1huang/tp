package seedu.duke.command;

import seedu.duke.command.exception.ItemNotExistException;
import seedu.duke.model.Item;
import seedu.duke.model.ItemContainer;

public class GetCommand extends Command {

    private final String name;

    /**
     * Constructor for GetCommand.
     *
     * @param name the name of selected item
     */
    public GetCommand(String name) {
        this.name = name;
    }

    /**
     * Retrieves information of item from list.
     *
     * @param list the ItemContainer in which information of item is retrieved
     * @return information of item
     * @throws ItemNotExistException if specified item does not exist
     */
    private String getInfo(ItemContainer list) throws ItemNotExistException {
        try {
            int initialSize = list.getSize();
            Item selectedItem = list.getItem(name);
            assert initialSize == list.getSize();
            return "name: " + selectedItem.getName() + "\nselling price: " + selectedItem.getSellingPrice()
                    + "\npurchase cost: " + selectedItem.getPurchaseCost();
        } catch (seedu.duke.model.exception.ItemNotExistException e) {
            throw new ItemNotExistException(e.getMessage());
        }
    }

    /**
     * Executes the operation of retrieving information of specified item.
     *
     * @param list the ItemContainer that manipulates the item
     * @throws ItemNotExistException if specified item does not exist
     */
    public void execute(ItemContainer list) throws ItemNotExistException {
        String info = getInfo(list);
        System.out.println(info);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof GetCommand)) {
            return false;
        }

        GetCommand command = (GetCommand) other;
        return name.equals(command.name);
    }
}
