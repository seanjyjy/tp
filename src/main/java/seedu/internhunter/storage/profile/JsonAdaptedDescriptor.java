package seedu.internhunter.storage.profile;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.internhunter.commons.exceptions.IllegalValueException;
import seedu.internhunter.model.profile.Descriptor;

/**
 * Jackson-friendly version of {@link Descriptor}.
 */
public class JsonAdaptedDescriptor {

    private final String descriptor;

    /**
     * Constructs a {@code JsonAdaptedDescriptor} with the given {@code descriptor}.
     */
    @JsonCreator
    public JsonAdaptedDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    /**
     * Converts a given {@code Descriptor} into this class for Jackson use.
     */
    public JsonAdaptedDescriptor(Descriptor source) {
        descriptor = source.descriptor;
    }

    @JsonValue
    public String getDescriptorName() {
        return descriptor;
    }

    /**
     * Converts this Jackson-friendly adapted industry object into the model's {@code Descriptor} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted industry.
     */
    public Descriptor toModelType() throws IllegalValueException {
        if (!Descriptor.isValidDescriptor(descriptor)) {
            throw new IllegalValueException(Descriptor.MESSAGE_CONSTRAINTS);
        }
        return new Descriptor(descriptor);
    }
}
