package seedu.internhunter.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.internhunter.commons.exceptions.IllegalValueException;
import seedu.internhunter.model.item.Item;
import seedu.internhunter.model.item.ItemList;
import seedu.internhunter.model.item.ReadOnlyItemList;
import seedu.internhunter.storage.item.JsonAdaptedItem;

/**
 * An Immutable Item List that is serializable to JSON format.
 */
public class JsonSerializableItemList<T extends Item, U extends JsonAdaptedItem> {

    public static final String MESSAGE_DUPLICATE_ITEM = "Item list contains duplicate item(s).";

    private final List<U> items = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableItemList} with the given items.
     */
    @JsonCreator
    public JsonSerializableItemList(@JsonProperty("items") List<U> items) {
        this.items.addAll(items);
    }

    /**
     * Converts a given {@code ReadOnlyItemList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableItemList}.
     */
    @SuppressWarnings("unchecked")
    public JsonSerializableItemList(ReadOnlyItemList<T> source) {
        items.addAll(source.getItemList().stream().map(item ->
                (U) item.getJsonAdaptedItem()).collect(Collectors.toList()));
    }

    /**
     * Converts this item list into the model's {@code ItemList<T>} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    @SuppressWarnings("unchecked")
    public ItemList<T> toModelType() throws IllegalValueException {
        ItemList<T> itemList = new ItemList<>();
        for (U jsonAdaptedItem : items) {
            T item = (T) jsonAdaptedItem.toModelType();
            if (itemList.hasItem(item)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ITEM);
            }
            itemList.addItem(item);
        }
        return itemList;
    }

}
