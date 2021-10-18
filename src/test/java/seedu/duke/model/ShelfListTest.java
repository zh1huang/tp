package seedu.duke.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.model.exception.DuplicateShelfException;
import seedu.duke.model.exception.IllegalArgumentException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShelfListTest {

    ShelfList shelfList;

    @BeforeEach
    void setUp() {
        shelfList = ShelfList.getShelfList();
        shelfList.resetShelfList();
    }

    @Test
    void resetShelfList() throws DuplicateShelfException, IllegalArgumentException {
        shelfList.addShelf("somerandomshelf1");
        shelfList.addShelf("somerandomshelf2");
        shelfList.resetShelfList();
        assertEquals(0, shelfList.getNumberOfShelves());

        // no shelf
        shelfList.resetShelfList();
        assertEquals(0, shelfList.getNumberOfShelves());

    }

    @Test
    void addShelf_() {

    }

    @Test
    void addShelf() {

    }

    @Test
    void testAddShelf() {
    }

    @Test
    void deleteShelf() {
    }

    @Test
    void testDeleteShelf() {
    }

    @Test
    void getShelf() {
    }

    @Test
    void existShelf() {
    }

    @Test
    void getAllShelvesName() {
    }

    @Test
    void shelfOfItem() {
    }
}