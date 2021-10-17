package seedu.duke.model;

import seedu.duke.model.exception.DuplicateItemContainerException;
import seedu.duke.model.exception.IllegalArgumentException;
import seedu.duke.model.exception.ItemContainerNotExistException;
import seedu.duke.model.exception.ItemNotExistException;

import java.util.ArrayList;

/**
 * Represents a collection of ItemContainers.
 * Provides methods to better manage ItemContainers.
 * Implemented using Singleton pattern.
 */
public class ContainerList {

    private static ContainerList containerList;
    ArrayList<ItemContainer> containers;

    private ContainerList() {
        containers = new ArrayList<ItemContainer>();
    }

    public static ContainerList getContainerList() {
        if (containerList == null) {
            containerList = new ContainerList();
        }
        return containerList;
    }

    public void resetContainerList() {
        containers = new ArrayList<>();
    }

    /**
     * Adds the specified itemContainer to the ContainerList.
     * (not recommended, as this method skips some checks)
     *
     * @param itemContainer The ItemContainer to be added
     */
    public void addContainer(ItemContainer itemContainer) {
        containers.add(itemContainer);
    }

    /**
     * Creates a new ItemContainer with the specified name in the ContainerList.
     *
     * @param name The name of the new ItemContainer
     * @throws IllegalArgumentException        if name does not follow the format
     * @throws DuplicateItemContainerException if there already exist a ItemContainer with this name
     */
    public ItemContainer addContainer(String name) throws IllegalArgumentException, DuplicateItemContainerException {
        if (existContainer(name)) {
            throw new DuplicateItemContainerException(name);
        }

        ItemContainer temp = new ItemContainer(name);
        containers.add(temp);
        return temp;
    }

    /**
     * Remove the item container from the ContainerList.
     *
     * @param itemContainer The ItemContainer to be removed
     */
    public void deleteContainer(ItemContainer itemContainer) {
        assert containers.contains(itemContainer);
        containers.remove(itemContainer);
    }

    /**
     * Remove the item container with the specified name from the ContainerList.
     *
     * @param name Name of the ItemContainer to be removed
     */
    public void deleteContainer(String name) throws ItemContainerNotExistException {
        containers.remove(getContainer(name));
    }

    /**
     * Returns the ItemContainer with the specified name.
     *
     * @param name The name of the ItemContainer
     * @return The ItemContainer that matches the specified name
     */
    public ItemContainer getContainer(String name) throws ItemContainerNotExistException {
        for (ItemContainer container : containers) {
            if (container.getName().equals(name)) {
                return container;
            }
        }
        throw new ItemContainerNotExistException(name);
    }

    /**
     * Check if there exists an item container with the specified name.
     *
     * @param name name of the item container
     * @return true if there exists
     */
    public boolean existContainer(String name) {
        try {
            getContainer(name);
        } catch (ItemContainerNotExistException e) {
            return false;
        }
        return true;
    }

    /**
     * Get the names of all ItemContainer.
     *
     * @return name of all ItemContainer, separated by "\n"
     */
    public String getAllItemContainerName() {
        StringBuilder temp = new StringBuilder();
        for (ItemContainer container : containers) {
            temp.append(container.getName());
            temp.append("\n");
        }
        return temp.toString();
    }

    /**
     * Return the ItemContainer that is storing the specified Item.
     *
     * @param item The target item
     * @return The ItemContainer that contains the item
     * @throws ItemNotExistException If the item does not belong to any ItemContainer
     */
    public ItemContainer containerOfItem(Item item) throws ItemNotExistException {
        for (ItemContainer container : containers) {
            if (container.contains(item)) {
                return container;
            }
        }
        throw new ItemNotExistException(item.getName());
    }
}
