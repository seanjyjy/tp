package seedu.internhunter.storage.profile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.internhunter.testutil.Assert.assertThrows;
import static seedu.internhunter.testutil.profile.SampleProfileItems.GRAPHQL_SKILL;
import static seedu.internhunter.testutil.profile.SampleProfileItems.HACKATHON_ACHIEVEMENT;
import static seedu.internhunter.testutil.profile.SampleProfileItems.HTML_SKILL;
import static seedu.internhunter.testutil.profile.SampleProfileItems.getSampleProfileItemList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.internhunter.commons.exceptions.DataConversionException;
import seedu.internhunter.model.item.ItemList;
import seedu.internhunter.model.item.ReadOnlyItemList;
import seedu.internhunter.model.profile.ProfileItem;
import seedu.internhunter.storage.JsonItemListStorage;

public class JsonProfileItemListStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonProfileItemListStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readProfileItemList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readProfileItemList(null));
    }

    private java.util.Optional<ReadOnlyItemList<ProfileItem>> readProfileItemList(String filePath) throws Exception {
        return new JsonItemListStorage<>(Paths.get(filePath),
                ProfileItem.class, JsonAdaptedProfileItem.class).readItemList(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readProfileItemList("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readProfileItemList(
                "notJsonFormatProfileItemList.json"));
    }

    @Test
    public void readProfileItemList_invalidProfileItemProfileItemList_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readProfileItemList("invalidProfileItemList.json"));
    }

    @Test
    public void readProfileItemList_invalidAndValidProfileItemProfileItemList_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readProfileItemList(
                "invalidAndValidProfileItemList.json"));
    }

    @Test
    public void readAndSaveProfileItemList_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempProfileItemList.json");
        ItemList<ProfileItem> original = getSampleProfileItemList();
        JsonItemListStorage<ProfileItem, JsonAdaptedProfileItem> jsonItemListStorage =
                new JsonItemListStorage<>(filePath, ProfileItem.class, JsonAdaptedProfileItem.class);

        // Save in new file and read back
        jsonItemListStorage.saveItemList(original, filePath);
        ReadOnlyItemList<ProfileItem> readBack = jsonItemListStorage.readItemList(filePath).get();
        assertEquals(original, new ItemList<>(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addItem(HACKATHON_ACHIEVEMENT);
        original.removeItem(HTML_SKILL);
        jsonItemListStorage.saveItemList(original, filePath);
        readBack = jsonItemListStorage.readItemList(filePath).get();
        assertEquals(original, new ItemList<>(readBack));

        // Save and read without specifying file path
        original.addItem(GRAPHQL_SKILL);
        jsonItemListStorage.saveItemList(original); // file path not specified
        readBack = jsonItemListStorage.readItemList().get(); // file path not specified
        assertEquals(original, new ItemList<>(readBack));

    }

    @Test
    public void saveProfileItemList_nullProfileItemList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveProfileItemList(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveProfileItemList(ItemList<ProfileItem> addressBook, String filePath) {
        try {
            new JsonItemListStorage<>(Paths.get(filePath), ProfileItem.class,
                    JsonAdaptedProfileItem.class).saveItemList(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveProfileItemList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveProfileItemList(new ItemList<>(), null));
    }
}
