package seedu.internhunter.logic.commands.edit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.commons.core.Messages.MESSAGE_EDIT_SUCCESS;
import static seedu.internhunter.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.internhunter.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.internhunter.logic.commands.CommandTestUtil.showApplicationAtIndex;
import static seedu.internhunter.logic.commands.edit.EditApplicationCommand.EditApplicationDescriptor;
import static seedu.internhunter.logic.commands.util.application.ApplicationCommandTestUtil.INVALID_APPLICATION_INDEX_MESSAGE;
import static seedu.internhunter.logic.commands.util.application.SampleEditApplicationDescriptor.DESC_GOLDMAN;
import static seedu.internhunter.logic.commands.util.application.SampleEditApplicationDescriptor.DESC_SHOPEE;
import static seedu.internhunter.model.util.ItemUtil.APPLICATION_NAME;
import static seedu.internhunter.model.util.StatusUtil.APPLIED_KEYWORD;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.internhunter.testutil.application.ApplicationItemFieldsUtil.STATUS_DATE_JUNE_2021;
import static seedu.internhunter.testutil.application.SampleApplicationItems.getSampleApplicationItemList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.logic.commands.ClearCommand;
import seedu.internhunter.logic.commands.util.application.EditApplicationDescriptorBuilder;
import seedu.internhunter.model.Model;
import seedu.internhunter.model.ModelManager;
import seedu.internhunter.model.UserPrefs;
import seedu.internhunter.model.application.ApplicationItem;
import seedu.internhunter.model.item.ItemList;
import seedu.internhunter.testutil.application.ApplicationItemBuilder;
import seedu.internhunter.ui.tabs.TabName;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * EditApplicationCommand.
 */
public class EditApplicationCommandTest {

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
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        ApplicationItem editedApplication = new ApplicationItemBuilder().build();
        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder(editedApplication).build();
        EditApplicationCommand editCommand = new EditApplicationCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_SUCCESS, APPLICATION_NAME, editedApplication);
        expectedModel.setApplication(model.getApplicationItemFromFilteredList(
                INDEX_FIRST.getZeroBased()), editedApplication);
        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_oneFieldSpecifiedUnfilteredList_success() {
        Index indexLastApplication = Index.fromOneBased(model.getFilteredApplicationListSize());
        ApplicationItem lastApplication = model.getApplicationItemFromFilteredList(indexLastApplication.getZeroBased());

        ApplicationItemBuilder applicationInList = new ApplicationItemBuilder(lastApplication);
        ApplicationItem editedApplication = applicationInList.withStatus(APPLIED_KEYWORD).build();

        EditApplicationDescriptor descriptor =
                new EditApplicationDescriptorBuilder().withStatus(APPLIED_KEYWORD).build();
        EditApplicationCommand editCommand = new EditApplicationCommand(indexLastApplication, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_SUCCESS, APPLICATION_NAME, editedApplication);

        expectedModel.setApplication(lastApplication, editedApplication);
        expectedModel.setApplicationViewIndex(indexLastApplication);
        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditApplicationCommand editCommand = new EditApplicationCommand(INDEX_FIRST, new EditApplicationDescriptor());
        ApplicationItem editedApplication = model.getApplicationItemFromFilteredList(INDEX_FIRST.getZeroBased());

        String expectedMessage = String.format(MESSAGE_EDIT_SUCCESS, APPLICATION_NAME, editedApplication);
        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showApplicationAtIndex(model, INDEX_FIRST);
        ApplicationItem applicationInFilteredList =
                model.getApplicationItemFromFilteredList(INDEX_FIRST.getZeroBased());
        ApplicationItem editedApplication =
                new ApplicationItemBuilder(applicationInFilteredList).withStatusDate(STATUS_DATE_JUNE_2021).build();
        EditApplicationCommand editCommand = new EditApplicationCommand(INDEX_FIRST,
                new EditApplicationDescriptorBuilder().withStatusDate(STATUS_DATE_JUNE_2021).build());

        String expectedMessage = String.format(MESSAGE_EDIT_SUCCESS, APPLICATION_NAME, editedApplication);
        expectedModel.setApplication(model.getApplicationItemFromFilteredList(INDEX_FIRST.getZeroBased()),
                editedApplication);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidApplicationIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredApplicationListSize() + 1);
        EditApplicationDescriptor descriptor =
                new EditApplicationDescriptorBuilder().withStatus(APPLIED_KEYWORD).build();
        EditApplicationCommand editCommand = new EditApplicationCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, INVALID_APPLICATION_INDEX_MESSAGE);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of application list
     */
    @Test
    public void execute_invalidApplicationIndexFilteredList_failure() {
        showApplicationAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of application list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getUnFilteredApplicationListSize());

        EditApplicationCommand editCommand = new EditApplicationCommand(outOfBoundIndex,
                new EditApplicationDescriptorBuilder().withStatus(APPLIED_KEYWORD).build());

        assertCommandFailure(editCommand, model, INVALID_APPLICATION_INDEX_MESSAGE);
    }

    @Test
    public void equals_test_success() {

        final EditApplicationCommand standardCommand = new EditApplicationCommand(INDEX_FIRST,
                DESC_GOLDMAN);

        // same values -> returns true
        EditApplicationDescriptor copyDescriptor = new EditApplicationDescriptor(DESC_GOLDMAN);
        EditApplicationCommand commandWithSameValues = new EditApplicationCommand(INDEX_FIRST, copyDescriptor);
        assertEquals(commandWithSameValues, standardCommand);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(standardCommand, null);

        // different types -> returns false
        assertNotEquals(new ClearCommand(), standardCommand);

        // different index -> returns false
        assertNotEquals(new EditApplicationCommand(INDEX_SECOND, DESC_GOLDMAN), standardCommand);

        // different descriptor -> returns false
        assertNotEquals(new EditApplicationCommand(INDEX_FIRST, DESC_SHOPEE), standardCommand);
    }

}
