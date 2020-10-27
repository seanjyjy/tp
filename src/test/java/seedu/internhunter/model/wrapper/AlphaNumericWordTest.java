package seedu.internhunter.model.wrapper;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AlphaNumericWordTest {

    @Test
    public void isValidAlphaNumericWord_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> AlphaNumericWord.isValidAlphaNumericWord(null));
    }

    @Test
    public void isValidAlphaNumericWord_validInput_success() {
        assertTrue(AlphaNumericWord.isValidAlphaNumericWord("Engineer")); // alphabets only
        assertTrue(AlphaNumericWord.isValidAlphaNumericWord("12345")); // numbers only
        assertTrue(AlphaNumericWord.isValidAlphaNumericWord("Software 2")); // alphanumeric characters
        assertTrue(AlphaNumericWord.isValidAlphaNumericWord("Machine Learning")); // with capital letters
        assertTrue(AlphaNumericWord.isValidAlphaNumericWord("Junior Front End Developer")); // long words
    }

    @Test
    public void isValidAlphaNumericWord_invalidInput_success() {
        assertFalse(AlphaNumericWord.isValidAlphaNumericWord("")); // empty string
        assertFalse(AlphaNumericWord.isValidAlphaNumericWord("    ")); // spaces only
        assertFalse(AlphaNumericWord.isValidAlphaNumericWord("^")); // only non-alphanumeric characters
        assertFalse(AlphaNumericWord.isValidAlphaNumericWord("-")); // only non-alphanumeric characters
        assertFalse(AlphaNumericWord.isValidAlphaNumericWord("Google*")); // contains non-alphanumeric characters
    }

}
