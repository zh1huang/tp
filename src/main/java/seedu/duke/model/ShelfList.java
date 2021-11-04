package seedu.duke.model;

import seedu.duke.model.exception.DuplicateShelfException;
import seedu.duke.model.exception.IllegalModelArgumentException;
import seedu.duke.model.exception.ItemNotExistException;
import seedu.duke.model.exception.ShelfNotExistException;

import java.util.ArrayList;
import java.util.Comparator;

//@@author yuejunfeng0909
/**
 * Represents a collection of ItemContainers.
 * Provides methods to better manage ItemContainers.
 * Implemented using Singleton pattern.
 */
public class ShelfList {

    private static ShelfList shelfList;
    private ArrayList<Shelf> shelves;

    private ShelfList() {
        shelves = new ArrayList<Shelf>();
    }

    public static ShelfList getShelfList() {
        if (shelfList == null) {
            shelfList = new ShelfList();
        }
        return shelfList;
    }

    public ArrayList<Shelf> getShelves() {
        return shelves;
    }

    public void resetShelfList() {
        shelves = new ArrayList<>();
    }

    /**
     * Adds the specified shelf to the ShelfList.
     * (not recommended, as this method skips some essential checks)
     *
     * @param shelf The Shelf to be added
     */
    protected void addShelf(Shelf shelf) {
        shelves.add(shelf);
        Comparator<Shelf> byName = Comparator.comparing((Shelf o) -> o.getName().toLowerCase());
        shelves.sort(byName);
    }

    /**
     * Creates a new Shelf with the specified name in the ShelfList.
     *
     * @param name The name of the new Shelf
     *
     * @throws IllegalModelArgumentException if name does not follow the format
     * @throws DuplicateShelfException       if there already exist a Shelf with this name
     */
    public Shelf addShelf(String name) throws IllegalModelArgumentException, DuplicateShelfException {
        if (existShelf(name)) {
            throw new DuplicateShelfException(name);
        }

        Shelf temp = new Shelf(name);
        return temp;
    }

    /**
     * Remove the item container from the ShelfList.
     *
     * @param shelf The Shelf to be removed
     *
     * @throws ShelfNotExistException If the Shelf is not in the ShelfList
     */
    protected void deleteShelf(Shelf shelf) throws ShelfNotExistException {
        if (!shelves.remove(shelf)) {
            throw new ShelfNotExistException(shelf.getName());
        }
        assert !shelves.contains(shelf);
    }

    /**
     * Remove the Shelf with the specified name from the ShelfList.
     *
     * @param name Name of the Shelf to be removed
     *
     * @throws ShelfNotExistException If no Shelf in the ShelfList has the specified name
     */
    public void deleteShelf(String name) throws ShelfNotExistException {
        shelves.remove(getShelf(name, true));
    }



    /**
     * Returns the Shelf with the specified name.
     *
     * @param name The name of the Shelf
     *
     * @return The Shelf that matches the specified name
     *
     * @throws ShelfNotExistException If no Shelf in the ShelfList has the specified name
     */
    public Shelf getShelf(String name) throws ShelfNotExistException {
        for (Shelf container : shelves) {
            if (container.getName().equals(name)) {
                return container;
            }
        }
        throw new ShelfNotExistException(name);
    }

    /**
     * OverLoaded getShelf method that can hide the soldItem shelf.
     * @param name the name of the target shelf
     * @param isSoldItemHidden whether to hide the soldItem shelf
     * @return the target shelf
     * @throws ShelfNotExistException if no such shelf exists.
     */
    public Shelf getShelf(String name, boolean isSoldItemHidden) throws ShelfNotExistException {
        if (name.equals("soldItems") && isSoldItemHidden) {
            throw new ShelfNotExistException(name);
        }
        for (Shelf container : shelves) {
            if (container.getName().equals(name)) {
                return container;
            }
        }
        throw new ShelfNotExistException(name);
    }

    /**
     * Check if there exists an item container with the specified name.
     *
     * @param name name of the item container
     *
     * @return true if there exists
     */
    public boolean existShelf(String name) {
        try {
            getShelf(name, true);
        } catch (ShelfNotExistException e) {
            return false;
        }
        return true;
    }

    /**
     * Return the total number of shelves.
     *
     * @return Number of shelves
     */
    public int getNumberOfShelves() {
        return shelves.size();
    }

    /**
     * Get the names of all Shelf.
     *
     * @return name of all Shelf, separated by "\n"
     */
    public String getAllShelvesName() {
        StringBuilder temp = new StringBuilder();
        for (Shelf container : shelves) {
            temp.append(container.getName());
            temp.append("\n");
        }
        return temp.toString().trim();
    }

    /**
     * Return the Shelf that is storing the specified Item.
     *
     * @param item The target item
     *
     * @return The Shelf that contains the item
     *
     * @throws ItemNotExistException If the item does not belong to any Shelf
     */
    public Shelf shelfOfItem(Item item) throws ItemNotExistException {
        for (Shelf shelf : shelves) {
            if (shelf.contains(item)) {
                return shelf;
            }
        }
        throw new ItemNotExistException(item.getName());
    }


    /**
     * Get a unique item from the list of shelves.
     *
     * @param itemID The ID of the target item
     *
     * @return the item if it exists
     *
     * @throws ItemNotExistException if the target item with the given ID does not exist
     */
    public Item getItem(String itemID) throws ItemNotExistException {
        for (Shelf shelf : shelves) {
            if (shelf.containsGivenID(itemID)) {
                return shelf.getItemByID(itemID);
            }
        }
        throw new ItemNotExistException("with ID: " + itemID);
    }
}
