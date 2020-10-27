package seedu.internhunter.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.internhunter.commons.exceptions.DataConversionException;
import seedu.internhunter.model.item.Item;
import seedu.internhunter.model.item.ItemList;
import seedu.internhunter.model.item.ReadOnlyItemList;
import seedu.internhunter.storage.item.JsonAdaptedItem;

/**
 * Represents a storage for {@link seedu.internhunter.model.item.ItemList}.
 */
public interface ItemListStorage<T extends Item, U extends JsonAdaptedItem> {

    /**
     * Returns the file path of the data file.
     */
    Path getItemListFilePath();

    /**
     * Returns ItemList data as a {@link ItemList}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException             if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyItemList<T>> readItemList() throws DataConversionException, IOException;

    /**
     * @see #getItemListFilePath()
     */
    Optional<ReadOnlyItemList<T>> readItemList(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ItemList} to the storage.
     *
     * @param itemList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveItemList(ReadOnlyItemList<T> itemList) throws IOException;

    /**
     * @see #saveItemList(ReadOnlyItemList)
     */
    void saveItemList(ReadOnlyItemList<T> itemList, Path filePath) throws IOException;

}
