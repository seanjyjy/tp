package seedu.internhunter.logic.commands.util.internship;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.internhunter.logic.commands.edit.EditInternshipCommand.EditInternshipDescriptor;
import seedu.internhunter.model.internship.InternshipItem;
import seedu.internhunter.model.internship.JobTitle;
import seedu.internhunter.model.internship.Period;
import seedu.internhunter.model.internship.Requirement;
import seedu.internhunter.model.internship.Wage;

/**
 * A utility class to help with building EditInternshipDescriptor objects.
 */
public class EditInternshipDescriptorBuilder {
    private EditInternshipDescriptor descriptor;

    /**
     * Constructs an {@code EditInternshipDescriptorBuilder}.
     */
    public EditInternshipDescriptorBuilder() {
        descriptor = new EditInternshipDescriptor();
    }

    /**
     * Constructs an {@code EditInternshipDescriptorBuilder} with the given {@code EditInternshipDescriptor}.
     *
     * @param descriptor The details to edit the internship with.
     */
    public EditInternshipDescriptorBuilder(EditInternshipDescriptor descriptor) {
        this.descriptor = new EditInternshipDescriptor(descriptor);
    }

    /**
     * Constructs an {@code EditInternshipDescriptor} with fields containing {@code internshipItem}'s details.
     *
     * @param internshipItem The internship whose details will be used in the created descriptor.
     */
    public EditInternshipDescriptorBuilder(InternshipItem internshipItem) {
        descriptor = new EditInternshipDescriptor();
        descriptor.setJobTitle(internshipItem.getJobTitle());
        descriptor.setPeriod(internshipItem.getPeriod());
        descriptor.setWage(internshipItem.getWage());
        descriptor.setRequirements(internshipItem.getRequirements());
    }

    /**
     * Sets the {@code JobTitle} of the {@code EditInternshipDescriptor} that we are building.
     *
     * @param jobTitle The job title to set.
     * @return The builder with the descriptor's job title set.
     */
    public EditInternshipDescriptorBuilder withJobTitle(String jobTitle) {
        requireNonNull(jobTitle);
        descriptor.setJobTitle(new JobTitle(jobTitle));
        return this;
    }

    /**
     * Sets the {@code Period} of the {@code EditInternshipDescriptor} that we are building.
     *
     * @param period The period to set.
     * @return The builder with the descriptor's period set.
     */
    public EditInternshipDescriptorBuilder withPeriod(String period) {
        requireNonNull(period);
        descriptor.setPeriod(new Period(period));
        return this;
    }

    /**
     * Sets the {@code Wage} of the {@code EditInternshipDescriptor} that we are building.
     *
     * @param wage The wage to set.
     * @return The builder with the descriptor's wage set.
     */
    public EditInternshipDescriptorBuilder withWage(String wage) {
        requireNonNull(wage);
        descriptor.setWage(new Wage(wage));
        return this;
    }

    /**
     * Sets the {@code Requirement} set of the {@code EditInternshipDescriptor} that we are building.
     *
     * @param requirements The requirements to set.
     * @return The builder with the descriptor's requirements set.
     */
    public EditInternshipDescriptorBuilder withRequirements(String... requirements) {
        requireNonNull(requirements);
        descriptor.setRequirements(getRequirementSet(requirements));
        return this;
    }

    /**
     * Returns the descriptor.
     *
     * @return The descriptor.
     */
    public EditInternshipDescriptor build() {
        return descriptor;
    }

    /**
     * Returns a set of requirements containing the list of strings given.
     *
     * @param strings Representative of requirements.
     * @return A set of requirements containing the list of strings given.
     */
    private Set<Requirement> getRequirementSet(String... strings) {
        return Arrays.stream(strings)
                .map(Requirement::new)
                .collect(Collectors.toSet());
    }
}
