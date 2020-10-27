package seedu.internhunter.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.internhunter.commons.core.LogsCenter;
import seedu.internhunter.commons.exceptions.DataConversionException;
import seedu.internhunter.commons.exceptions.IllegalValueException;
import seedu.internhunter.commons.util.FileUtil;
import seedu.internhunter.commons.util.JsonUtil;
import seedu.internhunter.model.item.Item;
import seedu.internhunter.model.item.ReadOnlyItemList;
import seedu.internhunter.storage.item.JsonAdaptedItem;

/**
 * A class to access ItemList data stored as a json file on the hard disk.
 */
public class JsonItemListStorage<T extends Item, U extends JsonAdaptedItem> implements ItemListStorage<T, U> {

    private static final Logger logger = LogsCenter.getLogger(JsonItemListStorage.class);

    private final Path filePath;

    private final Class<T> contentClass;
    private final Class<U> jsonClass;

    /**
     * Initializes the json item list storage.
     *
     * @param filePath     path of the storage.
     * @param contentClass item class saved in the storage.
     * @param jsonClass    json adapted class of the item.
     */
    public JsonItemListStorage(Path filePath, Class<T> contentClass, Class<U> jsonClass) {
        this.filePath = filePath;
        this.contentClass = contentClass;
        this.jsonClass = jsonClass;
    }

    @Override
    public Path getItemListFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyItemList<T>> readItemList() throws DataConversionException {
        return readItemList(filePath);
    }

    /**
     * Similar to {@link #readItemList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    @Override
    public Optional<ReadOnlyItemList<T>> readItemList(Path filePath) throws DataConversionException {
        logger.fine("Attempting to read data from file: " + filePath);
        requireNonNull(filePath);

        Optional<JsonSerializableItemList<T, U>> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, contentClass, jsonClass);
        if (jsonAddressBook.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveItemList(ReadOnlyItemList<T> itemList) throws IOException {
        saveItemList(itemList, filePath);
    }

    /**
     * Similar to {@link #saveItemList(ReadOnlyItemList)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    @Override
    public void saveItemList(ReadOnlyItemList<T> itemList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        requireNonNull(itemList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableItemList<T, U>(itemList), filePath);
    }

}
