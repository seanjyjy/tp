package seedu.internhunter.model.company;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.testutil.Assert.assertThrows;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.INVALID_PHONE_ALPHABETS;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.INVALID_PHONE_ALPHABETS_ONLY;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.INVALID_PHONE_BLANK;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.INVALID_PHONE_EMPTY;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.INVALID_PHONE_GOOGLE_LEADING_AND_TRAILING_SPACES;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.INVALID_PHONE_GOOGLE_LEADING_SPACES;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.INVALID_PHONE_GOOGLE_TRAILING_SPACES;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.INVALID_PHONE_SPECIAL_CHAR_DASH;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.INVALID_PHONE_SPECIAL_CHAR_PARENTHESES;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.INVALID_PHONE_SPECIAL_CHAR_PLUS;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.INVALID_PHONE_TWO_NUMBERS;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.INVALID_PHONE_WHITESPACE;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_PHONE_EIGHT_NUMBERS;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_PHONE_FACEBOOK;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_PHONE_GOLDMAN;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_PHONE_GOOGLE;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_PHONE_TEN_NUMBERS;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_PHONE_THREE_NUMBERS;

import org.junit.jupiter.api.Test;

public class PhoneTest {

    private static final Phone VALID_PHONE_ONE = new Phone(VALID_PHONE_FACEBOOK);
    private static final Phone VALID_PHONE_TWO = new Phone(VALID_PHONE_GOOGLE);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Phone(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                Phone.MESSAGE_CONSTRAINTS, () -> new Phone(INVALID_PHONE_EMPTY));
        assertThrows(IllegalArgumentException.class,
                Phone.MESSAGE_CONSTRAINTS, () -> new Phone(INVALID_PHONE_BLANK));
        assertThrows(IllegalArgumentException.class,
                Phone.MESSAGE_CONSTRAINTS, () -> new Phone(INVALID_PHONE_TWO_NUMBERS));
        assertThrows(IllegalArgumentException.class,
                Phone.MESSAGE_CONSTRAINTS, () -> new Phone(INVALID_PHONE_GOOGLE_LEADING_SPACES));
        assertThrows(IllegalArgumentException.class,
                Phone.MESSAGE_CONSTRAINTS, () -> new Phone(INVALID_PHONE_GOOGLE_TRAILING_SPACES));
        assertThrows(IllegalArgumentException.class,
                Phone.MESSAGE_CONSTRAINTS, () -> new Phone(INVALID_PHONE_GOOGLE_LEADING_AND_TRAILING_SPACES));
        assertThrows(IllegalArgumentException.class,
                Phone.MESSAGE_CONSTRAINTS, () -> new Phone(INVALID_PHONE_WHITESPACE));
        assertThrows(IllegalArgumentException.class,
                Phone.MESSAGE_CONSTRAINTS, () -> new Phone(INVALID_PHONE_SPECIAL_CHAR_PLUS));
        assertThrows(IllegalArgumentException.class,
                Phone.MESSAGE_CONSTRAINTS, () -> new Phone(INVALID_PHONE_SPECIAL_CHAR_PARENTHESES));
        assertThrows(IllegalArgumentException.class,
                Phone.MESSAGE_CONSTRAINTS, () -> new Phone(INVALID_PHONE_SPECIAL_CHAR_DASH));
        assertThrows(IllegalArgumentException.class,
                Phone.MESSAGE_CONSTRAINTS, () -> new Phone(INVALID_PHONE_ALPHABETS));
        assertThrows(IllegalArgumentException.class,
                Phone.MESSAGE_CONSTRAINTS, () -> new Phone(INVALID_PHONE_ALPHABETS_ONLY));
    }

    @Test
    public void isValidPhone_invalidFormats_success() {
        assertFalse(Phone.isValidPhone(INVALID_PHONE_EMPTY));
        assertFalse(Phone.isValidPhone(INVALID_PHONE_BLANK));
        assertFalse(Phone.isValidPhone(INVALID_PHONE_TWO_NUMBERS));
        assertFalse(Phone.isValidPhone(INVALID_PHONE_GOOGLE_LEADING_SPACES));
        assertFalse(Phone.isValidPhone(INVALID_PHONE_GOOGLE_TRAILING_SPACES));
        assertFalse(Phone.isValidPhone(INVALID_PHONE_GOOGLE_LEADING_AND_TRAILING_SPACES));
        assertFalse(Phone.isValidPhone(INVALID_PHONE_WHITESPACE));
        assertFalse(Phone.isValidPhone(INVALID_PHONE_SPECIAL_CHAR_PLUS));
        assertFalse(Phone.isValidPhone(INVALID_PHONE_SPECIAL_CHAR_PARENTHESES));
        assertFalse(Phone.isValidPhone(INVALID_PHONE_SPECIAL_CHAR_DASH));
        assertFalse(Phone.isValidPhone(INVALID_PHONE_ALPHABETS));
        assertFalse(Phone.isValidPhone(INVALID_PHONE_ALPHABETS_ONLY));
    }

    @Test
    public void isValidPhone_validFormats_success() {
        assertTrue(Phone.isValidPhone(VALID_PHONE_THREE_NUMBERS));
        assertTrue(Phone.isValidPhone(VALID_PHONE_EIGHT_NUMBERS));
        assertTrue(Phone.isValidPhone(VALID_PHONE_TEN_NUMBERS));
        assertTrue(Phone.isValidPhone(VALID_PHONE_FACEBOOK));
        assertTrue(Phone.isValidPhone(VALID_PHONE_GOLDMAN));
        assertTrue(Phone.isValidPhone(VALID_PHONE_GOOGLE));
    }

    @Test
    public void getValue_equalityTest_success() {
        assertEquals(VALID_PHONE_FACEBOOK, VALID_PHONE_ONE.getValue());
    }

    @Test
    public void toString_validFormat_success() {
        assertEquals(VALID_PHONE_FACEBOOK, VALID_PHONE_ONE.toString());
    }

    @Test
    public void equals_equalityTest_success() {
        // same object -> return true
        assertTrue(VALID_PHONE_ONE.equals(VALID_PHONE_ONE));
        Phone phoneCopy = new Phone(VALID_PHONE_FACEBOOK);
        // same value -> return true
        assertTrue(VALID_PHONE_ONE.equals(phoneCopy));
    }

    @Test
    public void equals_nonEqualityTest_success() {
        // different value -> return false
        assertFalse(VALID_PHONE_ONE.equals(VALID_PHONE_TWO));
        // null -> return false
        assertFalse(VALID_PHONE_ONE.equals(null));
        // different types -> return false
        assertFalse(VALID_PHONE_ONE.equals(0.5f));
    }

    @Test
    public void hashCode_equalityTest_success() {
        assertEquals(VALID_PHONE_ONE.hashCode(), VALID_PHONE_ONE.hashCode());
    }

}
