package seedu.internhunter.logic.commands.delete;

import static java.util.Objects.requireNonNull;
import static seedu.internhunter.commons.core.Messages.MESSAGE_DELETED_ITEM;
import static seedu.internhunter.logic.commands.util.CommandUtil.getApplication;
import static seedu.internhunter.logic.commands.util.CommandUtil.getCommandResult;
import static seedu.internhunter.model.util.ItemUtil.APPLICATION_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.APPLICATION_NAME;

import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.logic.commands.CommandResult;
import seedu.internhunter.logic.commands.exceptions.CommandException;
import seedu.internhunter.model.Model;
import seedu.internhunter.model.application.ApplicationItem;
import seedu.internhunter.ui.tabs.TabName;

/**
 * Deletes the application from the Application list.
 */
public class DeleteApplicationCommand extends DeleteCommandAbstract {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + APPLICATION_ALIAS
            + ": Deletes a "
            + APPLICATION_NAME
            + " from InternHunter by the index number used in the displayed list.\n"
            + "Parameters: INDEX\n"
            + "Note: INDEX must be a positive integer.\n"
            + "Example: "
            + COMMAND_WORD + " " + APPLICATION_ALIAS + " 1\n";

    private final Index targetIndex;

    public DeleteApplicationCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        TabName currentTabName = model.getTabName();
        ApplicationItem applicationToDelete = getApplication(model, targetIndex);
        model.deleteApplication(applicationToDelete);
        String deleteSuccessMessage = String.format(MESSAGE_DELETED_ITEM, APPLICATION_NAME, applicationToDelete);
        return getCommandResult(model, deleteSuccessMessage, currentTabName, TabName.APPLICATION, targetIndex);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteApplicationCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteApplicationCommand) other).targetIndex)); // state check
    }

}
