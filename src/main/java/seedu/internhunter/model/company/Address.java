package seedu.internhunter.model.company;

import seedu.internhunter.model.wrapper.NonEmptyString;

/**
 * Represents a Person's address in the address book. TODO: Javadocs (Shawn)
 * Guarantees: immutable; is valid as declared in {@link #isValidNonEmptyString(String)}
 */
public class Address extends NonEmptyString {

    public static final String MESSAGE_CONSTRAINTS = "Addresses can take any values, and it should not be blank";

    public Address(String address) {
        super(address, MESSAGE_CONSTRAINTS);
    }

    public static boolean isValidAddress(String address) {
        return isValidNonEmptyString(address);
    }

}
