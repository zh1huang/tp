package seedu.duke.model;

import java.util.ArrayList;

public class SKUList {
    ArrayList<SKU> skus;

    SKUList() {
        skus = new ArrayList<SKU>();
    }

    public void addSku(SKU sku) {
        // todo check duplicate
        skus.add(sku);
    }

    public ArrayList<SKU> getSkus() {
        return skus;
    }
}
