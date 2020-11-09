package seedu.internhunter.model.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.testutil.Assert.assertThrows;
import static seedu.internhunter.testutil.application.ApplicationItemFieldsUtil.BLANK_STATUS_DATE;
import static seedu.internhunter.testutil.application.ApplicationItemFieldsUtil.EXPECTED_DATE_JUNE_2021;
import static seedu.internhunter.testutil.application.ApplicationItemFieldsUtil.INVALID_STATUS_DATE;
import static seedu.internhunter.testutil.application.ApplicationItemFieldsUtil.STATUS_DATE_JUNE_2021;
import static seedu.internhunter.testutil.application.ApplicationItemFieldsUtil.STATUS_DATE_JUNE_2022;
import static seedu.internhunter.testutil.application.ApplicationItemFieldsUtil.STATUS_DATE_MAY_2021;
import static seedu.internhunter.testutil.application.ApplicationItemFieldsUtil.STATUS_DATE_WITH_TIME;
import static seedu.internhunter.testutil.application.ApplicationItemFieldsUtil.VALID_STATUS_DATE_JUNE_2021;
import static seedu.internhunter.testutil.application.ApplicationItemFieldsUtil.VALID_STATUS_DATE_JUNE_2022;
import static seedu.internhunter.testutil.application.ApplicationItemFieldsUtil.VALID_STATUS_DATE_MAY_2021;

import org.junit.jupiter.api.Test;

import seedu.internhunter.model.util.DateUtil;

public class StatusDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StatusDate(null));
    }

    @Test
    public void constructor_invalidStatusDate_throwsAssertionError() {
        assertThrows(AssertionError.class,
                DateUtil.ERROR_MESSAGE, () -> new StatusDate(DateUtil.convertToDateTime(BLANK_STATUS_DATE)));
        assertThrows(AssertionError.class,
                DateUtil.ERROR_MESSAGE, () -> new StatusDate(DateUtil.convertToDateTime(INVALID_STATUS_DATE)));
    }

    @Test
    public void isValidDate_validFormat_success() {
        // Date format
        assertTrue(StatusDate.isValidDate(STATUS_DATE_MAY_2021));
        assertTrue(StatusDate.isValidDate(STATUS_DATE_JUNE_2021));
        assertTrue(StatusDate.isValidDate(STATUS_DATE_JUNE_2022));
        // DateTime format
        assertTrue(StatusDate.isValidDate(STATUS_DATE_WITH_TIME));
    }

    @Test
    public void isValidDate_invalidFormat_success() {
        assertFalse(StatusDate.isValidDate(BLANK_STATUS_DATE));
        assertFalse(StatusDate.isValidDate(INVALID_STATUS_DATE));
    }

    @Test
    public void toString_validFormat_success() {
        assertEquals(EXPECTED_DATE_JUNE_2021, VALID_STATUS_DATE_JUNE_2021.toString());
    }

    @Test
    public void equals_equalityTest_success() {
        // same object -> return true
        assertTrue(VALID_STATUS_DATE_JUNE_2021.equals(VALID_STATUS_DATE_JUNE_2021));
        StatusDate statusDateCopy = new StatusDate(DateUtil.convertToDateTime(STATUS_DATE_JUNE_2021));
        // same value -> return true
        assertTrue(VALID_STATUS_DATE_JUNE_2021.equals(statusDateCopy));
    }

    @Test
    public void equals_nonEqualityTest_success() {
        // Different year
        assertFalse(VALID_STATUS_DATE_JUNE_2021.equals(VALID_STATUS_DATE_JUNE_2022));
        // Different day in same year
        assertFalse(VALID_STATUS_DATE_JUNE_2021.equals(VALID_STATUS_DATE_MAY_2021));
        // null -> return false
        assertFalse(VALID_STATUS_DATE_JUNE_2021.equals(null));
        // different types -> return false
        assertFalse(VALID_STATUS_DATE_JUNE_2021.equals(0.5f));
    }

    @Test
    public void hashCode_equalityTest_success() {
        assertEquals(VALID_STATUS_DATE_JUNE_2021.hashCode(), VALID_STATUS_DATE_JUNE_2021.hashCode());
    }

}
