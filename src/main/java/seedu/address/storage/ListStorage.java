package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.item.Item;
import seedu.address.model.item.ItemList;
import seedu.address.model.item.ReadOnlyItemList;

/**
 * Represents a storage for {@link seedu.address.model.item.ItemList}.
 */
public interface ListStorage<T extends Item> {

    /**
     * Returns the file path of the data file.
     */
    Path getItemListFilePath();

    /**
     * Returns ItemList data as a {@link ItemList}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyItemList<T>> readItemList() throws DataConversionException, IOException;

    /**
     * @see #getItemListFilePath()
     */
    Optional<ReadOnlyItemList<T>> readItemList(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ItemList} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveItemList(ReadOnlyItemList<T> addressBook) throws IOException;

    /**
     * @see #saveItemList(ReadOnlyItemList)
     */
    void saveItemList(ReadOnlyItemList<T> addressBook, Path filePath) throws IOException;

}
