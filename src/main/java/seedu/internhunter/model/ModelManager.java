package seedu.internhunter.model;

import static java.util.Objects.requireNonNull;
import static seedu.internhunter.commons.util.CollectionUtil.requireAllNonNull;

import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.internhunter.commons.core.GuiSettings;
import seedu.internhunter.commons.core.LogsCenter;
import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.model.application.ApplicationItem;
import seedu.internhunter.model.company.CompanyItem;
import seedu.internhunter.model.item.ItemList;
import seedu.internhunter.model.item.ReadOnlyItemList;
import seedu.internhunter.model.person.Person;
import seedu.internhunter.model.profile.ProfileItem;
import seedu.internhunter.model.tab.Tab;
import seedu.internhunter.model.tab.TabManager;
import seedu.internhunter.model.view.View;
import seedu.internhunter.model.view.ViewManager;
import seedu.internhunter.ui.tabs.TabName;

/**
 * Represents the in-memory model of the InternHunter app data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final FilterableItemList<Person> addressBook;
    private final FilterableItemList<CompanyItem> companyList;
    private final FilterableItemList<ApplicationItem> applicationList;
    private final FilterableItemList<ProfileItem> profileList;
    private final UserPrefs userPrefs;
    private final Tab tabControl;
    private final View viewControl;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(
            ReadOnlyItemList<Person> addressBook,
            ReadOnlyItemList<CompanyItem> companyList,
            ReadOnlyItemList<ApplicationItem> applicationList,
            ReadOnlyItemList<ProfileItem> profileList,
            ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, companyList, applicationList, profileList, userPrefs);

        logger.fine("Initializing with address book: " + addressBook
                + " and company list " + companyList
                + " and application list " + applicationList
                + " and profile list " + profileList
                + " and user prefs " + userPrefs);

        this.addressBook = new ItemListManager<>(new ItemList<>(addressBook));
        this.companyList = new ItemListManager<>(new ItemList<>(companyList));
        this.applicationList = new ItemListManager<>(new ItemList<>(applicationList));
        this.profileList = new ItemListManager<>(new ItemList<>(profileList));
        this.userPrefs = new UserPrefs(userPrefs);
        this.tabControl = new TabManager();
        this.viewControl = new ViewManager();
    }

    public ModelManager() {
        this(new ItemList<>(), new ItemList<>(), new ItemList<>(), new ItemList<>(), new UserPrefs());
    }

    //=========== Models Getters =============================================================================

    @Override
    public FilterableItemList<Person> getAddressBook() {
        return addressBook;
    }

    //=========== Company Methods ============================================================================

    @Override
    public FilterableItemList<CompanyItem> getCompanyList() {
        return companyList;
    }

    @Override
    public ObservableList<CompanyItem> getFilteredCompanyList() {
        return companyList.getFilteredItemList();
    }

    @Override
    public int getFilteredCompanyListSize() {
        return companyList.getSize();
    }

    @Override
    public ItemList<CompanyItem> getUnfilteredCompanyList() {
        return companyList.getUnfilteredItemList();
    }

    /**
     * Returns the company item list
     */
    @Override
    public ObservableList<CompanyItem> getCompanyItemList() {
        return companyList.getItemList();
    }

    @Override
    public boolean hasCompany(CompanyItem companyItem) {
        return companyList.hasItem(companyItem);
    }

    @Override
    public void deleteCompany(CompanyItem target) {
        companyList.deleteItem(target);
    }

    @Override
    public void addCompany(CompanyItem companyItem) {
        companyList.addItem(companyItem);
    }

    @Override
    public void setCompany(CompanyItem target, CompanyItem editedCompanyItem) {
        companyList.setItem(target, editedCompanyItem);
    }

    @Override
    public void updateFilteredCompanyList(Predicate<? super CompanyItem> predicate) {
        companyList.updateFilteredItemList(predicate);
    }

    @Override
    public void setCompanyList(ItemList<CompanyItem> companyList) {
        this.companyList.setItemList(companyList);
    }

    /**
     * Gets CompanyItem from Filtered Company list.
     *
     * @param index of item in filtered company list.
     */
    @Override
    public CompanyItem getCompanyItemFromFilteredList(int index) {
        return companyList.getItemFromFilteredItemList(index);
    }

    //=========== Application Methods ========================================================================

    @Override
    public FilterableItemList<ApplicationItem> getApplicationList() {
        return applicationList;
    }

    @Override
    public ObservableList<ApplicationItem> getFilteredApplicationList() {
        return applicationList.getFilteredItemList();
    }

    @Override
    public int getFilteredApplicationListSize() {
        return applicationList.getSize();
    }

    @Override
    public ItemList<ApplicationItem> getUnfilteredApplicationList() {
        return applicationList.getUnfilteredItemList();
    }

    @Override
    public boolean hasApplication(ApplicationItem applicationItem) {
        return applicationList.hasItem(applicationItem);
    }

    @Override
    public void deleteApplication(ApplicationItem target) {
        applicationList.deleteItem(target);
    }

    @Override
    public void deleteSameApplication(ApplicationItem target) {
        applicationList.deleteSameItem(target);
    }

    @Override
    public void addApplication(ApplicationItem applicationItem) {
        applicationList.addItem(applicationItem);
    }

    @Override
    public void setApplication(ApplicationItem target, ApplicationItem editedApplicationItem) {
        applicationList.setItem(target, editedApplicationItem);
    }

    @Override
    public void updateFilteredApplicationList(Predicate<? super ApplicationItem> predicate) {
        applicationList.updateFilteredItemList(predicate);
    }

    @Override
    public void setApplicationList(ItemList<ApplicationItem> applicationList) {
        this.applicationList.setItemList(applicationList);
    }

    /**
     * Returns the profile item list
     */
    @Override
    public ObservableList<ApplicationItem> getApplicationItemList() {
        return applicationList.getItemList();
    }

    /**
     * Gets ApplicationItem from Filtered Application list.
     *
     * @param index of item in filtered application list.
     */
    @Override
    public ApplicationItem getApplicationItemFromFilteredList(int index) {
        return applicationList.getItemFromFilteredItemList(index);
    }

    //=========== Profile Methods ============================================================================

    @Override
    public FilterableItemList<ProfileItem> getProfileList() {
        return profileList;
    }

    @Override
    public ObservableList<ProfileItem> getFilteredProfileList() {
        return profileList.getFilteredItemList();
    }

    @Override
    public int getFilteredProfileListSize() {
        return profileList.getSize();
    }

    /**
     * Gets the size of the unfiltered company list.
     */
    @Override
    public int getUnFilteredCompanyListSize() {
        return getCompanyItemList().size();
    }

    /**
     * Gets the size of the unfiltered application list.
     */
    @Override
    public int getUnFilteredApplicationListSize() {
        return getApplicationItemList().size();
    }

    /**
     * Gets the size of the unfiltered profile list.
     */
    @Override
    public int getUnFilteredProfileListSize() {
        return getProfileItemList().size();
    }

    @Override
    public ItemList<ProfileItem> getUnfilteredProfileList() {
        return profileList.getUnfilteredItemList();
    }

    /**
     * Returns the profile item list
     */
    @Override
    public ObservableList<ProfileItem> getProfileItemList() {
        return profileList.getItemList();
    }

    @Override
    public boolean hasProfileItem(ProfileItem profileItem) {
        return profileList.hasItem(profileItem);
    }

    @Override
    public void deleteProfileItem(ProfileItem target) {
        profileList.deleteItem(target);
    }

    @Override
    public void addProfileItem(ProfileItem profileItem) {
        profileList.addItem(profileItem);
    }

    @Override
    public void setProfileItem(ProfileItem target, ProfileItem editedProfileItem) {
        profileList.setItem(target, editedProfileItem);
    }

    @Override
    public void updateFilteredProfileList(Predicate<? super ProfileItem> predicate) {
        profileList.updateFilteredItemList(predicate);
    }

    @Override
    public void setProfileList(ItemList<ProfileItem> profileList) {
        this.profileList.setItemList(profileList);
    }

    /**
     * Gets ProfileItem from Filtered Profile list.
     *
     * @param index of item in filtered profile list.
     */
    @Override
    public ProfileItem getProfileItemFromFilteredList(int index) {
        return profileList.getItemFromFilteredItemList(index);
    }
    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }


    //=========== Tab Control Accessors =============================================================

    /**
     * Retrieves the current tab name.
     */
    @Override
    public TabName getTabName() {
        return tabControl.getTabName();
    }

    /**
     * Sets the current tab name with {@code tabName}.
     */
    @Override
    public void setTabName(TabName tabName) {
        tabControl.setTabName(tabName);
    }

    //=========== View Control Accessors =============================================================

    /**
     * Retrieves the current index of company view.
     */
    @Override
    public Index getCompanyViewIndex() {
        return viewControl.getCompanyViewIndex();
    }

    /**
     * Retrieves the current index of application view.
     */
    @Override
    public Index getApplicationViewIndex() {
        return viewControl.getApplicationViewIndex();
    }

    /**
     * Retrieves the current index of profile view.
     */
    @Override
    public Index getProfileViewIndex() {
        return viewControl.getProfileViewIndex();
    }

    /**
     * Sets the current company view index with {@code index}.
     */
    @Override
    public void setCompanyViewIndex(Index index) {
        viewControl.setCompanyViewIndex(index);
    }

    /**
     * Sets the current application view index with {@code index}.
     */
    @Override
    public void setApplicationViewIndex(Index index) {
        viewControl.setApplicationViewIndex(index);
    }

    /**
     * Sets the current profile view index with {@code index}.
     */
    @Override
    public void setProfileViewIndex(Index index) {
        viewControl.setProfileViewIndex(index);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && companyList.equals(other.companyList)
                && applicationList.equals(other.applicationList)
                && profileList.equals(other.profileList)
                && userPrefs.equals(other.userPrefs)
                && tabControl.equals(other.tabControl)
                && viewControl.equals(other.viewControl);
    }
}
