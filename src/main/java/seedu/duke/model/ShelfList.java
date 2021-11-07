package seedu.duke.model;


import seedu.duke.model.exception.DeniedAccessToShelfModelException;
import seedu.duke.model.exception.DuplicateShelfModelException;
import seedu.duke.model.exception.IllegalArgumentModelException;
import seedu.duke.model.exception.ShelfNotExistModelException;
import seedu.duke.model.exception.ItemNotExistModelException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author yuejunfeng0909
/**
 * Represents a collection of Shelfs.
 * Provides methods to better manage Shelf items.
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
        Logger.getLogger(Logger.GLOBAL_LOGGER_NAME)
                .log(Level.INFO, String.format("%s added to shelfList", shelf.getName()));
        shelves.add(shelf);
        Comparator<Shelf> byName = Comparator.comparing((Shelf o) -> o.getName().toLowerCase());
        shelves.sort(byName);
    }

    /**
     * Creates a new Shelf with the specified name in the ShelfList.
     *
     * @param name The name of the new Shelf
     * @throws IllegalArgumentModelException if name does not follow the format
     * @throws DuplicateShelfModelException  if there already exist a Shelf with this name
     */
    public Shelf addShelf(String name) throws IllegalArgumentModelException, DuplicateShelfModelException,
            DeniedAccessToShelfModelException {
        if (existShelf(name)) {
            throw new DuplicateShelfModelException(name);
        }

        Shelf temp = new Shelf(name);
        return temp;
    }

    /**
     * Creates a new Shelf with the specified name in the ShelfList. Need to specify whether can create soldItems shelf.
     *
     * @param name The name of the new Shelf
     * @param isDeniedAccessToSoldItems Whether can create soldItems shelf
     * @throws IllegalArgumentModelException if name does not follow the format
     * @throws DuplicateShelfModelException  if there already exist a Shelf with this name
     */
    public Shelf addShelf(String name, boolean isDeniedAccessToSoldItems) throws IllegalArgumentModelException,
            DuplicateShelfModelException,
            DeniedAccessToShelfModelException {
        if (existShelf(name)) {
            throw new DuplicateShelfModelException(name);
        }
        if (name.equals("soldItems") && isDeniedAccessToSoldItems) {
            throw new DeniedAccessToShelfModelException(name);
        }

        Shelf temp = new Shelf(name);
        return temp;
    }

    /**
     * Remove the specified Shelf from the ShelfList.
     *
     * @param shelf The Shelf to be removed
     * @throws ShelfNotExistModelException If the Shelf is not in the ShelfList
     */
    protected void deleteShelf(Shelf shelf) throws ShelfNotExistModelException {
        if (!shelves.remove(shelf)) {
            throw new ShelfNotExistModelException(shelf.getName());
        }
        assert !shelves.contains(shelf);
    }

    /**
     * Remove the Shelf with the specified name from the ShelfList.
     *
     * @param name Name of the Shelf to be removed
     * @throws ShelfNotExistModelException If no Shelf in the ShelfList has the specified name
     */
    public void deleteShelf(String name) throws ShelfNotExistModelException, DeniedAccessToShelfModelException {
        shelves.remove(getShelf(name, true));
    }


    /**
     * Returns the Shelf with the specified name.
     *
     * @param name The name of the Shelf
     * @return The Shelf that matches the specified name
     * @throws ShelfNotExistModelException If no Shelf in the ShelfList has the specified name
     */
    public Shelf getShelf(String name) throws ShelfNotExistModelException {
        for (Shelf shelf : shelves) {
            if (shelf.getName().equals(name)) {
                return shelf;
            }
        }
        throw new ShelfNotExistModelException(name);
    }

    /**
     * OverLoaded getShelf method that can hide the soldItem shelf.
     *
     * @param name             the name of the target shelf
     * @param isSoldItemHidden whether to hide the soldItem shelf
     * @return the target shelf
     * @throws ShelfNotExistModelException if no such shelf exists.
     */
    public Shelf getShelf(String name, boolean isSoldItemHidden) throws ShelfNotExistModelException,
            DeniedAccessToShelfModelException {
        if (name.equals("soldItems") && isSoldItemHidden) {
            throw new DeniedAccessToShelfModelException(name);
        }
        for (Shelf shelf : shelves) {
            if (shelf.getName().equals(name)) {
                return shelf;
            }
        }
        throw new ShelfNotExistModelException(name);
    }

    /**
     * Check if there exists a Shelf with the specified name.
     *
     * @param name name of the Shelf
     * @return true if there exists
     */
    public boolean existShelf(String name) {
        try {
            getShelf(name, true);
        } catch (ShelfNotExistModelException | DeniedAccessToShelfModelException e) {
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
    public ArrayList<String> getAllShelvesName() {
        ArrayList<String> shelvesNames = new ArrayList<>();
        for (Shelf shelf : shelves) {
            shelvesNames.add(shelf.getName());
        }
        return shelvesNames;
    }

    /**
     * Return the Shelf that is storing the specified Item.
     *
     * @param item The target item
     * @return The Shelf that contains the item
     * @throws ItemNotExistModelException If the item does not belong to any Shelf
     */
    public Shelf shelfOfItem(Item item) throws ItemNotExistModelException {
        for (Shelf shelf : shelves) {
            if (shelf.contains(item)) {
                return shelf;
            }
        }
        throw new ItemNotExistModelException(item.getName());
    }


    /**
     * Get a unique item from the list of shelves.
     *
     * @param itemID The ID of the target item
     * @return the item if it exists
     * @throws ItemNotExistModelException if the target item with the given ID does not exist
     */
    public Item getItem(String itemID) throws ItemNotExistModelException {
        for (Shelf shelf : shelves) {
            if (shelf.containsGivenID(itemID)) {
                return shelf.getItemByID(itemID);
            }
        }
        throw new ItemNotExistModelException("with ID: " + itemID);
    }
}
