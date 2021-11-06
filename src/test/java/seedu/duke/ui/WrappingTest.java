package seedu.duke.ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.duke.ui.Wrapping.restrictMessageLength;

public class WrappingTest {
    @Test
    void autoWrapTest() {
        Wrapping wrapping = new Wrapping("abcde abcd dsd a abcdef asdfe", 5);
        assertEquals("abcde", wrapping.nextLine());
        assertEquals("abcd", wrapping.nextLine());
        assertEquals("dsd a", wrapping.nextLine());
        assertEquals("abcd-", wrapping.nextLine());
        assertEquals("ef", wrapping.nextLine());
        assertEquals("asdfe", wrapping.nextLine());
        assertEquals("", wrapping.nextLine());
    }

    @Test
    void restrictMessageLength() {
        String message = "abcde";
        assertEquals(message, Wrapping.restrictMessageLength(message, 5));
        assertEquals("a...", Wrapping.restrictMessageLength(message,4));
        assertEquals("abcde ", Wrapping.restrictMessageLength(message,6));
    }
}
