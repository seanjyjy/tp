package seedu.internhunter.logic.commands.util.application;

import seedu.internhunter.logic.commands.edit.EditApplicationCommand.EditApplicationDescriptor;
import seedu.internhunter.model.application.ApplicationItem;
import seedu.internhunter.model.application.Status;
import seedu.internhunter.model.application.StatusDate;
import seedu.internhunter.model.util.DateUtil;

/**
 * A utility class to help with building EditApplicationDescriptor objects.
 */
public class EditApplicationDescriptorBuilder {

    private final EditApplicationDescriptor descriptor;

    public EditApplicationDescriptorBuilder() {
        descriptor = new EditApplicationDescriptor();
    }

    public EditApplicationDescriptorBuilder(EditApplicationDescriptor descriptor) {
        this.descriptor = new EditApplicationDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditApplicationDescriptor} with fields containing {@code applicationItem}'s details
     */
    public EditApplicationDescriptorBuilder(ApplicationItem applicationItem) {
        descriptor = new EditApplicationDescriptor();
        descriptor.setStatus(applicationItem.getStatus());
        descriptor.setStatusDate(applicationItem.getStatusDate());
    }

    /**
     * Sets the {@code Status} of the {@code EditApplicationDescriptor} that we are building.
     */
    public EditApplicationDescriptorBuilder withStatus(String status) {
        descriptor.setStatus(Status.valueOf(status.toUpperCase()));
        return this;
    }

    /**
     * Sets the {@code StatusDate} of the {@code EditApplicationDescriptor} that we are building.
     */
    public EditApplicationDescriptorBuilder withStatusDate(String statusDate) {
        descriptor.setStatusDate(new StatusDate(DateUtil.convertToDateTime(statusDate)));
        return this;
    }

    public EditApplicationDescriptor build() {
        return descriptor;
    }
}
