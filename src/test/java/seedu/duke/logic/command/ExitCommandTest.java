package seedu.duke.logic.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExitCommandTest {

    @Test
    public void execute_exit_success() throws Exception {
        String expected = ExitCommand.BYE_MESSAGE;
        Command testCommand = new ExitCommand();
        assertEquals(testCommand.execute(), expected);
    }
}
