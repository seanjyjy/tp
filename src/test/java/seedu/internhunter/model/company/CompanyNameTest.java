package seedu.internhunter.model.company;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.testutil.Assert.assertThrows;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.INVALID_COMPANY_NAME_BLANK;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.INVALID_COMPANY_NAME_EMPTY;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil
        .INVALID_COMPANY_NAME_GOOGLE_WITH_LEADING_AND_TRAILING_SPACES;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil
        .INVALID_COMPANY_NAME_GOOGLE_WITH_LEADING_SPACES;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.INVALID_COMPANY_NAME_SPECIAL_CHAR_AMPERSAND;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.INVALID_COMPANY_NAME_SPECIAL_CHAR_PERIOD;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_COMPANY_NAME_FACEBOOK;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_COMPANY_NAME_GOLDMAN;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_COMPANY_NAME_GOOGLE;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_COMPANY_NAME_GOOGLE_WITH_TRAILING_SPACES;

import org.junit.jupiter.api.Test;

public class CompanyNameTest {

    private static final CompanyName VALID_COMPANY_NAME_ONE = new CompanyName(VALID_COMPANY_NAME_FACEBOOK);
    private static final CompanyName VALID_COMPANY_NAME_TWO = new CompanyName(VALID_COMPANY_NAME_GOLDMAN);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CompanyName(null));
    }

    @Test
    public void constructor_invalidCompanyName_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                CompanyName.MESSAGE_CONSTRAINTS, () -> new CompanyName(INVALID_COMPANY_NAME_EMPTY));
        assertThrows(IllegalArgumentException.class,
                CompanyName.MESSAGE_CONSTRAINTS, () -> new CompanyName(INVALID_COMPANY_NAME_BLANK));
        assertThrows(IllegalArgumentException.class,
                CompanyName.MESSAGE_CONSTRAINTS, () -> new CompanyName(
                        INVALID_COMPANY_NAME_GOOGLE_WITH_LEADING_SPACES));
        assertThrows(IllegalArgumentException.class,
                CompanyName.MESSAGE_CONSTRAINTS, () -> new CompanyName(
                        INVALID_COMPANY_NAME_GOOGLE_WITH_LEADING_AND_TRAILING_SPACES));
        assertThrows(IllegalArgumentException.class,
                CompanyName.MESSAGE_CONSTRAINTS, () -> new CompanyName(INVALID_COMPANY_NAME_SPECIAL_CHAR_PERIOD));
        assertThrows(IllegalArgumentException.class,
                CompanyName.MESSAGE_CONSTRAINTS, () -> new CompanyName(INVALID_COMPANY_NAME_SPECIAL_CHAR_AMPERSAND));
    }

    @Test
    public void isValidCompanyName_invalidFormats_success() {
        assertFalse(CompanyName.isValidCompanyName(INVALID_COMPANY_NAME_EMPTY));
        assertFalse(CompanyName.isValidCompanyName(INVALID_COMPANY_NAME_BLANK));
        assertFalse(CompanyName.isValidCompanyName(INVALID_COMPANY_NAME_GOOGLE_WITH_LEADING_SPACES));
        assertFalse(CompanyName.isValidCompanyName(INVALID_COMPANY_NAME_GOOGLE_WITH_LEADING_AND_TRAILING_SPACES));
        assertFalse(CompanyName.isValidCompanyName(INVALID_COMPANY_NAME_SPECIAL_CHAR_PERIOD));
        assertFalse(CompanyName.isValidCompanyName(INVALID_COMPANY_NAME_SPECIAL_CHAR_AMPERSAND));
    }

    @Test
    public void isValidCompanyName_validFormats_success() {
        assertTrue(CompanyName.isValidCompanyName(VALID_COMPANY_NAME_FACEBOOK));
        assertTrue(CompanyName.isValidCompanyName(VALID_COMPANY_NAME_GOLDMAN));
        assertTrue(CompanyName.isValidCompanyName(VALID_COMPANY_NAME_GOOGLE));
        assertTrue(CompanyName.isValidCompanyName(VALID_COMPANY_NAME_GOOGLE_WITH_TRAILING_SPACES));
    }

    @Test
    public void getValue_equalityTest_success() {
        assertEquals(VALID_COMPANY_NAME_FACEBOOK, VALID_COMPANY_NAME_ONE.getValue());
    }

    @Test
    public void toString_validFormat_success() {
        assertEquals(VALID_COMPANY_NAME_FACEBOOK, VALID_COMPANY_NAME_ONE.toString());
    }

    @Test
    public void equals_equalityTest_success() {
        // same object -> return true
        assertTrue(VALID_COMPANY_NAME_ONE.equals(VALID_COMPANY_NAME_ONE));
        CompanyName companyNameCopy = new CompanyName(VALID_COMPANY_NAME_FACEBOOK);
        // same value -> return true
        assertTrue(VALID_COMPANY_NAME_ONE.equals(companyNameCopy));
    }

    @Test
    public void equals_nonEqualityTest_success() {
        // different values -> return false
        assertFalse(VALID_COMPANY_NAME_ONE.equals(VALID_COMPANY_NAME_TWO));
        // null -> return false
        assertFalse(VALID_COMPANY_NAME_ONE.equals(null));
        // different types -> return false
        assertFalse(VALID_COMPANY_NAME_ONE.equals(0.5f));
    }

    @Test
    public void hashCode_equalityTest_success() {
        assertEquals(VALID_COMPANY_NAME_ONE.hashCode(), VALID_COMPANY_NAME_ONE.hashCode());
    }

}
