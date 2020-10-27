package seedu.internhunter.logic.parser.edit;

import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_INDEX;
import static seedu.internhunter.logic.commands.util.profile.ProfileCommandTestUtil.CATEGORY_DESC_ACHIEVEMENT;
import static seedu.internhunter.logic.commands.util.profile.ProfileCommandTestUtil.CATEGORY_DESC_EXPERIENCE;
import static seedu.internhunter.logic.commands.util.profile.ProfileCommandTestUtil.CATEGORY_DESC_SKILL;
import static seedu.internhunter.logic.commands.util.profile.ProfileCommandTestUtil.DESCRIPTOR_DESC_IMPLEMENT;
import static seedu.internhunter.logic.commands.util.profile.ProfileCommandTestUtil.DESCRIPTOR_DESC_LEARN;
import static seedu.internhunter.logic.commands.util.profile.ProfileCommandTestUtil.INVALID_CATEGORY_DESC;
import static seedu.internhunter.logic.commands.util.profile.ProfileCommandTestUtil.INVALID_DESCRIPTOR_DESC;
import static seedu.internhunter.logic.commands.util.profile.ProfileCommandTestUtil.INVALID_TITLE_DESC;
import static seedu.internhunter.logic.commands.util.profile.ProfileCommandTestUtil.TITLE_DESC_COMPETITION;
import static seedu.internhunter.logic.commands.util.profile.ProfileCommandTestUtil.TITLE_DESC_INTERNSHIP;
import static seedu.internhunter.logic.commands.util.profile.ProfileCommandTestUtil.VALID_CATEGORY_ACHIEVEMENT;
import static seedu.internhunter.logic.commands.util.profile.ProfileCommandTestUtil.VALID_CATEGORY_EXPERIENCE;
import static seedu.internhunter.logic.commands.util.profile.ProfileCommandTestUtil.VALID_CATEGORY_SKILL;
import static seedu.internhunter.logic.commands.util.profile.ProfileCommandTestUtil.VALID_DESCRIPTOR_IMPLEMENT;
import static seedu.internhunter.logic.commands.util.profile.ProfileCommandTestUtil.VALID_DESCRIPTOR_LEARN;
import static seedu.internhunter.logic.commands.util.profile.ProfileCommandTestUtil.VALID_TITLE_COMPETITION;
import static seedu.internhunter.logic.commands.util.profile.ProfileCommandTestUtil.VALID_TITLE_HTML;
import static seedu.internhunter.logic.commands.util.profile.ProfileCommandTestUtil.VALID_TITLE_INTERNSHIP;
import static seedu.internhunter.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.internhunter.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.internhunter.logic.parser.clisyntax.ProfileCliSyntax.PREFIX_DESCRIPTOR;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_THIRD;

import org.junit.jupiter.api.Test;

import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.logic.commands.edit.EditCommand;
import seedu.internhunter.logic.commands.edit.EditProfileCommand;
import seedu.internhunter.logic.commands.edit.EditProfileCommand.EditProfileItemDescriptor;
import seedu.internhunter.model.profile.Descriptor;
import seedu.internhunter.model.profile.ProfileItemCategory;
import seedu.internhunter.model.profile.Title;
import seedu.internhunter.testutil.profile.EditProfileItemDescriptorBuilder;

public class EditProfileCommandParserTest {


