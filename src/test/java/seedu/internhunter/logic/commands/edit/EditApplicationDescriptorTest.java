package seedu.internhunter.logic.commands.edit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.internhunter.logic.commands.util.application.SampleEditApplicationDescriptor.DESC_GOLDMAN;
import static seedu.internhunter.logic.commands.util.application.SampleEditApplicationDescriptor.DESC_SHOPEE;
import static seedu.internhunter.model.util.StatusUtil.APPLIED_KEYWORD;
import static seedu.internhunter.testutil.application.ApplicationItemFieldsUtil.STATUS_DATE_WITH_TIME;

import org.junit.jupiter.api.Test;

import seedu.internhunter.logic.commands.edit.EditApplicationCommand.EditApplicationDescriptor;
import seedu.internhunter.logic.commands.util.application.EditApplicationDescriptorBuilder;

public class EditApplicationDescriptorTest {

    @Test
    public void equals_multipleTests_success() {

        // same values -> returns true
        EditApplicationDescriptor descriptorWithSameValues = new EditApplicationDescriptor(DESC_GOLDMAN);
        assertEquals(descriptorWithSameValues, DESC_GOLDMAN);

        // same object -> returns true
        assertEquals(DESC_GOLDMAN, DESC_GOLDMAN);

        // null -> returns false
        assertNotEquals(DESC_GOLDMAN, null);

        // different types -> returns false
        assertNotEquals(DESC_GOLDMAN, 5);

        // different values -> returns false
        assertNotEquals(DESC_SHOPEE, DESC_GOLDMAN);

        // different status -> returns false
        EditApplicationDescriptor editedGoldman =
                new EditApplicationDescriptorBuilder(DESC_GOLDMAN).withStatus(APPLIED_KEYWORD).build();
        assertNotEquals(editedGoldman, DESC_GOLDMAN);

        // different status date -> returns false
        editedGoldman =
                new EditApplicationDescriptorBuilder(DESC_GOLDMAN).withStatusDate(STATUS_DATE_WITH_TIME).build();
        assertNotEquals(editedGoldman, DESC_GOLDMAN);
    }
}
