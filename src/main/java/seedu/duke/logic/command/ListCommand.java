package seedu.duke.logic.command;

import seedu.duke.logic.command.exception.DeniedAccessToShelfCommandException;
import seedu.duke.logic.command.exception.EmptyListCommandException;
import seedu.duke.model.Item;
import seedu.duke.model.Shelf;
import seedu.duke.model.ShelfList;
import seedu.duke.model.exception.DeniedAccessToShelfModelException;
import seedu.duke.model.exception.IllegalArgumentModelException;
import seedu.duke.model.exception.ShelfNotExistModelException;
import seedu.duke.ui.Wrapping;

import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author zh1huang
public class ListCommand extends Command {

    public static final String LIST_ITEM_DATA_ARGS_FORMAT_STRING = "list [shlv/SHELF_NAME]";
    public static final String LIST_STRING = "list";
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static final String LIST_COMPLETE_MESSAGE = "Here is the list of items:\n";
    private static final String EMPTY_LIST_MESSAGE = "Shelf is empty";
    private static final String ITEM_INFO = " %s| %s| %s| %s| %s|   %s   \n";
    private static final String HEADER =
            "   No    |                        Item                        |   Cost    |   Price   | Qty  | Remark\n";
    private static final String BORDER =
            "-----------------------------------------------------------------------------------------------------\n";
    private static final int INDEX_TABLE_LENGTH = 8;
    private static final int ITEM_TABLE_LENGTH = 51;
    private static final int COST_TABLE_LENGTH = 10;
    private static final int PRICE_TABLE_LENGTH = 10;
    private static final int QTY_TABLE_LENGTH = 5;

    private final boolean toPrintAll;
    private String shelfName = null;
    private final ArrayList<Item> itemList;
    private final ArrayList<Integer> quantityList;

    /**
     * Constructor if ListCommand takes in shelfName as parameter.
     * ListCommand will only print out items in that specified shelf
     *
     * @param shelfName the name of shelf in which items are printed out
     */
    public ListCommand(String shelfName) {
        this.shelfName = shelfName;
        this.toPrintAll = false;
        this.itemList = new ArrayList<>();
        this.quantityList = new ArrayList<>();
    }

    /**
     * Constructor if ListCommand does not take in optional parameters.
     */
    public ListCommand() {
        this.toPrintAll = true;
        this.itemList = new ArrayList<>();
        this.quantityList = new ArrayList<>();
    }

    /**
     * Executes the list operation.
     *
     * @return Message string to be passed to UI
     * @throws ShelfNotExistModelException   If the Shelf is not in the ShelfList
     * @throws EmptyListCommandException     If list is empty
     * @throws IllegalArgumentModelException If illegal argument is entered
     */
    public String execute() throws ShelfNotExistModelException, EmptyListCommandException,
            IllegalArgumentModelException, DeniedAccessToShelfCommandException {
        String output = "";
        if (!toPrintAll) { //optional parameter entered so print that particular shelf
            try {
                Shelf selectedShelf = ShelfList
                        .getShelfList()
                        .getShelf(shelfName, true);
                if (selectedShelf.getItemCount() == 0) {
                    logger.log(Level.WARNING, "ListCommand failed to execute because shelf is empty");
                    throw new EmptyListCommandException(EMPTY_LIST_MESSAGE);
                }

                output = getOneList(selectedShelf);
            } catch (ShelfNotExistModelException e) {
                logger.log(Level.WARNING, "ListCommand failed to execute because shelf does not exist");
                throw new ShelfNotExistModelException(shelfName);
            } catch (DeniedAccessToShelfModelException e) {
                throw new DeniedAccessToShelfCommandException(e.getMessage());
            }
        } else {
            output = getEveryList();
        }
        logger.log(Level.INFO, "ListCommand successfully executed");
        return LIST_COMPLETE_MESSAGE + output;
    }

    /**
     * Generates the list for every shelf.
     *
     * @return string of all items for every shelf
     */
    private String getEveryList() {
        String output = "";
        ArrayList<Shelf> shelves = ShelfList.getShelfList().getShelves();
        for (Shelf shelf : shelves) {
            if (!Objects.equals(shelf.getName(), "soldItems")) { // soldItems will be in Sales Report
                String shelfName = shelf.getName();
                itemList.clear();
                quantityList.clear();
                output += "[" + shelfName + "]:\n" + getOneList(shelf);
            }
        }
        return output;
    }