    private static final String DESCRIPTOR_EMPTY = " " + PREFIX_DESCRIPTOR;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditProfileCommand.MESSAGE_USAGE);

    private EditProfileCommandParser parser = new EditProfileCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, TITLE_DESC_COMPETITION, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_TITLE_HTML, MESSAGE_INVALID_INDEX);

        // zero index
        assertParseFailure(parser, "0" + VALID_TITLE_COMPETITION, MESSAGE_INVALID_INDEX);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_INDEX);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid title
        assertParseFailure(parser, "1" + INVALID_TITLE_DESC, Title.MESSAGE_CONSTRAINTS);
        // invalid category
        assertParseFailure(parser, "1" + INVALID_CATEGORY_DESC, ProfileItemCategory.MESSAGE_CONSTRAINTS);
        // invalid descriptor
        assertParseFailure(parser, "1" + INVALID_DESCRIPTOR_DESC, Descriptor.MESSAGE_CONSTRAINTS);

        // invalid title followed by valid category
        assertParseFailure(parser, "1" + INVALID_TITLE_DESC + CATEGORY_DESC_ACHIEVEMENT, Title.MESSAGE_CONSTRAINTS);

        // valid title followed by invalid title. The test case for invalid title followed by valid title
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + TITLE_DESC_COMPETITION + INVALID_TITLE_DESC, Title.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_DESCRIPTOR} alone will reset the descriptor of the {@code ProfileItem} being
        // edited,
        // parsing it together with a valid descriptor results in error
        assertParseFailure(parser, "1" + DESCRIPTOR_DESC_IMPLEMENT + DESCRIPTOR_DESC_LEARN + DESCRIPTOR_EMPTY,
                Descriptor.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + DESCRIPTOR_DESC_IMPLEMENT + DESCRIPTOR_EMPTY + DESCRIPTOR_DESC_LEARN,
                Descriptor.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + DESCRIPTOR_EMPTY + DESCRIPTOR_DESC_LEARN + DESCRIPTOR_DESC_IMPLEMENT,
                Descriptor.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_TITLE_DESC + INVALID_CATEGORY_DESC + VALID_DESCRIPTOR_IMPLEMENT,
                Title.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + TITLE_DESC_COMPETITION
                + CATEGORY_DESC_ACHIEVEMENT + DESCRIPTOR_DESC_LEARN + DESCRIPTOR_DESC_IMPLEMENT;

        EditProfileItemDescriptor descriptor = new EditProfileItemDescriptorBuilder().withTitle(VALID_TITLE_COMPETITION)
                        .withProfileItemCategory(VALID_CATEGORY_ACHIEVEMENT)
                        .withDescriptors(VALID_DESCRIPTOR_LEARN, VALID_DESCRIPTOR_IMPLEMENT).build();
        EditProfileCommand expectedCommand = new EditProfileCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + TITLE_DESC_COMPETITION + CATEGORY_DESC_ACHIEVEMENT;

        EditProfileItemDescriptor descriptor = new EditProfileItemDescriptorBuilder().withTitle(VALID_TITLE_COMPETITION)
                        .withProfileItemCategory(VALID_CATEGORY_ACHIEVEMENT).build();
        EditProfileCommand expectedCommand = new EditProfileCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // title
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + TITLE_DESC_COMPETITION;
        EditProfileItemDescriptor descriptor =
                new EditProfileItemDescriptorBuilder().withTitle(VALID_TITLE_COMPETITION).build();
        EditProfileCommand expectedCommand = new EditProfileCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // category
        userInput = targetIndex.getOneBased() + CATEGORY_DESC_SKILL;
        descriptor = new EditProfileItemDescriptorBuilder().withProfileItemCategory(VALID_CATEGORY_SKILL).build();
        expectedCommand = new EditProfileCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);


        // profile descriptors
        userInput = targetIndex.getOneBased() + DESCRIPTOR_DESC_IMPLEMENT + DESCRIPTOR_DESC_LEARN;
        descriptor = new EditProfileItemDescriptorBuilder()
                .withDescriptors(VALID_DESCRIPTOR_IMPLEMENT, VALID_DESCRIPTOR_LEARN).build();
        expectedCommand = new EditProfileCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + TITLE_DESC_COMPETITION + CATEGORY_DESC_ACHIEVEMENT
                + DESCRIPTOR_DESC_LEARN + TITLE_DESC_INTERNSHIP + CATEGORY_DESC_EXPERIENCE + DESCRIPTOR_DESC_LEARN
                + DESCRIPTOR_DESC_IMPLEMENT;

        EditProfileItemDescriptor descriptor = new EditProfileItemDescriptorBuilder().withTitle(VALID_TITLE_INTERNSHIP)
                .withProfileItemCategory(VALID_CATEGORY_EXPERIENCE)
                .withDescriptors(VALID_DESCRIPTOR_LEARN, VALID_DESCRIPTOR_IMPLEMENT)
                .build();
        EditProfileCommand expectedCommand = new EditProfileCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + INVALID_CATEGORY_DESC + CATEGORY_DESC_ACHIEVEMENT;
        EditProfileItemDescriptor descriptor = new EditProfileItemDescriptorBuilder()
                .withProfileItemCategory(VALID_CATEGORY_ACHIEVEMENT).build();
        EditProfileCommand expectedCommand = new EditProfileCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput =
                targetIndex.getOneBased() + INVALID_CATEGORY_DESC + DESCRIPTOR_DESC_IMPLEMENT + TITLE_DESC_COMPETITION
                        + CATEGORY_DESC_ACHIEVEMENT;

        descriptor = new EditProfileItemDescriptorBuilder().withTitle(VALID_TITLE_COMPETITION)
                .withProfileItemCategory(VALID_CATEGORY_ACHIEVEMENT)
                .withDescriptors(VALID_DESCRIPTOR_IMPLEMENT).build();
        expectedCommand = new EditProfileCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetDescriptors_success() {
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + DESCRIPTOR_EMPTY;

        EditProfileItemDescriptor descriptor = new EditProfileItemDescriptorBuilder().withDescriptors().build();
        EditProfileCommand expectedCommand = new EditProfileCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
