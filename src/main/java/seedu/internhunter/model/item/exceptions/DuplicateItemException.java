package seedu.internhunter.model.item.exceptions;

import seedu.internhunter.model.item.Item;

/**
 * Signals that the operation will result in duplicate Items (Items are considered duplicates if they have the same
 * identity).
 */
public class DuplicateItemException extends RuntimeException {

    public DuplicateItemException(Item item) {
        super(String.format("Operation would result in duplicate %s items", item.getItemName()));
    }

}
