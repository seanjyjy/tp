package seedu.internhunter.storage.internship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.internhunter.storage.internship.JsonAdaptedInternshipItem.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.internhunter.testutil.Assert.assertThrows;
import static seedu.internhunter.testutil.internship.SampleInternshipItems.SHOPEE_SWE;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.internhunter.commons.exceptions.IllegalValueException;
import seedu.internhunter.model.company.CompanyName;
import seedu.internhunter.model.internship.JobTitle;
import seedu.internhunter.model.internship.Period;
import seedu.internhunter.model.internship.Requirement;
import seedu.internhunter.model.internship.Wage;

public class JsonAdaptedInternshipItemTest {

    public static final String INVALID_COMPANY_NAME = "@pp13";
    public static final String INVALID_JOB_TITLE = "j@n1+0r";
    public static final String INVALID_PERIOD = "";
    public static final String INVALID_WAGE = "-999";
    public static final String INVALID_REQUIREMENT = "";

    public static final String VALID_COMPANY_NAME = SHOPEE_SWE.getCompanyName().toString();
    public static final String VALID_JOB_TITLE = SHOPEE_SWE.getJobTitle().toString();
    public static final String VALID_PERIOD = SHOPEE_SWE.getPeriod().toString();
    public static final String VALID_WAGE = SHOPEE_SWE.getWage().toString().substring(1);
    public static final Set<JsonAdaptedRequirement> VALID_REQUIREMENT = SHOPEE_SWE.getRequirements().stream()
            .map(JsonAdaptedRequirement::new)
            .collect(Collectors.toSet());

    @Test
    public void toModelType_validInternshipItemDetails_returnsInternshipItem() throws Exception {
        JsonAdaptedInternshipItem internshipItem = new JsonAdaptedInternshipItem(SHOPEE_SWE);
        assertEquals(SHOPEE_SWE, internshipItem.toModelType());
    }

    @Test
    public void toModelType_invalidCompanyName_throwsIllegalValueException() {
        JsonAdaptedInternshipItem internshipItem = new JsonAdaptedInternshipItem(INVALID_COMPANY_NAME,
                VALID_JOB_TITLE, VALID_PERIOD, VALID_WAGE, VALID_REQUIREMENT);
        String expectedMessage = CompanyName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internshipItem::toModelType);
    }

    @Test
    public void toModelType_nullCompanyName_throwsIllegalValueException() {
        JsonAdaptedInternshipItem internshipItem = new JsonAdaptedInternshipItem(null,
                VALID_JOB_TITLE, VALID_PERIOD, VALID_WAGE, VALID_REQUIREMENT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, CompanyName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internshipItem::toModelType);
    }

    @Test
    public void toModelType_invalidJobTitle_throwsIllegalValueException() {
        JsonAdaptedInternshipItem internshipItem = new JsonAdaptedInternshipItem(VALID_COMPANY_NAME,
                INVALID_JOB_TITLE, VALID_PERIOD, VALID_WAGE, VALID_REQUIREMENT);
        String expectedMessage = JobTitle.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internshipItem::toModelType);
    }

    @Test
    public void toModelType_nullJobTitle_throwsIllegalValueException() {
        JsonAdaptedInternshipItem internshipItem = new JsonAdaptedInternshipItem(VALID_COMPANY_NAME,
                null, VALID_PERIOD, VALID_WAGE, VALID_REQUIREMENT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, JobTitle.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internshipItem::toModelType);
    }

    @Test
    public void toModelType_invalidPeriod_throwsIllegalValueException() {
        JsonAdaptedInternshipItem internshipItem = new JsonAdaptedInternshipItem(VALID_COMPANY_NAME,
                VALID_JOB_TITLE, INVALID_PERIOD, VALID_WAGE, VALID_REQUIREMENT);
        String expectedMessage = Period.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internshipItem::toModelType);
    }

    @Test
    public void toModelType_nullPeriod_throwsIllegalValueException() {
        JsonAdaptedInternshipItem internshipItem = new JsonAdaptedInternshipItem(VALID_COMPANY_NAME,
                VALID_JOB_TITLE, null, VALID_WAGE, VALID_REQUIREMENT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Period.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internshipItem::toModelType);
    }

    @Test
    public void toModelType_invalidWage_throwsIllegalValueException() {
        JsonAdaptedInternshipItem internshipItem = new JsonAdaptedInternshipItem(VALID_COMPANY_NAME,
                VALID_JOB_TITLE, VALID_PERIOD, INVALID_WAGE, VALID_REQUIREMENT);
        String expectedMessage = Wage.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internshipItem::toModelType);
    }

    @Test
    public void toModelType_nullWage_throwsIllegalValueException() {
        JsonAdaptedInternshipItem internshipItem = new JsonAdaptedInternshipItem(VALID_COMPANY_NAME,
                VALID_JOB_TITLE, VALID_PERIOD, null, VALID_REQUIREMENT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Wage.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internshipItem::toModelType);
    }

    @Test
    public void toModelType_invalidRequirement_throwsIllegalValueException() {
        Set<JsonAdaptedRequirement> invalidRequirements = new HashSet<>();
        invalidRequirements.add(new JsonAdaptedRequirement(INVALID_REQUIREMENT));
        JsonAdaptedInternshipItem internshipItem = new JsonAdaptedInternshipItem(VALID_COMPANY_NAME,
                VALID_JOB_TITLE, VALID_PERIOD, VALID_WAGE, invalidRequirements);
        String expectedMessage = Requirement.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internshipItem::toModelType);
    }

}
