package seedu.internhunter.model.profile;

/**
 * UserProfileCategory class contains the valid category for user profile.
 */
public enum ProfileItemCategory {
    EXPERIENCE,
    ACHIEVEMENT,
    SKILL;


    public static final String MESSAGE_CONSTRAINTS =
            "ProfileItem category c/ can only be experience, achievement or skill";

    /**
     * Returns true if the profile item category is valid.
     *
     * @param profileItemCategory Input category as String.
     * @return True if status is valid, false otherwise.
     */
    public static boolean isValidProfileItemCategory(String profileItemCategory) {
        profileItemCategory = profileItemCategory.toUpperCase();
        return profileItemCategory.equals(EXPERIENCE.toString())
                || profileItemCategory.equals(ACHIEVEMENT.toString())
                || profileItemCategory.equals(SKILL.toString());
    }
}
