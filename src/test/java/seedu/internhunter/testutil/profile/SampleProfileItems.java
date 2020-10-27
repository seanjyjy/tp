package seedu.internhunter.testutil.profile;

import static seedu.internhunter.logic.commands.util.profile.ProfileCommandTestUtil.VALID_CATEGORY_ACHIEVEMENT;
import static seedu.internhunter.logic.commands.util.profile.ProfileCommandTestUtil.VALID_CATEGORY_EXPERIENCE;
import static seedu.internhunter.logic.commands.util.profile.ProfileCommandTestUtil.VALID_DESCRIPTOR_LEARN;
import static seedu.internhunter.logic.commands.util.profile.ProfileCommandTestUtil.VALID_TITLE_COMPETITION;
import static seedu.internhunter.logic.commands.util.profile.ProfileCommandTestUtil.VALID_TITLE_INTERNSHIP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.internhunter.model.item.ItemList;
import seedu.internhunter.model.profile.ProfileItem;

/**
 * A utility class containing a list of {@code ProfileItem} objects to be used in tests.
 */
public abstract class SampleProfileItems {

    public static final ProfileItem HTML_SKILL = new ProfileItemBuilder()
            .withTitle("HTML")
            .withCategory("skill")
            .withDescriptors("Learn to create divs.", "learn to create tables.")
            .build();

    public static final ProfileItem HACKATHON_ACHIEVEMENT = new ProfileItemBuilder()
            .withTitle("Won second place in Code Jam")
            .withCategory("achievement")
            .withDescriptors("Solved the Board Meeting Question.", "Solved the Jungle Struggle question.")
            .build();

    public static final ProfileItem GOVTECH_EXPERIENCE = new ProfileItemBuilder()
            .withTitle("Internship with Govtech")
            .withCategory("experience")
            .withDescriptors("Implemented DevOps solution to speed up automated testing by 50%")
            .withDescriptors("Developed dashboard to keep track of key performance metrics")
            .withDescriptors("Build internal tooling for syncing data with remote servers")
            .build();

    public static final ProfileItem GRAPHQL_SKILL = new ProfileItemBuilder()
            .withTitle("GraphQL")
            .withCategory("skill")
            .build();

    public static final ProfileItem NUS_MODS_EXPERIENCE = new ProfileItemBuilder()
            .withTitle("Contribute to NUSMODS")
            .withCategory("experience")
            .withDescriptors("Fix UI bugs in Modules View")
            .build();

    public static final ProfileItem MS_HACKATHON_ACHIEVEMENT = new ProfileItemBuilder()
            .withTitle("Participation in MS OpenHack")
            .withCategory("achievement")
            .withDescriptors("Solve the Agriculture challenge with satellite imagery")
            .build();

    // Manually added - ProfileItem's details found in {@code ProfileCommandTestUtil}
    public static final ProfileItem BYTEDANCE_INTERN = new ProfileItemBuilder()
            .withTitle(VALID_TITLE_INTERNSHIP)
            .withCategory(VALID_CATEGORY_EXPERIENCE).build();

    public static final ProfileItem ORBITAL_ACHIEVEMENT = new ProfileItemBuilder()
            .withTitle(VALID_TITLE_COMPETITION)
            .withCategory(VALID_CATEGORY_ACHIEVEMENT)
            .withDescriptors(VALID_DESCRIPTOR_LEARN).build();

    /**
     * Returns an {@code ProfileItem} with all the sample profile items.
     */
    public static ItemList<ProfileItem> getSampleProfileItemList() {
        ItemList<ProfileItem> profileItemList = new ItemList<>();
        for (ProfileItem profileItem : getProfileItems()) {
            profileItemList.addItem(profileItem);
        }
        return profileItemList;
    }

    private static List<ProfileItem> getProfileItems() {
        return new ArrayList<>(Arrays.asList(HTML_SKILL, GOVTECH_EXPERIENCE, NUS_MODS_EXPERIENCE,
                MS_HACKATHON_ACHIEVEMENT));
    }
}
