package seedu.duke.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.model.exception.DuplicateItemModelException;
import seedu.duke.model.exception.DuplicateShelfModelException;
import seedu.duke.model.exception.IllegalArgumentModelException;
import seedu.duke.model.exception.ItemNotExistModelException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@@author yuejunfeng0909
class ShelfTest {

    Shelf testShelf;
    ItemStub testItem1;
    ItemStub testItem2;
    ItemStub testItem3;

    @BeforeEach
    void setup() throws IllegalArgumentModelException, DuplicateShelfModelException {
        ShelfList.getShelfList().resetShelfList();
        testShelf = new Shelf("testShelf");
        testItem1 = new ItemStub("Item1");
        testItem2 = new ItemStub("Item2");
        testItem3 = new ItemStub("Item3");
    }

    @Test
    void shelfConstructor_instantiateDuplicateShelves_throwsDuplicateShelfException() {
        assertThrows(DuplicateShelfModelException.class, () -> new Shelf("testShelf"));
    }

    @Test
    void setName_correctInputFormat_setNormally() throws IllegalArgumentModelException, DuplicateShelfModelException {
        String[] correctInputs =
                new String[]{"The Lord of the Rings", "1984_someone", "A LEVEL H2 PHYSICS (TOPICAL) 2011-2020"};
        for (String input : correctInputs) {
            testShelf.setName(input);
            assertEquals(input, testShelf.getName());
        }
    }

    @Test
    void setName_wrongInputFormat_throwsInvalidFormatException() {
        String[] wrongInputs = new String[]{"", " ", "\t", "\n", " \r", "1984+", "Bazinga!", "something~"};
        for (String input : wrongInputs) {
            assertThrows(IllegalArgumentModelException.class, () -> testShelf.setName(input));
        }
    }

    @Test
    void setName_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> testShelf.setName(null));
    }

    @Test
    void addItem_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> testShelf.addItem(null));
    }

    @Test
    void addItem_repeatedInput_throwsDuplicateItemException() throws DuplicateItemModelException {
        testShelf.addItem(testItem1);
        assertThrows(DuplicateItemModelException.class, () -> testShelf.addItem(testItem1));
    }

    @Test
    void addItem_normalInput_addNormally() throws DuplicateItemModelException {
        testShelf.addItem(testItem1);
        assertEquals(1, testShelf.getItemCount());
        assertEquals(testItem1, testShelf.getItem(0));
    }

    @Test
    void deleteItem_nullInput_throwsNullPointerException() throws DuplicateItemModelException {
        testShelf.addItem(testItem1);
        assertThrows(NullPointerException.class, () -> testShelf.deleteItem(null));
    }

    @Test
    void deleteItem_itemNotExist_throwsItemNotExistException() throws DuplicateItemModelException {
        testShelf.addItem(testItem1);
        assertThrows(ItemNotExistModelException.class, () -> testShelf.deleteItem(testItem2));
    }

    @Test
    void deleteItem_itemExists_deleteNormally() throws DuplicateItemModelException, ItemNotExistModelException {
        testShelf.addItem(testItem1);
        testShelf.deleteItem(testItem1);
        assertEquals(0, testShelf.getItemCount());
    }

    @Test
    void updateItem_nullInput_throwsNullPointerException() throws DuplicateItemModelException {
        testShelf.addItem(testItem1);
        assertThrows(NullPointerException.class, () -> testShelf.updateItem(null, null));
        assertThrows(NullPointerException.class, () -> testShelf.updateItem(testItem1, null));
        assertThrows(NullPointerException.class, () -> testShelf.updateItem(testItem2, null));
        assertThrows(NullPointerException.class, () -> testShelf.updateItem(null, testItem1));
        assertThrows(NullPointerException.class, () -> testShelf.updateItem(null, testItem2));
    }

    @Test
    void updateItem_originalItemNotExist_throwsItemNotExistException() throws DuplicateItemModelException {
        testShelf.addItem(testItem1);
        assertThrows(ItemNotExistModelException.class, () -> testShelf.updateItem(testItem2, testItem2));
        assertThrows(ItemNotExistModelException.class, () -> testShelf.updateItem(testItem2, testItem3));
    }

    @Test
    void updateItem_updatedItemExist_throwsDuplicateItemException() throws DuplicateItemModelException {
        testShelf.addItem(testItem1);
        testShelf.addItem(testItem2);
        assertThrows(DuplicateItemModelException.class, () -> testShelf.updateItem(testItem1, testItem2));
        assertThrows(DuplicateItemModelException.class, () -> testShelf.updateItem(testItem2, testItem1));
    }

    @Test
    void updateItem_originalExistUpdatedNotExist_updateNormally() throws DuplicateItemModelException,
            ItemNotExistModelException, IllegalArgumentModelException {
        testShelf.addItem(testItem1);
        testShelf.updateItem(testItem1, testItem2);
        assertEquals(1, testShelf.getItemCount());
        assertEquals(testItem2, testShelf.getItem(0));
    }

    @Test
    void getItem_wrongIndex_throwsIndexOutOfBoundsException() throws DuplicateItemModelException {
        assertThrows(IndexOutOfBoundsException.class, () -> testShelf.getItem(0));
        assertThrows(IndexOutOfBoundsException.class, () -> testShelf.getItem(1));
        testShelf.addItem(testItem1);
        assertThrows(IndexOutOfBoundsException.class, () -> testShelf.getItem(1));
        assertThrows(IndexOutOfBoundsException.class, () -> testShelf.getItem(2));
    }

    @Test
    void getItem_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> testShelf.getItem(null));
    }

    @Test
    void getItem_correctIndex_returnNormally() throws DuplicateItemModelException {
        testShelf.addItem(testItem1);
        testShelf.addItem(testItem2);
        assertEquals(testItem1, testShelf.getItem(0));
        assertEquals(testItem2, testShelf.getItem(1));
    }

    @Test
    void contains_normalInput_returnNormally() throws DuplicateItemModelException {
        testShelf.addItem(testItem1);
        assertTrue(testShelf.contains(testItem1.getName()));
        assertFalse(testShelf.contains(testItem2.getName()));
    }

    @Test
    void contains_nullInput_throwsNullPointerException() {
        String nullString = null;
        assertThrows(NullPointerException.class, () -> testShelf.contains(nullString));

        Item nullItem = null;
        assertThrows(NullPointerException.class, () -> testShelf.contains(nullItem));
    }

    @Test
    void toStringTest() throws DuplicateItemModelException {
        testShelf.addItem(testItem1);
        testShelf.addItem(testItem2);
        String expectedResult = "1. " + testItem1.getName() + "\n" + "2. " + testItem2.getName();
        assertEquals(expectedResult, testShelf.toString());
    }

    @Test
    void containsGivenID() throws DuplicateItemModelException {
        assertFalse(testShelf.containsGivenID("randomid"));
        testShelf.addItem(testItem1);
        assertTrue(testShelf.containsGivenID(ItemStub.dummyID));
    }

    @Test
    void getItemByID_itemNotFound_throwItemNotExistException() {
        assertThrows(ItemNotExistModelException.class, () -> testShelf.getItemByID("randomid"));
    }
}