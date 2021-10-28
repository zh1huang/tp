package seedu.duke.command;

import seedu.duke.command.exception.ItemNotExistException;
import seedu.duke.model.Item;
import seedu.duke.model.ShelfList;
import seedu.duke.model.exception.ShelfNotExistException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MarkUpCommand extends Command {
    public static final int INTEGER_ONE_HUNDRED = 100;
    public static final BigDecimal ONE_HUNDRED = new BigDecimal(INTEGER_ONE_HUNDRED);
    private static final String MESSAGE_ITEM_NOT_EXIST = "Item with index %d does not exist";
    private static final String ITEM_NAME_MESSAGE_FORMAT = "Item: %s\nCost: %s, Price: %s\n";
    private static final String CURRENT_ITEM_MARKUP_MESSAGE_FORMAT =
        "Amount Difference: %s\nCurrent %% Mark Up: %s\n";
    private static final String ESTIMATED_MARKUP_MESSAGE_FORMAT =
        "%% markup: %s, $ increase: %s, Final price: %s\n";
    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");
    public static final int FOUR_DECIMAL_POINT = 4;
    public static final int INTEGER_TEN = 10;
    public static final int INTEGER_ELEVEN = 11;
    public static final String WARNING_LARGE_PERCENT_MESSAGE_FORMAT = "!!!WARNING: NOT recommended to set a percentage > 100 to $%s.\n"
        + "This is to keep the price of the item resonable";

    private final String shelfName;
    private String itemName;
    private final int index;
    private BigDecimal userRequestPercent;
    private BigDecimal cost;
    private BigDecimal price;

    public MarkUpCommand(String shelfName, String index, String userRequestPercent) {
        this.shelfName = shelfName;
        this.index = Integer.parseInt(index) - 1;
        if (userRequestPercent != null) {
            this.userRequestPercent = new BigDecimal(userRequestPercent);
        }
        this.cost = BigDecimal.ZERO;
        this.price = BigDecimal.ZERO;
        this.itemName = null;
    }

    @Override
    public String execute() throws ShelfNotExistException, ItemNotExistException {
        StringBuilder resultString = new StringBuilder();
        try {
            getItemDetails();
            String currentItemInfo = String.format(ITEM_NAME_MESSAGE_FORMAT, itemName, cost, price);
            resultString.append(currentItemInfo);

            String currentItemMarkUpInfoString = getCurrentItemMarkUpInfo();
            resultString.append(currentItemMarkUpInfoString);

            if (userRequestPercent == null) {
                String getEstimatedMarkUpInfoString = getEstimatedMarkUpInfo();
                resultString.append(getEstimatedMarkUpInfoString);
            } else {
                String getUserRequestMarkUpInfoString = getUserRequestMarkUpInfo();
                resultString.append(getUserRequestMarkUpInfoString);
            }

            return resultString.toString();
        } catch (ShelfNotExistException e) {
            throw new ShelfNotExistException(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            throw new ItemNotExistException(String.format(MESSAGE_ITEM_NOT_EXIST, index));
        }
    }

    private void getItemDetails() throws ShelfNotExistException {
        Item selectedItem = ShelfList
            .getShelfList()
            .getShelf(shelfName)
            .getItem(index);
        this.itemName = selectedItem.getName();
        this.cost = cost.add(new BigDecimal(selectedItem.getPurchaseCost()));
        this.price = price.add(new BigDecimal(selectedItem.getSellingPrice()));
    }

    private String getUserRequestMarkUpInfo() {
        String stringToAppend, warningString;
        BigDecimal amountIncrease = userRequestPercent.divide(ONE_HUNDRED).multiply(cost);
        BigDecimal finalPrice = cost.add(amountIncrease);

        stringToAppend = String.format(ESTIMATED_MARKUP_MESSAGE_FORMAT, userRequestPercent, amountIncrease, finalPrice);
        if (userRequestPercent.compareTo(ONE_HUNDRED) > 1) {
            warningString = String.format(WARNING_LARGE_PERCENT_MESSAGE_FORMAT, finalPrice);
            stringToAppend += warningString;
        }

        return stringToAppend;
    }

    private String getEstimatedMarkUpInfo() {
        StringBuilder stringToAppend = new StringBuilder();
        for (int i = 0; i < INTEGER_ELEVEN; i++) {
            BigDecimal estimatePercentMarkUp = new BigDecimal(i).multiply(BigDecimal.valueOf(INTEGER_TEN));
            BigDecimal amountIncrease = estimatePercentMarkUp.divide(ONE_HUNDRED).multiply(cost);
            BigDecimal finalPrice = cost.add(amountIncrease);

            stringToAppend.append(
                String.format(ESTIMATED_MARKUP_MESSAGE_FORMAT,
                    estimatePercentMarkUp, amountIncrease, finalPrice));
        }
        return stringToAppend.toString();
    }

    private String getCurrentItemMarkUpInfo() {
        String stringToAppend;
        BigDecimal difference = price.subtract(cost);
        BigDecimal markUpPercent = difference
            .divide(cost, FOUR_DECIMAL_POINT, RoundingMode.HALF_UP).multiply(ONE_HUNDRED);

        stringToAppend = String.format(CURRENT_ITEM_MARKUP_MESSAGE_FORMAT,
            decimalFormat.format(difference), decimalFormat.format(markUpPercent));

        return stringToAppend;
    }
}
