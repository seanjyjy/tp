package seedu.internhunter.logic.commands.delete;

import static java.util.Objects.requireNonNull;
import static seedu.internhunter.commons.core.Messages.MESSAGE_DELETED_ITEM;
import static seedu.internhunter.logic.commands.util.CommandUtil.getCommandResult;
import static seedu.internhunter.logic.commands.util.CommandUtil.getCompany;
import static seedu.internhunter.model.util.ItemUtil.COMPANY_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.COMPANY_NAME;

import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.logic.commands.CommandResult;
import seedu.internhunter.logic.commands.exceptions.CommandException;
import seedu.internhunter.model.Model;
import seedu.internhunter.model.company.CompanyItem;
import seedu.internhunter.ui.tabs.TabName;

/**
 * Deletes a Company from the Model's Company list.
 */
public class DeleteCompanyCommand extends DeleteCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + COMPANY_ALIAS
            + ": Deletes a "
            + COMPANY_NAME
            + " from InternHunter by the index number used in the displayed list.\n"
            + "Parameters: INDEX\n"
            + "Note: INDEX must be a positive integer.\n"
            + "Example: "
            + COMMAND_WORD + " " + COMPANY_ALIAS + " 1\n";

    private final Index targetIndex;

    /**
     * Creates a DeleteCompanyCommand to delete the {@code CompanyItem} specified by the given index.
     *
     * @param targetIndex Target index of the company to be deleted in the company list.
     */
    public DeleteCompanyCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Executes the DeleteCompanyCommand and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return Feedback message of the operation result for display.
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        TabName currentTabName = model.getTabName();
        CompanyItem companyToDelete = getCompany(model, targetIndex);

        // Delete all internships in the company
        deleteAllInternshipsInCompany(model, companyToDelete);

        // Delete the company
        model.deleteCompany(companyToDelete);

        String deleteSuccessMessage = String.format(MESSAGE_DELETED_ITEM, COMPANY_NAME, companyToDelete);
        return getCommandResult(model, deleteSuccessMessage, currentTabName, TabName.COMPANY, targetIndex);
    }

    /**
     * Deletes all internships in the company's internship list.
     *
     * @param model {@code Model} which the command should operate on.
     * @param company {@code Company} from which the internships should be deleted.
     * @throws CommandException If an error occurs during command execution.
     */
    private void deleteAllInternshipsInCompany(Model model, CompanyItem company) throws CommandException {
        int numberOfInternships = company.getNumberOfInternships();
        for (int i = 0; i < numberOfInternships; i++) {
            new DeleteInternshipCommand(targetIndex, Index.fromZeroBased(0)).execute(model);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCompanyCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCompanyCommand) other).targetIndex)); // state check
    }

}
