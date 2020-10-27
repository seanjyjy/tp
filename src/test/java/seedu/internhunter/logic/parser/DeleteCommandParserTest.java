package seedu.internhunter.logic.parser;

import static seedu.internhunter.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.internhunter.commons.core.Messages;
import seedu.internhunter.logic.parser.delete.DeleteCommandParser;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // todo: change the test case to use the currently commented-out line when all 3 delete command parsers for
        //  individual items have been implemented
        assertParseFailure(parser, "a", Messages.MESSAGE_INVALID_ITEM_TYPE);
    }
}
