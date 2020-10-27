package seedu.internhunter.model.profile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.internhunter.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ProfileItemCategoryTest {
    static final String BLANK_CATEGORY = "";
    static final String VALID_CATEGORY_SKILL = "SKILL";
    static final String VALID_CATEGORY_ACHIEVEMENT = "ACHIEVEMENT";
    static final String VALID_CATEGORY_EXPERIENCE = "EXPERIENCE";
    static final String INVALID_CATEGORY_CASING = "sKiLl";
    static final String INVALID_CATEGORY = "AWARDS";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ProfileItemCategory.valueOf(null));
    }

    @Test
    public void constructor_invalidStatus_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> ProfileItemCategory.valueOf(BLANK_CATEGORY));
        assertThrows(IllegalArgumentException.class, () -> ProfileItemCategory.valueOf(INVALID_CATEGORY));
        assertThrows(IllegalArgumentException.class, () -> ProfileItemCategory.valueOf(INVALID_CATEGORY_CASING));
    }

    @Test
    public void toString_validFormats_success() {
        ProfileItemCategory achievement = ProfileItemCategory.valueOf(VALID_CATEGORY_ACHIEVEMENT);
        assertEquals(VALID_CATEGORY_ACHIEVEMENT, achievement.toString());
        ProfileItemCategory skill = ProfileItemCategory.valueOf(VALID_CATEGORY_SKILL);
        assertEquals(VALID_CATEGORY_SKILL, skill.toString());
        ProfileItemCategory experience = ProfileItemCategory.valueOf(VALID_CATEGORY_EXPERIENCE);
        assertEquals(VALID_CATEGORY_EXPERIENCE, experience.toString());
    }

    @Test
    public void equals_equalityTest_success() {
        ProfileItemCategory profileItemCategory = ProfileItemCategory.valueOf(VALID_CATEGORY_ACHIEVEMENT);
        ProfileItemCategory profileItemCategoryTwo = ProfileItemCategory.valueOf(VALID_CATEGORY_ACHIEVEMENT);
        assertEquals(profileItemCategory, profileItemCategoryTwo);
    }

    @Test
    public void hashCode_equalityTest_success() {
        ProfileItemCategory profileItemCategory = ProfileItemCategory.valueOf(VALID_CATEGORY_ACHIEVEMENT);
        ProfileItemCategory profileItemCategoryTwo = ProfileItemCategory.valueOf(VALID_CATEGORY_ACHIEVEMENT);
        assertEquals(profileItemCategory.hashCode(), profileItemCategoryTwo.hashCode());
    }
}
