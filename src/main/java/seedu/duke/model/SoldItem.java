package seedu.duke.model;

import seedu.duke.model.exception.EditSoldItemCommandException;
import seedu.duke.model.exception.IllegalArgumentModelException;

import java.time.LocalDateTime;

//@@author yuejunfeng0909
public class SoldItem extends Item {

    private LocalDateTime saleTime;
    private boolean soldItemFixed = false;

    /**
     * Constructor for SoldItem class.
     *
     * @param name  the name of the item
     *              consists of alphabet, number, space, underscore, round bracket and hyphen
     * @param cost  the cost of the item
     *              must be non-negative
     * @param price the selling price of the item
     *              must be non-negative
     * @throws IllegalArgumentModelException if any of the inputs does not follow the requirement
     */
    public SoldItem(String name, String cost, String price, String remarks, String itemId, LocalDateTime saleTime)
            throws IllegalArgumentModelException {
        super(name, cost, price, remarks, itemId);
        setSaleTime(saleTime);
        soldItemFixed = true;
    }

    @Override
    public void setName(String name) throws IllegalArgumentModelException {
        if (soldItemFixed) {
            throw new EditSoldItemCommandException();
        }
        super.setName(name);
    }

    @Override
    public void setPurchaseCost(String cost) throws IllegalArgumentModelException {
        if (soldItemFixed) {
            throw new EditSoldItemCommandException();
        }
        super.setPurchaseCost(cost);
    }

    @Override
    public void setSellingPrice(String price) throws IllegalArgumentModelException {
        if (soldItemFixed) {
            throw new EditSoldItemCommandException();
        }
        super.setSellingPrice(price);
    }

    public LocalDateTime getSaleTime() {
        return saleTime;
    }

    protected void setSaleTime(LocalDateTime newSaleTime) {
        if (soldItemFixed) {
            throw new EditSoldItemCommandException();
        }
        this.saleTime = newSaleTime;
    }
}
