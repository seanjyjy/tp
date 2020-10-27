package seedu.internhunter.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.model.FilterableItemList.PREDICATE_SHOW_ALL_ITEMS;
import static seedu.internhunter.testutil.Assert.assertThrows;
import static seedu.internhunter.testutil.TypicalPersons.ALICE;
import static seedu.internhunter.testutil.TypicalPersons.BENSON;

import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.internhunter.commons.core.GuiSettings;
import seedu.internhunter.model.application.ApplicationItem;
import seedu.internhunter.model.company.CompanyItem;
import seedu.internhunter.model.internship.InternshipItem;
import seedu.internhunter.model.item.ItemList;
import seedu.internhunter.model.person.NameContainsKeywordsPredicate;
import seedu.internhunter.model.person.Person;
import seedu.internhunter.model.profile.ProfileItem;
import seedu.internhunter.testutil.AddressBookBuilder;
import seedu.internhunter.testutil.application.ApplicationItemBuilder;
import seedu.internhunter.testutil.profile.ProfileItemBuilder;

public class ModelManagerTest {

    private ModelManager modelManager;
    // private CompanyItemBuilder companyItemBuilder;
    private ApplicationItemBuilder applicationItemBuilder;
    private ProfileItemBuilder profileItemBuilder;
    private ObservableList<CompanyItem> companyItemList;
    private ObservableList<ApplicationItem> applicationItemList;
    private ObservableList<ProfileItem> profileItemList;
    private ObservableList<CompanyItem> emptyCompanyItemList;
    private ObservableList<ApplicationItem> emptyApplicationItemList;
    private ObservableList<ProfileItem> emptyProfileItemList;

