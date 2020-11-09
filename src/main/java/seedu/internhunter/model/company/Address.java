package seedu.internhunter.model.company;

import seedu.internhunter.model.wrapper.NonEmptyString;

/**
 * Represents a Company's address.
 * Guarantees: immutable; is valid as declared in {@link #isValidNonEmptyString(String)}
 */
public class Address extends NonEmptyString {

    public static final String MESSAGE_CONSTRAINTS = "Addresses can take any values, and it should not be blank";

    /**
     * Constructs an {@code Address}.
     *
     * @param address A valid address
     */
    public Address(String address) {
        super(address, MESSAGE_CONSTRAINTS);
    }

    /**
     * Checks if the address is valid or not. Address must follow the format of {@code NonEmptyString}.
     *
     * @param address An address
     * @return True if address has a valid input format, false otherwise.
     */
    public static boolean isValidAddress(String address) {
        return isValidNonEmptyString(address);
    }

}
