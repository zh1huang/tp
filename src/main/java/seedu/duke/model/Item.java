package seedu.duke.model;

import java.math.BigDecimal;

public class Item {
    String name;
    BigDecimal purchaseCost;
    BigDecimal sellingPrice;
    ItemContainer location; // todo add a location class

    Item(String name, BigDecimal cost, BigDecimal price, ItemContainer location) {
        setName(name);
        setPurchaseCost(cost);
        setSellingPrice(price);
        setLocation(location);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { // todo check format
        this.name = name;
    }

    public BigDecimal getPurchaseCost() {
        return purchaseCost;
    }

    public void setPurchaseCost(BigDecimal cost) { // todo check format
        purchaseCost = cost;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal price) { // todo check format
        sellingPrice = price;
    }

    public ItemContainer getLocation() {
        return location;
    }

    public void setLocation(ItemContainer location) {
        this.location.deleteItem(this);
        this.location = location;
        location.addItem(this);
    }
}
