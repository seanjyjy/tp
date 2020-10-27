package seedu.internhunter.logic.commands.view;

import static java.util.Objects.requireNonNull;
import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX;
import static seedu.internhunter.commons.core.Messages.MESSAGE_VIEW_SUCCESS;
import static seedu.internhunter.logic.commands.util.CommandUtil.getCommandResult;
import static seedu.internhunter.model.util.ItemUtil.PROFILE_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.PROFILE_ITEM_NAME;
import static seedu.internhunter.model.util.ItemUtil.PROFILE_NAME;

import java.util.List;

import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.logic.commands.CommandResult;
import seedu.internhunter.logic.commands.exceptions.CommandException;
import seedu.internhunter.model.Model;
import seedu.internhunter.model.profile.ProfileItem;
import seedu.internhunter.ui.tabs.TabName;

public class ViewProfileCommand extends ViewCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " " + PROFILE_ALIAS + ": Views a " + PROFILE_ITEM_NAME + " in "
            + "InternHunter.\nParameters: "
            + "INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " "
            + PROFILE_ALIAS
            + " 5";

    private final String messageViewSuccess;
    private final String messageAlreadyViewing;
    private final Index targetIndex;

    /** todo javadocs */
    public ViewProfileCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        this.messageViewSuccess = String.format(MESSAGE_VIEW_SUCCESS, PROFILE_ITEM_NAME, targetIndex);
        this.messageAlreadyViewing = String.format(MESSAGE_ALREADY_VIEWING, PROFILE_ITEM_NAME, targetIndex);
    }

    /**
     * Executes the view profile command and returns the result message.
     * This command also auto-switches the user to the profile tab.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<ProfileItem> lastShownList = model.getFilteredProfileList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(String.format(MESSAGE_INVALID_ITEM_DISPLAYED_INDEX, PROFILE_NAME));
        }

        if (model.getTabName() == TabName.PROFILE && model.getProfileViewIndex().equals(targetIndex)) {
            return new CommandResult(messageAlreadyViewing, false, false , false, false);
        }
        model.setProfileViewIndex(targetIndex);
        return getCommandResult(model, messageViewSuccess, TabName.PROFILE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewProfileCommand // instanceof handles nulls
                && targetIndex.equals(((ViewProfileCommand) other).targetIndex)); // state check
    }

}
