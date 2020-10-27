package seedu.internhunter.model;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.internhunter.commons.core.GuiSettings;
import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.model.application.ApplicationItem;
import seedu.internhunter.model.company.CompanyItem;
import seedu.internhunter.model.item.ItemList;
import seedu.internhunter.model.person.Person;
import seedu.internhunter.model.profile.ProfileItem;
import seedu.internhunter.ui.tabs.TabName;

/**
 * The API of the Model component.
 */
public interface Model {

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the person list
     */ //todo: remove when possible
    FilterableItemList<Person> getAddressBook();

    /**
     * Returns the company list
     */ // todo: remove if ultimately not needed
    FilterableItemList<CompanyItem> getCompanyList();

    /**
     * Returns an unmodifiable view of the filtered company list
     */
    ObservableList<CompanyItem> getFilteredCompanyList();

    /**
     * Returns the unfiltered company list
     */
    ItemList<CompanyItem> getUnfilteredCompanyList();

    /**
     * Returns the company item list
     */
    ObservableList<CompanyItem> getCompanyItemList();

    /**
     * Returns true if a Company with the same identity as {@code companyItem} exists in the company list.
     */
    boolean hasCompany(CompanyItem companyItem);

    /**
     * Deletes the given Company.
     * The Company must exist in the company list.
     */
    void deleteCompany(CompanyItem target);

    /**
     * Adds the given Company.
     * {@code companyItem} must not already exist in the company list.
     */
    void addCompany(CompanyItem companyItem);

    /**
     * Replaces the given Company {@code target} with {@code editedCompanyItem}.
     * {@code target} must exist in the company list.
     * The Company identity of {@code editedCompanyItem} must not be the same as another existing Company in the
     * company list.
     */
    void setCompany(CompanyItem target, CompanyItem editedCompanyItem);

    /**
     * Updates the filter of the filtered Company list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredCompanyList(Predicate<? super CompanyItem> predicate);

    /**
     * Replaces company list data with the data in {@code companyList}.
     */
    void setCompanyList(ItemList<CompanyItem> companyList);

    /**
     * Returns the application list
     */ // todo: remove if ultimately not needed
    FilterableItemList<ApplicationItem> getApplicationList();

    /**
     * Returns an unmodifiable view of the filtered application list
     */
    ObservableList<ApplicationItem> getFilteredApplicationList();

    /**
     * Returns the unfiltered application list
     */
    ItemList<ApplicationItem> getUnfilteredApplicationList();

    /**
     * Returns true if an Application with the same identity as {@code applicationItem} exists in the application list.
     */
    boolean hasApplication(ApplicationItem applicationItem);

    /**
     * Deletes the given Application.
     * The Application must exist in the application list.
     */
    void deleteApplication(ApplicationItem target);

    /**
     * Deletes the given Application according to the weaker notion of equality.
     * The Application may not necessarily exist in the application list.
     */
    void deleteSameApplication(ApplicationItem target);

    /**
     * Adds the given Application.
     * {@code applicationItem} must not already exist in the application list.
     */
    void addApplication(ApplicationItem applicationItem);

    /**
     * Replaces the given Application {@code target} with {@code editedApplicationItem}.
     * {@code target} must exist in the application list.
     * The Application identity of {@code editedApplicationItem} must not be the same as another existing Application
     * in the application list.
     */
    void setApplication(ApplicationItem target, ApplicationItem editedApplicationItem);

    /**
     * Updates the filter of the filtered Application list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredApplicationList(Predicate<? super ApplicationItem> predicate);

    /**
     * Replaces application list data with the data in {@code applicationList}.
     */
    void setApplicationList(ItemList<ApplicationItem> applicationList);

    /**
     * Gets the application item list.
     *
     * @return Application item list.
     */
    ObservableList<ApplicationItem> getApplicationItemList();

    /**
     * Returns the profile list
     */ // todo: remove if ultimately not needed
    FilterableItemList<ProfileItem> getProfileList();

    /**
     * Returns an unmodifiable view of the filtered profile list
     */
    ObservableList<ProfileItem> getFilteredProfileList();


    /**
     * Returns the unfiltered profile list
     */
    ItemList<ProfileItem> getUnfilteredProfileList();

    /**
     * Returns the profile item list
     */
    ObservableList<ProfileItem> getProfileItemList();

    /**
     * Returns true if a Profile item with the same identity as {@code profileItem} exists in the profile list.
     */
    boolean hasProfileItem(ProfileItem profileItem);

    /**
     * Deletes the given Profile item.
     * The Profile item must exist in the profile list.
     */
    void deleteProfileItem(ProfileItem target);

    /**
     * Adds the given Profile item.
     * {@code profileItem} must not already exist in the profile list.
     */
    void addProfileItem(ProfileItem profileItem);

    /**
     * Replaces the given Profile item {@code target} with {@code editedProfileItem}.
     * {@code target} must exist in the profile list.
     * The Profile item identity of {@code editedProfileItem} must not be the same as another existing Profile item in
     * the profile list.
     */
    void setProfileItem(ProfileItem target, ProfileItem editedProfileItem);

    /**
     * Updates the filter of the filtered Profile list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredProfileList(Predicate<? super ProfileItem> predicate);

    /**
     * Replaces profile list data with the data in {@code profileList}.
     */
    void setProfileList(ItemList<ProfileItem> profileList);

    /**
     * Replaces the current tab name with {@code tabName}.
     */
    void setTabName(TabName tabName);

    /**
     * Retrieves the current tab name
     */
    TabName getTabName();

    /**
     * Replaces the current company view index with {@code index}.
     */
    void setCompanyViewIndex(Index index);

    /**
     * Replaces the current application view index with {@code index}.
     */
    void setApplicationViewIndex(Index index);

    /**
     * Replaces the current profile view index with {@code index}.
     */
    void setProfileViewIndex(Index index);

    /**
     * Retrieves the current company view Index.
     */
    Index getCompanyViewIndex();

    /**
     * Retrieves the current application view Index.
     */
    Index getApplicationViewIndex();

    /**
     * Retrieves the current profile view Index.
     */
    Index getProfileViewIndex();

    /**
     * Gets ProfileItem from Filtered Profile list.
     */
    ProfileItem getProfileItemFromFilteredList(int index);

    /**
     * Gets CompanyItem from Filtered Company list.
     */
    CompanyItem getCompanyItemFromFilteredList(int index);

    /**
     * Gets ApplicationItem from Filtered Application list.
     */
    ApplicationItem getApplicationItemFromFilteredList(int index);

    /**
     * Gets the size of the filtered company list.
     */
    int getFilteredCompanyListSize();

    /**
     * Gets the size of the filtered application list.
     */
    int getFilteredApplicationListSize();

    /**
     * Gets the size of the filtered profile list.
     */
    int getFilteredProfileListSize();

    /**
     * Gets the size of the unfiltered company list.
     */
    int getUnFilteredCompanyListSize();

    /**
     * Gets the size of the unfiltered application list.
     */
    int getUnFilteredApplicationListSize();

    /**
     * Gets the size of the unfiltered profile list.
     */
    int getUnFilteredProfileListSize();

}
