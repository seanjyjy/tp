package seedu.internhunter.model.profile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.internhunter.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class TitleTest {

    static final String VALID_TITLE = "Internship at facebook";
    static final String INVALID_EMPTY_TITLE = "";
    static final String INVALID_SPACE_TITLE = " ";
    static final String INVALID_SYMBOL_TITLE = "?";
    static final String INVALID_TITLE = "C++";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Title(null));
    }

    @Test
    public void constructor_invalidJobTitle_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Title(INVALID_TITLE));
        assertThrows(IllegalArgumentException.class, () -> new Title(INVALID_SPACE_TITLE));
        assertThrows(IllegalArgumentException.class, () -> new Title(INVALID_SYMBOL_TITLE));
        assertThrows(IllegalArgumentException.class, () -> new Title(INVALID_EMPTY_TITLE));
    }

    @Test
    public void toString_validFormats_success() {
        Title title = new Title(VALID_TITLE);
        assertEquals(VALID_TITLE, title.toString());
    }

    @Test
    public void equals_equalityTest_success() {
        Title title = new Title(VALID_TITLE);
        Title titleTwo = new Title(VALID_TITLE);
        assertEquals(title, titleTwo);
    }

    @Test
    public void hashCode_equalityTest_success() {
        Title title = new Title(VALID_TITLE);
        Title titleTwo = new Title(VALID_TITLE);
        assertEquals(title.hashCode(), titleTwo.hashCode());
    }
}
