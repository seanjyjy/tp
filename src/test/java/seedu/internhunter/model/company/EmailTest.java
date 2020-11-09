package seedu.internhunter.model.company;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.testutil.Assert.assertThrows;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.INVALID_EMAIL_BLANK;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.INVALID_EMAIL_DOMAIN_BEGIN_WITH_HYPHEN;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.INVALID_EMAIL_DOMAIN_BEGIN_WITH_PERIOD;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.INVALID_EMAIL_DOMAIN_END_WITH_HYPHEN;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.INVALID_EMAIL_DOMAIN_END_WITH_PERIOD;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.INVALID_EMAIL_DOMAIN_ONE_CHAR;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.INVALID_EMAIL_DOMAIN_SPACE;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.INVALID_EMAIL_EMPTY;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil
        .INVALID_EMAIL_GOOGLE_LEADING_AND_TRAILING_SPACES;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.INVALID_EMAIL_GOOGLE_LEADING_SPACES;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.INVALID_EMAIL_GOOGLE_TRAILING_SPACES;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.INVALID_EMAIL_LOCAL_PART_SPACE;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil
        .INVALID_EMAIL_LOCAL_PART_SPECIAL_CHAR_ANGLE_BRACKETS;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.INVALID_EMAIL_NO_AT;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_EMAIL_DOMAIN_HYPHEN;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_EMAIL_DOMAIN_NO_PERIOD;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_EMAIL_FACEBOOK;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_EMAIL_GOLDMAN;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_EMAIL_GOOGLE;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_EMAIL_LOCAL_PART_ALL_SPECIAL_CHARS;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil
        .VALID_EMAIL_LOCAL_PART_AND_DOMAIN_SPECIAL_CHARS;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_EMAIL_LOCAL_PART_SPECIAL_CHARS;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_EMAIL_LOCAL_PART_SPECIAL_CHAR_APOSTROPHE;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_EMAIL_SHORTEST;

import org.junit.jupiter.api.Test;

public class EmailTest {

