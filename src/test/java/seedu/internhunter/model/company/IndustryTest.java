package seedu.internhunter.model.company;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.testutil.Assert.assertThrows;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil
        .INVALID_INDUSTRY_BANKING_WITH_LEADING_AND_TRAILING_SPACES;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.INVALID_INDUSTRY_BANKING_WITH_LEADING_SPACES;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.INVALID_INDUSTRY_BLANK;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.INVALID_INDUSTRY_EMPTY;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.INVALID_INDUSTRY_SPECIAL_CHAR_AMPERSAND;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.INVALID_INDUSTRY_SPECIAL_CHAR_PERIOD;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_INDUSTRY_ARTIFICIAL_INTELLIGENCE;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_INDUSTRY_BANKING;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_INDUSTRY_BANKING_WITH_TRAILING_SPACES;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_INDUSTRY_CLOUD_COMPUTING;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_INDUSTRY_SOCIAL_MEDIA;

import org.junit.jupiter.api.Test;

public class IndustryTest {

    private static final Industry VALID_INDUSTRY_ONE = new Industry(VALID_INDUSTRY_SOCIAL_MEDIA);
    private static final Industry VALID_INDUSTRY_TWO = new Industry(VALID_INDUSTRY_ARTIFICIAL_INTELLIGENCE);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Industry(null));
    }

    @Test
    public void constructor_invalidIndustry_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                Industry.MESSAGE_CONSTRAINTS, () -> new Industry(INVALID_INDUSTRY_EMPTY));
        assertThrows(IllegalArgumentException.class,
                Industry.MESSAGE_CONSTRAINTS, () -> new Industry(INVALID_INDUSTRY_BLANK));
        assertThrows(IllegalArgumentException.class,
                Industry.MESSAGE_CONSTRAINTS, () -> new Industry(INVALID_INDUSTRY_BANKING_WITH_LEADING_SPACES));
        assertThrows(IllegalArgumentException.class,
                Industry.MESSAGE_CONSTRAINTS, () -> new Industry(
                        INVALID_INDUSTRY_BANKING_WITH_LEADING_AND_TRAILING_SPACES));
        assertThrows(IllegalArgumentException.class,
                Industry.MESSAGE_CONSTRAINTS, () -> new Industry(INVALID_INDUSTRY_SPECIAL_CHAR_PERIOD));
        assertThrows(IllegalArgumentException.class,
                Industry.MESSAGE_CONSTRAINTS, () -> new Industry(INVALID_INDUSTRY_SPECIAL_CHAR_AMPERSAND));
    }

    @Test
    public void isValidIndustry_invalidFormats_success() {
        assertFalse(Industry.isValidIndustryName(INVALID_INDUSTRY_EMPTY));
        assertFalse(Industry.isValidIndustryName(INVALID_INDUSTRY_BLANK));
        assertFalse(Industry.isValidIndustryName(INVALID_INDUSTRY_BANKING_WITH_LEADING_SPACES));
        assertFalse(Industry.isValidIndustryName(INVALID_INDUSTRY_BANKING_WITH_LEADING_AND_TRAILING_SPACES));
        assertFalse(Industry.isValidIndustryName(INVALID_INDUSTRY_SPECIAL_CHAR_PERIOD));
        assertFalse(Industry.isValidIndustryName(INVALID_INDUSTRY_SPECIAL_CHAR_AMPERSAND));
    }

    @Test
    public void isValidIndustry_validFormats_success() {
        assertTrue(Industry.isValidIndustryName(VALID_INDUSTRY_SOCIAL_MEDIA));
        assertTrue(Industry.isValidIndustryName(VALID_INDUSTRY_ARTIFICIAL_INTELLIGENCE));
        assertTrue(Industry.isValidIndustryName(VALID_INDUSTRY_CLOUD_COMPUTING));
        assertTrue(Industry.isValidIndustryName(VALID_INDUSTRY_BANKING));
        assertTrue(Industry.isValidIndustryName(VALID_INDUSTRY_BANKING_WITH_TRAILING_SPACES));
    }

    @Test
    public void getValue_equalityTest_success() {
        assertEquals(VALID_INDUSTRY_SOCIAL_MEDIA, VALID_INDUSTRY_ONE.getValue());
    }

    @Test
    public void toString_validFormat_success() {
        assertEquals(VALID_INDUSTRY_SOCIAL_MEDIA, VALID_INDUSTRY_ONE.toString());
    }

    @Test
    public void equals_equalityTest_success() {
        // same object -> return true
        assertTrue(VALID_INDUSTRY_ONE.equals(VALID_INDUSTRY_ONE));
        Industry industryCopy = new Industry(VALID_INDUSTRY_SOCIAL_MEDIA);
        // same value -> return true
        assertTrue(VALID_INDUSTRY_ONE.equals(industryCopy));
    }

    @Test
    public void equals_nonEqualityTest_success() {
        // different values -> return false
        assertFalse(VALID_INDUSTRY_ONE.equals(VALID_INDUSTRY_TWO));
        // null -> return true
        assertFalse(VALID_INDUSTRY_ONE.equals(null));
        // different types -> return false
        assertFalse(VALID_INDUSTRY_ONE.equals(0.5f));
    }

    @Test
    public void hashCode_equalityTest_success() {
        assertEquals(VALID_INDUSTRY_ONE.hashCode(), VALID_INDUSTRY_ONE.hashCode());
    }

}
