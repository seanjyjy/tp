package seedu.internhunter.logic.commands.delete;

import static seedu.internhunter.testutil.application.SampleApplicationItems.getSampleApplicationItemList;

import org.junit.jupiter.api.BeforeEach;

import seedu.internhunter.model.Model;
import seedu.internhunter.model.ModelManager;
import seedu.internhunter.model.UserPrefs;
import seedu.internhunter.model.item.ItemList;
import seedu.internhunter.ui.tabs.TabName;

/**
 * Contains integration tests (interaction with the Model and ApplicationItemList) and unit tests for
 * {@code DeleteInternshipCommand}.
 */
public class DeleteInternshipCommandTest {

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

}
