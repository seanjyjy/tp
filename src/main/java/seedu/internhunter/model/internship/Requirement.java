package seedu.internhunter.model.internship;

import seedu.internhunter.model.wrapper.NonEmptyString;

/**
 * Requirement is an optional field in an InternshipItem.
 * Guarantees: immutable; is valid as declared in {@link #isValidRequirement(String)}
 */
public class Requirement extends NonEmptyString {

    public static final String MESSAGE_CONSTRAINTS = "Requirements should not be blank";

    /**
     * Constructs a {@code Requirement}.
     *
     * @param requirement A valid requirement.
     */
    public Requirement(String requirement) {
        super(requirement, MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns true if a given string is a valid requirement.
     *
     * @param requirement String to test.
     * @return True if the given string is a valid requirement, false otherwise.
     */
    public static boolean isValidRequirement(String requirement) {
        return isValidNonEmptyString(requirement);
    }

}
