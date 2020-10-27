package seedu.internhunter.logic.commands.list;

import static seedu.internhunter.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.internhunter.logic.commands.CommandTestUtil.showProfileItemAtIndex;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.internhunter.testutil.profile.SampleProfileItems.getSampleProfileItemList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.logic.commands.CommandResult;
import seedu.internhunter.model.Model;
import seedu.internhunter.model.ModelManager;
import seedu.internhunter.model.UserPrefs;
import seedu.internhunter.model.item.ItemList;
import seedu.internhunter.ui.tabs.TabName;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListProfileCommand.
 */
class ListProfileCommandTest {

    private Model model;
    private Model expectedModel;
    private CommandResult commandResult;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new ItemList<>(), new ItemList<>(), new ItemList<>(), getSampleProfileItemList(),
                new UserPrefs());
        expectedModel = new ModelManager(new ItemList<>(), new ItemList<>(), new ItemList<>(),
                model.getUnfilteredProfileList(), new UserPrefs());
        commandResult = new CommandResult(ListProfileCommand.MESSAGE_SUCCESS, false, false, true, true);
    }

    @Test
    public void execute_listProfileIsNotFiltered_showsSameList() {
        expectedModel.setProfileViewIndex(Index.fromOneBased(1));
        expectedModel.setTabName(TabName.PROFILE); // default is Company
        assertCommandSuccess(new ListProfileCommand(), model, commandResult, expectedModel);
    }

    @Test
    public void execute_listProfileIsFiltered_showsEverything() {
        showProfileItemAtIndex(model, INDEX_FIRST);
        expectedModel.setProfileViewIndex(Index.fromOneBased(1));
        expectedModel.setTabName(TabName.PROFILE); // default is Company
        assertCommandSuccess(new ListProfileCommand(), model, commandResult, expectedModel);
    }
}
