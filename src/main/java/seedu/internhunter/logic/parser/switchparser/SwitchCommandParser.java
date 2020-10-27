package seedu.internhunter.logic.parser.switchparser;


import static java.util.Objects.requireNonNull;
import static seedu.internhunter.logic.parser.util.TabParserUtil.parseTab;

import seedu.internhunter.logic.commands.SwitchCommand;
import seedu.internhunter.logic.parser.Parser;
import seedu.internhunter.logic.parser.exceptions.ParseException;
import seedu.internhunter.ui.tabs.TabName;

/**
 * Parses input arguments and creates a new SwitchCommand object
 */
public class SwitchCommandParser implements Parser<SwitchCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SwitchCommand
     * and returns a SwitchCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public SwitchCommand parse(String args) throws ParseException {
        requireNonNull(args);
        TabName tabName = parseTab(args);
        return new SwitchCommand(tabName);
    }
}
