package seedu.internhunter.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.internhunter.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path applicationItemListFilePath = Paths.get("data", "applicationitemlist.json");
    private Path companyItemListFilePath = Paths.get("data", "companyitemlist.json");
    private Path profileItemListFilePath = Paths.get("data", "profileitemlist.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {
    }

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setApplicationItemListFilePath(newUserPrefs.getApplicationItemListFilePath());
        setCompanyItemListFilePath(newUserPrefs.getCompanyItemListFilePath());
        setProfileItemListFilePath(newUserPrefs.getProfileItemListFilePath());
    }

    @Override
    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    @Override
    public Path getApplicationItemListFilePath() {
        return applicationItemListFilePath;
    }

    public void setApplicationItemListFilePath(Path applicationItemListFilePath) {
        requireNonNull(applicationItemListFilePath);
        this.applicationItemListFilePath = applicationItemListFilePath;
    }

    @Override
    public Path getCompanyItemListFilePath() {
        return companyItemListFilePath;
    }

    public void setCompanyItemListFilePath(Path companyItemListFilePath) {
        requireNonNull(companyItemListFilePath);
        this.companyItemListFilePath = companyItemListFilePath;
    }

    @Override
    public Path getProfileItemListFilePath() {
        return profileItemListFilePath;
    }

    public void setProfileItemListFilePath(Path profileItemListFilePath) {
        requireNonNull(profileItemListFilePath);
        this.profileItemListFilePath = profileItemListFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UserPrefs)) { //this handles null as well.
            return false;
        }

        UserPrefs o = (UserPrefs) other;

        return guiSettings.equals(o.guiSettings)
                && applicationItemListFilePath.equals(o.applicationItemListFilePath)
                && companyItemListFilePath.equals(o.companyItemListFilePath)
                && profileItemListFilePath.equals(o.profileItemListFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, applicationItemListFilePath, companyItemListFilePath, profileItemListFilePath);
    }

    @Override
    public String toString() {
        return "Gui Settings : " + guiSettings
                + "\nLocal application item list data file location : " + applicationItemListFilePath
                + "\nLocal company item list data file location : " + companyItemListFilePath
                + "\nLocal profile item list data file location : " + profileItemListFilePath;
    }

}
