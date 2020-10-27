package seedu.internhunter.logic.commands;

import static seedu.internhunter.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.internhunter.testutil.application.SampleApplicationItems.getSampleApplicationItemList;

import org.junit.jupiter.api.Test;

import seedu.internhunter.model.Model;
import seedu.internhunter.model.ModelManager;
import seedu.internhunter.model.UserPrefs;
import seedu.internhunter.model.item.ItemList;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(new ItemList<>(), new ItemList<>(), getSampleApplicationItemList(),
                new ItemList<>(), new UserPrefs());
        Model expectedModel = new ModelManager(new ItemList<>(), new ItemList<>(), getSampleApplicationItemList(),
                new ItemList<>(), new UserPrefs());
        expectedModel.setApplicationList(new ItemList<>());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
