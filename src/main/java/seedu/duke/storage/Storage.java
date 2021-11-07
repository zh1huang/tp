package seedu.duke.storage;

import org.json.JSONObject;
import seedu.duke.model.Item;
import seedu.duke.model.Shelf;
import seedu.duke.model.ShelfList;
import seedu.duke.model.SoldItem;
import seedu.duke.model.exception.DeniedAccessToShelfModelException;
import seedu.duke.model.exception.DuplicateShelfModelException;
import seedu.duke.model.exception.IllegalArgumentModelException;
import seedu.duke.model.exception.ShelfNotExistModelException;
import seedu.duke.model.exception.DuplicateItemModelException;
import seedu.duke.ui.MessageBubble;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Logger;

//@@author yuejunfeng0909
public class Storage {

    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private static final int JSON_INDENTATION_LEVEL = 4;
    public static String STORAGE_PATH = "data/Data.txt";
    private static Storage singleStorageInstance;
    ShelfList shelfList;

    private Storage() {
        shelfList = ShelfList.getShelfList();
    }

    public static Storage getStorageManager() {
        if (singleStorageInstance == null) {
            singleStorageInstance = new Storage();
        }
        return singleStorageInstance;
    }

    /**
     * Stores all data to STORAGE_PATH.
     *
     * @throws IOException if CLIverShelf do not have IO privileges
     */
    public void saveData() throws IOException {
        ArrayList<String> nameOfAllShelves = shelfList.getAllShelvesName();
        JSONObject storedData = new JSONObject();

        if (nameOfAllShelves.isEmpty()) {
            storedData = sampleData();
        } else {
            for (String nameOfShelf : nameOfAllShelves) {
                Shelf currentShelf = null;

                try {
                    currentShelf = shelfList.getShelf(nameOfShelf, false);
                } catch (ShelfNotExistModelException | DeniedAccessToShelfModelException e) {
                    e.printStackTrace();
                }

                JSONObject shelfInfo = new JSONObject();

                shelfInfo.put("remarks", currentShelf.getRemarks());

                JSONObject itemsInShelf = new JSONObject();
                for (int i = 0; i < currentShelf.getItemCount(); i = i + 1) {
                    Item currentItem = currentShelf.getItem(i);
                    JSONObject itemDetail = new JSONObject();
                    itemDetail.put("id", currentItem.getID());
                    itemDetail.put("name", currentItem.getName());
                    itemDetail.put("cost", currentItem.getPurchaseCost());
                    itemDetail.put("price", currentItem.getSellingPrice());
                    itemDetail.put("remarks", currentItem.getRemarks());
                    if (currentShelf.getName().equals("soldItems")) {
                        itemDetail.put("saleTime", ((SoldItem) currentItem).getSaleTime());
                    }
                    itemsInShelf.put(Integer.toString(i), itemDetail);
                }
                shelfInfo.put("items", itemsInShelf);
                storedData.put(currentShelf.getName(), shelfInfo);
            }
        }
        File file = new File(STORAGE_PATH);
        if (!file.exists()) {
            String directory = STORAGE_PATH.substring(0, STORAGE_PATH.lastIndexOf("/"));
            Files.createDirectories(Paths.get(directory));
            file.createNewFile();
        }
        FileWriter writer = new FileWriter(STORAGE_PATH);
        writer.write(storedData.toString(JSON_INDENTATION_LEVEL));
        writer.close();
    }

    /**
     * Load data from STORAGE_PATH back to CLIverShelf.
     *
     * @throws IOException             if CLIverShelf do not have IO privileges
     * @throws DataCorruptionException if the data in STORAGE_PATH is of incorrect format
     */
    public void loadData()
            throws IOException, DataCorruptionException {
        File file = new File(STORAGE_PATH);
        if (!file.exists()) {
            // No file found
            MessageBubble.printMessageBubble("Initializing with sample database.");
            String directory = STORAGE_PATH.substring(0, STORAGE_PATH.lastIndexOf("/"));
            Files.createDirectories(Paths.get(directory));
            file.createNewFile();
            FileWriter writer = new FileWriter(STORAGE_PATH);
            writer.write(sampleData().toString(JSON_INDENTATION_LEVEL));
            writer.close();
        }

        String text = Files.readString(Paths.get(STORAGE_PATH));
        try {
            JSONObject storedData = new JSONObject(text);
            loadFromJson(storedData);
        } catch (Exception e) {
            throw new DataCorruptionException();
        }

    }

    protected void loadFromJson(JSONObject storedData)
            throws DuplicateShelfModelException, IllegalArgumentModelException, DuplicateItemModelException,
            DeniedAccessToShelfModelException {
        for (String shelfName : storedData.keySet()) {
            Shelf currentShelf = shelfList.addShelf(shelfName);
            try {
                currentShelf.setRemark(storedData.getJSONObject(shelfName).getString("remarks"));
            } catch (org.json.JSONException e) {
                currentShelf.setRemark(" ");
            }
            JSONObject itemsJson = storedData.getJSONObject(shelfName).getJSONObject("items");
            for (String itemName : itemsJson.keySet()) {
                if (itemName.isBlank()) {
                    continue;
                }
                JSONObject itemJson = itemsJson.getJSONObject(itemName);
                Item item;
                if (!shelfName.equals("soldItems")) {
                    item = new Item(
                            itemJson.getString("name"),
                            itemJson.getString("cost"),
                            itemJson.getString("price"),
                            itemJson.getString("remarks"),
                            itemJson.getString("id")
                    );
                } else {
                    item = new SoldItem(
                            itemJson.getString("name"),
                            itemJson.getString("cost"),
                            itemJson.getString("price"),
                            itemJson.getString("remarks"),
                            itemJson.getString("id"),
                            LocalDateTime.parse(itemJson.getString("saleTime"))
                    );
                }
                currentShelf.addItem(item);
            }
        }
    }

    protected JSONObject sampleData() {
        final JSONObject defaultShelves = new JSONObject();
        final JSONObject defaultWarehouse = new JSONObject();
        final JSONObject defaultItems = new JSONObject();
        JSONObject sampleItem = new JSONObject();
        sampleItem.put("name", "sample item");
        sampleItem.put("cost", "12.25");
        sampleItem.put("price", "25");
        sampleItem.put("remarks", " ");
        sampleItem.put("id", "111111111");
        defaultItems.put("0", sampleItem);
        defaultWarehouse.put("items", defaultItems);
        defaultWarehouse.put("remarks", " ");
        defaultShelves.put("warehouse", defaultWarehouse);
        return defaultShelves;
    }
}
