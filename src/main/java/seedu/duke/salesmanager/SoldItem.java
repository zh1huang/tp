package seedu.duke.salesmanager;

import seedu.duke.model.Item;
import seedu.duke.model.exception.IllegalArgumentException;

import java.time.LocalDateTime;

public class SoldItem extends Item {

    private LocalDateTime saleTime;

    /**
     * Constructor for SoldItem class.
     *
     * @param name  the name of the item
     *              consists of alphabet, number, space, underscore, round bracket and hyphen
     * @param cost  the cost of the item
     *              must be non-negative
     * @param price the selling price of the item
     *              must be non-negative
     * @throws IllegalArgumentException if any of the inputs does not follow the requirement
     */
    public SoldItem(String name, String cost, String price, LocalDateTime saleTime) throws IllegalArgumentException {
        super(name, cost, price);
        setSaleTime(saleTime);
    }

    protected void setSaleTime(LocalDateTime newSaleTime) {
        this.saleTime = newSaleTime;
    }

    public LocalDateTime getSaleTime() {
        return this.saleTime;
    }
}
