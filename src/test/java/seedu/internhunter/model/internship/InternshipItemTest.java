package seedu.internhunter.model.internship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.model.internship.Wage.WAGE_SYMBOL;
import static seedu.internhunter.model.util.InternshipItemUtil.COMPANY_OUTPUT_NAME;
import static seedu.internhunter.model.util.InternshipItemUtil.PERIOD_OUTPUT_NAME;
import static seedu.internhunter.model.util.InternshipItemUtil.REQUIREMENTS_OUTPUT_NAME;
import static seedu.internhunter.model.util.InternshipItemUtil.WAGE_OUTPUT_NAME;
import static seedu.internhunter.model.util.ItemUtil.INTERNSHIP_NAME;
import static seedu.internhunter.testutil.Assert.assertThrows;
import static seedu.internhunter.testutil.application.SampleApplicationItems.SHOPEE_OFFERED;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_COMPANY_NAME_FACEBOOK;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_COMPANY_NAME_SHOPEE;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_JOB_TITLE_BA;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_JOB_TITLE_FE;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_JOB_TITLE_SWE;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_PERIOD_MAY_TO_JULY;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_PERIOD_THREE_MONTHS;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_PERIOD_TWO_MONTHS;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_REQUIREMENT_R;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_REQUIREMENT_VUE;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_WAGE_2000;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_WAGE_3000;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_WAGE_3500;
import static seedu.internhunter.testutil.internship.SampleInternshipItems.FACEBOOK_FE;
import static seedu.internhunter.testutil.internship.SampleInternshipItems.LAZADA_DS;
import static seedu.internhunter.testutil.internship.SampleInternshipItems.SHOPEE_SWE;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.internhunter.model.company.CompanyName;
import seedu.internhunter.storage.internship.JsonAdaptedInternshipItem;
import seedu.internhunter.testutil.internship.InternshipItemBuilder;

public class InternshipItemTest {

