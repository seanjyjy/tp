package seedu.internhunter.storage.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.internhunter.storage.application.JsonAdaptedApplicationItem.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.internhunter.testutil.Assert.assertThrows;
import static seedu.internhunter.testutil.application.SampleApplicationItems.SHOPEE_OFFERED;

import org.junit.jupiter.api.Test;

import seedu.internhunter.commons.exceptions.IllegalValueException;
import seedu.internhunter.model.application.Status;
import seedu.internhunter.model.application.StatusDate;
import seedu.internhunter.model.internship.InternshipItem;
import seedu.internhunter.storage.internship.JsonAdaptedInternshipItem;

public class JsonAdaptedApplicationItemTest {
    private static final String INVALID_STATUS = "DEAD";
    private static final String INVALID_STATUS_DATE = "03-11-2020";

    private static final String VALID_STATUS = SHOPEE_OFFERED.getStatus().toString();
    private static final String VALID_STATUS_DATE = SHOPEE_OFFERED.getStatusDate().toString();
    private static final JsonAdaptedInternshipItem VALID_INTERNSHIP_ITEM =
            SHOPEE_OFFERED.getInternshipItem().getJsonAdaptedItem();

    @Test
    public void toModelType_validApplicationItemDetails_returnsApplicationItem() throws Exception {
        JsonAdaptedApplicationItem applicationItem = new JsonAdaptedApplicationItem(SHOPEE_OFFERED);
        assertEquals(SHOPEE_OFFERED, applicationItem.toModelType());
    }

    @Test
    public void toModelType_invalidStatus_throwsIllegalValueException() {
        JsonAdaptedApplicationItem applicationItem = new JsonAdaptedApplicationItem(INVALID_STATUS,
                VALID_STATUS_DATE, VALID_INTERNSHIP_ITEM);
        String expectedMessage = Status.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, applicationItem::toModelType);
    }

    @Test
    public void toModelType_nullStatus_throwsIllegalValueException() {
        JsonAdaptedApplicationItem applicationItem = new JsonAdaptedApplicationItem(null, VALID_STATUS_DATE,
                VALID_INTERNSHIP_ITEM);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Status.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, applicationItem::toModelType);
    }

    @Test
    public void toModelType_invalidStatusDate_throwsIllegalValueException() {
        JsonAdaptedApplicationItem applicationItem = new JsonAdaptedApplicationItem(VALID_STATUS, INVALID_STATUS_DATE,
                VALID_INTERNSHIP_ITEM);
        String expectedMessage = StatusDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, applicationItem::toModelType);
    }

    @Test
    public void toModelType_nullStatusDate_throwsIllegalValueException() {
        JsonAdaptedApplicationItem applicationItem = new JsonAdaptedApplicationItem(VALID_STATUS, null,
                VALID_INTERNSHIP_ITEM);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, StatusDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, applicationItem::toModelType);
    }

    // To do: invalid internship item

    @Test
    public void toModelType_nullInternshipItem_throwsIllegalValueException() {
        JsonAdaptedApplicationItem applicationItem = new JsonAdaptedApplicationItem(VALID_STATUS, VALID_STATUS_DATE,
                null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, InternshipItem.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, applicationItem::toModelType);
    }

}
