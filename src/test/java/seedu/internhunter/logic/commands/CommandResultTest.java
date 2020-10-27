package seedu.internhunter.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandResultTest {

    private static final String FEEDBACK = "feedback";
    private static final String DIFFERENT = "different";

    private CommandResult commandResult;
    private CommandResult commandResultWithDefaultValue;
    private CommandResult commandResultDifferentValue;
    private CommandResult commandResultIsShowHelpTrue;
    private CommandResult commandResultIsExitTrue;
    private CommandResult commandResultIsSwitchTabTrue;
    private CommandResult commandResultIsSwitchDisplayFalse;

    @BeforeEach
    public void setUp() {
        commandResult = new CommandResult(FEEDBACK);
        commandResultWithDefaultValue = new CommandResult(FEEDBACK, false, false, false, true);
        commandResultDifferentValue = new CommandResult(DIFFERENT);
        commandResultIsShowHelpTrue = new CommandResult(FEEDBACK, true, false, false, false);
        commandResultIsExitTrue = new CommandResult(FEEDBACK, false, true, false, false);
        commandResultIsSwitchTabTrue = new CommandResult(FEEDBACK, false, false, true, false);
        commandResultIsSwitchDisplayFalse = new CommandResult(FEEDBACK, false, false, false, false);
    }

    @Test
    public void equals() {

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult(FEEDBACK)));
        assertTrue(commandResult.equals(commandResultWithDefaultValue));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(commandResultDifferentValue));

        // different isShowHelp value -> returns false
        assertFalse(commandResult.equals(commandResultIsShowHelpTrue));

        // different isExit value -> returns false
        assertFalse(commandResult.equals(commandResultIsExitTrue));

        // different isShowTab value -> returns false
        assertFalse(commandResult.equals(commandResultIsSwitchTabTrue));

        // different isSwitchDisplay value -> returns false
        assertFalse(commandResult.equals(commandResultIsSwitchDisplayFalse));
    }

    @Test
    public void hashcode() {
        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult(FEEDBACK).hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), commandResultDifferentValue.hashCode());

        // different isShowHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), commandResultIsShowHelpTrue.hashCode());

        // different isExit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), commandResultIsExitTrue.hashCode());

        // different isSwitchTab value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), commandResultIsSwitchTabTrue.hashCode());

        // different isSwitchDisplay value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), commandResultIsSwitchDisplayFalse.hashCode());
    }

    @Test
    public void isShowHelp_false_success() {
        // default values
        assertFalse(commandResult.isShowHelp());

        // default values inputted
        assertFalse(commandResultWithDefaultValue.isShowHelp());

        // different isExit value
        assertFalse(commandResultIsExitTrue.isShowHelp());

        // different isSwitchTab value
        assertFalse(commandResultIsSwitchTabTrue.isShowHelp());

        // different isSwitchDisplay value
        assertFalse(commandResultIsSwitchDisplayFalse.isShowHelp());
    }

    @Test
    public void isShowHelp_true_success() {
        assertTrue(commandResultIsShowHelpTrue.isShowHelp());
    }

    @Test
    public void isShowExit_false_success() {
        // default values
        assertFalse(commandResult.isExit());

        // default values inputted
        assertFalse(commandResultWithDefaultValue.isExit());

        // different isShowHelp value
        assertFalse(commandResultIsShowHelpTrue.isExit());

        // different isSwitchTab value
        assertFalse(commandResultIsSwitchTabTrue.isExit());

        // different isSwitchDisplay value
        assertFalse(commandResultIsSwitchDisplayFalse.isExit());
    }

    @Test
    public void isShowExit_true_success() {
        assertTrue(commandResultIsExitTrue.isExit());
    }

    @Test
    public void isSwitchTab_false_success() {
        // default values
        assertFalse(commandResult.isSwitchTab());

        // default values inputted
        assertFalse(commandResultWithDefaultValue.isSwitchTab());

        // different isShowHelp value
        assertFalse(commandResultIsShowHelpTrue.isSwitchTab());

        // different isExit value
        assertFalse(commandResultIsExitTrue.isSwitchTab());

        // different isSwitchDisplay value
        assertFalse(commandResultIsSwitchDisplayFalse.isSwitchTab());
    }

    @Test
    public void isSwitchTab_true_success() {
        assertTrue(commandResultIsSwitchTabTrue.isSwitchTab());
    }

    @Test
    public void isSwitchDisplay_false_success() {
        // different isShowHelp value
        assertFalse(commandResultIsShowHelpTrue.isSwitchDisplay());

        // different isExit value
        assertFalse(commandResultIsExitTrue.isSwitchDisplay());

        // different isSwitchTab value
        assertFalse(commandResultIsSwitchTabTrue.isSwitchDisplay());

        // different isSwitchDisplay value
        assertFalse(commandResultIsSwitchDisplayFalse.isSwitchDisplay());
    }

    @Test
    public void isSwitchDisplay_true_success() {
        // default values
        assertTrue(commandResult.isSwitchDisplay());

        // default values inputted
        assertTrue(commandResultWithDefaultValue.isSwitchDisplay());
    }
}
