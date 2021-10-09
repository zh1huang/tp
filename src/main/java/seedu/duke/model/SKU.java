package seedu.duke.model;

import seedu.duke.model.exception.SKUError;

import java.math.BigDecimal;

public class SKU {
    String name;
    SKUNumber skuNumber;
    int quantity;

    SKU (String name, int quantity, String skuNumber) throws SKUError {
        setName(name);
        setQuantity(quantity);
        setSkuNumber(skuNumber);
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) throws SKUError {
        if (!name.isBlank()) {
            name = newName;
        } else {
            throw new SKUError("Empty name");
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) throws SKUError {
        if (quantity > 0) {
            this.quantity = quantity;
        } else if (quantity == 0) {
            // todo remove this sku
        } else {
            throw new SKUError("quantity cannot be negative");
        }
    }

    public void setSkuNumber(String skuNumber) {
        this.skuNumber = new SKUNumber(skuNumber, this);
    }
}
