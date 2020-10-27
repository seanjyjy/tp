package seedu.internhunter.storage.profile;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.internhunter.commons.exceptions.IllegalValueException;
import seedu.internhunter.model.profile.Descriptor;
import seedu.internhunter.model.profile.ProfileItem;
import seedu.internhunter.model.profile.ProfileItemCategory;
import seedu.internhunter.model.profile.Title;
import seedu.internhunter.storage.item.JsonAdaptedItem;

/**
 * Jackson-friendly version of {@link ProfileItem}.
 */
public class JsonAdaptedProfileItem extends JsonAdaptedItem {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Profile item's %s field is missing!";

    private final String title;
    private final String profileType;
    private final Set<JsonAdaptedDescriptor> descriptors = new HashSet<>();

    /**
     * Constructs a {@code JsonAdaptedProfileItem} with the given profile item details.
     */
    @JsonCreator
    public JsonAdaptedProfileItem(@JsonProperty("title") String title, @JsonProperty("profileType") String profileType,
            @JsonProperty("descriptors") Set<JsonAdaptedDescriptor> descriptors) {
        this.title = title;
        this.profileType = profileType;
        this.descriptors.addAll(descriptors);
    }

    /**
     * Converts a given {@code ProfileItem} into this class for Jackson use.
     */
    public JsonAdaptedProfileItem(ProfileItem source) {
        assert source != null : JsonAdaptedItem.NULL_SOURCE_ERROR_MESSAGE;
        title = source.getTitleValue();
        profileType = source.getCategory().toString();
        descriptors.addAll(source.getDescriptors().stream()
                .map(JsonAdaptedDescriptor::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted profile item object into the model's {@code ProfileItem} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted company item.
     */
    @Override
    public ProfileItem toModelType() throws IllegalValueException {
        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Title.class.getSimpleName()));
        }
        if (!Title.isValidAlphaNumericWord(title)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }
        final Title itemTitle = new Title(title);

        if (profileType == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ProfileItemCategory.class.getSimpleName()));
        }
        if (!ProfileItemCategory.isValidProfileItemCategory(profileType)) {
            throw new IllegalValueException(ProfileItemCategory.MESSAGE_CONSTRAINTS);
        }

        final ProfileItemCategory itemProfileType = ProfileItemCategory.valueOf(profileType.toUpperCase());

        final Set<Descriptor> itemDescriptors = new HashSet<>();

        for (JsonAdaptedDescriptor descriptor : descriptors) {
            itemDescriptors.add(descriptor.toModelType());
        }

        return new ProfileItem(itemTitle, itemProfileType, itemDescriptors);
    }
}
