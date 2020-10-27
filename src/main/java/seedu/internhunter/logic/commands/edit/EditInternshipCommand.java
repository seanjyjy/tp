package seedu.internhunter.logic.commands.edit;

import static java.util.Objects.requireNonNull;
import static seedu.internhunter.commons.core.Messages.MESSAGE_EDIT_SUCCESS;
import static seedu.internhunter.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.internhunter.logic.commands.util.CommandUtil.getCommandResult;
import static seedu.internhunter.logic.commands.util.CommandUtil.getCompany;
import static seedu.internhunter.logic.parser.clisyntax.GeneralCliSyntax.PREFIX_INDEX;
import static seedu.internhunter.logic.parser.clisyntax.InternshipCliSyntax.PREFIX_JOB_TITLE;
import static seedu.internhunter.logic.parser.clisyntax.InternshipCliSyntax.PREFIX_PERIOD;
import static seedu.internhunter.logic.parser.clisyntax.InternshipCliSyntax.PREFIX_REQUIREMENT;
import static seedu.internhunter.logic.parser.clisyntax.InternshipCliSyntax.PREFIX_WAGE;
import static seedu.internhunter.model.FilterableItemList.PREDICATE_SHOW_ALL_ITEMS;
import static seedu.internhunter.model.util.ItemUtil.COMPANY_NAME;
import static seedu.internhunter.model.util.ItemUtil.INTERNSHIP_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.INTERNSHIP_NAME;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.internhunter.commons.core.Messages;
import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.commons.util.CollectionUtil;
import seedu.internhunter.logic.commands.CommandResult;
import seedu.internhunter.logic.commands.exceptions.CommandException;
import seedu.internhunter.model.Model;
import seedu.internhunter.model.company.CompanyItem;
import seedu.internhunter.model.company.CompanyName;
import seedu.internhunter.model.internship.InternshipItem;
import seedu.internhunter.model.internship.JobTitle;
import seedu.internhunter.model.internship.Period;
import seedu.internhunter.model.internship.Requirement;
import seedu.internhunter.model.internship.Wage;
import seedu.internhunter.ui.tabs.TabName;

