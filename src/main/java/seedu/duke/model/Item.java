package seedu.duke.model;

import seedu.duke.model.exception.InvalidFormat;

import java.math.BigDecimal;

/**
 * Represents an item that can be stored in an ItemContainer.
 * e.g. You can store 10 Items named "Dune" in an ItemContainer named "Shelf_Sci-fi_1"
 */
public class Item {
    String name;
    BigDecimal purchaseCost;
    BigDecimal sellingPrice;
    ItemContainer location;

    /**
     * Constructor for Item class.
     *
     * @param name     the name of the item
     *                 consists of alphabet, number, underscore and hyphen
     * @param cost     the cost of the item
     *                 must be non-negative
     * @param price    the selling price of the item
     *                 must be non-negative
     * @param location the ItemContainer that this item belongs to
     * @throws InvalidFormat if any of the inputs does not follow the requirement
     */
    Item(String name, BigDecimal cost, BigDecimal price, ItemContainer location) throws InvalidFormat {
        setName(name);
        setPurchaseCost(cost);
        setSellingPrice(price);
        setLocation(location);
    }

    public String getName() {
        return name;
    }

    /**
     * Set a new name for the item.
     *
     * @param name new name
     *             consists of alphabet, number, underscore and hyphen
     * @throws InvalidFormat if the name contains other characters
     */
    public void setName(String name) throws InvalidFormat {
        if (name.matches("[a-zA-Z0-9_-]+")) {
            this.name = name;
        } else {
            throw new InvalidFormat("Invalid item name.");
        }
    }

    public BigDecimal getPurchaseCost() {
        return purchaseCost;
    }

    /**
     * Set a new cost of purchase for the item.
     *
     * @param cost new cost of the item
     *             must be non-negative
     * @throws InvalidFormat if the new cost is negative
     */
    public void setPurchaseCost(BigDecimal cost) throws InvalidFormat {
        if (cost.compareTo(new BigDecimal(0)) >= 0) {
            purchaseCost = cost;
        } else {
            // error if cost is negative
            throw new InvalidFormat("Item cost cannot be negative.");
        }
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    /**
     * Set a new selling price for the item.
     *
     * @param price new price of the item
     *              must be non-negative
     * @throws InvalidFormat if the new price is negative
     */
    public void setSellingPrice(BigDecimal price) throws InvalidFormat {
        if (price.compareTo(new BigDecimal(0)) >= 0) {
            sellingPrice = price;
        } else {
            // error if cost is negative
            throw new InvalidFormat("Item cost cannot be negative.");
        }
    }

    public ItemContainer getLocation() {
        return location;
    }

    /**
     * Move the Item from its original ItemContainer to the new ItemContainer.
     *
     * @param newLocation The ItemContainer location to store the Item
     */
    public void setLocation(ItemContainer newLocation) {
        if (location != null) {
            location.deleteItem(this);
        }
        location = newLocation;
        newLocation.addItem(this);
        assert newLocation.contains(this.getName()) : "The new location should contain this item";
    }

    @Override
    public String toString() {
        return getName();
    }
}
