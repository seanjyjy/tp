package seedu.internhunter.logic.commands.view;

import static java.util.Objects.requireNonNull;
import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX;
import static seedu.internhunter.commons.core.Messages.MESSAGE_VIEW_SUCCESS;
import static seedu.internhunter.logic.commands.util.CommandUtil.getCommandResult;
import static seedu.internhunter.model.util.ItemUtil.COMPANY_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.COMPANY_NAME;

import java.util.List;

import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.logic.commands.CommandResult;
import seedu.internhunter.logic.commands.exceptions.CommandException;
import seedu.internhunter.model.Model;
import seedu.internhunter.model.company.CompanyItem;
import seedu.internhunter.ui.tabs.TabName;

public class ViewCompanyCommand extends ViewCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " " + COMPANY_ALIAS + ": Views a " + COMPANY_NAME + " in "
            + "InternHunter.\nParameters: "
            + "INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " "
            + COMPANY_ALIAS
            + " 3";

    private final String messageViewSuccess;
    private final String messageAlreadyViewing;
    private final Index targetIndex;

    /** todo javadocs (shawn) */
    public ViewCompanyCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        this.messageViewSuccess = String.format(MESSAGE_VIEW_SUCCESS, COMPANY_NAME, targetIndex);
        this.messageAlreadyViewing = String.format(MESSAGE_ALREADY_VIEWING, COMPANY_NAME, targetIndex);
    }

    /**
     * Executes the view company command and returns the result message.
     * This command also auto-switches the user to the company tab.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<CompanyItem> lastShownList = model.getFilteredCompanyList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(String.format(MESSAGE_INVALID_ITEM_DISPLAYED_INDEX, COMPANY_NAME));
        }

        if (model.getTabName() == TabName.COMPANY && model.getCompanyViewIndex().equals(targetIndex)) {
            return new CommandResult(messageAlreadyViewing, false, false , false, false);
        }
        model.setCompanyViewIndex(targetIndex);
        return getCommandResult(model, messageViewSuccess, TabName.COMPANY);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCompanyCommand // instanceof handles nulls
                && targetIndex.equals(((ViewCompanyCommand) other).targetIndex)); // state check
    }

}
