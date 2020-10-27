package seedu.internhunter.testutil.profile;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.internhunter.model.profile.Descriptor;
import seedu.internhunter.model.profile.ProfileItem;
import seedu.internhunter.model.profile.ProfileItemCategory;
import seedu.internhunter.model.profile.Title;

/**
 * A utility class to help with building ProfileItem objects.
 */
public class ProfileItemBuilder {

    public static final String DEFAULT_PROFILE_TITLE = "Vue";
    public static final ProfileItemCategory DEFAULT_CATEGORY = ProfileItemCategory.SKILL;

    private Title title;
    private ProfileItemCategory category;
    private Set<Descriptor> descriptors;

    /**
     * Creates a {@code ProfileItemBuilder} with the default details.
     */
    public ProfileItemBuilder() {
        title = new Title(DEFAULT_PROFILE_TITLE);
        category = DEFAULT_CATEGORY;
        descriptors = new HashSet<>();
    }

    /**
     * Initializes the ProfileItemBuilder with the data of {@code profileItemToCopy}.
     */
    public ProfileItemBuilder(ProfileItem profileItemToCopy) {
        title = profileItemToCopy.getTitle();
        category = profileItemToCopy.getCategory();
        descriptors = new HashSet<>(profileItemToCopy.getDescriptors());
    }

    /**
     * Sets the {@code Title} of the {@code ProfileItem} that we are building.
     */
    public ProfileItemBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }

    /**
     * Sets the {@code ProfileItemCategory} of the {@code ProfileItem} that we are building.
     */
    public ProfileItemBuilder withCategory(String category) {
        this.category = ProfileItemCategory.valueOf(category.toUpperCase());
        return this;
    }


    /**
     * Parses the {@code descriptors} into a {@code Set<Descriptor>}
     * and set it to the {@code ProfileItem} that we are building.
     */
    public ProfileItemBuilder withDescriptors(String... descriptors) {
        this.descriptors = getDescriptorSet(descriptors);
        return this;
    }

    public ProfileItem build() {
        return new ProfileItem(title, category, descriptors);
    }

    /**
     * Returns a set of descriptors containing the list of strings given.
     */
    private Set<Descriptor> getDescriptorSet(String... strings) {
        return Arrays.stream(strings)
                .map(Descriptor::new)
                .collect(Collectors.toSet());
    }

}