/** todo javadocs (shawn) */
public class EditInternshipCommand extends EditCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + INTERNSHIP_ALIAS
            + ": Edits the " + INTERNSHIP_NAME + " identified by the index number used in the displayed "
            + INTERNSHIP_NAME + " list in a " + COMPANY_NAME + ".\n"
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX " + PREFIX_INDEX + "INDEX "
            + "[" + PREFIX_JOB_TITLE + "JOB_TITLE] "
            + "[" + PREFIX_WAGE + "WAGE] "
            + "[" + PREFIX_PERIOD + "PERIOD] "
            + "[" + PREFIX_REQUIREMENT + "REQUIREMENT]...\n"
            + "Note: Select a " + COMPANY_NAME + " with the first INDEX and an " + INTERNSHIP_NAME + " within that "
            + COMPANY_NAME + " with the second INDEX. "
            + "At least one of the optional fields must be provided.\n"
            + "Example: " + COMMAND_WORD + " " + INTERNSHIP_ALIAS + " 5 " + PREFIX_INDEX + "2 "
            + PREFIX_JOB_TITLE + "Web Developer "
            + PREFIX_REQUIREMENT + "HTML "
            + PREFIX_REQUIREMENT + "CSS "
            + PREFIX_REQUIREMENT + "JS ";

    private final Index companyIndex;
    private final Index internshipIndex;
    private final EditInternshipDescriptor editInternshipDescriptor;

    /** todo javadocs (shawn) */
    public EditInternshipCommand(Index companyIndex, Index internshipIndex,
            EditInternshipDescriptor editInternshipDescriptor) {
        requireAllNonNull(companyIndex, internshipIndex, editInternshipDescriptor);

        this.companyIndex = companyIndex;
        this.internshipIndex = internshipIndex;
        this.editInternshipDescriptor = new EditInternshipDescriptor(editInternshipDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        CompanyItem companyItem = getCompany(model, companyIndex);
        InternshipItem internshipToEdit = companyItem.getInternship(internshipIndex);
        InternshipItem editedInternship = createEditedInternship(internshipToEdit, editInternshipDescriptor);

        if (!internshipToEdit.isSameItem(editedInternship) && companyItem.getInternships().stream()
                .anyMatch(editedInternship::isSameItem)) {
            throw new CommandException(String.format(Messages.MESSAGE_DUPLICATE_ITEM, INTERNSHIP_NAME));
        }

        editInternship(internshipToEdit, editedInternship);
        model.updateFilteredCompanyList(PREDICATE_SHOW_ALL_ITEMS);
        model.setCompanyViewIndex(companyIndex);
        String editSuccessMessage = String.format(MESSAGE_EDIT_SUCCESS, INTERNSHIP_NAME, editedInternship);
        return getCommandResult(model, editSuccessMessage, TabName.COMPANY);
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static InternshipItem createEditedInternship(InternshipItem internshipToEdit,
            EditInternshipDescriptor editInternshipDescriptor) {
        assert internshipToEdit != null;

        CompanyName companyName = internshipToEdit.getCompanyName();
        JobTitle updatedJobTitle = editInternshipDescriptor.getJobTitle().orElse(internshipToEdit.getJobTitle());
        Wage updatedWage = editInternshipDescriptor.getWage().orElse(internshipToEdit.getWage());
        Period updatedPeriod = editInternshipDescriptor.getPeriod().orElse(internshipToEdit.getPeriod());
        Set<Requirement> updatedRequirements = editInternshipDescriptor.getRequirements()
                .orElse(internshipToEdit.getRequirements());

        return new InternshipItem(companyName, updatedJobTitle, updatedPeriod, updatedWage, updatedRequirements);
    }

    private static void editInternship(InternshipItem internshipToEdit, InternshipItem editedInternship) {
        internshipToEdit.setJobTitle(editedInternship.getJobTitle());
        internshipToEdit.setWage(editedInternship.getWage());
        internshipToEdit.setPeriod(editedInternship.getPeriod());
        internshipToEdit.setRequirements(editedInternship.getRequirements());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditInternshipCommand)) {
            return false;
        }

        // state check
        EditInternshipCommand e = (EditInternshipCommand) other;
        return companyIndex.equals(e.companyIndex)
                && internshipIndex.equals(e.internshipIndex)
                && editInternshipDescriptor.equals(e.editInternshipDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditInternshipDescriptor {
        private JobTitle jobTitle;
        private Wage wage;
        private Period period;
        private Set<Requirement> requirements;

        public EditInternshipDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditInternshipDescriptor(EditInternshipDescriptor toCopy) {
            setJobTitle(toCopy.jobTitle);
            setWage(toCopy.wage);
            setPeriod(toCopy.period);
            setRequirements(toCopy.requirements);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(jobTitle, wage, period, requirements);
        }

        public void setJobTitle(JobTitle jobTitle) {
            this.jobTitle = jobTitle;
        }

        public Optional<JobTitle> getJobTitle() {
            return Optional.ofNullable(jobTitle);
        }

        public void setWage(Wage wage) {
            this.wage = wage;
        }

        public Optional<Wage> getWage() {
            return Optional.ofNullable(wage);
        }

        public void setPeriod(Period period) {
            this.period = period;
        }

        public Optional<Period> getPeriod() {
            return Optional.ofNullable(period);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setRequirements(Set<Requirement> requirements) {
            this.requirements = (requirements != null) ? new HashSet<>(requirements) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Requirement>> getRequirements() {
            return (requirements != null) ? Optional.of(Collections.unmodifiableSet(requirements)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditInternshipDescriptor)) {
                return false;
            }

            // state check
            EditInternshipDescriptor e = (EditInternshipDescriptor) other;

            return getJobTitle().equals(e.getJobTitle())
                    && getWage().equals(e.getWage())
                    && getPeriod().equals(e.getPeriod())
                    && getRequirements().equals(e.getRequirements());
        }
    }
}