    @BeforeEach
    public void setUp() {
        modelManager = new ModelManager();

        // companyItemBuilder = new CompanyItemBuilder();
        applicationItemBuilder = new ApplicationItemBuilder();
        profileItemBuilder = new ProfileItemBuilder();

        companyItemList = FXCollections.observableArrayList();
        // companyItemList.add(companyItemBuilder.build());

        applicationItemList = FXCollections.observableArrayList();
        applicationItemList.add(applicationItemBuilder.build());

        profileItemList = FXCollections.observableArrayList();
        profileItemList.add(profileItemBuilder.build());

        emptyProfileItemList = FXCollections.observableArrayList();
        emptyApplicationItemList = FXCollections.observableArrayList();
        emptyCompanyItemList = FXCollections.observableArrayList();
    }

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new ItemListManager<Person>(), modelManager.getAddressBook());
        assertEquals(new ItemListManager<CompanyItem>(), modelManager.getCompanyList());
        assertEquals(new ItemListManager<ApplicationItem>(), modelManager.getApplicationList());
        assertEquals(new ItemListManager<ProfileItem>(), modelManager.getProfileList());
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setApplicationItemListFilePath(Paths.get("app/item/list/file/path"));
        userPrefs.setCompanyItemListFilePath(Paths.get("com/item/list/file/path"));
        userPrefs.setProfileItemListFilePath(Paths.get("app/me/list/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setApplicationItemListFilePath(Paths.get("new/app/item/list/file/path"));
        userPrefs.setCompanyItemListFilePath(Paths.get("new/com/item/list/file/path"));
        userPrefs.setProfileItemListFilePath(Paths.get("new/app/me/list/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void hasCompany_nullCompany_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasCompany(null));
    }

    @Test
    public void hasApplication_nullApplication_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasApplication(null));
    }

    @Test
    public void hasProfile_nullProfile_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasProfileItem(null));
    }

    @Test
    public void hasCompany_companyNotInCompanyList_returnsFalse() {
        // TODO when company item is ready
        // assertFalse(modelManager.hasCompany(companyItemBuilder.build()));
    }

    @Test
    public void hasApplication_applicationNotInApplicationList_returnsFalse() {
        assertFalse(modelManager.hasApplication(applicationItemBuilder.build()));
    }

    @Test
    public void hasProfileItem_profileItemNotInProfileItemList_returnsFalse() {
        assertFalse(modelManager.hasProfileItem(profileItemBuilder.build()));
    }

    @Test
    public void addCompany_addCompanyItemToList_returnsEquals() {
        // TODO when company item is ready
        // modelManager.addCompany(companyItemBuilder.build());
        // assertEquals(modelManager.getFilteredCompanyList(), companyItems);
    }

    @Test
    public void addApplication_addApplicationItemToList_returnsEquals() {
        modelManager.addApplication(applicationItemBuilder.build());
        assertEquals(modelManager.getFilteredApplicationList(), applicationItemList);
    }

    @Test
    public void addProfile_addProfileItemToList_returnsEquals() {
        modelManager.addProfileItem(profileItemBuilder.build());
        assertEquals(modelManager.getFilteredProfileList(), profileItemList);
    }

    @Test
    public void hasCompany_companyInCompanyList_returnsTrue() {
        // TODO when company item is ready
        // modelManager.addCompany(companyItemBuilder.build());
        // assertTrue(modelManager.hasCompany(companyItemBuilder.build()));
    }

    @Test
    public void hasApplication_applicationInApplicationList_returnsTrue() {
        modelManager.addApplication(applicationItemBuilder.build());
        assertTrue(modelManager.hasApplication(applicationItemBuilder.build()));
    }

    @Test
    public void hasProfileItem_profileItemInProfileItemList_returnsTrue() {
        modelManager.addProfileItem(profileItemBuilder.build());
        assertTrue(modelManager.hasProfileItem(profileItemBuilder.build()));
    }

    @Test
    public void getFilteredCompanyList_modifyCompanyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredCompanyList()
            .remove(0));
    }

    @Test
    public void getFilteredApplicationList_modifyApplicationList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredApplicationList()
            .remove(0));
    }

    @Test
    public void getFilteredProfileList_modifyProfileList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredProfileList()
            .remove(0));
    }

    @Test
    public void getFilteredCompanyListSize_getCompanyListSize_returnsEqual() {
        // TODO when company item is ready
        // modelManager.addCompany(companyItemBuilder.build());
        // assertEquals(modelManager.getFilteredCompanyListSize(), 1);
    }

    @Test
    public void getFilteredApplicationListSize_getApplicationListSize_returnsEqual() {
        modelManager.addApplication(applicationItemBuilder.build());
        assertEquals(modelManager.getFilteredApplicationListSize(), 1);
    }

    @Test
    public void getFilteredProfileListSize_getProfileListSize_returnsEqual() {
        modelManager.addProfileItem(profileItemBuilder.build());
        assertEquals(modelManager.getFilteredProfileListSize(), 1);
    }

    @Test
    public void getCompanyList_testIfEqualCompanyList_returnsEqual() {
        // TODO when company item is ready
        // modelManager.addCompany(companyItemBuilder.build());
        // assertEquals(modelManager.getCompanyList(), companyItems);
    }

    @Test
    public void getProfileList_testIfEqualProfileList_returnsEqual() {
        modelManager.addProfileItem(profileItemBuilder.build());
        assertEquals(modelManager.getProfileItemList(), profileItemList);
    }

    @Test
    public void deleteCompany_deleteCompanyFromList_returnsEqual() {
        // TODO when company item is ready
        // modelManager.addCompany(companyItemBuilder.build());
        // modelManager.deleteCompany(companyItemBuilder.build());
        // assertEquals(modelManager.getFilteredCompanyList(), emptyCompanyItemList);
    }

    @Test
    public void deleteApplication_deleteApplicationFromList_returnsEqual() {
        modelManager.addApplication(applicationItemBuilder.build());
        modelManager.deleteApplication(applicationItemBuilder.build());
        assertEquals(modelManager.getFilteredApplicationList(), emptyApplicationItemList);
    }

    @Test
    public void deleteProfileItem_deleteProfileItemFromList_returnsEqual() {
        modelManager.addProfileItem(profileItemBuilder.build());
        modelManager.deleteProfileItem(profileItemBuilder.build());
        assertEquals(modelManager.getFilteredProfileList(), emptyProfileItemList);
    }

    @Test
    public void setCompany_changeCompanyItem_returnsEqual() {
        // TODO when company item is ready
        // modelManager.addCompany(companyItemList.build());
        // CompanyItemBuilder secondCompanyItemBuilder = new CompanyItemBuilder();
        // secondCompanyItemBuilder.
        // modelManager.setCompany(companyItemList.build(), secondCompanyItemBuilder.build());
        // emptyCompanyItemList.add(secondCompanyItemBuilder.build());
        // assertEquals(modelManager.getFilteredCompanyList(), emptyCompanyItemList);
    }

    @Test
    public void setApplication_changeApplicationItem_returnsEqual() {
        modelManager.addApplication(applicationItemBuilder.build());
        ApplicationItemBuilder secondApplicationItemBuilder = new ApplicationItemBuilder();
        secondApplicationItemBuilder.withStatus("Interview");
        modelManager.setApplication(applicationItemBuilder.build(), secondApplicationItemBuilder.build());
        emptyApplicationItemList.add(secondApplicationItemBuilder.build());
        assertEquals(modelManager.getFilteredApplicationList(), emptyApplicationItemList);
    }

    @Test
    public void setProfile_changeProfileItem_returnsEqual() {
        modelManager.addProfileItem(profileItemBuilder.build());
        ProfileItemBuilder secondProfileItemBuilder = new ProfileItemBuilder();
        secondProfileItemBuilder.withTitle("HELLO WORLD");
        modelManager.setProfileItem(profileItemBuilder.build(), secondProfileItemBuilder.build());
        emptyProfileItemList.add(secondProfileItemBuilder.build());
        assertEquals(modelManager.getFilteredProfileList(), emptyProfileItemList);
    }

    @Test
    public void equals() {
        ItemList<Person> addressBook = new AddressBookBuilder().withPerson(ALICE).withPerson(BENSON).build();
        ItemList<Person> differentAddressBook = new ItemList<>();
        ItemList<CompanyItem> companyList = new ItemList<>();
        ItemList<InternshipItem> internshipList = new ItemList<>();
        ItemList<ApplicationItem> applicationList = new ItemList<>();
        ItemList<ProfileItem> profileList = new ItemList<>();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(addressBook, companyList, applicationList,
                profileList,
                userPrefs);
        ModelManager modelManagerCopy = new ModelManager(addressBook, companyList,
                applicationList, profileList, userPrefs);
        assertEquals(modelManagerCopy, modelManager);

        // same object -> returns true
        assertEquals(modelManager, modelManager);

        // null -> returns false
        assertNotEquals(modelManager, null);

        // different types -> returns false
        assertNotEquals(modelManager, 5);

        // different addressBook -> returns false
        assertNotEquals(new ModelManager(differentAddressBook, companyList,
                applicationList, profileList, userPrefs), modelManager);

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.getAddressBook()
                .updateFilteredItemList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertNotEquals(new ModelManager(addressBook, companyList,
                applicationList, profileList, userPrefs), modelManager);

        // resets modelManager to initial state for upcoming tests
        modelManager.getAddressBook().updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setApplicationItemListFilePath(Paths.get("differentFilePath"));
        assertNotEquals(new ModelManager(addressBook, companyList,
                applicationList, profileList, differentUserPrefs), modelManager);
    }
}
