package seedu.internhunter.model.wrapper;

import static java.util.Objects.requireNonNull;
import static seedu.internhunter.commons.util.AppUtil.checkArgument;

/**
 * Represents a non empty string object.
 * Guarantees: immutable; is valid as declared in {@link #isValidNonEmptyString(String)}
 */
public abstract class NonEmptyString {

    /*
     * Represents a string that contains at least one non-space character.
     */
    public static final String VALIDATION_REGEX = "^(?!\\s*$).+";

    private final String value;

    /**
     * Constructs a {@code AlNumWord}.
     *
     * @param nonEmptyString A valid word that has at least one non-space character.
     */
    public NonEmptyString(String nonEmptyString, String messageConstraints) {
        requireNonNull(nonEmptyString);
        checkArgument(isValidNonEmptyString(nonEmptyString), messageConstraints);
        value = nonEmptyString;
    }

    /**
     * Retrieves the NonEmptyString string value.
     *
     * @return String value in NonEmptyString.
     */
    public String getValue() {
        return value;
    }

    /**
     * Returns true if a given string is not empty.
     *
     * @param test String to test.
     * @return True if the given string is not empty, false otherwise.
     */
    public static boolean isValidNonEmptyString(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the string representation of this NonEmptyString object.
     *
     * @return String representation of this NonEmptyString object.
     */
    @Override
    public String toString() {
        return value;
    }

    /**
     * Returns true if the other NonEmptyString object have the same value.
     *
     * @param other Object object to compare to.
     * @return True if the other NonEmptyString object has the same value as this object.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NonEmptyString // instanceof handles nulls
                && value.equals(((NonEmptyString) other).value)); // state check
    }

    /**
     * Returns the hashcode of this NonEmptyString object, which is the hashcode of the value.
     *
     * @return Hashcode of this NonEmptyString object.
     */
    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
