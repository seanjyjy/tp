package seedu.internhunter.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.internhunter.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.internhunter.model.util.InternshipItemUtil.COMPANY_OUTPUT_NAME;
import static seedu.internhunter.model.util.InternshipItemUtil.PERIOD_OUTPUT_NAME;
import static seedu.internhunter.model.util.InternshipItemUtil.REQUIREMENTS_OUTPUT_NAME;
import static seedu.internhunter.model.util.InternshipItemUtil.WAGE_OUTPUT_NAME;
import static seedu.internhunter.model.util.ItemUtil.INTERNSHIP_NAME;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.COMPANY_DISPLAY_NAME;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.JOB_TITLE_DISPLAY_NAME;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.PERIOD_DISPLAY_NAME;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.REQUIREMENTS_DISPLAY_NAME;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.WAGE_DISPLAY_NAME;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.internhunter.model.company.CompanyName;
import seedu.internhunter.model.item.Item;
import seedu.internhunter.storage.internship.JsonAdaptedInternshipItem;

/**
 * Represents an InternshipItem in the InternHunter application.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class InternshipItem extends Item {

    // Identity fields
    private CompanyName companyName;
    private JobTitle jobTitle;
    private Period period;

    // Data fields
    private Wage wage;
    private Set<Requirement> requirements = new HashSet<>();

    /**
     * Constructs an InternshipItem object. Every field must be present and not null.
     *
     * @param companyName Company name.
     * @param jobTitle Job title.
     * @param period Period.
     * @param wage Wage.
     * @param requirements Requirements.
     */
    public InternshipItem(CompanyName companyName, JobTitle jobTitle, Period period, Wage wage,
            Set<Requirement> requirements) {
        requireAllNonNull(companyName, jobTitle, period, wage, requirements);
        this.companyName = companyName;
        this.jobTitle = jobTitle;
        this.period = period;
        this.wage = wage;
        this.requirements.addAll(requirements);
    }

    /**
     * Retrieves the company name of this InternshipItem.
     *
     * @return Company name of this InternshipItem.
     */
    public CompanyName getCompanyName() {
        return companyName;
    }

    public String getCompanyNameValue() {
        return companyName.getValue();
    }

    /**
     * Sets the company name of this InternshipItem.
     *
     * @param companyName Company name to set.
     */
    public void setCompanyName(CompanyName companyName) {
        requireNonNull(companyName);
        this.companyName = companyName;
    }

    /**
     * Retrieves the job title of this InternshipItem.
     *
     * @return Job Title of this InternshipItem.
     */
    public JobTitle getJobTitle() {
        return jobTitle;
    }

    public String getJobTitleValue() {
        return jobTitle.getValue();
    }

    /**
     * Sets the job title of this InternshipItem.
     *
     * @param jobTitle Job title to set.
     */
    public void setJobTitle(JobTitle jobTitle) {
        requireNonNull(jobTitle);
        this.jobTitle = jobTitle;
    }

    /**
     * Retrieves the period of this InternshipItem.
     *
     * @return Period of this InternshipItem.
     */
    public Period getPeriod() {
        return period;
    }

    public String getPeriodValue() {
        return period.getValue();
    }

    /**
     * Sets the period of this InternshipItem.
     *
     * @param period Job title to set.
     */
    public void setPeriod(Period period) {
        requireNonNull(period);
        this.period = period;
    }

    /**
     * Retrieves the wage of this InternshipItem.
     *
     * @return Wage of this InternshipItem.
     */
    public Wage getWage() {
        return wage;
    }

    public String getWageValue() {
        return wage.getValue();
    }

    /**
     * Sets the wage of this InternshipItem.
     *
     * @param wage Wage to set.
     */
    public void setWage(Wage wage) {
        requireNonNull(wage);
        this.wage = wage;
    }

    /**
     * Returns an immutable requirement set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     *
     * @return Immutable requirement set.
     */
    public Set<Requirement> getRequirements() {
        return Collections.unmodifiableSet(requirements);
    }

    /**
     * Sets the requirements of this InternshipItem.
     *
     * @param requirements Requirements to set.
     */
    public void setRequirements(Set<Requirement> requirements) {
        requireNonNull(requirements);
        this.requirements = requirements;
    }

    /**
     * Returns true if the skill list provided matches this internship item. A match is found when any skill in
     * the skill list is found in the list of requirements of this internship.
     *
     * @param skillList List of skills to check.
     * @return True if the skill list provided matches the internship item, false otherwise.
     */
    public boolean matches(List<String> skillList) {
        assert skillList != null;
        return requirements.stream().anyMatch(requirement ->
                skillList.stream().anyMatch(skill -> skill.equalsIgnoreCase(requirement.toString())));
    }

    /**
     * Obtains the name of this InternshipItem.
     *
     * @return InternshipItem name.
     */
    @Override
    public String getItemName() {
        return INTERNSHIP_NAME;
    }

    /**
     * Obtains the mapping of all field names to their corresponding fields in an InternshipItem object.
     *
     * @return Mapping of field names to fields for the InternshipItem.
     */
    @Override
    public LinkedHashMap<String, Object> getMapping() {
        LinkedHashMap<String, Object> mapping = new LinkedHashMap<>();
        mapping.put(JOB_TITLE_DISPLAY_NAME, jobTitle);
        mapping.put(COMPANY_DISPLAY_NAME, companyName);
        mapping.put(PERIOD_DISPLAY_NAME, period);
        mapping.put(WAGE_DISPLAY_NAME, wage);
        mapping.put(REQUIREMENTS_DISPLAY_NAME, requirements);
        return mapping;
    }

    /**
     * Returns true if both InternshipItems have the same jobTitle and period.
     * This defines a weaker notion of equality between two InternshipItems.
     *
     * @param otherItem Other InternshipItem to compare to.
     * @return True if and only if the 2 InternshipItems have the same identity fields.
     */
    @Override
    public boolean isSameItem(Item otherItem) {
        if (otherItem == this) {
            return true;
        }

        if (!(otherItem instanceof InternshipItem)) {
            return false;
        }

        InternshipItem otherInternshipItem = (InternshipItem) otherItem;
        return otherInternshipItem.getCompanyName().equals(getCompanyName())
                && otherInternshipItem.getJobTitle().equals(getJobTitle())
                && otherInternshipItem.getPeriod().equals(getPeriod());
    }

    /**
     * Returns true if both InternshipItems have the same identity and data fields.
     * This defines a stronger notion of equality between two InternshipItems.
     *
     * @param other Object object to compare to.
     * @return True if the other InternshipItem object has the same identity and data fields as this one.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof InternshipItem)) {
            return false;
        }

        InternshipItem otherInternshipItem = (InternshipItem) other;
        return otherInternshipItem.getCompanyName().equals(getCompanyName())
                && otherInternshipItem.getJobTitle().equals(getJobTitle())
                && otherInternshipItem.getPeriod().equals(getPeriod())
                && otherInternshipItem.getWage().equals(getWage())
                && otherInternshipItem.getRequirements().equals(getRequirements());
    }

    /**
     * Returns the hashcode of this InternshipItem object, which is the hashcode of its fields.
     *
     * @return Hashcode of this InternshipItem object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(companyName, jobTitle, period, wage, requirements);
    }

    /**
     * Returns the string representation of this InternshipItem object.
     *
     * @return String representation of this InternshipItem object.
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getJobTitle())
                .append(", ")
                .append(COMPANY_OUTPUT_NAME)
                .append(getCompanyName())
                .append(", ")
                .append(PERIOD_OUTPUT_NAME)
                .append(getPeriod())
                .append(", ")
                .append(WAGE_OUTPUT_NAME)
                .append(getWage())
                .append(", ")
                .append(REQUIREMENTS_OUTPUT_NAME)
                .append(getRequirements().isEmpty() ? "-" : getRequirements())
                .append(System.lineSeparator());
        return builder.toString();
    }

    /**
     * Gets the json adapted version of this InternshipItem.
     *
     * @return Json adapted InternshipItem.
     */
    @Override
    public JsonAdaptedInternshipItem getJsonAdaptedItem() {
        return new JsonAdaptedInternshipItem(this);
    }

}
