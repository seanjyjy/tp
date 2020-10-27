package seedu.internhunter.storage.application;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.internhunter.commons.exceptions.IllegalValueException;
import seedu.internhunter.model.application.ApplicationItem;
import seedu.internhunter.model.application.Status;
import seedu.internhunter.model.application.StatusDate;
import seedu.internhunter.model.internship.InternshipItem;
import seedu.internhunter.model.util.DateUtil;
import seedu.internhunter.storage.internship.JsonAdaptedInternshipItem;
import seedu.internhunter.storage.item.JsonAdaptedItem;

/**
 * Jackson-friendly version of {@link ApplicationItem}.
 */
public class JsonAdaptedApplicationItem extends JsonAdaptedItem {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Application item's %s field is missing!";

    private final String status;
    private final String statusDate;
    private final JsonAdaptedInternshipItem internshipItem;

    /**
     * Constructs a {@code JsonAdaptedApplicationItem} with the given application item details.
     */
    @JsonCreator
    public JsonAdaptedApplicationItem(@JsonProperty("status") String status,
            @JsonProperty("statusDate") String statusDate,
            @JsonProperty("internshipItem") JsonAdaptedInternshipItem internshipItem) {
        this.status = status;
        this.statusDate = statusDate;
        this.internshipItem = internshipItem;
    }

    /**
     * Converts a given {@code ApplicationItem} into this class for Jackson use.
     */
    public JsonAdaptedApplicationItem(ApplicationItem source) {
        assert source != null : JsonAdaptedItem.NULL_SOURCE_ERROR_MESSAGE;
        status = source.getStatusString();
        statusDate = source.getStatusDateString();
        internshipItem = new JsonAdaptedInternshipItem(source.getInternshipItem());
    }

    /**
     * Converts this Jackson-friendly adapted internship item object into the model's {@code ApplicationItem} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted application item.
     */
    @Override
    public ApplicationItem toModelType() throws IllegalValueException {

        if (internshipItem == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    InternshipItem.class.getSimpleName()));
        }

        final InternshipItem itemInternship = internshipItem.toModelType();

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Status.class.getSimpleName()));
        }
        if (!Status.isValidStatus(status)) {
            throw new IllegalValueException(Status.MESSAGE_CONSTRAINTS);
        }
        final Status itemStatus = Status.valueOf(status.toUpperCase());

        if (statusDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    StatusDate.class.getSimpleName()));
        }
        if (!DateUtil.isValidOutputDate(statusDate)) {
            throw new IllegalValueException(StatusDate.MESSAGE_CONSTRAINTS);
        }
        final StatusDate itemStatusDate = new StatusDate(DateUtil.convertOutputFormat(statusDate));

        return new ApplicationItem(itemInternship, itemStatus, itemStatusDate);
    }

}
