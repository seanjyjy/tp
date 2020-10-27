package seedu.internhunter.storage.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.internhunter.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.internhunter.commons.exceptions.IllegalValueException;
import seedu.internhunter.commons.util.JsonUtil;
import seedu.internhunter.model.application.ApplicationItem;
import seedu.internhunter.model.item.ItemList;
import seedu.internhunter.storage.JsonSerializableItemList;
import seedu.internhunter.testutil.application.SampleApplicationItems;

public class JsonSerializableApplicationItemListTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src",
            "test", "data", "JsonSerializableApplicationItemListTest");
    private static final Path SAMPLE_APPLICATION_ITEMS_FILE = TEST_DATA_FOLDER.resolve(
            "sampleApplicationItemList.json");
    private static final Path INVALID_APPLICATION_ITEM_FILE = TEST_DATA_FOLDER.resolve(
            "invalidApplicationItemList.json");
    private static final Path DUPLICATE_APPLICATION_ITEM_FILE = TEST_DATA_FOLDER.resolve(
            "duplicateApplicationItemList.json");

    @Test
    public void toModelType_sampleApplicationItemsFile_success() throws Exception {
        JsonSerializableItemList<ApplicationItem, JsonAdaptedApplicationItem> dataFromFile = JsonUtil.readJsonFile(
                SAMPLE_APPLICATION_ITEMS_FILE, ApplicationItem.class, JsonAdaptedApplicationItem.class).get();
        ItemList<ApplicationItem> addressBookFromFile = dataFromFile.toModelType();
        ItemList<ApplicationItem> sampleApplicationItemListStorage =
                SampleApplicationItems.getSampleApplicationItemList();
        assertEquals(addressBookFromFile, sampleApplicationItemListStorage);
    }

    @Test
    public void toModelType_invalidApplicationItemFile_throwsIllegalValueException() throws Exception {
        JsonSerializableItemList<ApplicationItem, JsonAdaptedApplicationItem> dataFromFile = JsonUtil.readJsonFile(
                INVALID_APPLICATION_ITEM_FILE, ApplicationItem.class, JsonAdaptedApplicationItem.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateApplicationItems_throwsIllegalValueException() throws Exception {
        JsonSerializableItemList<ApplicationItem, JsonAdaptedApplicationItem> dataFromFile = JsonUtil.readJsonFile(
                DUPLICATE_APPLICATION_ITEM_FILE, ApplicationItem.class, JsonAdaptedApplicationItem.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableItemList.MESSAGE_DUPLICATE_ITEM,
                dataFromFile::toModelType);
    }

}
