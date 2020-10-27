package seedu.internhunter.model.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.model.util.StatusUtil.ACCEPTED_KEYWORD;
import static seedu.internhunter.model.util.StatusUtil.APPLIED_KEYWORD;
import static seedu.internhunter.model.util.StatusUtil.INTERVIEW_KEYWORD;
import static seedu.internhunter.model.util.StatusUtil.OFFERED_KEYWORD;
import static seedu.internhunter.model.util.StatusUtil.REJECTED_KEYWORD;
import static seedu.internhunter.model.util.StatusUtil.WAITING_KEYWORD;
import static seedu.internhunter.testutil.Assert.assertThrows;
import static seedu.internhunter.testutil.application.ApplicationItemFieldsUtil.INVALID_STATUS;
import static seedu.internhunter.testutil.application.ApplicationItemFieldsUtil.STATUS_BLANK;
import static seedu.internhunter.testutil.application.ApplicationItemFieldsUtil.VALID_STATUS_APPLIED_MIX_CASE;
import static seedu.internhunter.testutil.application.ApplicationItemFieldsUtil.VALID_STATUS_REJECTED_MIX_CASE;

import org.junit.jupiter.api.Test;

public class StatusTest {

    private static final Status VALID_STATUS_ONE = Status.valueOf(APPLIED_KEYWORD.toUpperCase());
    private static final Status VALID_STATUS_TWO = Status.valueOf(REJECTED_KEYWORD.toUpperCase());

    @Test
    public void valueOf_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Status.valueOf(null));
    }

    @Test
    public void valueOf_invalidStatus_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Status.valueOf(STATUS_BLANK));
        assertThrows(IllegalArgumentException.class, () -> Status.valueOf(INVALID_STATUS));
    }

    @Test
    public void isValidStatus_validFormats_success() {
        assertTrue(Status.isValidStatus(APPLIED_KEYWORD));
        assertTrue(Status.isValidStatus(INTERVIEW_KEYWORD));
        assertTrue(Status.isValidStatus(WAITING_KEYWORD));
        assertTrue(Status.isValidStatus(OFFERED_KEYWORD));
        assertTrue(Status.isValidStatus(ACCEPTED_KEYWORD));
        assertTrue(Status.isValidStatus(REJECTED_KEYWORD));
    }

    @Test
    public void isValidStatus_caseInsensitivityTest_success() {
        assertTrue(Status.isValidStatus(VALID_STATUS_APPLIED_MIX_CASE));
        assertTrue(Status.isValidStatus(VALID_STATUS_REJECTED_MIX_CASE));
    }

    @Test
    public void isValidStatus_invalidFormats_success() {
        assertFalse(Status.isValidStatus(STATUS_BLANK));
        assertFalse(Status.isValidStatus(INVALID_STATUS));
    }

    @Test
    public void toString_validFormat_success() {
        assertEquals(APPLIED_KEYWORD, VALID_STATUS_ONE.toString());
    }

    @Test
    public void equals_equalityTest_success() {
        assertEquals(VALID_STATUS_ONE, VALID_STATUS_ONE);
        Status statusCopy = Status.valueOf(APPLIED_KEYWORD.toUpperCase());
        assertEquals(VALID_STATUS_ONE, statusCopy);
    }

    @Test
    public void equals_nonEqualityTest_success() {
        assertNotEquals(VALID_STATUS_ONE, VALID_STATUS_TWO);
    }

}
