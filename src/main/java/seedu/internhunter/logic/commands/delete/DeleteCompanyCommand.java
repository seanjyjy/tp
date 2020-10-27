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
 * Deletes a Company from the Model's Company list. todo javadocs (shawn)
 */
public class DeleteCompanyCommand extends DeleteCommandAbstract {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + COMPANY_ALIAS
            + ": Deletes a "
            + COMPANY_NAME
            + " from InternHunter by the index number used in the displayed list.\n"
            + "Parameters: INDEX\n"
            + "Note: INDEX must be a positive integer.\n"
            + "Example: "
            + COMMAND_WORD + " " + COMPANY_ALIAS + " 1\n";

    private final Index targetIndex;

    public DeleteCompanyCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

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
