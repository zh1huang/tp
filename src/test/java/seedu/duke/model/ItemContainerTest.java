package seedu.duke.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.model.exception.DuplicateItemContainerException;
import seedu.duke.model.exception.DuplicateItemException;
import seedu.duke.model.exception.IllegalArgumentException;
import seedu.duke.model.exception.ItemNotExistException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ItemContainerTest {

    ItemContainer testContainer;
    ItemStub testItem1;
    ItemStub testItem2;
    ItemStub testItem3;

    @BeforeEach
    void setup() throws IllegalArgumentException, DuplicateItemContainerException {
        ContainerList.getContainerList().resetContainerList();
        testContainer = new ItemContainer("testContainer");
        testItem1 = new ItemStub("Item1");
        testItem2 = new ItemStub("Item2");
        testItem3 = new ItemStub("Item3");
    }

    @Test
    void setName_correctInputFormat_setNormally() throws IllegalArgumentException, DuplicateItemContainerException {
        String[] correctInputs =
            new String[]{"The Lord of the Rings", "1984_someone", "A LEVEL H2 PHYSICS (TOPICAL) 2011-2020"};
        for (String input : correctInputs) {
            testContainer.setName(input);
            assertEquals(input, testContainer.getName());
        }
    }

    @Test
    void setName_wrongInputFormat_throwsInvalidFormatException() {
        String[] wrongInputs = new String[]{"", " ", "\t", "\n", " \r", "1984+", "Bazinga!", "something~"};
        for (String input : wrongInputs) {
            assertThrows(IllegalArgumentException.class, () -> testContainer.setName(input));
        }
    }

    @Test
    void setName_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> testContainer.setName(null));
    }

    @Test
    void addItem_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> testContainer.addItem(null));
    }

    @Test
    void addItem_repeatedInput_throwsDuplicateItemException() throws DuplicateItemException {
        testContainer.addItem(testItem1);
        assertThrows(DuplicateItemException.class, () -> testContainer.addItem(testItem1));
    }

    @Test
    void addItem_normalInput_addNormally() throws DuplicateItemException {
        testContainer.addItem(testItem1);
        assertEquals(1, testContainer.getItemCount());
        assertEquals(testItem1, testContainer.getItem(0));
    }

    @Test
    void deleteItem_nullInput_throwsNullPointerException() throws DuplicateItemException {
        testContainer.addItem(testItem1);
        assertThrows(NullPointerException.class, () -> testContainer.deleteItem(null));
    }

    @Test
    void deleteItem_itemNotExist_throwsItemNotExistException() throws DuplicateItemException {
        testContainer.addItem(testItem1);
        assertThrows(ItemNotExistException.class, () -> testContainer.deleteItem(testItem2));
    }

    @Test
    void deleteItem_itemExists_deleteNormally() throws DuplicateItemException, ItemNotExistException {
        testContainer.addItem(testItem1);
        testContainer.deleteItem(testItem1);
        assertEquals(0, testContainer.getItemCount());
    }

    @Test
    void updateItem_nullInput_throwsNullPointerException() throws DuplicateItemException {
        testContainer.addItem(testItem1);
        assertThrows(NullPointerException.class, () -> testContainer.updateItem(null, null));
        assertThrows(NullPointerException.class, () -> testContainer.updateItem(testItem1, null));
        assertThrows(NullPointerException.class, () -> testContainer.updateItem(testItem2, null));
        assertThrows(NullPointerException.class, () -> testContainer.updateItem(null, testItem1));
        assertThrows(NullPointerException.class, () -> testContainer.updateItem(null, testItem2));
    }

    @Test
    void updateItem_originalItemNotExist_throwsItemNotExistException() throws DuplicateItemException {
        testContainer.addItem(testItem1);
        assertThrows(ItemNotExistException.class, () -> testContainer.updateItem(testItem2, testItem2));
        assertThrows(ItemNotExistException.class, () -> testContainer.updateItem(testItem2, testItem3));
    }

    @Test
    void updateItem_updatedItemExist_throwsDuplicateItemException() throws DuplicateItemException {
        testContainer.addItem(testItem1);
        testContainer.addItem(testItem2);
        assertThrows(DuplicateItemException.class, () -> testContainer.updateItem(testItem1, testItem2));
        assertThrows(DuplicateItemException.class, () -> testContainer.updateItem(testItem2, testItem1));
    }

    @Test
    void updateItem_originalExistUpdatedNotExist_updateNormally() throws DuplicateItemException,
            ItemNotExistException, IllegalArgumentException {
        testContainer.addItem(testItem1);
        testContainer.updateItem(testItem1, testItem2);
        assertEquals(1, testContainer.getItemCount());
        assertEquals(testItem2, testContainer.getItem(0));
    }

    @Test
    void getItem_wrongIndex_throwsIndexOutOfBoundsException() throws DuplicateItemException {
        assertThrows(IndexOutOfBoundsException.class, () -> testContainer.getItem(0));
        assertThrows(IndexOutOfBoundsException.class, () -> testContainer.getItem(1));
        testContainer.addItem(testItem1);
        assertThrows(IndexOutOfBoundsException.class, () -> testContainer.getItem(1));
        assertThrows(IndexOutOfBoundsException.class, () -> testContainer.getItem(2));
    }

    @Test
    void getItem_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> testContainer.getItem(null));
    }

    @Test
    void getItem_correctIndex_returnNormally() throws DuplicateItemException {
        testContainer.addItem(testItem1);
        testContainer.addItem(testItem2);
        assertEquals(testItem1, testContainer.getItem(0));
        assertEquals(testItem2, testContainer.getItem(1));
    }

    @Test
    void contains_normalInput_returnNormally() throws DuplicateItemException {
        testContainer.addItem(testItem1);
        assertTrue(testContainer.contains(testItem1.getName()));
        assertFalse(testContainer.contains(testItem2.getName()));
    }

    @Test
    void contains_nullInput_throwsNullPointerException() {
        String nullString = null;
        assertThrows(NullPointerException.class, () -> testContainer.contains(nullString));

        Item nullItem = null;
        assertThrows(NullPointerException.class, () -> testContainer.contains(nullItem));
    }

    @Test
    void toStringTest() throws DuplicateItemException {
        testContainer.addItem(testItem1);
        testContainer.addItem(testItem2);
        String expectedResult = "1. " + testItem1.getName() + "\n" + "2. " + testItem2.getName();
        assertEquals(expectedResult, testContainer.toString());
    }
}