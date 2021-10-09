package seedu.duke.model;

import java.util.ArrayList;

public class ItemContainer {
    String name;
    ArrayList<Item> items;

    ItemContainer(String name) {
        setName(name);
        items = new ArrayList<Item>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { // todo check format
        this.name = name;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void deleteItem(Item item) {
        try {
            items.remove(item);
        } catch (NullPointerException e) {
            // todo throw exception cuz the item does not exist in the container
        }
    }

    public void updateItem(Item originalItem, Item updatedItem) {
        try {
            int index = items.indexOf(originalItem);
            items.set(index, updatedItem);
        } catch (NullPointerException e) {
            // todo throw exception cuz no item found
        }
    }

    public Item getItem(String name) {
        for (Item item : items) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        // todo throw exception cuz no item found
        return null;
    }

    public Item getItem(int index) {
        try {
            return items.get(index);
        } catch (IndexOutOfBoundsException e) {
            // todo item does not exist at the index
        }
        return null;
    }

    public boolean contains(String name) {
        return getItem(name) != null;
    }

    // todo add printItemContainer() method
    public void printItemContainer() {

    }
}
