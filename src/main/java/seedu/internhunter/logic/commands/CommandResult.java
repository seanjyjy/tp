package seedu.internhunter.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.internhunter.model.internship.InternshipItem;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean isShowHelp;

    /** The application should exit. */
    private final boolean isExit;

    /** The application should switch tab. */
    private final boolean isSwitchTab;

    /** The application should switch display. */
    private final boolean isSwitchDisplay;

    /** List of matching internships generated based on user's profile skills */
    private ObservableList<InternshipItem> matchingInternships = FXCollections.observableArrayList();

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean isShowHelp, boolean isExit, boolean isSwitchTab,
            boolean isSwitchDisplay) {

        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.isShowHelp = isShowHelp;
        this.isExit = isExit;
        this.isSwitchTab = isSwitchTab;
        this.isSwitchDisplay = isSwitchDisplay;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, true);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return isShowHelp;
    }

    public boolean isExit() {
        return isExit;
    }

    public boolean isSwitchTab() {
        return isSwitchTab;
    }

    public boolean isSwitchDisplay() {
        return isSwitchDisplay;
    }

    public boolean isShowMatchingInternships() {
        return !matchingInternships.isEmpty();
    }

    public void setMatchingInternships(ObservableList<InternshipItem> listOfInternships) {
        matchingInternships = listOfInternships;
    }

    public ObservableList<InternshipItem> getMatchingInternships() {
        return matchingInternships;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && isShowHelp == otherCommandResult.isShowHelp
                && isExit == otherCommandResult.isExit
                && isSwitchTab == otherCommandResult.isSwitchTab
                && isSwitchDisplay == otherCommandResult.isSwitchDisplay;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, isShowHelp, isExit, isSwitchTab, isSwitchDisplay);
    }

}
