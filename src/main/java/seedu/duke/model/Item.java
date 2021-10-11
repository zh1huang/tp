package seedu.duke.model;

import seedu.duke.model.exception.InvalidFormatException;

import java.math.BigDecimal;

/**
 * Represents an item that can be stored in an ItemContainer.
 * e.g. You can store 10 Items named "Dune" in an ItemContainer named "Shelf_Sci-fi_1"
 */
public class Item {

    public static final String MESSAGE_INVALID_NAME_FORMAT = "Invalid item name";
    public static final String MESSAGE_INVALID_PRICE_FORMAT = "Invalid price format";
    public static final String MESSAGE_INVALID_NEGATIVE_PRICE = "Price cannot be negative";

    private String name;
    private BigDecimal purchaseCost;
    private BigDecimal sellingPrice;

    /**
     * Constructor for Item class.
     *
     * @param name  the name of the item
     *              consists of alphabet, number, space, underscore, round bracket and hyphen
     * @param cost  the cost of the item
     *              must be non-negative
     * @param price the selling price of the item
     *              must be non-negative
     * @throws InvalidFormatException if any of the inputs does not follow the requirement
     */
    public Item(String name, String cost, String price) throws InvalidFormatException {
        setName(name);
        setPurchaseCost(cost);
        setSellingPrice(price);
    }

    public String getName() {
        return name;
    }

    /**
     * Set a new name for the item.
     *
     * @param name new name
     *             consists of alphabet, number, space, underscore, round bracket and hyphen
     * @throws InvalidFormatException if the name contains other characters
     */
    public void setName(String name) throws InvalidFormatException {
        if (name.matches("[a-zA-Z0-9 _()-]+") && !name.isBlank()) {
            this.name = name;
        } else {
            throw new InvalidFormatException(MESSAGE_INVALID_NAME_FORMAT);
        }
    }

    public BigDecimal getPurchaseCost() {
        return purchaseCost;
    }

    /**
     * Set a new cost of purchase for the item.
     *
     * @param cost new cost of the item
     *             must be non-negative
     * @throws InvalidFormatException if the new cost is negative
     */
    public void setPurchaseCost(String cost) throws InvalidFormatException {
        purchaseCost = convert2BD_NonNegative(cost);

    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    /**
     * Set a new selling price for the item.
     *
     * @param price new price of the item
     *              must be non-negative
     * @throws InvalidFormatException if the new price is negative
     */
    public void setSellingPrice(String price) throws InvalidFormatException {
        sellingPrice = convert2BD_NonNegative(price);
    }

    private BigDecimal convert2BD_NonNegative(String value) throws InvalidFormatException {
        try {
            BigDecimal newValue = new BigDecimal(value);
            if (newValue.compareTo(new BigDecimal(0)) < 0) {
                throw new InvalidFormatException(MESSAGE_INVALID_NEGATIVE_PRICE);
            }
            return newValue;
        } catch (NumberFormatException e) {
            throw new InvalidFormatException(MESSAGE_INVALID_PRICE_FORMAT);
        }

    }

    @Override
    public String toString() {
        return getName();
    }
}
