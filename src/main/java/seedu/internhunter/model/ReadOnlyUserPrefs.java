package seedu.internhunter.model;

import java.nio.file.Path;

import seedu.internhunter.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getApplicationItemListFilePath();

    Path getCompanyItemListFilePath();

    Path getProfileItemListFilePath();

}
