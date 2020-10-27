package seedu.internhunter.logic.parser.delete;

import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_INDEX;
import static seedu.internhunter.logic.commands.CommandTestUtil.INVALID_INDEX_RANDOM_STRING;
import static seedu.internhunter.logic.commands.CommandTestUtil.PREAMBLE_EMPTY;
import static seedu.internhunter.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY_RANDOM;
import static seedu.internhunter.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.internhunter.logic.commands.CommandTestUtil.VALID_INDEX_ONE;
import static seedu.internhunter.logic.commands.CommandTestUtil.VALID_INDEX_TWO;
import static seedu.internhunter.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.internhunter.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.logic.commands.delete.DeleteInternshipCommand;

public class DeleteInternshipCommandParserTest {

    private final DeleteInternshipCommandParser parser = new DeleteInternshipCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        final Index companyIndex = INDEX_FIRST;
        final Index internshipIndex = INDEX_SECOND;

        DeleteInternshipCommand expectedDeleteCommand = new DeleteInternshipCommand(companyIndex, internshipIndex);

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + companyIndex.getOneBased() + VALID_INDEX_TWO,
                expectedDeleteCommand);

        // multiple indexes - last index accepted
        assertParseSuccess(parser, companyIndex.getOneBased() + VALID_INDEX_ONE + VALID_INDEX_TWO,
                expectedDeleteCommand);

    }

    @Nested
    class ParseFailure {

        private final String expectedMessageUsage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteInternshipCommand.MESSAGE_USAGE);

        @Test
        public void parse_missingFields_failure() {
            // missing index in preamble
            assertParseFailure(parser, VALID_INDEX_TWO, expectedMessageUsage);

            // missing index prefix
            assertParseFailure(parser, INDEX_FIRST.toString(), expectedMessageUsage);

            // missing all prefixes
            assertParseFailure(parser, "", expectedMessageUsage);

        }

        @Test
        public void parse_invalidValue_failure() {
            // invalid index in preamble
            assertParseFailure(parser, PREAMBLE_NON_EMPTY_RANDOM + VALID_INDEX_TWO, MESSAGE_INVALID_INDEX);

            // empty preamble
            assertParseFailure(parser, PREAMBLE_EMPTY + VALID_INDEX_TWO, expectedMessageUsage);

            // invalid index in prefix
            assertParseFailure(parser, INDEX_FIRST.getOneBased() + INVALID_INDEX_RANDOM_STRING,
                    MESSAGE_INVALID_INDEX);

        }

    }

}
