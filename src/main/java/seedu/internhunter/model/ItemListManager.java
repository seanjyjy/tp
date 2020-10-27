package seedu.internhunter.model;

import static java.util.Objects.requireNonNull;
import static seedu.internhunter.commons.util.CollectionUtil.requireAllNonNull;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.internhunter.model.item.Item;
import seedu.internhunter.model.item.ItemList;

public class ItemListManager<T extends Item> implements FilterableItemList<T> {
    private ItemList<T> itemList;
    private final FilteredList<T> filteredItems;

    /**
     * Initializes ItemListManager with the given itemList.
     *
     * @param itemList initial item list.
     */
    public ItemListManager(ItemList<T> itemList) {
        this.itemList = itemList;
        filteredItems = new FilteredList<>(this.itemList.getItemList());
    }

    /**
     * Creates an empty item list manager.
     */
    public ItemListManager() {
        this.itemList = new ItemList<>();
        filteredItems = new FilteredList<>(this.itemList.getItemList());
    }

    @Override
    public void setItemList(ItemList<T> itemList) {
        this.itemList.resetData(itemList);
    }

    @Override
    public ItemList<T> getUnfilteredItemList() {
        return itemList;
    }

    @Override
    public ObservableList<T> getItemList() {
        return itemList.getItemList();
    }

    @Override
    public boolean hasItem(T item) {
        requireNonNull(item);
        return itemList.hasItem(item);
    }

    @Override
    public void deleteItem(T target) {
        itemList.removeItem(target);
    }

    @Override
    public void deleteSameItem(T target) {
        itemList.removeSameItem(target);
    }

    @Override
    public void addItem(T item) {
        itemList.addItem(item);
        updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS);
    }

    @Override
    public void setItem(T target, T editedItem) {
        requireAllNonNull(target, editedItem);
        itemList.setItem(target, editedItem);
        updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS);
    }

    //=========== Filtered Item List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Item} backed by the internal list of
     * {@code versionedItemList}
     */
    @Override
    public ObservableList<T> getFilteredItemList() {
        return filteredItems;
    }

    /**
     * Retrieves the size of the filterable item list.
     *
     * @return the size of the filterable item list.
     */
    @Override
    public int getSize() {
        return filteredItems.size();
    }

    /**
     * Returns an {@code Item} from filteredItems.
     * @param index of item to be returned.
     * @return Item at index specified.
     */
    @Override
    public T getItemFromFilteredItemList(int index) {
        return filteredItems.get(index);
    }


    @Override
    public void updateFilteredItemList(Predicate<? super T> predicate) {
        requireNonNull(predicate);
        filteredItems.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ItemListManager)) {
            return false;
        }

        // state check
        ItemListManager other = (ItemListManager) obj;
        return itemList.equals(other.itemList)
                && filteredItems.equals(other.filteredItems);
    }
}
