package seedu.internhunter.logic.parser;

import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_ITEM_TYPE_ABRIDGED;
import static seedu.internhunter.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.internhunter.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.internhunter.model.util.ItemUtil.APPLICATION_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.COMPANY_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.PROFILE_ALIAS;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.internhunter.logic.commands.SwitchCommand;
import seedu.internhunter.logic.parser.switchparser.SwitchCommandParser;
import seedu.internhunter.ui.tabs.TabName;

/**
 * todo javadocs
 */
public class SwitchCommandParserTest {

    private static final String EXPECTED_MESSAGE = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
        SwitchCommand.MESSAGE_USAGE);

    private SwitchCommandParser switchCommandParser;

    @BeforeEach
    public void setUp() {
        switchCommandParser = new SwitchCommandParser();
    }

    @Test
    public void parse_missingTabNames_failure() {
        // no tab name input
        assertParseFailure(switchCommandParser, "", EXPECTED_MESSAGE);

        // space
        assertParseFailure(switchCommandParser, " ", EXPECTED_MESSAGE);

    }

    @Test
    public void parse_invalidTabNames_failure() {
        // invalid tab name(words)
        assertParseFailure(switchCommandParser, "hello", MESSAGE_INVALID_ITEM_TYPE_ABRIDGED);

        // invalid tab name(numbers)
        assertParseFailure(switchCommandParser, "1", MESSAGE_INVALID_ITEM_TYPE_ABRIDGED);

        // almost correct tab name
        assertParseFailure(switchCommandParser, "com1", MESSAGE_INVALID_ITEM_TYPE_ABRIDGED);

        // adopting a strict case sensitive for parsers
        assertParseFailure(switchCommandParser, "Com", MESSAGE_INVALID_ITEM_TYPE_ABRIDGED);

        // adopting a strict case sensitive for parsers
        assertParseFailure(switchCommandParser, "App", MESSAGE_INVALID_ITEM_TYPE_ABRIDGED);

        // adopting a strict case sensitive for parsers
        assertParseFailure(switchCommandParser, "Me", MESSAGE_INVALID_ITEM_TYPE_ABRIDGED);
    }

    @Test
    public void parse_validTabNames_success() {
        // Valid tab name - com
        assertParseSuccess(switchCommandParser, COMPANY_ALIAS, new SwitchCommand(TabName.COMPANY));

        // valid tab name - app
        assertParseSuccess(switchCommandParser, APPLICATION_ALIAS, new SwitchCommand(TabName.APPLICATION));

        // valid tab name - me
        assertParseSuccess(switchCommandParser, PROFILE_ALIAS, new SwitchCommand(TabName.PROFILE));
    }
}
