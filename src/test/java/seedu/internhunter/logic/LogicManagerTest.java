package seedu.internhunter.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX;
import static seedu.internhunter.commons.core.Messages.MESSAGE_SAME_SCREEN;
import static seedu.internhunter.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.internhunter.model.util.ItemUtil.APPLICATION_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.APPLICATION_NAME;
import static seedu.internhunter.model.util.ItemUtil.COMPANY_ALIAS;
import static seedu.internhunter.testutil.Assert.assertThrows;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.internhunter.commons.core.GuiSettings;
import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.logic.commands.CommandResult;
import seedu.internhunter.logic.commands.SwitchCommand;
import seedu.internhunter.logic.commands.delete.DeleteCommand;
import seedu.internhunter.logic.commands.exceptions.CommandException;
import seedu.internhunter.logic.parser.exceptions.ParseException;
import seedu.internhunter.model.Model;
import seedu.internhunter.model.ModelManager;
import seedu.internhunter.model.UserPrefs;
import seedu.internhunter.model.application.ApplicationItem;
import seedu.internhunter.model.company.CompanyItem;
import seedu.internhunter.model.profile.ProfileItem;
import seedu.internhunter.storage.JsonItemListStorage;
import seedu.internhunter.storage.JsonUserPrefsStorage;
import seedu.internhunter.storage.StorageManager;
import seedu.internhunter.storage.application.JsonAdaptedApplicationItem;
import seedu.internhunter.storage.company.JsonAdaptedCompanyItem;
import seedu.internhunter.storage.profile.JsonAdaptedProfileItem;
import seedu.internhunter.ui.tabs.TabName;

public class LogicManagerTest {

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
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> logic.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        logic.setGuiSettings(guiSettings);
        assertEquals(guiSettings, logic.getGuiSettings());
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
        assertEquals(Index.fromOneBased(1), logic.getProfileViewIndex());
    }

    @Test
    public void getCompanyViewIndex_equals_success() {
        assertEquals(Index.fromOneBased(1), logic.getCompanyViewIndex());
    }

    @Test
    public void getApplicationViewIndex_equals_success() {
        assertEquals(Index.fromOneBased(1), logic.getApplicationViewIndex());
    }

    @Test
    public void setProfileViewIndex_equals_success() {
        logic.setProfileViewIndex(Index.fromOneBased(10));
        assertEquals(Index.fromOneBased(10), logic.getProfileViewIndex());
    }

    @Test
    public void setCompanyViewIndex_equals_success() {
        logic.setCompanyViewIndex(Index.fromOneBased(10));
        assertEquals(Index.fromOneBased(10), logic.getCompanyViewIndex());
    }

    @Test
    public void setApplicationViewIndex_equals_success() {
        logic.setApplicationViewIndex(Index.fromOneBased(10));
        assertEquals(Index.fromOneBased(10), logic.getApplicationViewIndex());
    }

    @Test
    public void getTabName_equals_success() {
        assertEquals(TabName.COMPANY, logic.getTabName());
    }

    @Test
    public void setTabName_changeTabNameToCompanyTestEquals_success() {
        logic.setTabName(TabName.COMPANY);
        assertEquals(TabName.COMPANY, logic.getTabName());
    }

    @Test
    public void setTabName_changeTabNameToApplicationTestEquals_success() {
        logic.setTabName(TabName.APPLICATION);
        assertEquals(TabName.APPLICATION, logic.getTabName());
    }

    @Test
    public void setTabName_changeTabNameToProfileTestEquals_success() {
        logic.setTabName(TabName.PROFILE);
        assertEquals(TabName.PROFILE, logic.getTabName());
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
        Model expectedModel = new ModelManager(model.getUnfilteredCompanyList(), model.getUnfilteredApplicationList(),
            model.getUnfilteredProfileList(), new UserPrefs());
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

}
