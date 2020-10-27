package seedu.internhunter.storage.item;

import seedu.internhunter.commons.exceptions.IllegalValueException;
import seedu.internhunter.model.item.Item;

/*
    Represents a Jackson-friendly version  of an item.
 */
public abstract class JsonAdaptedItem {

    public static final String NULL_SOURCE_ERROR_MESSAGE =
            "A JsonAdaptedItem should never be created from a null object";

    /**
     * Converts this Jackson-friendly adapted item object into the model's {@code Item} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted item.
     */
    public abstract Item toModelType() throws IllegalValueException;
}
