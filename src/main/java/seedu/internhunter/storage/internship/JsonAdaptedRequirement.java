package seedu.internhunter.storage.internship;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.internhunter.commons.exceptions.IllegalValueException;
import seedu.internhunter.model.internship.Requirement;

/**
 * Jackson-friendly version of {@link Requirement}.
 */
public class JsonAdaptedRequirement {

    private final String requirement;

    /**
     * Constructs a {@code JsonAdaptedRequirement} with the given {@code requirement}.
     */
    @JsonCreator
    public JsonAdaptedRequirement(String requirement) {
        this.requirement = requirement;
    }

    /**
     * Converts a given {@code Requirement} into this class for Jackson use.
     */
    public JsonAdaptedRequirement(Requirement source) {
        requirement = source.getValue();
    }

    @JsonValue
    public String getRequirementName() {
        return requirement;
    }

    /**
     * Converts this Jackson-friendly adapted requirement object into the model's {@code Requirement} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted requirement.
     */
    public Requirement toModelType() throws IllegalValueException {
        if (!Requirement.isValidRequirement(requirement)) {
            throw new IllegalValueException(Requirement.MESSAGE_CONSTRAINTS);
        }
        return new Requirement(requirement);
    }
}