    /**
     * Gets the list of items from specified shelf.
     *
     * @param shelf Shelf to get list of items from
     */
    private String getOneList(Shelf shelf) {
        StringBuilder output = new StringBuilder();
        output.append(HEADER + BORDER);

        for (int i = 0; i < shelf.getItemCount(); i++) {
            Item selectedItem = shelf.getItem(i);
            compileQuantity(selectedItem);
        }
        output.append(createOutput());
        return output.toString();
    }

    /**
     * Checks if 2 items are equal.
     *
     * @param item1 Item 1 to be checked
     * @param item2 Item 2 to be checked
     * @return if 2 items compared are exactly equal
     */
    private boolean isEqual(Item item1, Item item2) {
        return item1.getName().equals(item2.getName())
                && item1.getPurchaseCost().equals(item2.getPurchaseCost())
                && item1.getSellingPrice().equals(item2.getSellingPrice())
                && item1.getRemarks().equals(item2.getRemarks());
    }

    /**
     * As every item added is a different object, we use isEqual method
     * to group items of the same attributes as one single entry.
     *
     * @param item Item to be checked and grouped if quantity is more than 1
     */
    private void compileQuantity(Item item) {
        boolean hasMatch = false;
        for (Item temp : itemList) {
            if (isEqual(temp, item)) {
                hasMatch = true;
                int index = itemList.indexOf(temp);
                int newQuantity = quantityList.get(index) + 1;
                quantityList.set(index, newQuantity);
                break;
            }
        }
        if (!hasMatch) {
            itemList.add(item);
            quantityList.add(1); //if item not found in list, add as new entry with 1 as quantity
        }
    }

    /**
     * Formats every line such that the delimiters align with header.
     *
     * @param length Length of header
     * @param input  Input to be placed under that header
     * @return the formatted and aligned String entry
     */
    private String lineEntry(int length, String input) {
        int spacesWidth = length - input.length();
        String spaces = String.format("%" + spacesWidth + "s", "");

        return input + spaces;
    }

    /**
     * Creates the print output after calling updateQuantity method.
     *
     * @return the print output after grouping same items together
     */
    private String createOutput() {
        StringBuilder output = new StringBuilder();
        int count = 1;

        for (int i = 0; i < itemList.size(); i++) {
            Item selectedItem = itemList.get(i);

            String name = selectedItem.getName();
            name = Wrapping.restrictMessageLength(name, ITEM_TABLE_LENGTH); //only name is padded

            String cost = String.format("%.2f", Double.parseDouble(selectedItem.getPurchaseCost()));
            cost = lineEntry(COST_TABLE_LENGTH, cost);

            String price = String.format("%.2f", Double.parseDouble(selectedItem.getSellingPrice()));
            price = lineEntry(PRICE_TABLE_LENGTH, price);

            String quantity = String.valueOf(quantityList.get(i));
            quantity = lineEntry(QTY_TABLE_LENGTH, quantity);

            int quantityCount = quantityList.get(i);
            String paddedIndex = padIndexString(quantityCount, count);
            String indexString = lineEntry(INDEX_TABLE_LENGTH, paddedIndex);

            String remarks = selectedItem.getRemarks();
            String remarkStatus = remarks.isBlank() ? "x" : "o";

            count += quantityCount;
            output.append(String.format(ITEM_INFO, indexString, name, cost, price, quantity, remarkStatus));
        }
        return output.toString();
    }

    /**
     * Pads full index string.
     *
     * @param quantityCount quantity count of that item in itemList
     * @param count         current quantity count of itemList
     * @return string of index range
     */
    private String padIndexString(int quantityCount, int count) {
        String index;
        if (quantityCount != 1) {
            int end = count + quantityCount - 1;
            String startIndex = padIndex(count);
            String endIndex = padIndex(end);
            index = startIndex + "-" + endIndex;
        } else {
            index = padIndex(count);
        }
        return index;
    }

    /**
     * Pads each index so that they are always 3 digits
     * for consistency and clarity.
     *
     * @param count current count of itemList
     * @return padded index string of index
     */
    private String padIndex(int count) {
        String indexString;
        if (count < 10) {
            indexString = "00" + count;
        } else if (count < 100) {
            indexString = "0" + count;
        } else {
            indexString = String.valueOf(count);
        }
        return indexString;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof ListCommand)) {
            return false;
        }
        ListCommand command = (ListCommand) other;
        if (shelfName != null) {
            return shelfName.equals(command.shelfName)
                    && toPrintAll == command.toPrintAll
                    && itemList.equals(command.itemList)
                    && quantityList.equals(command.quantityList);
        }
        return toPrintAll == command.toPrintAll
                && itemList.equals(command.itemList)
                && quantityList.equals(command.quantityList);
    }
}
