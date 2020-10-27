package seedu.internhunter.logic.parser.add;

import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internhunter.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY_RANDOM;
import static seedu.internhunter.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
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
import static seedu.internhunter.logic.commands.util.profile.ProfileCommandTestUtil.VALID_DESCRIPTOR_IMPLEMENT;
import static seedu.internhunter.logic.commands.util.profile.ProfileCommandTestUtil.VALID_DESCRIPTOR_LEARN;
import static seedu.internhunter.logic.commands.util.profile.ProfileCommandTestUtil.VALID_TITLE_COMPETITION;
import static seedu.internhunter.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.internhunter.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.internhunter.testutil.profile.SampleProfileItems.BYTEDANCE_INTERN;
import static seedu.internhunter.testutil.profile.SampleProfileItems.ORBITAL_ACHIEVEMENT;

import org.junit.jupiter.api.Test;

import seedu.internhunter.logic.commands.add.AddProfileCommand;
import seedu.internhunter.model.profile.Descriptor;
import seedu.internhunter.model.profile.ProfileItem;
import seedu.internhunter.model.profile.ProfileItemCategory;
import seedu.internhunter.model.profile.Title;
import seedu.internhunter.testutil.profile.ProfileItemBuilder;

class AddProfileCommandParserTest {

    private AddProfileCommandParser parser = new AddProfileCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        ProfileItem expectedProfileItem =
                new ProfileItemBuilder(BYTEDANCE_INTERN).withDescriptors(VALID_DESCRIPTOR_LEARN).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TITLE_DESC_INTERNSHIP + CATEGORY_DESC_EXPERIENCE
                + DESCRIPTOR_DESC_LEARN, new AddProfileCommand(expectedProfileItem));

        // multiple title - last title accepted
        assertParseSuccess(parser, TITLE_DESC_COMPETITION + TITLE_DESC_INTERNSHIP
                + CATEGORY_DESC_EXPERIENCE + DESCRIPTOR_DESC_LEARN, new AddProfileCommand(expectedProfileItem));

        // multiple categories - last category accepted
        assertParseSuccess(parser, TITLE_DESC_INTERNSHIP + CATEGORY_DESC_SKILL
                + CATEGORY_DESC_EXPERIENCE + DESCRIPTOR_DESC_LEARN, new AddProfileCommand(expectedProfileItem));

        // multiple descriptors - all accepted
        ProfileItem expectedProfileItemMultipleDescriptors = new ProfileItemBuilder(BYTEDANCE_INTERN)
                .withDescriptors(VALID_DESCRIPTOR_IMPLEMENT, VALID_DESCRIPTOR_LEARN)
                .build();

        assertParseSuccess(parser, TITLE_DESC_INTERNSHIP + CATEGORY_DESC_EXPERIENCE
                        + DESCRIPTOR_DESC_LEARN + DESCRIPTOR_DESC_IMPLEMENT,
                new AddProfileCommand(expectedProfileItemMultipleDescriptors));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero descriptors
        ProfileItem expectedProfileItem = new ProfileItemBuilder(ORBITAL_ACHIEVEMENT).withDescriptors().build();
        assertParseSuccess(parser, TITLE_DESC_COMPETITION + CATEGORY_DESC_ACHIEVEMENT,
                new AddProfileCommand(expectedProfileItem));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddProfileCommand.MESSAGE_USAGE);

        // missing title prefix
        assertParseFailure(parser, VALID_TITLE_COMPETITION + CATEGORY_DESC_EXPERIENCE + DESCRIPTOR_DESC_LEARN,
                expectedMessage);

        // missing category prefix
        assertParseFailure(parser,
                TITLE_DESC_COMPETITION + VALID_CATEGORY_ACHIEVEMENT + DESCRIPTOR_DESC_LEARN,
                expectedMessage);

        // both prefixes missing
        assertParseFailure(parser,
                VALID_TITLE_COMPETITION + VALID_CATEGORY_ACHIEVEMENT + VALID_DESCRIPTOR_LEARN,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid title
        assertParseFailure(parser, INVALID_TITLE_DESC + CATEGORY_DESC_EXPERIENCE
                + DESCRIPTOR_DESC_LEARN + DESCRIPTOR_DESC_IMPLEMENT , Title.MESSAGE_CONSTRAINTS);

        // invalid category
        assertParseFailure(parser, TITLE_DESC_COMPETITION + INVALID_CATEGORY_DESC
                + DESCRIPTOR_DESC_LEARN + DESCRIPTOR_DESC_IMPLEMENT , ProfileItemCategory.MESSAGE_CONSTRAINTS);

        // invalid descriptor
        assertParseFailure(parser, TITLE_DESC_COMPETITION + CATEGORY_DESC_EXPERIENCE
                + INVALID_DESCRIPTOR_DESC + DESCRIPTOR_DESC_IMPLEMENT , Descriptor.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_TITLE_DESC + INVALID_CATEGORY_DESC
                + DESCRIPTOR_DESC_LEARN + DESCRIPTOR_DESC_IMPLEMENT , Title.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, INVALID_TITLE_DESC + DESCRIPTOR_DESC_IMPLEMENT + INVALID_CATEGORY_DESC
                + DESCRIPTOR_DESC_LEARN, Title.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY_RANDOM + TITLE_DESC_INTERNSHIP + CATEGORY_DESC_EXPERIENCE
                        + DESCRIPTOR_DESC_LEARN + DESCRIPTOR_DESC_IMPLEMENT ,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddProfileCommand.MESSAGE_USAGE));
    }
}
