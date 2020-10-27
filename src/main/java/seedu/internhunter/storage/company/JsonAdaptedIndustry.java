package seedu.internhunter.storage.company;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.internhunter.commons.exceptions.IllegalValueException;
import seedu.internhunter.model.company.Industry;

/**
 * Jackson-friendly version of {@link Industry}.
 */
class JsonAdaptedIndustry {
    private final String industryType;

    /**
     * Constructs a {@code JsonAdaptedIndustry} with the given {@code industry}.
     */
    @JsonCreator
    public JsonAdaptedIndustry(String industryType) {
        this.industryType = industryType;
    }

    /**
     * Converts a given {@code Industry} into this class for Jackson use.
     */
    public JsonAdaptedIndustry(Industry source) {
        industryType = source.getValue();
    }

    @JsonValue
    public String getIndustryName() {
        return industryType;
    }

    /**
     * Converts this Jackson-friendly adapted industry object into the model's {@code Industry} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted industry.
     */
    public Industry toModelType() throws IllegalValueException {
        if (!Industry.isValidAlphaNumericWord(industryType)) {
            throw new IllegalValueException(Industry.MESSAGE_CONSTRAINTS);
        }
        return new Industry(industryType);
    }
}
