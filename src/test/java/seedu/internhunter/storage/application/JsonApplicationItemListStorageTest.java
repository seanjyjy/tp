package seedu.internhunter.storage.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.internhunter.testutil.Assert.assertThrows;
import static seedu.internhunter.testutil.application.SampleApplicationItems.FACEBOOK_ACCEPTED;
import static seedu.internhunter.testutil.application.SampleApplicationItems.SHOPEE_OFFERED;
import static seedu.internhunter.testutil.application.SampleApplicationItems.getSampleApplicationItemList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.internhunter.commons.exceptions.DataConversionException;
import seedu.internhunter.model.application.ApplicationItem;
import seedu.internhunter.model.item.ItemList;
import seedu.internhunter.model.item.ReadOnlyItemList;
import seedu.internhunter.storage.JsonItemListStorage;

public class JsonApplicationItemListStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonApplicationItemListStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readApplicationItemList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readApplicationItemList(null));
    }

    private java.util.Optional<ReadOnlyItemList<ApplicationItem>> readApplicationItemList(
            String filePath) throws Exception {
        return new JsonItemListStorage<>(Paths.get(filePath),
                ApplicationItem.class, JsonAdaptedApplicationItem.class).readItemList(
                addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readApplicationItemList("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readApplicationItemList(
                "notJsonFormatApplicationItemList.json"));
    }

    @Test
    public void readApplicationItemList_invalidApplicationItemList_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readApplicationItemList(
                "invalidApplicationItemList.json"));
    }

    @Test
    public void readApplicationItemList_invalidAndValidApplicationItemList_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readApplicationItemList(
                "invalidAndValidApplicationItemList.json"));
    }

    @Test
    public void readAndSaveApplicationItemList_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempApplicationItemList.json");
        ItemList<ApplicationItem> original = getSampleApplicationItemList();
        JsonItemListStorage<ApplicationItem, JsonAdaptedApplicationItem> jsonItemListStorage =
                new JsonItemListStorage<>(filePath, ApplicationItem.class, JsonAdaptedApplicationItem.class);

        // Save in new file and read back
        jsonItemListStorage.saveItemList(original, filePath);
        ReadOnlyItemList<ApplicationItem> readBack = jsonItemListStorage.readItemList(filePath).get();
        assertEquals(original, new ItemList<>(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addItem(FACEBOOK_ACCEPTED);
        original.removeItem(SHOPEE_OFFERED);
        jsonItemListStorage.saveItemList(original, filePath);
        readBack = jsonItemListStorage.readItemList(filePath).get();
        assertEquals(original, new ItemList<>(readBack));

        // Save and read without specifying file path
        original.addItem(SHOPEE_OFFERED);
        jsonItemListStorage.saveItemList(original); // file path not specified
        readBack = jsonItemListStorage.readItemList().get(); // file path not specified
        assertEquals(original, new ItemList<>(readBack));

    }

    @Test
    public void saveApplicationItemList_nullApplicationItemList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveApplicationItemList(null,
                "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveApplicationItemList(ItemList<ApplicationItem> addressBook, String filePath) {
        try {
            new JsonItemListStorage<>(Paths.get(filePath), ApplicationItem.class,
                    JsonAdaptedApplicationItem.class).saveItemList(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveApplicationItemList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveApplicationItemList(new ItemList<>(), null));
    }
}