    @Test
    public void requirements_invalidDataType_throwsUnsupportedOperationException() {
        InternshipItem internshipItem = new InternshipItemBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> internshipItem.getRequirements().remove(0));
    }

    @Test
    public void setCompanyName_newCompanyName_success() {
        CompanyName companyName = new CompanyName(VALID_COMPANY_NAME_FACEBOOK);
        InternshipItem editedInternshipItem = new InternshipItemBuilder(SHOPEE_SWE).build();
        editedInternshipItem.setCompanyName(companyName);
        assertEquals(companyName, editedInternshipItem.getCompanyName());
    }

    @Test
    public void setJobTitle_newJobTitle_success() {
        JobTitle jobTitle = new JobTitle(VALID_JOB_TITLE_BA);
        InternshipItem editedInternshipItem = new InternshipItemBuilder(SHOPEE_SWE).build();
        editedInternshipItem.setJobTitle(jobTitle);
        assertEquals(jobTitle, editedInternshipItem.getJobTitle());
    }

    @Test
    public void setWage_newWage_success() {
        Wage wage = new Wage(VALID_WAGE_3000);
        InternshipItem editedInternshipItem = new InternshipItemBuilder(SHOPEE_SWE).build();
        editedInternshipItem.setWage(wage);
        assertEquals(wage, editedInternshipItem.getWage());
    }

    @Test
    public void setPeriod_newPeriod_success() {
        Period period = new Period(VALID_PERIOD_THREE_MONTHS);
        InternshipItem editedInternshipItem = new InternshipItemBuilder(SHOPEE_SWE).build();
        editedInternshipItem.setPeriod(period);
        assertEquals(period, editedInternshipItem.getPeriod());
    }

    @Test
    public void setRequirements_newRequirements_success() {
        Requirement requirement = new Requirement(VALID_REQUIREMENT_R);
        Set<Requirement> requirementSet = new HashSet<>();
        requirementSet.add(requirement);
        InternshipItem editedInternshipItem = new InternshipItemBuilder(SHOPEE_SWE).build();
        editedInternshipItem.setRequirements(requirementSet);
        assertEquals(requirementSet, editedInternshipItem.getRequirements());
    }

    @Test
    public void getItemName_equalityTest_success() {
        assertEquals(INTERNSHIP_NAME, SHOPEE_SWE.getItemName());
    }

    @Test
    public void getMapping_size_success() {
        LinkedHashMap<String, Object> mapping = SHOPEE_SWE.getMapping();
        assertEquals(5, mapping.size());
    }

    @Test
    public void getMapping_correctOrdering_success() {
        LinkedHashMap<String, Object> mapping = SHOPEE_SWE.getMapping();
        Iterator<Object> fields = mapping.values().iterator();
        assertEquals(SHOPEE_SWE.getJobTitle(), fields.next());
        assertEquals(SHOPEE_SWE.getCompanyName(), fields.next());
        assertEquals(SHOPEE_SWE.getPeriod(), fields.next());
        assertEquals(SHOPEE_SWE.getWage(), fields.next());
        assertEquals(SHOPEE_SWE.getRequirements(), fields.next());
    }

    @Test
    public void isSameItem_true_success() {
        // same object -> returns true
        assertTrue(SHOPEE_SWE.isSameItem(SHOPEE_SWE));

        InternshipItem editedShopeeSwe;

        // different object, same values -> returns true
        editedShopeeSwe = new InternshipItemBuilder(SHOPEE_SWE).build();
        assertTrue(SHOPEE_SWE.isSameItem(editedShopeeSwe));

        // different wage, everything else the same -> returns true
        editedShopeeSwe = new InternshipItemBuilder(SHOPEE_SWE).withWage(VALID_WAGE_3000).build();
        assertTrue(SHOPEE_SWE.isSameItem(editedShopeeSwe));

        // different requirements, everything else the same -> returns true
        editedShopeeSwe = new InternshipItemBuilder(SHOPEE_SWE).withRequirements(VALID_REQUIREMENT_R,
                VALID_REQUIREMENT_VUE).build();
        assertTrue(SHOPEE_SWE.isSameItem(editedShopeeSwe));

    }

    @Test
    public void isSameItem_false_success() {
        // null -> returns false
        assertFalse(SHOPEE_SWE.isSameItem(null));

        // Two different internships -> returns false
        assertFalse(SHOPEE_SWE.isSameItem(LAZADA_DS));

        // Two different items -> return false
        assertFalse(SHOPEE_SWE.isSameItem(SHOPEE_OFFERED));

        // different company -> returns false
        InternshipItem editedShopeeSwe = new InternshipItemBuilder(SHOPEE_SWE)
                .withCompanyName(VALID_COMPANY_NAME_FACEBOOK).build();
        assertFalse(SHOPEE_SWE.isSameItem(editedShopeeSwe));

        // different job title -> returns false
        editedShopeeSwe = new InternshipItemBuilder(SHOPEE_SWE).withJobTitle(VALID_JOB_TITLE_BA).build();
        assertFalse(SHOPEE_SWE.isSameItem(editedShopeeSwe));

        // different period -> returns false
        editedShopeeSwe = new InternshipItemBuilder(SHOPEE_SWE).withPeriod(VALID_PERIOD_THREE_MONTHS).build();
        assertFalse(SHOPEE_SWE.isSameItem(editedShopeeSwe));

    }

    @Test
    public void equals_true_success() {
        // same object -> returns true
        assertEquals(SHOPEE_SWE, SHOPEE_SWE);

        // different object, same values -> returns true
        InternshipItem shopeeSweCopy = new InternshipItemBuilder(SHOPEE_SWE).build();
        assertEquals(shopeeSweCopy, SHOPEE_SWE);
    }

    @Test
    public void equals_false_success() {
        // null -> returns false
        assertNotEquals(SHOPEE_SWE, null);

        // different type -> returns false
        assertNotEquals(SHOPEE_SWE, 5);

        // Two different items -> return false
        assertNotEquals(SHOPEE_SWE, SHOPEE_OFFERED);

        // different internshipItem -> returns false
        assertNotEquals(LAZADA_DS, SHOPEE_SWE);

        // different company name -> returns false
        InternshipItem editedShopeeSwe = new InternshipItemBuilder(SHOPEE_SWE)
                .withCompanyName(VALID_COMPANY_NAME_FACEBOOK).build();
        assertNotEquals(editedShopeeSwe, SHOPEE_SWE);

        // different job title -> returns false
        editedShopeeSwe = new InternshipItemBuilder(SHOPEE_SWE).withJobTitle(VALID_JOB_TITLE_BA).build();
        assertNotEquals(editedShopeeSwe, SHOPEE_SWE);

        // different period -> returns false
        editedShopeeSwe = new InternshipItemBuilder(SHOPEE_SWE).withPeriod(VALID_PERIOD_THREE_MONTHS).build();
        assertNotEquals(editedShopeeSwe, SHOPEE_SWE);

        // different wage -> returns false
        editedShopeeSwe = new InternshipItemBuilder(SHOPEE_SWE).withWage(VALID_WAGE_3000).build();
        assertNotEquals(editedShopeeSwe, SHOPEE_SWE);

        // different requirements -> returns false
        editedShopeeSwe = new InternshipItemBuilder(SHOPEE_SWE).withRequirements(VALID_REQUIREMENT_R).build();
        assertNotEquals(editedShopeeSwe, SHOPEE_SWE);
    }

    @Test
    public void hashCode_equalityTest_success() {
        assertEquals(SHOPEE_SWE.hashCode(), SHOPEE_SWE.hashCode());
        InternshipItem shopeeSweCopy = new InternshipItemBuilder(SHOPEE_SWE).build();
        assertEquals(SHOPEE_SWE.hashCode(), shopeeSweCopy.hashCode());
    }

    @Test
    public void toString_nonEmptyRequirements_success() {
        final StringBuilder builder = new StringBuilder();
        builder.append(VALID_JOB_TITLE_SWE)
                .append(", ")
                .append(COMPANY_OUTPUT_NAME)
                .append(VALID_COMPANY_NAME_SHOPEE)
                .append(", ")
                .append(PERIOD_OUTPUT_NAME)
                .append(VALID_PERIOD_TWO_MONTHS)
                .append(", ")
                .append(WAGE_OUTPUT_NAME)
                .append(WAGE_SYMBOL)
                .append(VALID_WAGE_2000)
                .append(", ")
                .append(REQUIREMENTS_OUTPUT_NAME)
                .append(SHOPEE_SWE.getRequirements())
                .append(System.lineSeparator());
        assertEquals(builder.toString(), SHOPEE_SWE.toString());
    }

    @Test
    public void toString_noRequirements_success() {
        final StringBuilder builder = new StringBuilder();
        builder.append(VALID_JOB_TITLE_FE)
                .append(", ")
                .append(COMPANY_OUTPUT_NAME)
                .append(VALID_COMPANY_NAME_FACEBOOK)
                .append(", ")
                .append(PERIOD_OUTPUT_NAME)
                .append(VALID_PERIOD_MAY_TO_JULY)
                .append(", ")
                .append(WAGE_OUTPUT_NAME)
                .append(WAGE_SYMBOL)
                .append(VALID_WAGE_3500)
                .append(", ")
                .append(REQUIREMENTS_OUTPUT_NAME)
                .append("-")
                .append(System.lineSeparator());
        assertEquals(builder.toString(), FACEBOOK_FE.toString());
    }

    @Test
    public void getJsonAdaptedItem_nonEqualityTest_success() {
        assertNotEquals(new JsonAdaptedInternshipItem(SHOPEE_SWE), SHOPEE_SWE.getJsonAdaptedItem());
    }

}
