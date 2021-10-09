package seedu.duke.model;

import seedu.duke.command.exception.CommandException;

import java.util.ArrayList;

public class ItemContainer {
    String name;
    ArrayList<Item> items;

    ItemContainer (String name) {
        setName(name);
        items = new ArrayList<Item>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { // todo check format
        this.name = name;
    }

    public void addItem(Item item) throws CommandException {
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
            items.remove(index);
            items.add(index, updatedItem);
        } catch (NullPointerException e) {
            // todo throw exception cuz no item found
        }
    }

    public Item getItem(String name) {
        for (Item item: items) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        // todo throw exception cuz no item found
        return null;
    }

    public boolean contains(String name) {
        if (getItem(name) != null) {
            return true;
        }
        return false;
    }

    public void printItemContainer() {

    }
}
