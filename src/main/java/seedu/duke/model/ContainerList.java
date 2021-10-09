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
}
