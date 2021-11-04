package seedu.duke.model;

import seedu.duke.model.exception.EditSoldItemException;
import seedu.duke.model.exception.IllegalModelArgumentException;

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
     *
     * @throws IllegalModelArgumentException if any of the inputs does not follow the requirement
     */
    public SoldItem(String name, String cost, String price, String remarks, String itemId, LocalDateTime saleTime)
            throws IllegalModelArgumentException {
        super(name, cost, price, remarks, itemId);
        setSaleTime(saleTime);
        soldItemFixed = true;
    }

    @Override
    public void setName(String name) throws IllegalModelArgumentException {
        if (soldItemFixed) {
            throw new EditSoldItemException();
        }
        super.setName(name);
    }

    @Override
    public void setPurchaseCost(String cost) throws IllegalModelArgumentException {
        if (soldItemFixed) {
            throw new EditSoldItemException();
        }
        super.setPurchaseCost(cost);
    }

    @Override
    public void setSellingPrice(String price) throws IllegalModelArgumentException {
        if (soldItemFixed) {
            throw new EditSoldItemException();
        }
        super.setSellingPrice(price);
    }

    public LocalDateTime getSaleTime() {
        return saleTime;
    }

    protected void setSaleTime(LocalDateTime newSaleTime) {
        if (soldItemFixed) {
            throw new EditSoldItemException();
        }
        this.saleTime = newSaleTime;
    }
}
