package seedu.duke.model;

import java.util.ArrayList;

/**
 * Represents a collection of ItemContainers.
 */
public class ContainerList {
    int numOfContainers;
    ArrayList<ItemContainer> containers;

    ContainerList() {
        numOfContainers = 0;
        containers = new ArrayList<ItemContainer>();
    }

    /**
     * Creates a new ItemContainer with the name of the location in the ContainerList.
     *
     * @param location A brief description of the position of ItemContainer
     */
    public void addContainer(String location) {
        containers.add(new ItemContainer(location));
    }

    // todo add deleteContainer method

    /**
     * Returns the ItemContainer with location as specified.
     *
     * @param location The location of the ItemContainer
     * @return The ItemContainer that matches the specified location
     */
    public ItemContainer getContainer(String location) {
        for (ItemContainer container : containers) {
            if (container.getName().equals(location)) {
                return container;
            }
        }

        // todo throw error when no container found
        return null;
    }
}
