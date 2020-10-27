package seedu.internhunter.logic.parser.edit;

import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_INDEX;
import static seedu.internhunter.logic.commands.edit.EditCommand.MESSAGE_NOT_EDITED;
import static seedu.internhunter.logic.commands.util.application.ApplicationCommandTestUtil.INVALID_STATUS_DATE_DESC;
import static seedu.internhunter.logic.commands.util.application.ApplicationCommandTestUtil.INVALID_STATUS_DESC;
import static seedu.internhunter.logic.commands.util.application.ApplicationCommandTestUtil.STATUS_DATE_DESC_JUNE_2021;
import static seedu.internhunter.logic.commands.util.application.ApplicationCommandTestUtil.STATUS_DATE_DESC_JUNE_2022;
import static seedu.internhunter.logic.commands.util.application.ApplicationCommandTestUtil.STATUS_DESC_ACCEPTED;
import static seedu.internhunter.logic.commands.util.application.ApplicationCommandTestUtil.STATUS_DESC_REJECTED;
import static seedu.internhunter.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.internhunter.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.internhunter.model.util.StatusUtil.ACCEPTED_KEYWORD;
import static seedu.internhunter.model.util.StatusUtil.REJECTED_KEYWORD;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_THIRD;
import static seedu.internhunter.testutil.application.ApplicationItemFieldsUtil.STATUS_DATE_JUNE_2021;
import static seedu.internhunter.testutil.application.ApplicationItemFieldsUtil.STATUS_DATE_JUNE_2022;

import org.junit.jupiter.api.Test;

import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.logic.commands.edit.EditApplicationCommand;
import seedu.internhunter.logic.commands.edit.EditApplicationCommand.EditApplicationDescriptor;
import seedu.internhunter.logic.commands.util.application.EditApplicationDescriptorBuilder;
import seedu.internhunter.model.application.Status;
import seedu.internhunter.model.application.StatusDate;

public class EditApplicationCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditApplicationCommand.MESSAGE_USAGE);

    private final EditApplicationCommandParser parser = new EditApplicationCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, STATUS_DESC_ACCEPTED, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + STATUS_DESC_REJECTED, MESSAGE_INVALID_INDEX);

        // zero index
        assertParseFailure(parser, "0" + STATUS_DESC_REJECTED, MESSAGE_INVALID_INDEX);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_INDEX);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/string", MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_invalidFields_failure() {
        assertParseFailure(parser, "1" + INVALID_STATUS_DESC, Status.MESSAGE_CONSTRAINTS); // invalid status
        assertParseFailure(parser, "1" + INVALID_STATUS_DATE_DESC, StatusDate.MESSAGE_CONSTRAINTS); // invalid status
        // date

        // invalid status date followed by valid email
        assertParseFailure(parser, "1" + INVALID_STATUS_DATE_DESC + STATUS_DESC_ACCEPTED,
                StatusDate.MESSAGE_CONSTRAINTS);

        // Invalid status and status date, but only the status is captured
        assertParseFailure(parser, "1" + INVALID_STATUS_DATE_DESC + INVALID_STATUS_DESC,
                Status.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validFieldFollowedByInvalidField_failure() {
        // valid status date followed by invalid status date. The test case for invalid status date followed by valid
        // status date is tested at {@code parse_invalidFieldFollowedByValidField_success()}
        assertParseFailure(parser, "1" + STATUS_DESC_ACCEPTED + INVALID_STATUS_DATE_DESC,
                StatusDate.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + STATUS_DESC_ACCEPTED + STATUS_DATE_DESC_JUNE_2021;

        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder().withStatus(ACCEPTED_KEYWORD)
                .withStatusDate(STATUS_DATE_JUNE_2021).build();
        EditApplicationCommand expectedCommand = new EditApplicationCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // status
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + STATUS_DESC_REJECTED;
        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder()
                .withStatus(REJECTED_KEYWORD).build();
        EditApplicationCommand expectedCommand = new EditApplicationCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // status date
        userInput = targetIndex.getOneBased() + STATUS_DATE_DESC_JUNE_2022;
        descriptor = new EditApplicationDescriptorBuilder().withStatusDate(STATUS_DATE_JUNE_2022).build();
        expectedCommand = new EditApplicationCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + STATUS_DATE_DESC_JUNE_2021 + STATUS_DATE_DESC_JUNE_2022;

        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder()
                .withStatusDate(STATUS_DATE_JUNE_2022).build();
        EditApplicationCommand expectedCommand = new EditApplicationCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidFieldFollowedByValidField_success() {
        // Status field only
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + INVALID_STATUS_DESC + STATUS_DESC_ACCEPTED;
        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder()
                .withStatus(ACCEPTED_KEYWORD).build();
        EditApplicationCommand expectedCommand = new EditApplicationCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // Both status and status date fields
        userInput = targetIndex.getOneBased() + INVALID_STATUS_DESC + INVALID_STATUS_DATE_DESC + STATUS_DESC_ACCEPTED
                + STATUS_DATE_DESC_JUNE_2022;
        descriptor = new EditApplicationDescriptorBuilder().withStatusDate(STATUS_DATE_JUNE_2022)
                .withStatus(ACCEPTED_KEYWORD).build();
        expectedCommand = new EditApplicationCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
