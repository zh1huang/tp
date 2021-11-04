package seedu.duke.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.model.exception.DuplicateItemException;
import seedu.duke.model.exception.DuplicateShelfException;
import seedu.duke.model.exception.IllegalModelArgumentException;
import seedu.duke.model.exception.ItemNotExistException;
import seedu.duke.model.exception.ShelfNotExistException;

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
    void resetShelfList() throws DuplicateShelfException, IllegalModelArgumentException {
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
            throws DuplicateShelfException, IllegalModelArgumentException {
        shelfList.addShelf("someRandomShelf");
        assertThrows(DuplicateShelfException.class, () -> shelfList.addShelf("someRandomShelf"));
        assertThrows(DuplicateShelfException.class, () -> shelfList.addShelf("someRandomShelf "));
        assertThrows(DuplicateShelfException.class, () -> shelfList.addShelf(" someRandomShelf"));
    }

    @Test
    void addShelf_emptyName_throwIllegalArgumentException() {
        assertThrows(IllegalModelArgumentException.class, () -> shelfList.addShelf(""));
        assertThrows(IllegalModelArgumentException.class, () -> shelfList.addShelf(" "));
    }

    @Test
    void addShelf_correctName_throwIllegalArgumentException() throws DuplicateShelfException,
            IllegalModelArgumentException {
        shelfList.addShelf("basdAKLDJFL91203_ 3(sda)-sdf");
        assertTrue(shelfList.existShelf("basdAKLDJFL91203_ 3(sda)-sdf"));
    }

    @Test
    void deleteShelf_emptyName_throwIllegalArgumentException() {
        assertThrows(IllegalModelArgumentException.class, () -> shelfList.addShelf(""));
        assertThrows(IllegalModelArgumentException.class, () -> shelfList.addShelf(" "));
    }

    @Test
    void deleteShelf_shelfNotExist_throwShelfNotExistException() {
        shelfList.resetShelfList();
        assertThrows(ShelfNotExistException.class, () -> shelfList.deleteShelf("something"));
    }

    @Test
    void deleteShelf_existShelf_deleteNormally()
            throws DuplicateShelfException, IllegalModelArgumentException, ShelfNotExistException {
        shelfList.addShelf("correctShelf");
        shelfList.deleteShelf("correctShelf");
        assertFalse(shelfList.existShelf("correctShelf"));
    }

    @Test
    void getShelf_shelfNotExist_throwShelfNotExistException() {
        shelfList.resetShelfList();
        assertThrows(ShelfNotExistException.class, () -> shelfList.getShelf("shelfNotExist", true));
    }

    @Test
    void getShelf_shelfExist_returnNormally()
            throws ShelfNotExistException, DuplicateShelfException, IllegalModelArgumentException {
        Shelf addedShelf = shelfList.addShelf("theShelfThatExists");
        assertEquals(addedShelf, shelfList.getShelf("theShelfThatExists", true));
    }

    @Test
    void existShelf() throws DuplicateShelfException, IllegalModelArgumentException {
        shelfList.addShelf("somerandomshelf1");
        shelfList.addShelf("somerandomshelf2");
        assertTrue(shelfList.existShelf("somerandomshelf1"));
        assertTrue(shelfList.existShelf("somerandomshelf2"));
    }

    @Test
    void getAllShelvesName() throws DuplicateShelfException, IllegalModelArgumentException {
        shelfList.addShelf("somerandomshelf1");
        shelfList.addShelf("somerandomshelf2");
        assertEquals("somerandomshelf1\nsomerandomshelf2", shelfList.getAllShelvesName());
    }

    @Test
    void shelfOfItem() throws IllegalModelArgumentException, ShelfNotExistException, DuplicateItemException,
            ItemNotExistException, DuplicateShelfException {
        ItemStub testItem = new ItemStub("randomName");
        shelfList.addShelf("somerandomshelf1");
        shelfList.getShelf("somerandomshelf1", true).addItem(testItem);
        assertEquals(shelfList.getShelf("somerandomshelf1", true), shelfList.shelfOfItem(testItem));
    }
}