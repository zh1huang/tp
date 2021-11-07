package seedu.duke.model;

import seedu.duke.model.exception.IllegalArgumentModelException;

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
    private static boolean printDummyId = false;
    public static String DUMMY_ID = "11111111";

    private String name;
    private String itemID;
    private BigDecimal purchaseCost;
    private BigDecimal sellingPrice;
    private String remark;

    /**
     * Constructor for Item class.
     *
     * @param name    the name of the item
     *                consists of alphabet, number, space, underscore, round bracket and hyphen
     * @param cost    the cost of the item
     *                must be non-negative
     * @param price   the selling price of the item
     *                must be non-negative
     * @param remarks the remark that is tied to the item
     * @throws IllegalArgumentModelException if any of the inputs does not follow the requirement
     */

    public Item(String name, String cost, String price, String remarks) throws IllegalArgumentModelException {
        setName(name);
        setPurchaseCost(cost);
        setSellingPrice(price);
        setRemarks(remarks);
        setID();
        logger.log(Level.INFO, String.format("Item %s created, with cost $%s and price $%s", name, cost, price));
    }

    /**
     * Overloaded constructor (with ID specified) for Item class (used for storage).
     *
     * @param name   the name of the item
     *               consists of alphabet, number, space, underscore, round bracket and hyphen
     * @param cost   the cost of the item
     *               must be non-negative
     * @param price  the selling price of the item
     *               must be non-negative
     * @param itemID the ID of the existing item
     * @throws IllegalArgumentModelException if any of the inputs does not follow the requirement
     */
    public Item(String name, String cost, String price, String remarks, String itemID) throws
            IllegalArgumentModelException {
        setName(name);
        setPurchaseCost(cost);
        setSellingPrice(price);
        setRemarks(remarks);
        setID(itemID);
        logger.log(Level.INFO, String.format("Item %s created, with cost $%s and price $%s", name, cost, price));
    }

    public String getName() {
        assert name != null;
        return name;
    }

    /**
     * Set a new name for the item.
     *
     * @param name new name
     *             consists of alphabet, number, space, underscore, round bracket and hyphen
     * @throws IllegalArgumentModelException if the name contains other characters
     */
    public void setName(String name) throws IllegalArgumentModelException {
        if (name.matches("[a-zA-Z0-9 _()-]+") && !name.isBlank()) {
            String temp = (this.name == null) ? "new item" : this.name;
            this.name = name;
            logger.log(Level.INFO, String.format("Successfully set Item %s's name as %s", temp, name));
        } else {
            logger.log(Level.WARNING, String.format("Trying to set Item %s's name as %s", this.getName(), name));
            throw new IllegalArgumentModelException(MESSAGE_INVALID_NAME_FORMAT);
        }
    }

    public static void setPrintDummyID(boolean printDummyId) {
        Item.printDummyId = printDummyId;
    }

    /**
     * Automatically generate the ID for a new item.
     */
    public void setID() {
        int idLength = 8;
        int hashCode = this.hashCode();
        String hashCodeInString = Integer.toHexString(hashCode);
        String itemId = String.format("%1$" + idLength + "s", hashCodeInString)
                .replace(' ', '0');
        this.itemID = itemId;
    }

    /**
     * Set the item ID for existing items in backup date file.
     *
     * @param itemID the ID of the existing ID
     */
    public void setID(String itemID) {
        this.itemID = itemID;
    }


    public String getID() {
        if (printDummyId) {
            return DUMMY_ID;
        }
        return this.itemID;
    }

    public String getPurchaseCost() {
        return purchaseCost.toString();
    }

    /**
     * Set a new cost of purchase for the item.
     *
     * @param cost new cost of the item
     *             must be non-negative
     * @throws IllegalArgumentModelException if the new cost is negative
     */
    public void setPurchaseCost(String cost) throws IllegalArgumentModelException {
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
     * @throws IllegalArgumentModelException if the new price is negative
     */
    public void setSellingPrice(String price) throws IllegalArgumentModelException {
        sellingPrice = convert2BD_NonNegative(price);
        logger.log(Level.INFO, String.format("Successfully set %s's selling price as %s", this.getName(), price));
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

    private BigDecimal convert2BD_NonNegative(String value) throws IllegalArgumentModelException {
        try {
            BigDecimal newValue = new BigDecimal(value);
            if (newValue.compareTo(new BigDecimal("0")) < 0) {
                throw new IllegalArgumentModelException(MESSAGE_INVALID_NEGATIVE_PRICE);
            }
            return newValue;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentModelException(MESSAGE_INVALID_PRICE_FORMAT);
        }

    }

    @Override
    public String toString() {
        return getName();
    }
}
