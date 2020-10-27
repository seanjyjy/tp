package seedu.internhunter.model.profile;

import static java.util.Objects.requireNonNull;
import static seedu.internhunter.commons.util.AppUtil.checkArgument;


/**
  * Represents a descriptor in the profile item.
  * Guarantees: immutable; non-empty; descriptor is valid as declared in {@link #isValidDescriptor(String)}
  */
public class Descriptor {
    public static final String MESSAGE_CONSTRAINTS = "descriptors should be alphanumeric and non-empty,"
        + "punctuations limited to .,?!:;+#%";
    public static final String VALIDATION_REGEX = "^[A-Za-z0-9 .,!?:;+#%]+$";

    public final String descriptor;

    /**
     * Constructs a {@code Descriptor}.
     *
     * @param descriptor A valid descriptor.
     */
    public Descriptor(String descriptor) {
        requireNonNull(descriptor);
        checkArgument(isValidDescriptor(descriptor), MESSAGE_CONSTRAINTS);
        this.descriptor = descriptor;
    }

    /**
     * Returns true if a given string is a valid descriptor.
     */
    public static boolean isValidDescriptor(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Descriptor// instanceof handles nulls
                && descriptor.equals(((Descriptor) other).descriptor)); // state check
    }

    @Override
    public int hashCode() {
        return descriptor.hashCode();
    }

    /**
     * Format descriptor as text for viewing.
     */
    public String toString() {
        return descriptor;
    }
}
