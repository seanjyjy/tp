package seedu.internhunter.model.company;

import seedu.internhunter.model.wrapper.AlphaNumericWord;

/**
 * Represents a name of a field in an Item. TODO: Javadocs (Shawn)
 * Guarantees: immutable; is valid as declared in {@link #isValidAlphaNumericWord(String)}
 */
public class CompanyName extends AlphaNumericWord {

    public static final String MESSAGE_CONSTRAINTS =
            "Company names should only contain alphanumeric characters and spaces, and it should not be blank";

    /**
     * Constructs a {@code CompanyName}.
     *
     * @param companyName A valid company name.
     */
    public CompanyName(String companyName) {
        super(companyName, MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns true if a given string is a valid company name.
     */
    public static boolean isValidCompanyName(String test) {
        return isValidAlphaNumericWord(test);
    }
}
