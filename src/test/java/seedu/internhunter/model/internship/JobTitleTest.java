package seedu.internhunter.model.internship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.testutil.Assert.assertThrows;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.INVALID_JOB_TITLE_BLANK;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.INVALID_JOB_TITLE_DASH;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_JOB_TITLE_FE;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_JOB_TITLE_SWE;

import org.junit.jupiter.api.Test;

public class JobTitleTest {

    private static final JobTitle VALID_JOB_TITLE_ONE = new JobTitle(VALID_JOB_TITLE_SWE);
    private static final JobTitle VALID_JOB_TITLE_TWO = new JobTitle(VALID_JOB_TITLE_FE);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new JobTitle(null));
    }

    @Test
    public void constructor_invalidJobTitle_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                JobTitle.MESSAGE_CONSTRAINTS, () -> new JobTitle(INVALID_JOB_TITLE_DASH));
        assertThrows(IllegalArgumentException.class,
                JobTitle.MESSAGE_CONSTRAINTS, () -> new JobTitle(INVALID_JOB_TITLE_BLANK));
    }

    @Test
    public void isValidJobTitle_invalidFormats_success() {
        assertFalse(JobTitle.isValidJobTitle(INVALID_JOB_TITLE_DASH));
        assertFalse(JobTitle.isValidJobTitle(INVALID_JOB_TITLE_BLANK));
    }

    @Test
    public void isValidJobTitle_validFormats_success() {
        assertTrue(JobTitle.isValidJobTitle(VALID_JOB_TITLE_SWE));
        assertTrue(JobTitle.isValidJobTitle(VALID_JOB_TITLE_FE));
    }

    @Test
    public void getValue_equalityTest_success() {
        assertEquals(VALID_JOB_TITLE_SWE, VALID_JOB_TITLE_ONE.getValue());
    }

    @Test
    public void toString_validFormat_success() {
        assertEquals(VALID_JOB_TITLE_SWE, VALID_JOB_TITLE_ONE.toString());
    }

    @Test
    public void equals_equalityTest_success() {
        assertEquals(VALID_JOB_TITLE_ONE, VALID_JOB_TITLE_ONE);
        JobTitle jobTitleCopy = new JobTitle(VALID_JOB_TITLE_SWE);
        assertEquals(VALID_JOB_TITLE_ONE, jobTitleCopy);
    }

    @Test
    public void equals_nonEqualityTest_success() {
        assertNotEquals(VALID_JOB_TITLE_ONE, VALID_JOB_TITLE_TWO);
    }

    @Test
    public void hashCode_equalityTest_success() {
        assertEquals(VALID_JOB_TITLE_ONE.hashCode(), VALID_JOB_TITLE_ONE.hashCode());
    }

}
