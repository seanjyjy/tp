package seedu.internhunter.model.company;

import seedu.internhunter.model.wrapper.AlphaNumericWord;

/**
 * Represents a Company's name.
 * Guarantees: immutable; is valid as declared in {@link #isValidAlphaNumericWord(String)}.
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
     * Checks if the company name is valid or not. Company name must follow the format of {@code AlphaNumericWord}.
     *
     * @param test The string to test.
     * @return True if test string has a valid input format, false otherwise.
     */
    public static boolean isValidCompanyName(String test) {
        return isValidAlphaNumericWord(test);
    }
}
