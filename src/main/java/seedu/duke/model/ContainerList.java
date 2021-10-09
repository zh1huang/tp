package seedu.duke.model;

import java.util.ArrayList;

public class ContainerList {
    int numOfContainers;
    ArrayList<ItemContainer> containers;

    ContainerList() {
        numOfContainers = 0;
        containers = new ArrayList<ItemContainer>();
    }

    public void addContainer(String location) {
        containers.add(new ItemContainer(location));
    }

    // todo add deleteContainer method

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
