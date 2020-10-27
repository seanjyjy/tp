package seedu.internhunter.model;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.internhunter.model.item.Item;
import seedu.internhunter.model.item.ItemList;

public interface FilterableItemList<T extends Item> {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Item> PREDICATE_SHOW_ALL_ITEMS = unused -> true;

    /**
     * Replaces item list data with the data in {@code itemList}.
     */
    void setItemList(ItemList<T> itemList);

    /**
     * Returns the ItemList
     */
    ItemList<T> getUnfilteredItemList();

    /**
     * Returns true if a Item with the same identity as {@code Item} exists in the item list.
     */
    boolean hasItem(T item);

    /**
     * Deletes the given Item.
     * The Item must exist in the item list.
     */
    void deleteItem(T target);

    /**
     * Deletes the given Item according to the weaker notion of equality.
     * The Item may not necessarily exist in the item list.
     */
    void deleteSameItem(T target);

    /**
     * Adds the given Item.
     * {@code Item} must not already exist in the item list.
     */
    void addItem(T item);

    /**
     * Replaces the given Item {@code target} with {@code editedItem}.
     * {@code target} must exist in the item list.
     * The Item identity of {@code editedItem} must not be the same as another existing Item in the item list.
     */
    void setItem(T target, T editedItem);

    /**
     * Returns an unmodifiable view of the filtered Item list
     */
    ObservableList<T> getFilteredItemList();


    /** Returns an Item from the filtered Item list */
    T getItemFromFilteredItemList(int index);

    /** Returns the observable Item List */
    ObservableList<T> getItemList();

    /**
     * Updates the filter of the filtered Item list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredItemList(Predicate<? super T> predicate);

    /** Retrieves the size of the list. */
    int getSize();
}
