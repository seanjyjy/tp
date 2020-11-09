package seedu.internhunter.commons.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.commons.util.FileUtil.isFileExists;
import static seedu.internhunter.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class FileUtilTest {

    @Test
    public void isValidPath() {
        // valid path
        assertTrue(FileUtil.isValidPath("valid/file/path"));

        // invalid path
        assertFalse(FileUtil.isValidPath("a\0"));

        // null path -> throws NullPointerException
        assertThrows(NullPointerException.class, () -> FileUtil.isValidPath(null));

    }

    @Test
    public void isFileExists_null_false() {
        Path path = Paths.get("valid/file/path/data"); // valid file path but doesnt exist
        assertFalse(isFileExists(path));
        Path pathExist = Paths.get("src", "test", "data", "JsonSerializableApplicationItemListTest")
            .resolve("sampleApplicationItemList.json");
        assertTrue(isFileExists(pathExist));
    }

}
