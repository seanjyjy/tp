package seedu.internhunter.model.item;

import java.util.LinkedHashMap;

import seedu.internhunter.storage.item.JsonAdaptedItem;

/**
 * Represents an Item in the InternHunter application.
 * There are 4 types of items, CompanyItem, InternshipItem, ApplicationItem, ProfileItem.
 */
public abstract class Item {

    /**
     * Returns true if both items are the have the same identity fields.
     * This defines a weaker notion of equality between two {@code Items}.
     *
     * @param otherItem Other item to compare to.
     * @return True if and only if the 2 items have the same identity fields.
     */
    public abstract boolean isSameItem(Item otherItem);

    /**
     * Obtains the name of the item.
     *
     * @return Item name.
     */
    public abstract String getItemName();

    /**
     * Obtains the mapping of all field names to their corresponding fields.
     *
     * @return Mapping of field names to fields for the item.
     */
    public abstract LinkedHashMap<String, Object> getMapping();

    /**
     * Gets the json adapted version of item.
     *
     * @return json adapted item.
     */
    public abstract JsonAdaptedItem getJsonAdaptedItem();

}
