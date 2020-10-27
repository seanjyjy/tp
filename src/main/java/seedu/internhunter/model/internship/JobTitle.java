package seedu.internhunter.model.internship;

import seedu.internhunter.model.wrapper.AlphaNumericWord;

/**
 * Represents an InternshipItem's job title.
 * Guarantees: immutable; is valid as declared in {@link #isValidJobTitle(String)}
 */
public class JobTitle extends AlphaNumericWord {

    public static final String MESSAGE_CONSTRAINTS =
            "Job titles should only contain alphanumeric characters and spaces, and it should not be blank";

    /**
     * Constructs a {@code JobTitle}.
     *
     * @param jobTitle A valid job title.
     */
    public JobTitle(String jobTitle) {
        super(jobTitle, MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns true if a given string is a valid job title.
     *
     * @param jobTitle String to test.
     * @return True if the given string is a valid job title, false otherwise.
     */
    public static boolean isValidJobTitle(String jobTitle) {
        return isValidAlphaNumericWord(jobTitle);
    }

}
