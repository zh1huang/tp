package seedu.duke.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.model.exception.DuplicateItemModelException;
import seedu.duke.model.exception.DuplicateShelfModelException;
import seedu.duke.model.exception.IllegalArgumentModelException;
import seedu.duke.model.exception.ItemNotExistModelException;
import seedu.duke.model.exception.ShelfNotExistModelException;
import seedu.duke.model.exception.DeniedAccessToShelfModelException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@@author yuejunfeng0909
class ShelfListTest {

    ShelfList shelfList;

    @BeforeEach
    void setUp() {
        shelfList = ShelfList.getShelfList();
        shelfList.resetShelfList();
    }

    @Test
    void resetShelfList() throws DuplicateShelfModelException, IllegalArgumentModelException,
            DeniedAccessToShelfModelException {
        shelfList.addShelf("somerandomshelf1");
        shelfList.addShelf("somerandomshelf2");
        shelfList.resetShelfList();
        assertEquals(0, shelfList.getNumberOfShelves());

        // no shelf
        shelfList.resetShelfList();
        assertEquals(0, shelfList.getNumberOfShelves());

    }

    @Test
    void addShelf_duplicateName_throwsDuplicateShelfException()
            throws DuplicateShelfModelException, IllegalArgumentModelException, DeniedAccessToShelfModelException {
        shelfList.addShelf("someRandomShelf");
        assertThrows(DuplicateShelfModelException.class, () -> shelfList.addShelf("someRandomShelf"));
        assertThrows(DuplicateShelfModelException.class, () -> shelfList.addShelf("someRandomShelf "));
        assertThrows(DuplicateShelfModelException.class, () -> shelfList.addShelf(" someRandomShelf"));
    }

    @Test
    void addShelf_emptyName_throwIllegalArgumentException() {
        assertThrows(IllegalArgumentModelException.class, () -> shelfList.addShelf(""));
        assertThrows(IllegalArgumentModelException.class, () -> shelfList.addShelf(" "));
    }

    @Test
    void addShelf_correctName_throwIllegalArgumentException() throws DuplicateShelfModelException,
            IllegalArgumentModelException, DeniedAccessToShelfModelException {
        shelfList.addShelf("basdAKLDJFL91203_ 3(sda)-sdf");
        assertTrue(shelfList.existShelf("basdAKLDJFL91203_ 3(sda)-sdf"));
    }

    @Test
    void deleteShelf_emptyName_throwIllegalArgumentException() {
        assertThrows(IllegalArgumentModelException.class, () -> shelfList.addShelf(""));
        assertThrows(IllegalArgumentModelException.class, () -> shelfList.addShelf(" "));
    }

    @Test
    void deleteShelf_shelfNotExist_throwShelfNotExistException() {
        shelfList.resetShelfList();
        assertThrows(ShelfNotExistModelException.class, () -> shelfList.deleteShelf("something"));
    }

    @Test
    void deleteShelf_existShelf_deleteNormally()
            throws DuplicateShelfModelException, IllegalArgumentModelException, ShelfNotExistModelException,
            DeniedAccessToShelfModelException {
        shelfList.addShelf("correctShelf");
        shelfList.deleteShelf("correctShelf");
        assertFalse(shelfList.existShelf("correctShelf"));
    }

    @Test
    void getShelf_shelfNotExist_throwShelfNotExistException() {
        shelfList.resetShelfList();
        assertThrows(ShelfNotExistModelException.class, () -> shelfList.getShelf("shelfNotExist",
                true));
    }

    @Test
    void getShelf_shelfExist_returnNormally()
            throws ShelfNotExistModelException, DuplicateShelfModelException, IllegalArgumentModelException,
            DeniedAccessToShelfModelException {
        Shelf addedShelf = shelfList.addShelf("theShelfThatExists");
        assertEquals(addedShelf, shelfList.getShelf("theShelfThatExists", true));
    }

    @Test
    void existShelf() throws DuplicateShelfModelException, IllegalArgumentModelException,
            DeniedAccessToShelfModelException {
        shelfList.addShelf("somerandomshelf1");
        shelfList.addShelf("somerandomshelf2");
        assertTrue(shelfList.existShelf("somerandomshelf1"));
        assertTrue(shelfList.existShelf("somerandomshelf2"));
    }

    @Test
    void getAllShelvesName() throws DuplicateShelfModelException, IllegalArgumentModelException,
            DeniedAccessToShelfModelException {
        shelfList.addShelf("somerandomshelf1");
        shelfList.addShelf("somerandomshelf2");
        assertEquals("somerandomshelf1", shelfList.getAllShelvesName().get(0));
        assertEquals("somerandomshelf2", shelfList.getAllShelvesName().get(1));
    }

    @Test
    void shelfOfItem_itemExist_returnNormally() throws IllegalArgumentModelException, ShelfNotExistModelException,
            DuplicateItemModelException,
            ItemNotExistModelException, DuplicateShelfModelException, DeniedAccessToShelfModelException {
        ItemStub testItem = new ItemStub("randomName");
        shelfList.addShelf("somerandomshelf1");
        shelfList.getShelf("somerandomshelf1", true).addItem(testItem);
        assertEquals(shelfList.getShelf("somerandomshelf1", true), shelfList.shelfOfItem(testItem));
    }

    @Test
    void shelfOfItem_itemNotExist_throwItemNotExistException() throws IllegalArgumentModelException {
        ItemStub testItem = new ItemStub("randomName");
        assertThrows(ItemNotExistModelException.class, () -> shelfList.shelfOfItem(testItem));
    }

    @Test
    void getItem_IdExist_returnNormally() throws IllegalArgumentModelException, ItemNotExistModelException,
            ShelfNotExistModelException, DuplicateItemModelException, DuplicateShelfModelException,
            DeniedAccessToShelfModelException {
        ItemStub testItem = new ItemStub("randomName");
        shelfList.addShelf("somerandomshelf1");
        shelfList.getShelf("somerandomshelf1").addItem(testItem);
        assertEquals(testItem, shelfList.getItem(ItemStub.dummyID));
    }

    @Test
    void getItem_IdNotExist_throwItemNotExistException() {
        assertThrows(ItemNotExistModelException.class, () -> shelfList.getItem("11111111"));
    }
}