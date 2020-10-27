package seedu.internhunter.model.internship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.testutil.Assert.assertThrows;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.INVALID_REQUIREMENT_EMPTY;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.INVALID_REQUIREMENT_SPACES;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_REQUIREMENT_R;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_REQUIREMENT_VUE;

import org.junit.jupiter.api.Test;

public class RequirementTest {

    private static final Requirement VALID_REQUIREMENT_ONE = new Requirement(VALID_REQUIREMENT_VUE);
    private static final Requirement VALID_REQUIREMENT_TWO = new Requirement(VALID_REQUIREMENT_R);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Requirement(null));
    }

    @Test
    public void constructor_invalidRequirement_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                Requirement.MESSAGE_CONSTRAINTS, () -> new Requirement(INVALID_REQUIREMENT_EMPTY));
        assertThrows(IllegalArgumentException.class,
                Requirement.MESSAGE_CONSTRAINTS, () -> new Requirement(INVALID_REQUIREMENT_SPACES));
    }

    @Test
    public void isValidRequirement_invalidFormats_success() {
        assertFalse(Requirement.isValidRequirement(INVALID_REQUIREMENT_EMPTY));
        assertFalse(Requirement.isValidRequirement(INVALID_REQUIREMENT_SPACES));
    }

    @Test
    public void isValidRequirement_validFormats_success() {
        assertTrue(Requirement.isValidRequirement(VALID_REQUIREMENT_VUE));
        assertTrue(Requirement.isValidRequirement(VALID_REQUIREMENT_R));
    }

    @Test
    public void getValue_equalityTest_success() {
        assertEquals(VALID_REQUIREMENT_VUE, VALID_REQUIREMENT_ONE.getValue());
    }

    @Test
    public void toString_validFormat_success() {
        assertEquals(VALID_REQUIREMENT_VUE, VALID_REQUIREMENT_ONE.toString());
    }

    @Test
    public void equals_equalityTest_success() {
        assertEquals(VALID_REQUIREMENT_ONE, VALID_REQUIREMENT_ONE);
        Requirement validRequirementCopy = new Requirement(VALID_REQUIREMENT_VUE);
        assertEquals(VALID_REQUIREMENT_ONE, validRequirementCopy);
    }

    @Test
    public void equals_nonEqualityTest_success() {
        assertNotEquals(VALID_REQUIREMENT_ONE, VALID_REQUIREMENT_TWO);
    }

    @Test
    public void hashCode_equalityTest_success() {
        assertEquals(VALID_REQUIREMENT_ONE.hashCode(), VALID_REQUIREMENT_ONE.hashCode());
    }

}
