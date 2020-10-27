package seedu.internhunter.model.company;

import seedu.internhunter.model.wrapper.AlphaNumericWord;

/**
 * Represents a Tag in the address book. TODO: Javadocs (Shawn)
 * Guarantees: immutable; name is valid as declared in {@link #isValidAlphaNumericWord(String)}
 */
public class Industry extends AlphaNumericWord {

    public static final String MESSAGE_CONSTRAINTS = "Industry types should only contain alphanumeric characters and "
            + "spaces, and it should not be blank";

    /**
     * Constructs a {@code Industry}.
     *
     * @param industryType A valid industry type.
     */
    public Industry(String industryType) {
        super(industryType, MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns true if a given string is a valid industry name.
     */
    public static boolean isValidIndustryName(String test) {
        return isValidAlphaNumericWord(test);
    }
}
