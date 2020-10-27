package seedu.internhunter.testutil.internship;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.internhunter.model.company.CompanyName;
import seedu.internhunter.model.internship.InternshipItem;
import seedu.internhunter.model.internship.JobTitle;
import seedu.internhunter.model.internship.Period;
import seedu.internhunter.model.internship.Requirement;
import seedu.internhunter.model.internship.Wage;

/**
 * A utility class to help with building InternshipItem objects.
 */
public class InternshipItemBuilder {

    public static final String DEFAULT_COMPANY_NAME = "Google";
    public static final String DEFAULT_JOB_TITLE = "Software Tester";
    public static final String DEFAULT_PERIOD = "Summer break, 3 months";
    public static final String DEFAULT_WAGE = "3500";

    private CompanyName companyName;
    private JobTitle jobTitle;
    private Period period;
    private Wage wage;
    private Set<Requirement> requirements;

    /**
     * Creates a {@code InternshipItemBuilder} with the default details.
     */
    public InternshipItemBuilder() {
        companyName = new CompanyName(DEFAULT_COMPANY_NAME);
        jobTitle = new JobTitle(DEFAULT_JOB_TITLE);
        period = new Period(DEFAULT_PERIOD);
        wage = new Wage(DEFAULT_WAGE);
        requirements = new HashSet<>();
    }

    /**
     * Initializes the InternshipItemBuilder with the data of {@code internshipToCopy}.
     */
    public InternshipItemBuilder(InternshipItem internshipToCopy) {
        requireNonNull(internshipToCopy);
        companyName = internshipToCopy.getCompanyName();
        jobTitle = internshipToCopy.getJobTitle();
        period = internshipToCopy.getPeriod();
        wage = internshipToCopy.getWage();
        requirements = new HashSet<>(internshipToCopy.getRequirements());
    }

    /**
     * Sets the {@code CompanyName} of the {@code InternshipItem} that we are building.
     */
    public InternshipItemBuilder withCompanyName(String companyName) {
        requireNonNull(companyName);
        this.companyName = new CompanyName(companyName);
        return this;
    }

    /**
     * Sets the {@code JobTitle} of the {@code InternshipItem} that we are building.
     */
    public InternshipItemBuilder withJobTitle(String jobTitle) {
        requireNonNull(jobTitle);
        this.jobTitle = new JobTitle(jobTitle);
        return this;
    }

    /**
     * Sets the {@code Period} of the {@code InternshipItem} that we are building.
     */
    public InternshipItemBuilder withPeriod(String period) {
        requireNonNull(period);
        this.period = new Period(period);
        return this;
    }

    /**
     * Sets the {@code Wage} of the {@code InternshipItem} that we are building.
     */
    public InternshipItemBuilder withWage(String wage) {
        requireNonNull(wage);
        this.wage = new Wage(wage);
        return this;
    }

    /**
     * Parses the {@code requirements} into a {@code Set<Requirement>}
     * and set it to the {@code InternshipItem} that we are building.
     */
    public InternshipItemBuilder withRequirements(String... requirements) {
        requireNonNull(requirements);
        this.requirements = getRequirementSet(requirements);
        return this;
    }

    public InternshipItem build() {
        return new InternshipItem(companyName, jobTitle, period, wage, requirements);
    }

    /**
     * Returns a set of requirements containing the list of strings given.
     */
    private Set<Requirement> getRequirementSet(String... strings) {
        return Arrays.stream(strings)
                .map(Requirement::new)
                .collect(Collectors.toSet());
    }

}
