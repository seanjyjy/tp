package seedu.internhunter.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX;
import static seedu.internhunter.commons.core.Messages.MESSAGE_SAME_SCREEN;
import static seedu.internhunter.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.internhunter.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.internhunter.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.internhunter.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.internhunter.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.internhunter.model.util.ItemUtil.APPLICATION_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.APPLICATION_NAME;
import static seedu.internhunter.model.util.ItemUtil.COMPANY_ALIAS;
import static seedu.internhunter.testutil.Assert.assertThrows;
import static seedu.internhunter.testutil.TypicalPersons.AMY;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.logic.commands.CommandResult;
import seedu.internhunter.logic.commands.SwitchCommand;
import seedu.internhunter.logic.commands.add.AddCommand;
import seedu.internhunter.logic.commands.delete.DeleteCommand;
import seedu.internhunter.logic.commands.exceptions.CommandException;
import seedu.internhunter.logic.parser.exceptions.ParseException;
import seedu.internhunter.model.Model;
import seedu.internhunter.model.ModelManager;
import seedu.internhunter.model.UserPrefs;
import seedu.internhunter.model.application.ApplicationItem;
import seedu.internhunter.model.company.CompanyItem;
import seedu.internhunter.model.item.ItemList;
import seedu.internhunter.model.item.ReadOnlyItemList;
import seedu.internhunter.model.person.Person;
import seedu.internhunter.model.profile.ProfileItem;
import seedu.internhunter.storage.JsonItemListStorage;
import seedu.internhunter.storage.JsonUserPrefsStorage;
import seedu.internhunter.storage.StorageManager;
import seedu.internhunter.storage.application.JsonAdaptedApplicationItem;
import seedu.internhunter.storage.company.JsonAdaptedCompanyItem;
import seedu.internhunter.storage.profile.JsonAdaptedProfileItem;
import seedu.internhunter.testutil.PersonBuilder;
import seedu.internhunter.ui.tabs.TabName;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private final Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonItemListStorage<ApplicationItem, JsonAdaptedApplicationItem> applicationItemListStorage =
                new JsonItemListStorage<>(temporaryFolder.resolve("applicationitemlist.json"),
                        ApplicationItem.class, JsonAdaptedApplicationItem.class);
        JsonItemListStorage<CompanyItem, JsonAdaptedCompanyItem> companyItemListStorage =
                new JsonItemListStorage<>(temporaryFolder.resolve("companyitemlist.json"),
                        CompanyItem.class, JsonAdaptedCompanyItem.class);
        JsonItemListStorage<ProfileItem, JsonAdaptedProfileItem> profileItemListStorage =
                new JsonItemListStorage<>(temporaryFolder.resolve("profileitemlist.json"),
                        ProfileItem.class, JsonAdaptedProfileItem.class);
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(applicationItemListStorage, companyItemListStorage,
                profileItemListStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = DeleteCommand.COMMAND_WORD + " " + APPLICATION_ALIAS + " 9";
        assertCommandException(deleteCommand, String.format(MESSAGE_INVALID_ITEM_DISPLAYED_INDEX, APPLICATION_NAME));
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String switchCommand = SwitchCommand.COMMAND_WORD + " " + COMPANY_ALIAS;
        assertCommandSuccess(switchCommand, String.format(MESSAGE_SAME_SCREEN, TabName.COMPANY), model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonItemListIoExceptionThrowingStub
        JsonItemListStorage<ApplicationItem, JsonAdaptedApplicationItem> applicationItemListStorage =
                new JsonItemListStorage<>(temporaryFolder.resolve("applicationitemlist.json"),
                        ApplicationItem.class, JsonAdaptedApplicationItem.class);
        JsonItemListStorage<CompanyItem, JsonAdaptedCompanyItem> companyItemListStorage =
                new JsonItemListStorage<>(temporaryFolder.resolve("companyitemlist.json"),
                        CompanyItem.class, JsonAdaptedCompanyItem.class);
        JsonItemListStorage<ProfileItem, JsonAdaptedProfileItem> profileItemListStorage =
                new JsonItemListStorage<>(temporaryFolder.resolve("profileitemlist.json"),
                        ProfileItem.class, JsonAdaptedProfileItem.class);
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(applicationItemListStorage, companyItemListStorage,
                profileItemListStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + " int " + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY;
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.getAddressBook().addItem(expectedPerson);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        // Todo: Update testcase for expected model
        //        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredApplicationItemList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredApplicationItemList().remove(0));
    }

    @Test
    public void getFilteredProfileItemList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredProfileItemList().remove(0));
    }

    @Test
    public void getFilteredCompanyItemList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredCompanyItemList().remove(0));
    }

    @Test
    public void getProfileViewIndex_equals_success() {
        assertEquals(logic.getProfileViewIndex(), Index.fromOneBased(1));
    }

    @Test
    public void getCompanyViewIndex_equals_success() {
        assertEquals(logic.getCompanyViewIndex(), Index.fromOneBased(1));
    }

    @Test
    public void getApplicationViewIndex_equals_success() {
        assertEquals(logic.getApplicationViewIndex(), Index.fromOneBased(1));
    }

    @Test
    public void setProfileViewIndex_equals_success() {
        logic.setProfileViewIndex(Index.fromOneBased(10));
        assertEquals(logic.getProfileViewIndex(), Index.fromOneBased(10));
    }

    @Test
    public void setCompanyViewIndex_equals_success() {
        logic.setCompanyViewIndex(Index.fromOneBased(10));
        assertEquals(logic.getCompanyViewIndex(), Index.fromOneBased(10));
    }

    @Test
    public void setApplicationViewIndex_equals_success() {
        logic.setApplicationViewIndex(Index.fromOneBased(10));
        assertEquals(logic.getApplicationViewIndex(), Index.fromOneBased(10));
    }

    @Test
    public void getTabName_equals_success() {
        assertEquals(logic.getTabName(), TabName.COMPANY);
    }

    @Test
    public void setTabName_changeTabNameToCompanyTestEquals_success() {
        logic.setTabName(TabName.COMPANY);
        assertEquals(logic.getTabName(), TabName.COMPANY);
    }

    @Test
    public void setTabName_changeTabNameToApplicationTestEquals_success() {
        logic.setTabName(TabName.APPLICATION);
        assertEquals(logic.getTabName(), TabName.APPLICATION);
    }

    @Test
    public void setTabName_changeTabNameToProfileTestEquals_success() {
        logic.setTabName(TabName.PROFILE);
        assertEquals(logic.getTabName(), TabName.PROFILE);
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage,
            Model expectedModel) throws CommandException, ParseException {
        CommandResult result = logic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    /**
     * Executes the command, confirms that a ParseException is thrown and that the result message is correct.
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage) {
        Model expectedModel = new ModelManager(model.getAddressBook().getUnfilteredItemList(), new ItemList<>(),
                new ItemList<>(), new ItemList<>(), new UserPrefs());
        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     *
     * @see #assertCommandSuccess(String, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage, Model expectedModel) {
        assertThrows(expectedException, expectedMessage, () -> logic.execute(inputCommand));
        assertEquals(expectedModel, model);
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonItemListIoExceptionThrowingStub extends
            JsonItemListStorage<ApplicationItem, JsonAdaptedApplicationItem> {
        private JsonItemListIoExceptionThrowingStub(Path filePath) {
            super(filePath, ApplicationItem.class, JsonAdaptedApplicationItem.class);
        }

        @Override
        public void saveItemList(ReadOnlyItemList<ApplicationItem> addressBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
