package seedu.internhunter.logic.commands.find;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.commons.core.Messages.MESSAGE_FIND_SUCCESS;
import static seedu.internhunter.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.internhunter.model.util.ItemUtil.PROFILE_NAME;
import static seedu.internhunter.testutil.profile.SampleProfileItems.GOVTECH_EXPERIENCE;
import static seedu.internhunter.testutil.profile.SampleProfileItems.getSampleProfileItemList;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.logic.commands.CommandResult;
import seedu.internhunter.model.Model;
import seedu.internhunter.model.ModelManager;
import seedu.internhunter.model.UserPrefs;
import seedu.internhunter.model.item.ItemList;
import seedu.internhunter.model.profile.ProfileItemContainsKeywordPredicate;
import seedu.internhunter.ui.tabs.TabName;

class FindProfileCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new ItemList<>(), new ItemList<>(), new ItemList<>() ,
            getSampleProfileItemList(), new UserPrefs());
        expectedModel = new ModelManager(new ItemList<>(), new ItemList<>(), new ItemList<>(),
            model.getUnfilteredProfileList(), new UserPrefs());
    }

    @Test
    public void equals() {
        ProfileItemContainsKeywordPredicate firstPredicate =
                new ProfileItemContainsKeywordPredicate(Collections.singletonList("first"));
        ProfileItemContainsKeywordPredicate secondPredicate =
                new ProfileItemContainsKeywordPredicate(Collections.singletonList("second"));

        FindProfileCommand findProfileFirstCommand = new FindProfileCommand(firstPredicate);
        FindProfileCommand findProfileSecondCommand = new FindProfileCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findProfileFirstCommand.equals(findProfileFirstCommand));
        assertTrue(findProfileSecondCommand.equals(findProfileSecondCommand));

        // same values -> returns true
        FindProfileCommand findProfileFirstCommandCopy = new FindProfileCommand(firstPredicate);
        assertTrue(findProfileFirstCommand.equals(findProfileFirstCommandCopy));
        FindProfileCommand findProfileSecondCommandCopy = new FindProfileCommand(secondPredicate);
        assertTrue(findProfileSecondCommand.equals(findProfileSecondCommandCopy));

        // different types -> returns false
        assertFalse(findProfileFirstCommand.equals(1));
        assertFalse(findProfileSecondCommand.equals(1));

        // null -> returns false
        assertFalse(findProfileFirstCommand.equals(null));
        assertFalse(findProfileSecondCommand.equals(null));

        // different person -> returns false
        assertFalse(findProfileFirstCommand.equals(findProfileSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noApplicationFound() {
        String expectedMessage = String.format(MESSAGE_FIND_SUCCESS, 0, PROFILE_NAME);
        CommandResult commandResult = new CommandResult(expectedMessage, false, false, true, true);
        ProfileItemContainsKeywordPredicate predicate = preparePredicate(" ");
        FindProfileCommand command = new FindProfileCommand(predicate);

        // update for expected model
        expectedModel.updateFilteredProfileList(predicate);
        expectedModel.setProfileViewIndex(Index.fromOneBased(1));
        expectedModel.setTabName(TabName.PROFILE); // default is Company

        // test
        assertCommandSuccess(command, model, commandResult, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredProfileList());
    }

    @Test
    public void execute_multipleKeywords_multipleApplicationFound() {
        String expectedMessage = String.format(MESSAGE_FIND_SUCCESS, 1, PROFILE_NAME);
        CommandResult commandResult = new CommandResult(expectedMessage, false, false, true, true);
        ProfileItemContainsKeywordPredicate predicate = preparePredicate("Internship");
        FindProfileCommand command = new FindProfileCommand(predicate);

        // update for expected model
        expectedModel.updateFilteredProfileList(predicate);
        expectedModel.setProfileViewIndex(Index.fromOneBased(1));
        expectedModel.setTabName(TabName.PROFILE); // default is Company

        // test
        assertCommandSuccess(command, model, commandResult, expectedModel);
        assertEquals(Arrays.asList(GOVTECH_EXPERIENCE), model.getFilteredProfileList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private ProfileItemContainsKeywordPredicate preparePredicate(String userInput) {
        return new ProfileItemContainsKeywordPredicate(Arrays.asList(userInput.split("\\s+")));
    }

}
