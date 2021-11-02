package seedu.duke.model;

import seedu.duke.model.exception.IllegalArgumentException;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author yuejunfeng0909

/**
 * Represents an item that can be stored in an Shelf.
 * e.g. You can store 10 Items named "Dune" in an Shelf named "Shelf_Sci-fi_1"
 */
public class Item {

    public static final String MESSAGE_INVALID_NAME_FORMAT = "Invalid item name";
    public static final String MESSAGE_INVALID_PRICE_FORMAT = "Invalid price format";
    public static final String MESSAGE_INVALID_NEGATIVE_PRICE = "Price cannot be negative";

    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private String name;
    private BigDecimal purchaseCost;
    private BigDecimal sellingPrice;
    private String remark;

    /**
     * Constructor for Item class.
     *
     * @param name  the name of the item
     *              consists of alphabet, number, space, underscore, round bracket and hyphen
     * @param cost  the cost of the item
     *              must be non-negative
     * @param price the selling price of the item
     *              must be non-negative
     * @throws IllegalArgumentException if any of the inputs does not follow the requirement
     */

    public Item(String name, String cost, String price, String remarks) throws IllegalArgumentException {
        setName(name);
        setPurchaseCost(cost);
        setSellingPrice(price);
        setRemarks(remarks);
        logger.log(Level.INFO, String.format("Item %s created, with cost $%s and price $%s", name, cost, price));
    }

    public String getName() {
        return name;
    }

    /**
     * Set a new name for the item.
     *
     * @param name new name
     *             consists of alphabet, number, space, underscore, round bracket and hyphen
     * @throws IllegalArgumentException if the name contains other characters
     */
    public void setName(String name) throws IllegalArgumentException {
        if (name.matches("[a-zA-Z0-9 _()-]+") && !name.isBlank()) {
            String temp = (this.name == null) ? "new item" : this.name;
            this.name = name;
            logger.log(Level.INFO, String.format("Successfully set Item %s's name as %s", temp, name));
        } else {
            logger.log(Level.WARNING, String.format("Trying to set Item %s's name as %s", this.getName(), name));
            throw new IllegalArgumentException(MESSAGE_INVALID_NAME_FORMAT);
        }
    }

    public String getPurchaseCost() {
        return purchaseCost.toString();
    }

    /**
     * Set a new cost of purchase for the item.
     *
     * @param cost new cost of the item
     *             must be non-negative
     * @throws IllegalArgumentException if the new cost is negative
     */
    public void setPurchaseCost(String cost) throws IllegalArgumentException {
        purchaseCost = convert2BD_NonNegative(cost);
        logger.log(Level.INFO, String.format("Successfully set %s's purchase cost as %s", this.getName(), cost));
    }

    public String getSellingPrice() {
        return sellingPrice.toString();
    }

    /**
     * Set a new selling price for the item.
     *
     * @param price new price of the item
     *              must be non-negative
     * @throws IllegalArgumentException if the new price is negative
     */
    public void setSellingPrice(String price) throws IllegalArgumentException {
        sellingPrice = convert2BD_NonNegative(price);
        logger.log(Level.INFO, String.format("Successfully set %s's purchase cost as %s", this.getName(), price));
    }

    public String getRemarks() {
        return remark;
    }

    public void setRemarks(String newRemark) {
        if (newRemark.isBlank()) {
            remark = "";
        } else {
            remark = newRemark;
        }
    }

    private BigDecimal convert2BD_NonNegative(String value) throws IllegalArgumentException {
        try {
            BigDecimal newValue = new BigDecimal(value);
            if (newValue.compareTo(new BigDecimal("0")) < 0) {
                throw new IllegalArgumentException(MESSAGE_INVALID_NEGATIVE_PRICE);
            }
            return newValue;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(MESSAGE_INVALID_PRICE_FORMAT);
        }

    }

    @Override
    public String toString() {
        return getName();
    }
}
