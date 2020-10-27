package seedu.internhunter.model.wrapper;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NonEmptyStringTest {

    @Test
    public void isValidNonEmptyString_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> NonEmptyString.isValidNonEmptyString(null));
    }

    @Test
    public void isValidNonEmptyString_validInput_success() {
        assertTrue(NonEmptyString.isValidNonEmptyString("Engineer")); // alphabets only
        assertTrue(NonEmptyString.isValidNonEmptyString("12345")); // numbers only
        assertTrue(NonEmptyString.isValidNonEmptyString("Machine Learning")); // with capital letters
        assertTrue(NonEmptyString.isValidNonEmptyString("   Senior       Software       Engineer,"
                + "Engineering, Data and   Machine L  earning")); // Random spaces

        assertTrue(NonEmptyString.isValidNonEmptyString("---")); // only non-alphanumeric characters
        assertTrue(NonEmptyString.isValidNonEmptyString("-/")); // only non-alphanumeric characters
        assertTrue(NonEmptyString.isValidNonEmptyString("20-2-20 to 30-2-20")); // contains non-alphanumeric characters
    }

    @Test
    public void isValidNonEmptyString_invalidInput_success() {
        assertFalse(NonEmptyString.isValidNonEmptyString("")); // empty string
        assertFalse(NonEmptyString.isValidNonEmptyString("    ")); // spaces only
    }

}
