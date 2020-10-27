package seedu.internhunter.model.internship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.testutil.Assert.assertThrows;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.INVALID_PERIOD_EMPTY;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.INVALID_PERIOD_SPACES;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_PERIOD_SUMMER;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_PERIOD_THREE_MONTHS;

import org.junit.jupiter.api.Test;

public class PeriodTest {

    private static final Period VALID_PERIOD_ONE = new Period(VALID_PERIOD_SUMMER);
    private static final Period VALID_PERIOD_TWO = new Period(VALID_PERIOD_THREE_MONTHS);


    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Period(null));
    }

    @Test
    public void constructor_invalidPeriod_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                Period.MESSAGE_CONSTRAINTS, () -> new Period(INVALID_PERIOD_EMPTY));
        assertThrows(IllegalArgumentException.class,
                Period.MESSAGE_CONSTRAINTS, () -> new Period(INVALID_PERIOD_SPACES));
    }

    @Test
    public void isValidPeriod_invalidFormats_success() {
        assertFalse(Period.isValidPeriod(INVALID_PERIOD_EMPTY));
        assertFalse(Period.isValidPeriod(INVALID_PERIOD_SPACES));
    }

    @Test
    public void isValidPeriod_validFormats_success() {
        assertTrue(Period.isValidPeriod(VALID_PERIOD_SUMMER));
        assertTrue(Period.isValidPeriod(VALID_PERIOD_THREE_MONTHS));
    }

    @Test
    public void getValue_equalityTest_success() {
        assertEquals(VALID_PERIOD_SUMMER, VALID_PERIOD_ONE.getValue());
    }

    @Test
    public void equals_equalityTest_success() {
        assertEquals(VALID_PERIOD_ONE, VALID_PERIOD_ONE);
        Period periodCopy = new Period(VALID_PERIOD_SUMMER);
        assertEquals(VALID_PERIOD_ONE, periodCopy);
    }

    @Test
    public void equals_nonEqualityTest_success() {
        assertNotEquals(VALID_PERIOD_ONE, VALID_PERIOD_TWO);
    }

    @Test
    public void hashCode_equalityTest_success() {
        assertEquals(VALID_PERIOD_ONE.hashCode(), VALID_PERIOD_ONE.hashCode());
    }

}
