package seedu.internhunter.logic.commands.delete;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.commons.core.Messages.MESSAGE_DELETED_ITEM;
import static seedu.internhunter.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.internhunter.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.internhunter.logic.commands.CommandTestUtil.showApplicationAtIndex;
import static seedu.internhunter.logic.commands.util.application.ApplicationCommandTestUtil.INVALID_APPLICATION_INDEX_MESSAGE;
import static seedu.internhunter.model.util.ItemUtil.APPLICATION_NAME;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.internhunter.testutil.application.SampleApplicationItems.getSampleApplicationItemList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.model.Model;
import seedu.internhunter.model.ModelManager;
import seedu.internhunter.model.UserPrefs;
import seedu.internhunter.model.application.ApplicationItem;
import seedu.internhunter.model.item.ItemList;
import seedu.internhunter.ui.tabs.TabName;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteApplicationCommand}.
 */
public class DeleteApplicationCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new ItemList<>(), new ItemList<>(), getSampleApplicationItemList(),
                new ItemList<>(), new UserPrefs());
        expectedModel = new ModelManager(new ItemList<>(), new ItemList<>(),
                model.getUnfilteredApplicationList(), new ItemList<>(), new UserPrefs());
        model.setTabName(TabName.APPLICATION);
        expectedModel.setTabName(TabName.APPLICATION);
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        ApplicationItem applicationToDelete = model.getApplicationItemFromFilteredList(INDEX_FIRST.getZeroBased());
        DeleteApplicationCommand deleteCommand = new DeleteApplicationCommand(INDEX_FIRST);

        String deleteSuccessMessage = String.format(MESSAGE_DELETED_ITEM, APPLICATION_NAME, applicationToDelete);

        expectedModel.deleteApplication(applicationToDelete);
        assertCommandSuccess(deleteCommand, model, deleteSuccessMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredApplicationListSize() + 1);
        DeleteApplicationCommand deleteCommand = new DeleteApplicationCommand(outOfBoundIndex);
        assertCommandFailure(deleteCommand, model, INVALID_APPLICATION_INDEX_MESSAGE);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showApplicationAtIndex(model, INDEX_FIRST);
        ApplicationItem applicationToDelete = model.getApplicationItemFromFilteredList(INDEX_FIRST.getZeroBased());
        DeleteApplicationCommand deleteCommand = new DeleteApplicationCommand(INDEX_FIRST);

        String deleteSuccessMessage = String.format(MESSAGE_DELETED_ITEM, APPLICATION_NAME, applicationToDelete);

        expectedModel.deleteApplication(applicationToDelete);
        showNoApplication(expectedModel);

        assertCommandSuccess(deleteCommand, model, deleteSuccessMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showApplicationAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of application list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getUnFilteredApplicationListSize());

        DeleteApplicationCommand deleteCommand = new DeleteApplicationCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, INVALID_APPLICATION_INDEX_MESSAGE);
    }

    @Test
    public void equals_test_success() {
        DeleteApplicationCommand deleteFirstCommand = new DeleteApplicationCommand(INDEX_FIRST);
        DeleteApplicationCommand deleteSecondCommand = new DeleteApplicationCommand(INDEX_SECOND);

        // same object -> returns true
        assertEquals(deleteFirstCommand, deleteFirstCommand);

        // same values -> returns true
        DeleteApplicationCommand deleteFirstCommandCopy = new DeleteApplicationCommand(INDEX_FIRST);
        assertEquals(deleteFirstCommandCopy, deleteFirstCommand);

        // different types -> returns false
        assertNotEquals(deleteFirstCommand, new DeleteProfileCommand(INDEX_FIRST));

        // null -> returns false
        assertNotEquals(deleteFirstCommand, null);

        // different index -> returns false
        assertNotEquals(deleteSecondCommand, deleteFirstCommand);
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoApplication(Model model) {
        model.updateFilteredApplicationList(p -> false);
        assertEquals(model.getFilteredApplicationListSize(), 0);
    }

}
