package seedu.address.logic.commands.find;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_FIND_SUCCESS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.util.ItemUtil.APPLICATION_NAME;
import static seedu.address.testutil.application.SampleApplicationItems.SHOPEE_OFFERED;
import static seedu.address.testutil.application.SampleApplicationItems.getSampleApplicationItemList;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.application.ApplicationNameContainsKeyWordsPredicate;
import seedu.address.model.item.ItemList;
import seedu.address.ui.tabs.TabName;

/**
 * todo javadocs
 */
public class FindApplicationCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new ItemList<>(), new ItemList<>(), getSampleApplicationItemList(),
            new ItemList<>(), new UserPrefs());
        expectedModel = new ModelManager(new ItemList<>(), new ItemList<>(),
            model.getApplicationList().getUnfilteredItemList(), new ItemList<>(), new UserPrefs());
    }

    @Test
    public void equals() {
        ApplicationNameContainsKeyWordsPredicate firstPredicate =
            new ApplicationNameContainsKeyWordsPredicate(Collections.singletonList("first"));
        ApplicationNameContainsKeyWordsPredicate secondPredicate =
            new ApplicationNameContainsKeyWordsPredicate(Collections.singletonList("second"));

        FindApplicationCommand findApplicationFirstCommand = new FindApplicationCommand(firstPredicate);
        FindApplicationCommand findApplicationSecondCommand = new FindApplicationCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findApplicationFirstCommand.equals(findApplicationFirstCommand));
        assertTrue(findApplicationSecondCommand.equals(findApplicationSecondCommand));

        // same values -> returns true
        FindApplicationCommand findApplicationFirstCommandCopy = new FindApplicationCommand(firstPredicate);
        assertTrue(findApplicationFirstCommand.equals(findApplicationFirstCommandCopy));
        FindApplicationCommand findApplicationSecondCommandCopy = new FindApplicationCommand(secondPredicate);
        assertTrue(findApplicationFirstCommand.equals(findApplicationSecondCommandCopy));

        // different types -> returns false
        assertFalse(findApplicationFirstCommand.equals(1));
        assertFalse(findApplicationSecondCommand.equals(1));

        // null -> returns false
        assertFalse(findApplicationFirstCommand.equals(null));
        assertFalse(findApplicationSecondCommand.equals(null));

        // different person -> returns false
        assertFalse(findApplicationFirstCommand.equals(findApplicationSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noApplicationFound() {
        String expectedMessage = String.format(MESSAGE_FIND_SUCCESS, 0, APPLICATION_NAME);
        CommandResult commandResult = new CommandResult(expectedMessage, false, false, true, true);
        ApplicationNameContainsKeyWordsPredicate predicate = preparePredicate(" ");
        FindApplicationCommand command = new FindApplicationCommand(predicate);

        // update for expected model
        expectedModel.getApplicationList().updateFilteredItemList(predicate);
        expectedModel.setApplicationViewIndex(Index.fromOneBased(1));
        expectedModel.setTabName(TabName.APPLICATION); // default is Company

        // test
        assertCommandSuccess(command, model, commandResult, expectedModel);
        assertEquals(Collections.emptyList(), model.getApplicationList().getFilteredItemList());
    }

    @Test
    public void execute_multipleKeywords_multipleApplicationFound() {
        String expectedMessage = String.format(MESSAGE_FIND_SUCCESS, 1, APPLICATION_NAME);
        CommandResult commandResult = new CommandResult(expectedMessage, false, false, true, true);
        ApplicationNameContainsKeyWordsPredicate predicate = preparePredicate("Software");
        FindApplicationCommand command = new FindApplicationCommand(predicate);

        // update for expected model
        expectedModel.getApplicationList().updateFilteredItemList(predicate);
        expectedModel.setApplicationViewIndex(Index.fromOneBased(1));
        expectedModel.setTabName(TabName.APPLICATION); // default is Company

        // test
        assertCommandSuccess(command, model, commandResult, expectedModel);
        assertEquals(Arrays.asList(SHOPEE_OFFERED), model.getApplicationList().getFilteredItemList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private ApplicationNameContainsKeyWordsPredicate preparePredicate(String userInput) {
        return new ApplicationNameContainsKeyWordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
