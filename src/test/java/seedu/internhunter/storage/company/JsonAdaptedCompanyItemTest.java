package seedu.internhunter.storage.company;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.internhunter.storage.company.JsonAdaptedCompanyItem.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.internhunter.storage.internship.JsonAdaptedInternshipItemTest.INVALID_JOB_TITLE;
import static seedu.internhunter.storage.internship.JsonAdaptedInternshipItemTest.VALID_PERIOD;
import static seedu.internhunter.storage.internship.JsonAdaptedInternshipItemTest.VALID_REQUIREMENT;
import static seedu.internhunter.storage.internship.JsonAdaptedInternshipItemTest.VALID_WAGE;
import static seedu.internhunter.testutil.Assert.assertThrows;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.INVALID_ADDRESS_BLANK;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.INVALID_COMPANY_NAME_GOOGLE_WITH_LEADING_AND_TRAILING_SPACES;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.INVALID_EMAIL_DOMAIN_BEGIN_WITH_PERIOD;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.INVALID_INDUSTRY_EMPTY;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.INVALID_PHONE_ALPHABETS;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_ADDRESS_ONE_ALPHABET;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_COMPANY_NAME_GOLDMAN;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_EMAIL_GOOGLE;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_PHONE_EIGHT_NUMBERS;
import static seedu.internhunter.testutil.company.SampleCompanyItems.FACEBOOK;
import static seedu.internhunter.testutil.company.SampleCompanyItems.GOLDMAN;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.internhunter.commons.exceptions.IllegalValueException;
import seedu.internhunter.model.company.Address;
import seedu.internhunter.model.company.CompanyItem;
import seedu.internhunter.model.company.CompanyName;
import seedu.internhunter.model.company.Email;
import seedu.internhunter.model.company.Industry;
import seedu.internhunter.model.company.Phone;
import seedu.internhunter.model.internship.JobTitle;
import seedu.internhunter.storage.internship.JsonAdaptedInternshipItem;

public class JsonAdaptedCompanyItemTest {

    public static final String INVALID_COMPANY_NAME = INVALID_COMPANY_NAME_GOOGLE_WITH_LEADING_AND_TRAILING_SPACES;
    public static final String INVALID_PHONE = INVALID_PHONE_ALPHABETS;
    public static final String INVALID_EMAIL = INVALID_EMAIL_DOMAIN_BEGIN_WITH_PERIOD;
    public static final String INVALID_ADDRESS = INVALID_ADDRESS_BLANK;

    public static final String VALID_COMPANY_NAME = VALID_COMPANY_NAME_GOLDMAN;
    public static final String VALID_PHONE = VALID_PHONE_EIGHT_NUMBERS;
    public static final String VALID_EMAIL = VALID_EMAIL_GOOGLE;
    public static final String VALID_ADDRESS = VALID_ADDRESS_ONE_ALPHABET;
    public static final Set<JsonAdaptedIndustry> VALID_INDUSTRIES = FACEBOOK.getIndustries().stream()
            .map(JsonAdaptedIndustry::new)
            .collect(Collectors.toSet());
    public static final List<JsonAdaptedInternshipItem> VALID_INTERNSHIPS = GOLDMAN.getInternships().stream()
            .map(JsonAdaptedInternshipItem::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validCompanyItemDetails_returnsCompanyItem() throws Exception {
        JsonAdaptedCompanyItem applicationItem = new JsonAdaptedCompanyItem(FACEBOOK);
        assertEquals(FACEBOOK, applicationItem.toModelType());
    }

    @Test
    public void toModelType_invalidCompanyName_throwsIllegalValueException() {
        JsonAdaptedCompanyItem companyItem = new JsonAdaptedCompanyItem(INVALID_COMPANY_NAME, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, VALID_INDUSTRIES, VALID_INTERNSHIPS);
        String expectedMessage = CompanyName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, companyItem::toModelType);
    }

    @Test
    public void toModelType_nullCompanyName_throwsIllegalValueException() {
        JsonAdaptedCompanyItem companyItem = new JsonAdaptedCompanyItem(null, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, VALID_INDUSTRIES, VALID_INTERNSHIPS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, CompanyName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, companyItem::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedCompanyItem companyItem = new JsonAdaptedCompanyItem(VALID_COMPANY_NAME, INVALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, VALID_INDUSTRIES, VALID_INTERNSHIPS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, companyItem::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedCompanyItem companyItem = new JsonAdaptedCompanyItem(VALID_COMPANY_NAME, null,
                VALID_EMAIL, VALID_ADDRESS, VALID_INDUSTRIES, VALID_INTERNSHIPS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, companyItem::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedCompanyItem companyItem = new JsonAdaptedCompanyItem(VALID_COMPANY_NAME, VALID_PHONE,
                INVALID_EMAIL, VALID_ADDRESS, VALID_INDUSTRIES, VALID_INTERNSHIPS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, companyItem::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedCompanyItem companyItem = new JsonAdaptedCompanyItem(VALID_COMPANY_NAME, VALID_PHONE,
                null, VALID_ADDRESS, VALID_INDUSTRIES, VALID_INTERNSHIPS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, companyItem::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedCompanyItem companyItem = new JsonAdaptedCompanyItem(VALID_COMPANY_NAME, VALID_PHONE,
                VALID_EMAIL, INVALID_ADDRESS, VALID_INDUSTRIES, VALID_INTERNSHIPS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, companyItem::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedCompanyItem companyItem = new JsonAdaptedCompanyItem(VALID_COMPANY_NAME, VALID_PHONE,
                VALID_EMAIL, null, VALID_INDUSTRIES, VALID_INTERNSHIPS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, companyItem::toModelType);
    }

    @Test
    public void toModelType_invalidIndustry_throwsIllegalValueException() {
        Set<JsonAdaptedIndustry> invalidIndustries = new HashSet<>();
        invalidIndustries.add(new JsonAdaptedIndustry(INVALID_INDUSTRY_EMPTY));
        JsonAdaptedCompanyItem companyItem = new JsonAdaptedCompanyItem(VALID_COMPANY_NAME, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, invalidIndustries, VALID_INTERNSHIPS);
        String expectedMessage = Industry.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, companyItem::toModelType);
    }

    @Test
    public void toModelType_invalidInternship_throwsIllegalValueException() {
        JsonAdaptedInternshipItem invalidInternshipItem = new JsonAdaptedInternshipItem(VALID_COMPANY_NAME,
                INVALID_JOB_TITLE, VALID_WAGE, VALID_PERIOD, VALID_REQUIREMENT);
        List<JsonAdaptedInternshipItem> invalidInternships = new ArrayList<>();
        invalidInternships.add(invalidInternshipItem);
        JsonAdaptedCompanyItem companyItem = new JsonAdaptedCompanyItem(VALID_COMPANY_NAME, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, VALID_INDUSTRIES, invalidInternships);
        String expectedMessage = JobTitle.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, companyItem::toModelType);
    }

    @Test
    public void constructor_nullInput_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> new JsonAdaptedCompanyItem(null));
        CompanyItem companyItem = null;
        assertThrows(AssertionError.class, () -> new JsonAdaptedCompanyItem(companyItem));
    }

}
