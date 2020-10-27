package seedu.internhunter.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.internhunter.model.Model;
import seedu.internhunter.model.ModelManager;
import seedu.internhunter.ui.tabs.TabName;

public class SwitchCommandTest {

    // Default screen is Internship.
    public static final String SAME_SCREEN_TEXT = "Already in %s tab";
    public static final String SWITCHED_SCREEN_TEXT = "Switching to %s tab";

    private Model model;
    private Model expectedModel;

    @BeforeEach
    void setUp() {
        model = new ModelManager();
        expectedModel = new ModelManager();
    }

    @Test
    public void execute_companySwitchToProfileTab_success() {
        String message = String.format(SWITCHED_SCREEN_TEXT, TabName.PROFILE.toString());
        CommandResult expectedCommandResult = new CommandResult(message, false, false, true, true);
        expectedModel.setTabName(TabName.PROFILE);
        assertCommandSuccess(new SwitchCommand(TabName.PROFILE), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_companySwitchToCompanyTab_success() {
        String message = String.format(SAME_SCREEN_TEXT, TabName.COMPANY);
        CommandResult expectedCommandResult = new CommandResult(message, false, false, false, true);
        assertCommandSuccess(new SwitchCommand(TabName.COMPANY), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_companySwitchToApplicationTab_success() {
        String message = String.format(SWITCHED_SCREEN_TEXT, TabName.APPLICATION.toString());
        CommandResult expectedCommandResult = new CommandResult(message, false, false, true, true);
        expectedModel.setTabName(TabName.APPLICATION);
        assertCommandSuccess(new SwitchCommand(TabName.APPLICATION), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_applicationSwitchToCompanyTab_success() {
        String message = String.format(SWITCHED_SCREEN_TEXT, TabName.COMPANY.toString());
        CommandResult expectedCommandResult = new CommandResult(message, false, false, true, true);
        model.setTabName(TabName.APPLICATION);
        expectedModel.setTabName(TabName.COMPANY);
        assertCommandSuccess(new SwitchCommand(TabName.COMPANY), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_applicationSwitchToApplicationTab_success() {
        String message = String.format(SAME_SCREEN_TEXT, TabName.APPLICATION);
        CommandResult expectedCommandResult = new CommandResult(message, false, false, false, true);
        model.setTabName(TabName.APPLICATION);
        expectedModel.setTabName(TabName.APPLICATION);
        assertCommandSuccess(new SwitchCommand(TabName.APPLICATION), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_applicationSwitchToProfileTab_success() {
        String message = String.format(SWITCHED_SCREEN_TEXT, TabName.PROFILE.toString());
        CommandResult expectedCommandResult = new CommandResult(message, false, false, true, true);
        model.setTabName(TabName.APPLICATION);
        expectedModel.setTabName(TabName.PROFILE);
        assertCommandSuccess(new SwitchCommand(TabName.PROFILE), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_profileSwitchToCompanyTab_success() {
        String message = String.format(SWITCHED_SCREEN_TEXT, TabName.COMPANY.toString());
        CommandResult expectedCommandResult = new CommandResult(message, false, false, true, true);
        model.setTabName(TabName.PROFILE);
        expectedModel.setTabName(TabName.COMPANY);
        assertCommandSuccess(new SwitchCommand(TabName.COMPANY), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_profileSwitchToApplicationTab_success() {
        String message = String.format(SWITCHED_SCREEN_TEXT, TabName.APPLICATION);
        CommandResult expectedCommandResult = new CommandResult(message, false, false, true, true);
        model.setTabName(TabName.PROFILE);
        expectedModel.setTabName(TabName.APPLICATION);
        assertCommandSuccess(new SwitchCommand(TabName.APPLICATION), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_profileSwitchToProfileTab_success() {
        String message = String.format(SAME_SCREEN_TEXT, TabName.PROFILE.toString());
        CommandResult expectedCommandResult = new CommandResult(message, false, false, false, true);
        model.setTabName(TabName.PROFILE);
        expectedModel.setTabName(TabName.PROFILE);
        assertCommandSuccess(new SwitchCommand(TabName.PROFILE), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void equals() {
        SwitchCommand switchCommand = new SwitchCommand(TabName.COMPANY);
        // same values -> returns true
        assertTrue(switchCommand.equals(new SwitchCommand(TabName.COMPANY)));

        // same object -> returns true
        assertTrue(switchCommand.equals(switchCommand));

        // null -> returns false
        assertFalse(switchCommand.equals(null));

        // different types -> returns false
        assertFalse(switchCommand.equals(0.5f));

        // different values (Profile tab) -> returns false
        assertFalse(switchCommand.equals(new SwitchCommand(TabName.PROFILE)));

        // different values (Application tab) -> returns false
        assertFalse(switchCommand.equals(new SwitchCommand(TabName.APPLICATION)));
    }
}
