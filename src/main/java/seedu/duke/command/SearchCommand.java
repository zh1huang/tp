package seedu.duke.command;

import seedu.duke.command.exception.CommandException;
import seedu.duke.model.Item;
import seedu.duke.model.Shelf;
import seedu.duke.model.ShelfList;
import seedu.duke.model.exception.IllegalModelArgumentException;
import seedu.duke.model.exception.ShelfNotExistException;
import seedu.duke.ui.Wrapping;

import java.util.ArrayList;

//@@author yuejunfeng0909
public class SearchCommand extends Command {

    public static final String SEARCH_ITEM_DATA_ARGS_FORMAT = "search KEYWORD";
    public static final String SEARCH_STRING = "search";

    private static final String HEADER =
            "    ID   |                   Item                   |   Cost    |   Price   |          Remark        \n";
    private static final String BORDER =
            "-----------------------------------------------------------------------------------------------------\n";
    private static final int ID_LENGTH = 8;
    private static final int NAME_LENGTH = 41;
    private static final int COST_LENGTH = 10;
    private static final int PRICE_LENGTH = 10;
    private static final int REMARK_LENGTH = 22;

    private static final String SEARCH_ID_HEADER = "Here are the items that has matching ID\n";
    private static final String SEARCH_MATCH_RESULT_FORMAT = " %s| %s| %s| %s| %s \n";
    private static final String SEARCH_NAME_HEADER = "Here are the items that has matching name\n";
    private static final String SEARCH_REMARK_HEADER = "Here are the items that has matching remark\n";
    private static final String SEARCH_PRICE_HEADER = "Here are the items that has matching cost/price\n";

    private static final String NO_RESULT_FOUND = "No match item found";

    private final String keyword;

    public SearchCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public String execute() throws CommandException, ShelfNotExistException, IllegalModelArgumentException {
        ArrayList<Item> searchResultMatchID = new ArrayList<>();
        ArrayList<Item> searchResultMatchName = new ArrayList<>();
        ArrayList<Item> searchResultMatchRemark = new ArrayList<>();
        ArrayList<Item> searchResultMatchPrice = new ArrayList<>();
        ShelfList shelfList = ShelfList.getShelfList();
        for (String shelfName : shelfList.getAllShelvesName()) {
            Shelf currentShelf = shelfList.getShelf(shelfName);
            for (int i = 0; i < currentShelf.getItemCount(); i++) {
                Item currentItem = currentShelf.getItem(i);
                if (currentItem.getID().toLowerCase().contains(keyword)) {
                    searchResultMatchID.add(currentItem);
                }
                if (currentItem.getName().toLowerCase().contains(keyword)) {
                    searchResultMatchName.add(currentItem);
                }
                if (currentItem.getRemarks().toLowerCase().contains(keyword)) {
                    searchResultMatchRemark.add(currentItem);
                }
                if (currentItem.getPurchaseCost().contains(keyword)
                        || currentItem.getSellingPrice().contains(keyword)) {
                    searchResultMatchPrice.add(currentItem);
                }
            }
        }

        if (searchResultMatchID.isEmpty() && searchResultMatchName.isEmpty()
                && searchResultMatchPrice.isEmpty() && searchResultMatchRemark.isEmpty()) {
            return NO_RESULT_FOUND;
        }

        StringBuilder searchResults = new StringBuilder();

        addToResult(searchResultMatchID, SEARCH_ID_HEADER, searchResults);
        addToResult(searchResultMatchName, SEARCH_NAME_HEADER, searchResults);
        addToResult(searchResultMatchRemark, SEARCH_REMARK_HEADER, searchResults);
        addToResult(searchResultMatchPrice, SEARCH_PRICE_HEADER, searchResults);

        return searchResults.toString();
    }

    private void addToResult(ArrayList<Item> result, String resultType, StringBuilder resultStringBuilder) {
        if (result.isEmpty()) {
            return;
        }
        if (resultStringBuilder.length() != 0) {
            resultStringBuilder.append(BORDER);
            resultStringBuilder.append("\n");
        }
        resultStringBuilder.append(resultType);
        resultStringBuilder.append(BORDER);
        resultStringBuilder.append(HEADER);
        resultStringBuilder.append(BORDER);
        for (Item item : result) {
            resultStringBuilder.append(convertToResult(item));
        }
    }

    private String convertToResult(Item item) {
        return String.format(SEARCH_MATCH_RESULT_FORMAT,
                Wrapping.restrictMessageLength(item.getID(), ID_LENGTH),
                Wrapping.restrictMessageLength(item.getName(), NAME_LENGTH),
                Wrapping.restrictMessageLength(item.getPurchaseCost(), COST_LENGTH),
                Wrapping.restrictMessageLength(item.getSellingPrice(), PRICE_LENGTH),
                Wrapping.restrictMessageLength(item.getRemarks(), REMARK_LENGTH));
    }
}
