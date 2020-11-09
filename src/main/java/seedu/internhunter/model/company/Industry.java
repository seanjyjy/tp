package seedu.internhunter.model.company;

import seedu.internhunter.model.wrapper.AlphaNumericWord;

/**
 * Represents a Company's industry type(s).
 * Guarantees: immutable; is valid as declared in {@link #isValidAlphaNumericWord(String)}
 */
public class Industry extends AlphaNumericWord {

    public static final String MESSAGE_CONSTRAINTS = "Industry types should only contain alphanumeric characters and "
            + "spaces, and it should not be blank";

    /**
     * Constructs an {@code Industry}.
     *
     * @param industryType A valid industry type.
     */
    public Industry(String industryType) {
        super(industryType, MESSAGE_CONSTRAINTS);
    }

    /**
     * Checks if the test string is a valid industry based on {@code #isValidAlphaNumericWord(String)}.
     *
     * @param test The string to test.
     * @return True if test string has a valid input format, false otherwise.
     */
    public static boolean isValidIndustryName(String test) {
        return isValidAlphaNumericWord(test);
    }
}
