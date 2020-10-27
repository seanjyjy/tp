package seedu.internhunter.model.internship;

import seedu.internhunter.model.wrapper.NonEmptyString;

/**
 * Represents an InternshipItem's period.
 * Guarantees: immutable; is valid as declared in {@link #isValidPeriod(String)}
 */
public class Period extends NonEmptyString {

    public static final String MESSAGE_CONSTRAINTS = "Periods should not be blank";

    /**
     * Constructs a {@code Period}.
     *
     * @param period A valid period.
     */
    public Period(String period) {
        super(period, MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns true if a given string is a valid period.
     *
     * @param period String to test.
     * @return True if the given string is a valid period, false otherwise.
     */
    public static boolean isValidPeriod(String period) {
        return isValidNonEmptyString(period);
    }

}
