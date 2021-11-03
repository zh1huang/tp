package seedu.duke.salesmanager;

import seedu.duke.model.Item;
import seedu.duke.model.ShelfList;
import seedu.duke.model.exception.ShelfNotExistException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class SalesMarkUp {
    public static final int FOUR_DECIMAL_POINT = 4;
    public static final int TWO_DECIMAL_POINTS = 2;
    public static final int INTEGER_ONE_HUNDRED = 100;
    public static final BigDecimal ONE_HUNDRED = new BigDecimal(INTEGER_ONE_HUNDRED);
    private static final DecimalFormat decimalFormat = new DecimalFormat("#.##");
    public static final String PARSE_MARKUP_SUCCESS_MESSAGE_FORMAT = "shelfname: %s\nindex: %s\npercent: %s\n";
    public static final int INTEGER_TEN = 10;
    public static final int INTEGER_ELEVEN = 11;
    public static final String WARNING_LARGE_PERCENT_MESSAGE_FORMAT = "!!!WARNING: "
        + "NOT recommended to set a percentage > 100 to $%s.\n"
        + "This is to keep the price of the item reasonable";
    private static final String ITEM_NAME_MESSAGE_FORMAT = "Item: %s\nCost: %s, Price: %s\n";

    private static final String CURRENT_ITEM_MARKUP_MESSAGE_FORMAT =
        "Amount Difference: %s\nCurrent Mark Up: %s%%\n";
    private static final String ESTIMATED_MARKUP_MESSAGE_FORMAT =
        "markup: %s%%, increase: $%s, Final price: $%s\n";
    private BigDecimal cost;
    private BigDecimal price;
    private BigDecimal userRequestPercent;
    private String itemName;

    public SalesMarkUp(String shelfName, int index, String userRequestPercent) throws ShelfNotExistException {
        Item selectedItem = ShelfList
            .getShelfList()
            .getShelf(shelfName)
            .getItem(index);
        this.itemName = selectedItem.getName();
        this.cost = new BigDecimal(selectedItem.getPurchaseCost());
        this.price = new BigDecimal(selectedItem.getSellingPrice());
        if(!userRequestPercent.equals("")){
            this.userRequestPercent = new BigDecimal(userRequestPercent);
        }
    }

    public String getItemToMarkUpInfo(){
        return String.format(ITEM_NAME_MESSAGE_FORMAT, itemName, cost, price);
    }

    /**
     * Get the selected item markup information based on the current selling price.
     *
     * @return String containing the selected item markup information
     */
    public String getSelectedItemMarkUpInfo() {
        String stringToAppend;
        BigDecimal difference = price.subtract(cost);
        BigDecimal markUpPercent = difference
            .divide(cost, TWO_DECIMAL_POINTS, RoundingMode.HALF_UP).multiply(ONE_HUNDRED);

        stringToAppend = String.format(CURRENT_ITEM_MARKUP_MESSAGE_FORMAT,
            decimalFormat.format(difference), decimalFormat.format(markUpPercent));

        return stringToAppend;
    }

    /**
     * Calculates the markup information based on the user requested markup percent.
     * Shows a warning when user requests for a percent more than one hundred.
     *
     * @return String containing calculations for user requested markup percentage information.
     */
    public String getUserRequestMarkUpInfo() {
        BigDecimal amountIncrease = userRequestPercent
            .divide(ONE_HUNDRED, FOUR_DECIMAL_POINT, RoundingMode.HALF_UP)
            .multiply(cost).setScale(TWO_DECIMAL_POINTS, RoundingMode.HALF_UP);
        BigDecimal finalPrice = cost.add(amountIncrease);

        String stringToAppend = String.format(ESTIMATED_MARKUP_MESSAGE_FORMAT,
            userRequestPercent, amountIncrease, finalPrice);

        if (userRequestPercent.compareTo(ONE_HUNDRED) == 1) {
            String warningString = String.format(WARNING_LARGE_PERCENT_MESSAGE_FORMAT, finalPrice);
            stringToAppend += warningString;
        }

        return stringToAppend;
    }

    /**
     * Calculates markup percentages in multiples of 10, along with the corresponding price increase
     * final price.
     *
     * @return String containing the estimated markup information
     */
    public String getEstimatedMarkUpInfo() {
        StringBuilder stringToAppend = new StringBuilder();
        for (int i = 0; i < INTEGER_ELEVEN; i++) {
            BigDecimal estimatePercentMarkUp = new BigDecimal(i).multiply(BigDecimal.valueOf(INTEGER_TEN));
            BigDecimal amountIncrease = estimatePercentMarkUp
                .divide(ONE_HUNDRED, FOUR_DECIMAL_POINT, RoundingMode.HALF_UP)
                .multiply(cost).setScale(TWO_DECIMAL_POINTS, RoundingMode.HALF_UP);
            BigDecimal finalPrice = cost.add(amountIncrease);

            stringToAppend.append(
                String.format(ESTIMATED_MARKUP_MESSAGE_FORMAT,
                    estimatePercentMarkUp, amountIncrease, finalPrice));
        }
        return stringToAppend.toString();
    }
}
