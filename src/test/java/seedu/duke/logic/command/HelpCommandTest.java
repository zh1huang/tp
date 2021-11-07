package seedu.duke.logic.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelpCommandTest {

    @Test
    public void execute_help_success() throws Exception {
        String expected = HelpCommand.COMMAND_LIST;
        Command testCommand = new HelpCommand();
        assertEquals(testCommand.execute(), expected);
    }
}
