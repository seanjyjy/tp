package seedu.internhunter.model.item;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;

/**
 * Wraps all item data at the macro level.
 * Duplicates are not allowed (by .isSameItem comparison)
 */
public class ItemList<T extends Item> implements ReadOnlyItemList<T> {

    private final UniqueItemList<T> items;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        items = new UniqueItemList<>();
    }

    public ItemList() {
    }

    /**
     * Creates an ItemList using the Items in the {@code toBeCopied}
     */
    public ItemList(ReadOnlyItemList<T> toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the item list with {@code items}.
     * {@code items} must not contain duplicate items.
     */
    public void setItems(List<T> items) {
        this.items.setItems(items);
    }

    /**
     * Resets the existing data of this {@code ItemList} with {@code newData}.
     */
    public void resetData(ReadOnlyItemList<T> newData) {
        requireNonNull(newData);
        setItems(newData.getItemList());
    }

    //// item-level operations

    /**
     * Returns true if an item with the same identity as {@code item} exists in the item list.
     */
    public boolean hasItem(T item) {
        requireNonNull(item);
        return items.contains(item);
    }

    /**
     * Adds an item to the item list.
     * The item must not already exist in the item list.
     */
    public void addItem(T p) {
        items.add(p);
    }

    /**
     * Replaces the given item {@code target} in the list with {@code editedItem}.
     * {@code target} must exist in the item list.
     * The item identity of {@code editedItem} must not be the same as another existing item in the
     * item list.
     */
    public void setItem(T target, T editedItem) {
        requireNonNull(editedItem);
        items.setItem(target, editedItem);
    }

    /**
     * Removes {@code key} from this {@code ItemList}.
     * {@code key} must exist in the item list.
     */
    public void removeItem(T key) {
        items.remove(key);
    }

    /**
     * Removes {@code key} from this {@code ItemList}, removing the object based on the weaker notion of equality.
     * {@code key} need not be in this item list.
     */
    public void removeSameItem(T key) {
        items.removeSameItem(key);
    }

    // util methods

    @Override
    public String toString() {
        return items.asUnmodifiableObservableList().size() + " items";
    }

    @Override
    public ObservableList<T> getItemList() {
        return items.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ItemList // instanceof handles nulls
                && items.equals(((ItemList) other).items));
    }

    @Override
    public int hashCode() {
        return items.hashCode();
    }
}
