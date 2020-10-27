package seedu.internhunter.logic.commands.add;

import static java.util.Objects.requireNonNull;
import static seedu.internhunter.commons.core.Messages.MESSAGE_ADD_SUCCESS;
import static seedu.internhunter.commons.core.Messages.MESSAGE_DUPLICATE_ITEM;
import static seedu.internhunter.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.internhunter.logic.commands.util.CommandUtil.getCommandResult;
import static seedu.internhunter.logic.commands.util.CommandUtil.getCompany;
import static seedu.internhunter.logic.parser.clisyntax.ApplicationCliSyntax.PREFIX_STATUS;
import static seedu.internhunter.logic.parser.clisyntax.ApplicationCliSyntax.PREFIX_STATUS_DATE;
import static seedu.internhunter.logic.parser.clisyntax.GeneralCliSyntax.PREFIX_INDEX;
import static seedu.internhunter.model.util.ItemUtil.APPLICATION_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.APPLICATION_NAME;

import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.logic.commands.CommandResult;
import seedu.internhunter.logic.commands.exceptions.CommandException;
import seedu.internhunter.model.Model;
import seedu.internhunter.model.application.ApplicationItem;
import seedu.internhunter.model.application.Status;
import seedu.internhunter.model.application.StatusDate;
import seedu.internhunter.model.company.CompanyItem;
import seedu.internhunter.model.internship.InternshipItem;
import seedu.internhunter.ui.tabs.TabName;

/**
 * Adds an application to the Application list.
 */
public class AddApplicationCommand extends AddCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + APPLICATION_ALIAS
            + ": Adds an " + APPLICATION_NAME + " to "
            + "InternHunter.\nParameters: "
            + "INDEX "
            + PREFIX_INDEX + "INDEX "
            + "[" + PREFIX_STATUS + "STATUS] "
            + "[" + PREFIX_STATUS_DATE + "STATUS_DATE]\n"
            + "Example: " + COMMAND_WORD + " " + APPLICATION_ALIAS + " 1 "
            + PREFIX_INDEX + "2 "
            + PREFIX_STATUS + "waiting "
            + PREFIX_STATUS_DATE + "23-12-20";

    private final Index companyIndex;
    private final Index internshipIndex;
    private final Status status;
    private final StatusDate statusDate;

    /**
     * Creates an AddApplicationCommand to add the specified {@code ApplicationItem}.
     */
    public AddApplicationCommand(Index companyIndex, Index internshipIndex, Status status, StatusDate statusDate) {
        requireAllNonNull(companyIndex, internshipIndex, status, statusDate);
        this.companyIndex = companyIndex;
        this.internshipIndex = internshipIndex;
        this.status = status;
        this.statusDate = statusDate;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display.
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        CompanyItem companyItem = getCompany(model, companyIndex);
        InternshipItem internshipItem = companyItem.getInternship(internshipIndex);
        ApplicationItem applicationToAdd = new ApplicationItem(internshipItem, status, statusDate);

        if (model.hasApplication(applicationToAdd)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_ITEM, APPLICATION_NAME));
        }

        model.addApplication(applicationToAdd);
        setApplicationViewIndex(model);
        String addSuccessMessage = String.format(MESSAGE_ADD_SUCCESS, APPLICATION_NAME, applicationToAdd);
        return getCommandResult(model, addSuccessMessage, TabName.APPLICATION);
    }

    /**
     * Sets the application view index to the newly added application.
     *
     * @param model {@code Model} which the command should operate on.
     */
    private void setApplicationViewIndex(Model model) {
        int size = model.getFilteredApplicationListSize();
        model.setApplicationViewIndex(Index.fromOneBased(size));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddApplicationCommand)) {
            return false;
        }

        AddApplicationCommand otherAddApplicationCommand = (AddApplicationCommand) other;
        return companyIndex.equals(otherAddApplicationCommand.companyIndex)
                && internshipIndex.equals(otherAddApplicationCommand.internshipIndex)
                && status.equals(otherAddApplicationCommand.status)
                && statusDate.equals(otherAddApplicationCommand.statusDate);
    }

}
