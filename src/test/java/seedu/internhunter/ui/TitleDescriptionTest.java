package seedu.internhunter.ui;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.internhunter.ui.textstyle.TitleDescription;

public class TitleDescriptionTest {

    private static final String HELLO = "Hello";
    private static final String DESCRIPTION = "Description";
    private static final String TITLE = "Title";
    private static final String WORLD = "WORLD";
    private TitleDescription helloDescription;
    private TitleDescription helloWorld;
    private TitleDescription titleWorld;

    @BeforeEach
    public void setUp() {
        helloDescription = TitleDescription.createTitleDescription(HELLO, DESCRIPTION);
        helloWorld = TitleDescription.createTitleDescription(HELLO, WORLD);
        titleWorld = TitleDescription.createTitleDescription(TITLE, WORLD);
    }

    @Test
    public void equals() {
        // same values -> returns true
        assertTrue(helloDescription.equals(TitleDescription.createTitleDescription(HELLO, DESCRIPTION)));

        // same object -> returns true
        assertTrue(helloDescription.equals(helloDescription));

        // null -> returns false
        assertFalse(helloDescription.equals(null));

        // different types -> returns false
        assertFalse(helloDescription.equals(0.5f));

        // different title -> returns false
        assertFalse(helloDescription.equals(TitleDescription.createTitleDescription("T", DESCRIPTION)));

        // different description -> returns false
        assertFalse(helloDescription.equals(TitleDescription.createTitleDescription(TITLE, "D")));

        // different title and description -> returns false
        assertFalse(helloDescription.equals(TitleDescription.createTitleDescription("HELLO", WORLD)));
    }

    @Test
    public void getTitle_true_success() {
        assertTrue(helloWorld.getTitle().getText().equals(HELLO));
        assertTrue(titleWorld.getTitle().getText().equals(TITLE));
    }

    @Test
    public void getTitle_false_success() {
        assertFalse(helloWorld.getTitle().getText().equals(TITLE));
        assertFalse(titleWorld.getTitle().getText().equals(HELLO));
    }

    @Test
    public void getDescription_true_success() {
        assertTrue(helloWorld.getDescription().getText().equals(WORLD));
        assertTrue(helloDescription.getDescription().getText().equals(DESCRIPTION));
    }

    @Test
    public void getDescription_false_success() {
        assertFalse(helloDescription.getDescription().getText().equals(WORLD));
        assertFalse(helloWorld.getDescription().getText().equals(DESCRIPTION));
    }
}
