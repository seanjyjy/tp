package seedu.internhunter.logic.parser.add;

import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_INDEX;
import static seedu.internhunter.logic.commands.CommandTestUtil.INVALID_INDEX_RANDOM_STRING;
import static seedu.internhunter.logic.commands.CommandTestUtil.PREAMBLE_EMPTY;
import static seedu.internhunter.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY_RANDOM;
import static seedu.internhunter.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.internhunter.logic.commands.CommandTestUtil.VALID_INDEX_ONE;
import static seedu.internhunter.logic.commands.CommandTestUtil.VALID_INDEX_TWO;
import static seedu.internhunter.logic.commands.util.application.ApplicationCommandTestUtil.INVALID_STATUS_DATE_DESC;
import static seedu.internhunter.logic.commands.util.application.ApplicationCommandTestUtil.INVALID_STATUS_DESC;
import static seedu.internhunter.logic.commands.util.application.ApplicationCommandTestUtil.STATUS_DATE_DESC_JUNE_2021;
import static seedu.internhunter.logic.commands.util.application.ApplicationCommandTestUtil.STATUS_DATE_DESC_JUNE_2022;
import static seedu.internhunter.logic.commands.util.application.ApplicationCommandTestUtil.STATUS_DESC_ACCEPTED;
import static seedu.internhunter.logic.commands.util.application.ApplicationCommandTestUtil.STATUS_DESC_REJECTED;
import static seedu.internhunter.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.internhunter.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.internhunter.model.util.StatusUtil.ACCEPTED_KEYWORD;
import static seedu.internhunter.model.util.StatusUtil.APPLIED_KEYWORD;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.internhunter.testutil.application.ApplicationItemFieldsUtil.STATUS_DATE_JUNE_2021;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.logic.commands.add.AddApplicationCommand;
import seedu.internhunter.model.application.Status;
import seedu.internhunter.model.application.StatusDate;
import seedu.internhunter.model.util.DateUtil;

class AddApplicationCommandParserTest {

    private final AddApplicationCommandParser parser = new AddApplicationCommandParser();

    @Nested
    class ParseSuccess {

        private final Index companyIndex = INDEX_FIRST;
        private final Index internshipIndex = INDEX_SECOND;

        private final Status validStatus = Status.valueOf(ACCEPTED_KEYWORD.toUpperCase());
        private final StatusDate validStatusDate = new StatusDate(DateUtil.convertToDateTime(STATUS_DATE_JUNE_2021));

        private final Status defaultStatus = Status.valueOf(APPLIED_KEYWORD.toUpperCase());
        private final StatusDate defaultStatusDate = new StatusDate(DateUtil.getTodayDate());

        @Test
        public void parse_allFieldsPresent_success() {
            AddApplicationCommand expectedApplicationCommand = new AddApplicationCommand(companyIndex, internshipIndex,
                    validStatus, validStatusDate);

            // whitespace only preamble
            assertParseSuccess(parser, PREAMBLE_WHITESPACE + companyIndex.getOneBased() + VALID_INDEX_TWO
                    + STATUS_DESC_ACCEPTED + STATUS_DATE_DESC_JUNE_2021, expectedApplicationCommand);

            // multiple indexes - last index accepted
            assertParseSuccess(parser, companyIndex.getOneBased() + VALID_INDEX_ONE + VALID_INDEX_TWO
                    + STATUS_DESC_ACCEPTED + STATUS_DATE_DESC_JUNE_2021, expectedApplicationCommand);

            // multiple statuses - last validStatus accepted
            assertParseSuccess(parser, companyIndex.getOneBased() + VALID_INDEX_TWO + STATUS_DESC_REJECTED
                    + STATUS_DESC_ACCEPTED + STATUS_DATE_DESC_JUNE_2021, expectedApplicationCommand);

            // multiple validStatus dates - last validStatus date accepted
            assertParseSuccess(parser, companyIndex.getOneBased() + VALID_INDEX_TWO + STATUS_DESC_ACCEPTED
                    + STATUS_DATE_DESC_JUNE_2022 + STATUS_DATE_DESC_JUNE_2021, expectedApplicationCommand);
        }

        @Test
        public void parse_missingAllOptionalFields_success() {
            AddApplicationCommand expectedApplicationCommand = new AddApplicationCommand(companyIndex, internshipIndex,
                    defaultStatus, defaultStatusDate);

            // missing both prefixes
            assertParseSuccess(parser, companyIndex.getOneBased() + VALID_INDEX_TWO, expectedApplicationCommand);
        }

        @Test
        public void parse_missingStatus_success() {
            AddApplicationCommand expectedApplicationCommand = new AddApplicationCommand(companyIndex, internshipIndex,
                    defaultStatus, validStatusDate);

            // missing Status
            assertParseSuccess(parser, INDEX_FIRST.getOneBased() + VALID_INDEX_TWO + STATUS_DATE_DESC_JUNE_2021,
                    expectedApplicationCommand);
        }

        @Test
        public void parse_missingStatusDate_success() {
            AddApplicationCommand expectedApplicationCommand = new AddApplicationCommand(companyIndex, internshipIndex,
                    validStatus, defaultStatusDate);

            // missing Status date
            assertParseSuccess(parser, INDEX_FIRST.getOneBased() + VALID_INDEX_TWO + STATUS_DESC_ACCEPTED,
                    expectedApplicationCommand);
        }

    }

    @Nested
    class ParseFailure {
        private final String expectedMessageUsage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddApplicationCommand.MESSAGE_USAGE);

        @Test
        public void parse_compulsoryFieldMissing_failure() {

            // missing index in preamble
            assertParseFailure(parser, VALID_INDEX_TWO, expectedMessageUsage);

            // missing index prefix
            assertParseFailure(parser, INDEX_FIRST.getOneBased() + STATUS_DESC_ACCEPTED, expectedMessageUsage);

            // missing all prefixes
            assertParseFailure(parser, "", expectedMessageUsage);

        }

        @Test
        public void parse_invalidValue_failure() {
            // invalid index in preamble
            assertParseFailure(parser, PREAMBLE_NON_EMPTY_RANDOM + VALID_INDEX_TWO + STATUS_DESC_ACCEPTED
                    + STATUS_DATE_DESC_JUNE_2021, MESSAGE_INVALID_INDEX);

            // empty preamble
            assertParseFailure(parser, PREAMBLE_EMPTY + VALID_INDEX_TWO + STATUS_DESC_ACCEPTED
                    + STATUS_DATE_DESC_JUNE_2021, expectedMessageUsage);

            // invalid index in prefix
            assertParseFailure(parser, INDEX_FIRST.getOneBased() + INVALID_INDEX_RANDOM_STRING + STATUS_DESC_ACCEPTED,
                    MESSAGE_INVALID_INDEX);

            // invalid status
            assertParseFailure(parser, INDEX_FIRST.getOneBased() + VALID_INDEX_TWO + INVALID_STATUS_DESC
                    + STATUS_DATE_DESC_JUNE_2021, Status.MESSAGE_CONSTRAINTS);

            // invalid status date
            assertParseFailure(parser, INDEX_FIRST.getOneBased() + VALID_INDEX_TWO + STATUS_DESC_ACCEPTED
                    + INVALID_STATUS_DATE_DESC, StatusDate.MESSAGE_CONSTRAINTS);

            // all invalid values, only first invalid value reported
            assertParseFailure(parser, INDEX_FIRST.getOneBased() + INVALID_INDEX_RANDOM_STRING + INVALID_STATUS_DESC
                    + INVALID_STATUS_DATE_DESC, MESSAGE_INVALID_INDEX);
        }

    }

}
