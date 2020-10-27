package seedu.internhunter.logic;

import javafx.collections.ObservableList;
import seedu.internhunter.commons.core.GuiSettings;
import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.logic.commands.CommandResult;
import seedu.internhunter.logic.commands.exceptions.CommandException;
import seedu.internhunter.logic.parser.exceptions.ParseException;
import seedu.internhunter.model.application.ApplicationItem;
import seedu.internhunter.model.company.CompanyItem;
import seedu.internhunter.model.profile.ProfileItem;
import seedu.internhunter.ui.tabs.TabName;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     *
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns an unmodifiable view of the filtered list of Application Item
     */
    ObservableList<ApplicationItem> getFilteredApplicationItemList();

    /**
     * Returns an unmodifiable view of the filtered list of Company Item
     */
    ObservableList<CompanyItem> getFilteredCompanyItemList();

    /**
     * Returns an unmodifiable view of the filtered list of Profile Item
     */
    ObservableList<ProfileItem> getFilteredProfileItemList();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the current tab of InternHunter.
     */
    TabName getTabName();

    /**
     * Sets the current tab of InternHunter.
     */
    void setTabName(TabName tabName);

    /**
     * Returns the current company view Index.
     */
    Index getCompanyViewIndex();

    /**
     * Returns the current application view Index.
     */
    Index getApplicationViewIndex();

    /**
     * Returns the current profile view Index.
     */
    Index getProfileViewIndex();

    /**
     * Sets the current company view index to {@code index}.
     */
    void setCompanyViewIndex(Index index);

    /**
     * Sets the current application view index to {@code index}.
     */
    void setApplicationViewIndex(Index index);

    /**
     * Sets the current profile view index to {@code index}.
     */
    void setProfileViewIndex(Index index);
}
