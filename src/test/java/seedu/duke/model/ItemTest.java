package seedu.duke.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.model.exception.IllegalArgumentModelException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

//@@author yuejunfeng0909
class ItemTest {

    Item testItem;

    @BeforeEach
    void setup() throws IllegalArgumentModelException {
        testItem = new Item("Dune", "1.00", "2.00", "");
    }

    @Test
    void setName_correctInputFormat_setNormally() throws IllegalArgumentModelException {
        String[] correctInputs =
                new String[]{"The Lord of the Rings", "1984_someone", "A LEVEL H2 PHYSICS (TOPICAL) 2011-2020"};
        for (String input : correctInputs) {
            testItem.setName(input);
            assertEquals(input, testItem.getName());
        }
    }

    @Test
    void setName_wrongInputFormat_throwsInvalidFormatException() {
        String[] wrongInputs = new String[]{"", " ", "\t", "\n", " \r", "1984+", "Bazinga!", "something~"};
        for (String input : wrongInputs) {
            assertThrows(IllegalArgumentModelException.class, () -> testItem.setName(input));
        }
    }

    @Test
    void setName_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> testItem.setName(null));
    }

    @Test
    void setPurchaseCost_correctInputFormat_setNormally() throws IllegalArgumentModelException {
        String[] correctInput = new String[]{"0.01", "1000", "-0.00", "0.0", ".1"};
        for (String input : correctInput) {
            testItem.setPurchaseCost(input);
        }
    }

    @Test
    void setPurchaseCost_wrongInputFormat_throwsInvalidFormatException() {
        // negative amount
        String[] negativeInputs = new String[]{"-10", "-0.1"};
        for (String input : negativeInputs) {
            try {
                testItem.setPurchaseCost(input);
                fail();
            } catch (Exception e) {
                assertEquals(Item.MESSAGE_INVALID_NEGATIVE_PRICE, e.getMessage());
            }
        }

        // wrong format
        String[] wrongFormat = new String[]{"0.0.0", ".o1", "something~"};
        for (String input : wrongFormat) {
            try {
                testItem.setPurchaseCost(input);
                fail();
            } catch (Exception e) {
                assertEquals(Item.MESSAGE_INVALID_PRICE_FORMAT, e.getMessage());
            }
        }

        // null input
        assertThrows(NullPointerException.class, () -> testItem.setPurchaseCost(null));
    }

    @Test
    void getPurchaseCost() throws IllegalArgumentModelException {
        testItem.setPurchaseCost("10.001");
        assertEquals("10.001", testItem.getPurchaseCost());
    }

    @Test
    void setSellingPrice_correctInputFormat_setNormally() throws IllegalArgumentModelException {
        String[] correctInput = new String[]{"0.01", "1000", "-0.00", "0.0", ".1"};
        for (String input : correctInput) {
            testItem.setSellingPrice(input);
        }
    }

    @Test
    void setSellingPrice_wrongInputFormat_throwsInvalidFormatException() {
        // negative amount
        String[] negativeInputs = new String[]{"-10", "-0.1"};
        for (String input : negativeInputs) {
            try {
                testItem.setSellingPrice(input);
                fail();
            } catch (Exception e) {
                assertEquals(Item.MESSAGE_INVALID_NEGATIVE_PRICE, e.getMessage());
            }
        }

        // wrong format
        String[] wrongFormat = new String[]{"0.0.0", ".o1", "something~"};
        for (String input : wrongFormat) {
            try {
                testItem.setSellingPrice(input);
                fail();
            } catch (Exception e) {
                assertEquals(Item.MESSAGE_INVALID_PRICE_FORMAT, e.getMessage());
            }
        }

        // null input
        assertThrows(NullPointerException.class, () -> testItem.setSellingPrice(null));
    }

    @Test
    void getSellingPrice() throws IllegalArgumentModelException {
        testItem.setSellingPrice("10.001");
        assertEquals("10.001", testItem.getSellingPrice());
    }
}
