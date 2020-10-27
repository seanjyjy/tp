package seedu.internhunter.model.wrapper;

import static java.util.Objects.requireNonNull;
import static seedu.internhunter.commons.util.AppUtil.checkArgument;

/**
 * Represents a alphanumeric word object.
 * Guarantees: immutable; is valid as declared in {@link #isValidAlphaNumericWord(String)}
 */
public abstract class AlphaNumericWord {

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    private final String value;

    /**
     * Constructs a {@code AlphaNumericWord}.
     *
     * @param alphaNumericWord A valid word consisting of only alphanumeric characters.
     * @param messageConstraints Message constraint to produce if given string is invalid.
     */
    public AlphaNumericWord(String alphaNumericWord, String messageConstraints) {
        requireNonNull(alphaNumericWord);
        checkArgument(isValidAlphaNumericWord(alphaNumericWord), messageConstraints);
        value = alphaNumericWord;
    }

    /**
     * Retrieves the AlphaNumericWord string value.
     *
     * @return String value in AlphaNumericWord.
     */
    public String getValue() {
        return value;
    }

    /**
     * Returns true if a given string only consists of alphanumeric characters.
     *
     * @param test String to test.
     * @return True if the given string consists of only alphanumeric characters, false otherwise.
     */
    public static boolean isValidAlphaNumericWord(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the string representation of this AlphaNumericWord object.
     *
     * @return String representation of this AlphaNumericWord object.
     */
    @Override
    public String toString() {
        return value;
    }

    /**
     * Returns true if the other AlphaNumericWord object have the same value.
     *
     * @param other Object object to compare to.
     * @return True if the other AlphaNumericWord object has the same value as this object.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AlphaNumericWord // instanceof handles nulls
                && value.equals(((AlphaNumericWord) other).value)); // state check
    }

    /**
     * Returns the hashcode of this AlphaNumericWord object, which is the hashcode of the value.
     *
     * @return Hashcode of this AlphaNumericWord object.
     */
    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
