package seedu.duke.command;

import seedu.duke.command.exception.ItemNotExistException;
import seedu.duke.model.Item;
import seedu.duke.model.Shelf;

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
     * @param list the Shelf in which information of item is retrieved
     * @return information of item
     * @throws ItemNotExistException if specified item does not exist
     */
    private String getInfo(Shelf list) throws ItemNotExistException {
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
     * @param list the Shelf that manipulates the item
     * @throws ItemNotExistException if specified item does not exist
     */
    public void execute(Shelf list) throws ItemNotExistException {
        String info = getInfo(list);
        System.out.println(info);
    }
}
