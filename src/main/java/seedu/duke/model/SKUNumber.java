package seedu.duke.model;

public class SKUNumber {
    String number;
    SKU sku;

    SKUNumber (String number, SKU sku) {
        setNumber(number);
        setSku(sku);
    }

    protected void setNumber(String number) {
        // todo check if empty
        this.number = number;
    }

    protected void setSku(SKU sku) {
        if (sku != null) {
            this.sku = sku;
        } else {
            // todo throw error
        }
    }
}
