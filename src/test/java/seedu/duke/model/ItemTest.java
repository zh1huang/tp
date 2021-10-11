package seedu.duke.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.model.exception.InvalidFormatException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;


class ItemTest {
    Item testItem;

    @BeforeEach
    void setup() {
        testItem = new Item("Dune", "1.00", "2.00");
    }

    @Test
    void setName_correctInputFormat_setNormally() {
        testItem.setName("The Lord of the Rings");
        testItem.setName("1984_someone");
        testItem.setName("A LEVEL H2 PHYSICS (TOPICAL) 2011-2020");
    }

    @Test
    void setName_wrongInputFormat_throwsInvalidFormatException() {
        String[] wrongInputs = new String[]{"", " ", "\t", "\n", " \r", "1984+", "Bazinga!", "something~"};
        for (String input : wrongInputs) {
            assertThrows(InvalidFormatException.class, () -> testItem.setName(input));
        }
    }

    @Test
    void setPurchaseCost_correctInputFormat_setNormally() {
        String[] correctInput = new String[]{"0.01", "1000", "-0.00", "0.0", ".1"};
        for (String input: correctInput) {
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
    }

    @Test
    void setSellingPrice_correctInputFormat_setNormally()  {
        String[] correctInput = new String[]{"0.01", "1000", "-0.00", "0.0", ".1"};
        for (String input: correctInput) {
            testItem.setSellingPrice(input);
        }
    }

    @Test
    void setSellingPrice_wrongInputFormat_throwsInvalidFormatException()  {
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
    }
}