package seedu.duke.model;

import seedu.duke.model.exception.DuplicateItemException;
import seedu.duke.model.exception.InvalidFormatException;

import java.util.ArrayList;

/**
 * Represents a container that is able to store items.
 * e.g. A shelf
 */
public class ItemContainer {

    public static final String MESSAGE_INVALID_NAME_FORMAT = "Invalid item container name";
    public static final String MESSAGE_NULL_ITEM_ADDITION = "Null item cannot be added";
    public static final String MESSAGE_ITEM_NOT_EXIST = "%s does not exist in the item container";

    private String name;
    private final ArrayList<Item> items;

    /**
     * Constructor for the ItemContainer class.
     *
     * @param name new name for the ItemContainer
     *             consists of alphabet, number, space, underscore, round bracket and hyphen
     * @throws InvalidFormatException if the name contains other characters
     */
    ItemContainer(String name) throws InvalidFormatException {
        setName(name);
        items = new ArrayList<>();
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
     * @throws InvalidFormatException if the name contains other characters
     */
    public void setName(String name) throws InvalidFormatException {
        if (name.matches("[a-zA-Z0-9 _()-]+") && !name.isBlank()) {
            this.name = name;
        } else {
            throw new InvalidFormatException(MESSAGE_INVALID_NAME_FORMAT);
        }
    }

    /**
     * Adds the Item to the ItemContainer.
     *
     * @param item The item to be added
     * @throws NullPointerException   if item is null
     * @throws DuplicateItemException if the item already exists in the ItemContainer
     */
    public void addItem(Item item) {
        if (item == null) {
            throw new NullPointerException(MESSAGE_NULL_ITEM_ADDITION);
        } else if (items.contains(item)) {
            throw new DuplicateItemException(item.getName());
        }
        items.add(item);
    }

    /**
     * Remove the reference of the Item from the ItemContainer.
     *
     * @param item The Item to be removed from the ItemContainer
     * @throws NullPointerException If the Item does not exist in the ItemContainer
     */
    public void deleteItem(Item item) throws NullPointerException {
        if (!items.remove(item)) {
            throw new NullPointerException(String.format(MESSAGE_ITEM_NOT_EXIST, item.getName()));
        }
    }

    /**
     * Replace an Item in the ItemContainer with another Item.
     *
     * @param originalItem The Item that is in the ItemContainer
     * @param updatedItem  The replacement Item
     * @throws NullPointerException If the originalItem does not exist in the ItemContainer
     * @throws DuplicateItemException if the updatedItem already exist in the ItemContainer
     */
    public void updateItem(Item originalItem, Item updatedItem) throws NullPointerException, DuplicateItemException {
        int index = items.indexOf(originalItem);
        if (index == -1 || updatedItem == null) {
            throw new NullPointerException(String.format(MESSAGE_ITEM_NOT_EXIST, originalItem.getName()));
        }
        if (items.contains(updatedItem)) {
            throw new DuplicateItemException(updatedItem.getName());
        }
        items.set(index, updatedItem);
        assert items.get(index) == updatedItem : "Updated item should be at the index of the original item";
    }

    /**
     * Search through the ItemContainer and returns the first Item with the specified name.
     *
     * @param name The specified name of Item
     * @return Item with the specified name
     * @throws NullPointerException if no item has the name
     */
    public Item getItem(String name) {
        for (Item item : items) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        throw new NullPointerException(String.format(MESSAGE_ITEM_NOT_EXIST, name));
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
        } catch (NullPointerException e) {
            return false;
        }
        return true;
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
        for (Item temp : items) {
            output.append(temp.getName() + "\n");
        }
        return output.toString().trim();
    }
}
