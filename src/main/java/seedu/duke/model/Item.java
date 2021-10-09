package seedu.duke.model;

import java.math.BigDecimal;

public class Item {
    String name;
    BigDecimal purchaseCost;
    BigDecimal sellingPrice;
    SKU sku;
    ItemContainer location; // todo add a location class

    Item (String name, BigDecimal cost, BigDecimal price, SKU sku, ItemContainer location) {
        setName(name);
        setPurchaseCost(cost);
        setSellingPrice(price);
        setSku(sku);
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

    public void setPurchaseCost(BigDecimal purchaseCost) { // todo check format
        this.purchaseCost = purchaseCost;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) { // todo check format
        this.sellingPrice = sellingPrice;
    }

    public SKU getSku() {
        return sku;
    }

    public void setSku(SKU sku) {
        this.sku = sku;
    }

    public ItemContainer getLocation() {
        return location;
    }

    public void setLocation(ItemContainer location) {
        this.location = location;
    }
}
