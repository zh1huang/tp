package seedu.duke.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.model.exception.InvalidFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;


class ItemTest {
    Item testItem;

    @BeforeEach
    void prepare() {
        testItem = new Item("Dune", "1.00", "2.00");
    }

    @Test
    void setName() {
        // correct name format
        testItem.setName("The Lord of the Rings");
        testItem.setName("1984_someone");
        testItem.setName("A LEVEL H2 PHYSICS (TOPICAL) 2011-2020");

        // empty name or wrong format
        String[] wrongInputs = new String[]{"", " ", "\t", "\n", " \r", "1984+", "Bazinga!", "something~"};
        for (String input : wrongInputs) {
            assertThrows(InvalidFormat.class, () -> testItem.setName(input));
        }
    }

    @Test
    void setPurchaseCost() {
        // correct amount
        testItem.setPurchaseCost("0.01");
        testItem.setPurchaseCost("1000");
        testItem.setPurchaseCost("-0.00");
        testItem.setPurchaseCost("0.0");
        testItem.setPurchaseCost(".1");

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
    void setSellingPrice() {
        // correct amount
        testItem.setSellingPrice("0.01");
        testItem.setSellingPrice("1000");
        testItem.setSellingPrice("-0.00");
        testItem.setSellingPrice("0.0");
        testItem.setSellingPrice(".1");

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