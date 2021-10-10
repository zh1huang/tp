package seedu.duke.model;

import java.math.BigDecimal;

/**
 * Represents an item that can be stored in an ItemContainer.
 * e.g. You can store 10 Items named "Dune" in an ItemContainer named "Shelf - Sci-fi"
 */
public class Item {
    String name;
    BigDecimal purchaseCost;
    BigDecimal sellingPrice;
    ItemContainer location;

    Item(String name, BigDecimal cost, BigDecimal price, ItemContainer location) {
        setName(name);
        setPurchaseCost(cost);
        setSellingPrice(price);
        setLocation(location);
    }

    public String getName() {
        return name;
    }

    // todo check format
    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPurchaseCost() {
        return purchaseCost;
    }

    // todo check format
    public void setPurchaseCost(BigDecimal cost) {
        purchaseCost = cost;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    // todo check format
    public void setSellingPrice(BigDecimal price) {
        sellingPrice = price;
    }

    public ItemContainer getLocation() {
        return location;
    }

    /**
     * Move the Item from its original ItemContainer to the new ItemContainer.
     *
     * @param location The ItemContainer to store the Item
     */
    public void setLocation(ItemContainer location) {
        this.location.deleteItem(this);
        this.location = location;
        location.addItem(this);
    }
}
