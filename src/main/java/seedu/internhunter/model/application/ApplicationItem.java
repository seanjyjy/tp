package seedu.internhunter.model.application;

import static java.util.Objects.requireNonNull;
import static seedu.internhunter.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.internhunter.model.util.ApplicationItemUtil.DATE_OUTPUT_NAME;
import static seedu.internhunter.model.util.ApplicationItemUtil.STATUS_OUTPUT_NAME;
import static seedu.internhunter.model.util.ItemUtil.APPLICATION_NAME;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.DATE_DISPLAY_NAME;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.STATUS_DISPLAY_NAME;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Objects;

import seedu.internhunter.model.internship.InternshipItem;
import seedu.internhunter.model.internship.JobTitle;
import seedu.internhunter.model.item.Item;
import seedu.internhunter.storage.application.JsonAdaptedApplicationItem;
import seedu.internhunter.storage.item.JsonAdaptedItem;

/**
 * Represents an ApplicationItem in the InternHunter application.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class ApplicationItem extends Item {

    // Identity fields
    private final InternshipItem internshipItem;

    // Data fields
    private final Status status;
    private final StatusDate statusDate;

    /**
     * Every field must be present and not null.
     *
     * @param internshipItem Internship item.
     * @param status Status.
     * @param statusDate Status date.
     */
    public ApplicationItem(InternshipItem internshipItem, Status status, StatusDate statusDate) {
        requireAllNonNull(internshipItem, status, statusDate);
        this.internshipItem = internshipItem;
        this.status = status;
        this.statusDate = statusDate;
    }

    /**
     * Creates an application item using the internship item.
     * Constructor is used to match internships with their application.
     *
     * @param internshipItem Internship item.
     */
    public ApplicationItem(InternshipItem internshipItem) {
        requireNonNull(internshipItem);
        this.internshipItem = internshipItem;
        this.status = Status.APPLIED;
        this.statusDate = new StatusDate(LocalDateTime.now());
    }

    /**
     * Retrieves the InternshipItem of this ApplicationItem.
     *
     * @return InternshipItem of this ApplicationItem.
     */
    public InternshipItem getInternshipItem() {
        return internshipItem;
    }

    /**
     * Retrieves the JobTitle of the InternshipItem of this ApplicationItem.
     *
     * @return InternshipItem of this ApplicationItem.
     */
    public JobTitle getJobTitleOfInternshipItem() {
        return internshipItem.getJobTitle();
    }

    /**
     * Retrieves the status of this ApplicationItem.
     *
     * @return Status of this ApplicationItem.
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Retrieves the status in string representation.
     *
     * @return Status in string representation.
     */
    public String getStatusString() {
        return status.toString();
    }

    /**
     * Retrieves the status date of this ApplicationItem.
     *
     * @return Status date of this ApplicationItem.
     */
    public StatusDate getStatusDate() {
        return statusDate;
    }

    /**
     * Retrieves the status date in string representation.
     *
     * @return Status date in string representation.
     */
    public String getStatusDateString() {
        return statusDate.toString();
    }

    /**
     * Retrieves the internship job title in string representation.
     *
     * @return Internship job title in string representation.
     */
    public String getInternshipJobTitleValue() {
        return internshipItem.getJobTitleValue();
    }

    /**
     * Obtains the name of the ApplicationItem.
     *
     * @return ApplicationItem name.
     */
    @Override
    public String getItemName() {
        return APPLICATION_NAME;
    }

    /**
     * Obtains the mapping of all field names to their corresponding fields.
     *
     * @return Mapping of field names to fields for the ApplicationItem object.
     */
    @Override
    public LinkedHashMap<String, Object> getMapping() {
        LinkedHashMap<String, Object> mapping = internshipItem.getMapping();
        mapping.put(STATUS_DISPLAY_NAME, status);
        mapping.put(DATE_DISPLAY_NAME, statusDate);
        return mapping;
    }

    /**
     * Returns true if both Applications have the {@code InternshipItem}.
     * This defines a weaker notion of equality between two ApplicationItem objects.
     *
     * @param otherItem Other ApplicationItem to compare to.
     * @return True if and only if the 2 Applications have the same identity field.
     */
    @Override
    public boolean isSameItem(Item otherItem) {
        // short circuit if same object
        if (otherItem == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(otherItem instanceof ApplicationItem)) {
            return false;
        }

        ApplicationItem otherApplication = (ApplicationItem) otherItem;
        return otherApplication.getInternshipItem().equals(getInternshipItem());
    }

    /**
     * Returns true if both ApplicationItems have the same data fields.
     * This defines a stronger notion of equality between two ApplicationItem objects.
     *
     * @param other Object object to compare to.
     * @return True if the other ApplicationItem object has the same identity and data fields as this one.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ApplicationItem)) {
            return false;
        }

        ApplicationItem otherApplication = (ApplicationItem) other;
        return otherApplication.getInternshipItem().equals(getInternshipItem())
                && otherApplication.getStatus().equals(getStatus())
                && otherApplication.getStatusDate().equals(getStatusDate());
    }

    /**
     * Returns the hashcode of this ApplicationItem object, which is the hashcode of its fields.
     *
     * @return Hashcode of this ApplicationItem object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(internshipItem, status, statusDate);
    }

    /**
     * Returns the string representation of this ApplicationItem object.
     *
     * @return String representation of this ApplicationItem object.
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getInternshipItem())
                .append(STATUS_OUTPUT_NAME)
                .append(getStatus())
                .append(", ")
                .append(DATE_OUTPUT_NAME)
                .append(getStatusDate());
        return builder.toString();
    }

    /**
     * Gets the json adapted version of this ApplicationItem.
     *
     * @return Json adapted ApplicationItem.
     */
    @Override
    public JsonAdaptedItem getJsonAdaptedItem() {
        return new JsonAdaptedApplicationItem(this);
    }

}
