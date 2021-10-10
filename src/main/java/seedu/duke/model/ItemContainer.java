package seedu.duke.model;

import java.util.ArrayList;

/**
 * Represents a container that is able to store items.
 * e.g. A shelf
 */
public class ItemContainer {
    String name;
    ArrayList<Item> items;

    ItemContainer(String name) {
        setName(name);
        items = new ArrayList<Item>();
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
     */
    // todo check format
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Adds the Item to the ItemContainer.
     *
     * @param item The item to be added
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * Remove the reference of the Item from the ItemContainer.
     *
     * @param item The Item to be removed from the ItemContainer
     * @throws NullPointerException If the Item does not belong to the ItemContainer
     */
    public void deleteItem(Item item) throws NullPointerException {
        items.remove(item);
    }

    /**
     * Replace an Item in the ItemContainer with another Item.
     *
     * @param originalItem The Item that is in the ItemContainer
     * @param updatedItem  The replacement Item
     */
    public void updateItem(Item originalItem, Item updatedItem) {
        try {
            int index = items.indexOf(originalItem);
            items.set(index, updatedItem);
        } catch (NullPointerException e) {
            // todo throw exception cuz no item found
        }
    }

    /**
     * Search through the ItemContainer and returns the first Item with the specified name.
     *
     * @param name The specified name of Item
     * @return Item with the specified name
     */
    public Item getItem(String name) {
        for (Item item : items) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        // todo throw exception cuz no item found
        return null;
    }

    /**
     * Return the Item at the specified index.
     *
     * @param index The index of the Item
     * @return The Item at the specified index
     */
    public Item getItem(int index) {
        try {
            return items.get(index);
        } catch (IndexOutOfBoundsException e) {
            // todo item does not exist at the index
        }
        return null;
    }

    /**
     * Returns true if there is an Item in the ItemContainer with the specified name.
     *
     * @param name Name of the item
     * @return True if the item exists
     */
    public boolean contains(String name) {
        return getItem(name) != null;
    }

    /**
     * Returns a String of names of the items in the ItemContainer separated by "\n".
     */
    public String printItemContainer() {
        String output = "";
        for (Item temp : items) {
            output += temp.getName();
        }
        return output;
    }
}
