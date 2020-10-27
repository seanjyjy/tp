package seedu.internhunter.storage.profile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.internhunter.storage.profile.JsonAdaptedProfileItem.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.internhunter.testutil.Assert.assertThrows;
import static seedu.internhunter.testutil.profile.SampleProfileItems.HTML_SKILL;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.internhunter.commons.exceptions.IllegalValueException;
import seedu.internhunter.model.profile.Descriptor;
import seedu.internhunter.model.profile.ProfileItemCategory;
import seedu.internhunter.model.profile.Title;

public class JsonAdaptedProfileItemTest {
    public static final String INVALID_TITLE = "@w350M3";
    public static final String INVALID_CATEGORY = "PROJECT";
    public static final String INVALID_DESCRIPTOR = "";

    public static final String VALID_TITLE = HTML_SKILL.getTitle().toString();
    public static final String VALID_CATEGORY = HTML_SKILL.getCategory().toString();
    public static final Set<JsonAdaptedDescriptor> VALID_DESCRIPTOR = HTML_SKILL.getDescriptors().stream()
            .map(JsonAdaptedDescriptor::new)
            .collect(Collectors.toSet());

    @Test
    public void toModelType_validProfileItemDetails_returnsProfileItem() throws Exception {
        JsonAdaptedProfileItem internshipItem = new JsonAdaptedProfileItem(HTML_SKILL);
        assertEquals(HTML_SKILL, internshipItem.toModelType());
    }

    @Test
    public void toModelType_invalidTitle_throwsIllegalValueException() {
        JsonAdaptedProfileItem profileItem = new JsonAdaptedProfileItem(INVALID_TITLE,
                VALID_CATEGORY, VALID_DESCRIPTOR);
        String expectedMessage = Title.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, profileItem::toModelType);
    }

    @Test
    public void toModelType_nullTitle_throwsIllegalValueException() {
        JsonAdaptedProfileItem profileItem = new JsonAdaptedProfileItem(null, VALID_CATEGORY, VALID_DESCRIPTOR);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, profileItem::toModelType);
    }

    @Test
    public void toModelType_invalidCategory_throwsIllegalValueException() {
        JsonAdaptedProfileItem profileItem = new JsonAdaptedProfileItem(VALID_TITLE,
                INVALID_CATEGORY, VALID_DESCRIPTOR);
        String expectedMessage = ProfileItemCategory.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, profileItem::toModelType);
    }

    @Test
    public void toModelType_nullCategory_throwsIllegalValueException() {
        JsonAdaptedProfileItem profileItem = new JsonAdaptedProfileItem(VALID_TITLE, null, VALID_DESCRIPTOR);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ProfileItemCategory.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, profileItem::toModelType);
    }

    @Test
    public void toModelType_invalidDescriptor_throwsIllegalValueException() {
        Set<JsonAdaptedDescriptor> invalidDescriptors = new HashSet<>();
        invalidDescriptors.add(new JsonAdaptedDescriptor(INVALID_DESCRIPTOR));
        JsonAdaptedProfileItem profileItem = new JsonAdaptedProfileItem(VALID_TITLE,
                VALID_CATEGORY, invalidDescriptors);
        String expectedMessage = Descriptor.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, profileItem::toModelType);
    }
}
