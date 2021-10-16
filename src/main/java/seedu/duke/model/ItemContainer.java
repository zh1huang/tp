package seedu.duke.model;

import seedu.duke.model.exception.DuplicateItemContainerException;
import seedu.duke.model.exception.DuplicateItemException;
import seedu.duke.model.exception.IllegalArgumentException;
import seedu.duke.model.exception.ItemNotExistException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents a container that is able to store items.
 * e.g. A shelf
 */
public class ItemContainer {

    public static final String MESSAGE_INVALID_NAME_FORMAT = "Invalid item container name";
    public static final String MESSAGE_NULL_ITEM_ADDITION = "Null item cannot be added";
    public static final String ITEM_DESCRIPTION = "name: %s\nselling price: %s\npurchase cost: %s";

    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private String name;
    private final ArrayList<Item> items;

    /**
     * Constructor for the ItemContainer class.
     *
     * @param name new name for the ItemContainer
     *             consists of alphabet, number, space, underscore, round bracket and hyphen
     * @throws IllegalArgumentException if the name contains other characters
     */
    public ItemContainer(String name) throws IllegalArgumentException, DuplicateItemContainerException {
        ContainerList containerList = ContainerList.getContainerList();
        if (containerList.existContainer(name)) {
            throw new DuplicateItemContainerException(name);
        }
        setName(name);
        items = new ArrayList<>();
        containerList.addContainer(this);
        logger.log(Level.INFO, String.format("ItemContainer %s created", name));
    }

    public void deleteItemContainer() {
        ContainerList.getContainerList().deleteContainer(this);
    }

    /**
     * Returns the name of the ItemContainer. e.g. Shelf-Scifi
     *
     * @return name of the ItemContainer
     */
    public String getName() {
        return name;
    }

    /**
     * Rename the ItemContainer to the new name.
     *
     * @param name New name
     *             consists of alphabet, number, space, underscore, round bracket and hyphen
     * @throws IllegalArgumentException if the name contains other characters
     */
    public void setName(String name) throws IllegalArgumentException, DuplicateItemContainerException {
        if (name.matches("[a-zA-Z0-9 _()-]+") && !name.isBlank()) {
            if (ContainerList.getContainerList().existContainer(name)) {
                throw new DuplicateItemContainerException(name);
            }
            String temp = this.getName();
            this.name = name;
            logger.log(Level.INFO, String.format("Successfully set ItemContainer %s's name as %s", temp, name));
        } else {
            logger.log(Level.WARNING, String.format(
                "Trying to set ItemContainer %s's name as %s",
                this.getName(), name));
            throw new IllegalArgumentException(MESSAGE_INVALID_NAME_FORMAT);
        }
    }

    /**
     * Adds the Item to the ItemContainer.
     *
     * @param item The item to be added
     * @throws DuplicateItemException If the item already exists in the ItemContainer
     */
    public void addItem(Item item) throws DuplicateItemException {
        if (!contains(item)) {
            items.add(item);
            logger.log(Level.INFO, String.format("Successfully added Item %s into ItemContainer %s",
                item.getName(), this.getName()));
        } else {
            logger.log(Level.WARNING, String.format("Item %s already exists in ItemContainer %s",
                item.getName(), this.getName()));
            throw new DuplicateItemException(item.getName());
        }
    }

    /**
     * Remove the reference of the Item from the ItemContainer.
     *
     * @param item The Item to be removed from the ItemContainer
     * @throws ItemNotExistException If the Item does not exist
     */
    public void deleteItem(Item item) throws ItemNotExistException {
        if (item == null) {
            logger.log(Level.WARNING, String.format("Trying to delete Null item from ItemContainer %s",
                this.getName()));
            throw new NullPointerException();
        } else if (!contains(item)) {
            logger.log(Level.WARNING, String.format("Trying to delete Item %s which does not exist in ItemContainer %s",
                item.getName(), this.getName()));
            throw new ItemNotExistException(item.getName());
        }
        items.remove(item);
    }

    /**
     * Replace an Item in the ItemContainer with another Item.
     *
     * @param originalItem The Item that is in the ItemContainer
     * @param updatedItem  The replacement Item
     * @throws ItemNotExistException  If the originalItem does not exist in the ItemContainer
     * @throws DuplicateItemException if the updatedItem already exist in the ItemContainer
     */
    public void updateItem(Item originalItem, Item updatedItem)
            throws DuplicateItemException, ItemNotExistException {
        int index = items.indexOf(originalItem);
        if (originalItem == null) {
            logger.log(Level.WARNING, String.format("Trying to update Null item from ItemContainer %s",
                this.getName()));
            throw new NullPointerException();
        } else if (contains(updatedItem)) {
            logger.log(Level.WARNING, String.format(
                "Trying to replace Item %s with %s, which already exists in the ItemContainer %s",
                originalItem.getName(), updatedItem.getName(), this.getName()));
            throw new DuplicateItemException(updatedItem.getName());
        } else if (index == -1) {
            logger.log(Level.WARNING, String.format(
                "Trying to replace Item %s, which does not exist in ItemContainer %s",
                originalItem.getName(), this.getName()));
            throw new ItemNotExistException(originalItem.getName());
        }
        items.set(index, updatedItem);
        assert items.get(index) == updatedItem : "Updated item should be at the index of the original item";
        logger.log(Level.INFO, String.format(
            "Successfully replace Item %s with %s in the ItemContainer %s",
            originalItem.getName(), updatedItem.getName(), this.getName()));
    }

    /**
     * Search through the ItemContainer and returns the first Item with the specified name.
     *
     * @param name The specified name of Item
     * @return Item with the specified name
     * @throws ItemNotExistException if no item has the name
     */
    public Item getItem(String name) throws ItemNotExistException {
        if (name == null) {
            logger.log(Level.WARNING, String.format("Trying to get Null item from ItemContainer %s",
                this.getName()));
            throw new NullPointerException();
        }
        for (Item item : items) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        logger.log(Level.WARNING, String.format("Item %s is not fond in the ItemContainer %s",
            name, this.getName()));
        throw new ItemNotExistException(name);
    }

    /**
     * Return the Item at the specified index.
     *
     * @param index The index of the Item
     * @return The Item at the specified index
     * @throws IndexOutOfBoundsException if index >= number of items in the item container
     */
    public Item getItem(int index) {
        return items.get(index);
    }

    /**
     * Returns true if there is an Item in the ItemContainer with the specified name.
     *
     * @param name Name of the item
     * @return True if the item exists
     */
    public boolean contains(String name) {
        try {
            getItem(name);
        } catch (ItemNotExistException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns true if there is an Item in the ItemContainer with the specified name.
     *
     * @param item the specified item
     * @return True if the item exists
     */
    public boolean contains(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        return items.contains(item);
    }

    /**
     * Returns the number if items in the item container.
     */
    public int getItemCount() {
        return items.size();
    }

    /**
     * Returns a string of names of the items in the ItemContainer separated by "\n".
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < items.size();  i++) {
            output.append((i + 1) + ". " + items.get(i).getName() + "\n");
        }
        return output.toString().trim();
    }

    /**
     * Get the number of items in this container.
     *
     * @return the number of items in this container
     */
    public int getSize() {
        return items.size();
    }
}
