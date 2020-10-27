package seedu.internhunter.model.internship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.model.internship.Wage.WAGE_SYMBOL;
import static seedu.internhunter.testutil.Assert.assertThrows;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.INVALID_WAGE_DECIMAL;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.INVALID_WAGE_NEGATIVE;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.INVALID_WAGE_ZERO;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_WAGE_2000;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_WAGE_3000;

import org.junit.jupiter.api.Test;

public class WageTest {

    private static final Wage VALID_WAGE_ONE = new Wage(VALID_WAGE_2000);
    private static final Wage VALID_WAGE_TWO = new Wage(VALID_WAGE_3000);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Wage(null));
    }

    @Test
    public void constructor_invalidWage_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, Wage.MESSAGE_CONSTRAINTS, () -> new Wage(INVALID_WAGE_ZERO));
        assertThrows(IllegalArgumentException.class, Wage.MESSAGE_CONSTRAINTS, () -> new Wage(INVALID_WAGE_NEGATIVE));
        assertThrows(IllegalArgumentException.class, Wage.MESSAGE_CONSTRAINTS, () -> new Wage(INVALID_WAGE_DECIMAL));
    }

    @Test
    public void isValidWage_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Wage.isValidWage(null));
    }

    @Test
    public void isValidWage_validInput_success() {
        assertTrue(Wage.isValidWage("2"));
        assertTrue(Wage.isValidWage("12345"));
        assertTrue(Wage.isValidWage("243"));
    }

    @Test
    public void isValidWage_invalidDigits_success() {
        assertFalse(Wage.isValidWage("0")); // Zero
        assertFalse(Wage.isValidWage("-4")); // Negative number
        assertFalse(Wage.isValidWage("400.00")); // Decimals
        assertFalse(Wage.isValidWage("0.400")); // Decimals
    }

    @Test
    public void isValidWage_invalidInput_success() {
        assertFalse(Wage.isValidWage("")); // empty string
        assertFalse(Wage.isValidWage("    ")); // spaces only
        assertFalse(Wage.isValidWage("   213")); // space then number
        assertFalse(Wage.isValidWage("1221abc2131")); // alphabets within digits
        assertFalse(Wage.isValidWage("90 41")); // spaces within digits
    }

    @Test
    public void getValue_equalityTest_success() {
        assertEquals(VALID_WAGE_2000, VALID_WAGE_ONE.getValue());
    }

    @Test
    public void toString_validFormat_success() {
        assertEquals(WAGE_SYMBOL + VALID_WAGE_2000, VALID_WAGE_ONE.toString());
    }

    @Test
    public void equals_equalityTest_success() {
        assertEquals(VALID_WAGE_ONE, VALID_WAGE_ONE);
        Wage wageCopy = new Wage(VALID_WAGE_2000);
        assertEquals(VALID_WAGE_ONE, wageCopy);
    }

    @Test
    public void equals_nonEqualityTest_success() {
        assertNotEquals(VALID_WAGE_ONE, VALID_WAGE_TWO);
    }

    @Test
    public void hashCode_equalityTest_success() {
        assertEquals(VALID_WAGE_ONE.hashCode(), VALID_WAGE_ONE.hashCode());
    }

}
