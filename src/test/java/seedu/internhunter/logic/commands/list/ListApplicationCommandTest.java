package seedu.internhunter.logic.commands.list;


import static seedu.internhunter.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.internhunter.logic.commands.CommandTestUtil.showApplicationAtIndex;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.internhunter.testutil.application.SampleApplicationItems.getSampleApplicationItemList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.logic.commands.CommandResult;
import seedu.internhunter.model.Model;
import seedu.internhunter.model.ModelManager;
import seedu.internhunter.model.UserPrefs;
import seedu.internhunter.model.item.ItemList;
import seedu.internhunter.ui.tabs.TabName;

public class ListApplicationCommandTest {

    private Model model;
    private Model expectedModel;
    private CommandResult commandResult;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new ItemList<>(), new ItemList<>(), getSampleApplicationItemList(),
            new ItemList<>(), new UserPrefs());
        expectedModel = new ModelManager(new ItemList<>(), new ItemList<>(),
            model.getUnfilteredApplicationList(), new ItemList<>(), new UserPrefs());
        commandResult = new CommandResult(ListApplicationCommand.MESSAGE_SUCCESS, false, false, true, true);
    }

    @Test
    public void execute_listApplicationIsNotFiltered_showsSameList() {
        expectedModel.setApplicationViewIndex(Index.fromOneBased(1));
        expectedModel.setTabName(TabName.APPLICATION); // default is Company
        assertCommandSuccess(new ListApplicationCommand(), model, commandResult, expectedModel);
    }

    @Test
    public void execute_listApplicationIsFiltered_showsEverything() {
        showApplicationAtIndex(model, INDEX_FIRST);
        expectedModel.setApplicationViewIndex(Index.fromOneBased(1));
        expectedModel.setTabName(TabName.APPLICATION); // default is Company
        assertCommandSuccess(new ListApplicationCommand(), model, commandResult, expectedModel);
    }
}
