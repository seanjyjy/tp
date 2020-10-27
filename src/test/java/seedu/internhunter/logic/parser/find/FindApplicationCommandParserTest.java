package seedu.internhunter.logic.parser.find;

import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internhunter.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.internhunter.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.internhunter.logic.commands.find.FindApplicationCommand;
import seedu.internhunter.model.application.ApplicationNameContainsKeyWordsPredicate;

/**
 * todo javadocs
 */
public class FindApplicationCommandParserTest {

    private static final String MESSAGE = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
        FindApplicationCommand.MESSAGE_USAGE);
    private FindApplicationCommandParser findApplicationCommandParser;

    @BeforeEach
    public void setUp() {
        findApplicationCommandParser = new FindApplicationCommandParser();
    }

    @Test
    public void parse_missingDescription_throwsParseException() {
        assertParseFailure(findApplicationCommandParser, "    ", MESSAGE);
        assertParseFailure(findApplicationCommandParser, " ", MESSAGE);
    }

    @Test
    public void parse_validArguments_returnsFindApplicationCommand() {
        FindApplicationCommand expectedCommand = new FindApplicationCommand(
            new ApplicationNameContainsKeyWordsPredicate(Arrays.asList("Software", "Hardware", "Developers")));

        assertParseSuccess(findApplicationCommandParser, "Software Hardware Developers", expectedCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(findApplicationCommandParser, "\n Software \n \t Hardware Developers\t",
            expectedCommand);
    }
}