    private static final Email VALID_EMAIL_ONE = new Email(VALID_EMAIL_GOLDMAN);
    private static final Email VALID_EMAIL_TWO = new Email(VALID_EMAIL_GOOGLE);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Email(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                Email.MESSAGE_CONSTRAINTS, () -> new Email(INVALID_EMAIL_EMPTY));
        assertThrows(IllegalArgumentException.class,
                Email.MESSAGE_CONSTRAINTS, () -> new Email(INVALID_EMAIL_BLANK));
        assertThrows(IllegalArgumentException.class,
                Email.MESSAGE_CONSTRAINTS, () -> new Email(INVALID_EMAIL_GOOGLE_LEADING_SPACES));
        assertThrows(IllegalArgumentException.class,
                Email.MESSAGE_CONSTRAINTS, () -> new Email(INVALID_EMAIL_GOOGLE_TRAILING_SPACES));
        assertThrows(IllegalArgumentException.class,
                Email.MESSAGE_CONSTRAINTS, () -> new Email(INVALID_EMAIL_GOOGLE_LEADING_AND_TRAILING_SPACES));
        assertThrows(IllegalArgumentException.class,
                Email.MESSAGE_CONSTRAINTS, () -> new Email(INVALID_EMAIL_NO_AT));
        assertThrows(IllegalArgumentException.class,
                Email.MESSAGE_CONSTRAINTS, () -> new Email(INVALID_EMAIL_LOCAL_PART_SPACE));
        assertThrows(IllegalArgumentException.class,
                Email.MESSAGE_CONSTRAINTS, () -> new Email(INVALID_EMAIL_LOCAL_PART_SPECIAL_CHAR_ANGLE_BRACKETS));
        assertThrows(IllegalArgumentException.class,
                Email.MESSAGE_CONSTRAINTS, () -> new Email(INVALID_EMAIL_DOMAIN_SPACE));
        assertThrows(IllegalArgumentException.class,
                Email.MESSAGE_CONSTRAINTS, () -> new Email(INVALID_EMAIL_DOMAIN_ONE_CHAR));
        assertThrows(IllegalArgumentException.class,
                Email.MESSAGE_CONSTRAINTS, () -> new Email(INVALID_EMAIL_DOMAIN_BEGIN_WITH_PERIOD));
        assertThrows(IllegalArgumentException.class,
                Email.MESSAGE_CONSTRAINTS, () -> new Email(INVALID_EMAIL_DOMAIN_END_WITH_PERIOD));
        assertThrows(IllegalArgumentException.class,
                Email.MESSAGE_CONSTRAINTS, () -> new Email(INVALID_EMAIL_DOMAIN_BEGIN_WITH_HYPHEN));
        assertThrows(IllegalArgumentException.class,
                Email.MESSAGE_CONSTRAINTS, () -> new Email(INVALID_EMAIL_DOMAIN_END_WITH_HYPHEN));
    }

    @Test
    public void isValidEmail_invalidFormats_success() {
        assertFalse(Email.isValidEmail(INVALID_EMAIL_EMPTY));
        assertFalse(Email.isValidEmail(INVALID_EMAIL_BLANK));
        assertFalse(Email.isValidEmail(INVALID_EMAIL_GOOGLE_LEADING_SPACES));
        assertFalse(Email.isValidEmail(INVALID_EMAIL_GOOGLE_TRAILING_SPACES));
        assertFalse(Email.isValidEmail(INVALID_EMAIL_GOOGLE_LEADING_AND_TRAILING_SPACES));
        assertFalse(Email.isValidEmail(INVALID_EMAIL_NO_AT));
        assertFalse(Email.isValidEmail(INVALID_EMAIL_LOCAL_PART_SPACE));
        assertFalse(Email.isValidEmail(INVALID_EMAIL_LOCAL_PART_SPECIAL_CHAR_ANGLE_BRACKETS));
        assertFalse(Email.isValidEmail(INVALID_EMAIL_DOMAIN_SPACE));
        assertFalse(Email.isValidEmail(INVALID_EMAIL_DOMAIN_ONE_CHAR));
        assertFalse(Email.isValidEmail(INVALID_EMAIL_DOMAIN_BEGIN_WITH_PERIOD));
        assertFalse(Email.isValidEmail(INVALID_EMAIL_DOMAIN_END_WITH_PERIOD));
        assertFalse(Email.isValidEmail(INVALID_EMAIL_DOMAIN_BEGIN_WITH_HYPHEN));
        assertFalse(Email.isValidEmail(INVALID_EMAIL_DOMAIN_END_WITH_HYPHEN));
    }

    @Test
    public void isValidEmail_validFormats_success() {
        assertTrue(Email.isValidEmail(VALID_EMAIL_FACEBOOK));
        assertTrue(Email.isValidEmail(VALID_EMAIL_GOLDMAN));
        assertTrue(Email.isValidEmail(VALID_EMAIL_GOOGLE));
        assertTrue(Email.isValidEmail(VALID_EMAIL_LOCAL_PART_SPECIAL_CHAR_APOSTROPHE));
        assertTrue(Email.isValidEmail(VALID_EMAIL_LOCAL_PART_SPECIAL_CHARS));
        assertTrue(Email.isValidEmail(VALID_EMAIL_LOCAL_PART_ALL_SPECIAL_CHARS));
        assertTrue(Email.isValidEmail(VALID_EMAIL_DOMAIN_NO_PERIOD));
        assertTrue(Email.isValidEmail(VALID_EMAIL_DOMAIN_HYPHEN));
        assertTrue(Email.isValidEmail(VALID_EMAIL_SHORTEST));
        assertTrue(Email.isValidEmail(VALID_EMAIL_LOCAL_PART_AND_DOMAIN_SPECIAL_CHARS));
    }

    @Test
    public void getValue_equalityTest_success() {
        assertEquals(VALID_EMAIL_GOLDMAN, VALID_EMAIL_ONE.getValue());
    }

    @Test
    public void toString_validFormat_success() {
        assertEquals(VALID_EMAIL_GOLDMAN, VALID_EMAIL_ONE.toString());
    }

    @Test
    public void equals_equalityTest_success() {
        // same object -> return true
        assertTrue(VALID_EMAIL_ONE.equals(VALID_EMAIL_ONE));
        Email emailCopy = new Email(VALID_EMAIL_GOLDMAN);
        // same value -> return true
        assertTrue(VALID_EMAIL_ONE.equals(emailCopy));
    }

    @Test
    public void equals_nonEqualityTest_success() {
        // different value -> return false
        assertFalse(VALID_EMAIL_ONE.equals(VALID_EMAIL_TWO));
        // null -> return true
        assertFalse(VALID_EMAIL_ONE.equals(null));
        // different types -> return false
        assertFalse(VALID_EMAIL_ONE.equals(0.5f));
    }

    @Test
    public void hashCode_equalityTest_success() {
        assertEquals(VALID_EMAIL_ONE.hashCode(), VALID_EMAIL_ONE.hashCode());
    }

}
