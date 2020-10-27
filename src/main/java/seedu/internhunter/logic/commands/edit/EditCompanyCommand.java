package seedu.internhunter.logic.commands.edit;

import static java.util.Objects.requireNonNull;
import static seedu.internhunter.commons.core.Messages.MESSAGE_EDIT_SUCCESS;
import static seedu.internhunter.logic.commands.util.CommandUtil.getCommandResult;
import static seedu.internhunter.logic.commands.util.CommandUtil.getCompany;
import static seedu.internhunter.logic.parser.clisyntax.CompanyCliSyntax.PREFIX_ADDRESS;
import static seedu.internhunter.logic.parser.clisyntax.CompanyCliSyntax.PREFIX_COMPANY_NAME;
import static seedu.internhunter.logic.parser.clisyntax.CompanyCliSyntax.PREFIX_EMAIL;
import static seedu.internhunter.logic.parser.clisyntax.CompanyCliSyntax.PREFIX_INDUSTRY;
import static seedu.internhunter.logic.parser.clisyntax.CompanyCliSyntax.PREFIX_PHONE;
import static seedu.internhunter.model.util.ItemUtil.COMPANY_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.COMPANY_NAME;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.internhunter.commons.core.Messages;
import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.commons.util.CollectionUtil;
import seedu.internhunter.logic.commands.CommandResult;
import seedu.internhunter.logic.commands.exceptions.CommandException;
import seedu.internhunter.model.Model;
import seedu.internhunter.model.company.Address;
import seedu.internhunter.model.company.CompanyItem;
import seedu.internhunter.model.company.CompanyName;
import seedu.internhunter.model.company.Email;
import seedu.internhunter.model.company.Industry;
import seedu.internhunter.model.company.Phone;
import seedu.internhunter.model.internship.InternshipItem;
import seedu.internhunter.ui.tabs.TabName;

/** todo javadocs (shawn) */
public class EditCompanyCommand extends EditCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + COMPANY_ALIAS
            + ": Edits the details of a " + COMPANY_NAME + " from InternHunter accessed "
            + "by the index number used in the displayed list.\n"
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX "
            + "[" + PREFIX_COMPANY_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_INDUSTRY + "INDUSTRY_TYPE]...\n"
            + "Note: At least one of the optional fields must be provided. INDEX must be a positive integer.\n"
            + "Example: " + COMMAND_WORD + " " + COMPANY_ALIAS + " 5 "
            + PREFIX_COMPANY_NAME + "Amazon Inc "
            + PREFIX_PHONE + "61234567 "
            + PREFIX_INDUSTRY + "Commerce "
            + PREFIX_INDUSTRY + "Cloud Computing";

    private final Index index;
    private final EditCompanyDescriptor editCompanyDescriptor;

    /** todo javadocs (shawn)
     * @param index of the person in the filtered person list to edit
     * @param editCompanyDescriptor details to edit the person with
     */
    public EditCompanyCommand(Index index, EditCompanyDescriptor editCompanyDescriptor) {
        requireNonNull(index);
        requireNonNull(editCompanyDescriptor);

        this.index = index;
        this.editCompanyDescriptor = new EditCompanyDescriptor(editCompanyDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        CompanyItem companyToEdit = getCompany(model, index);
        CompanyItem editedCompany = createEditedCompany(companyToEdit, editCompanyDescriptor);

        if (!companyToEdit.isSameItem(editedCompany) && model.hasCompany(editedCompany)) {
            throw new CommandException(String.format(Messages.MESSAGE_DUPLICATE_ITEM, COMPANY_NAME));
        }

        model.setCompany(companyToEdit, editedCompany);
        model.setCompanyViewIndex(index);
        String editSuccessMessage = String.format(MESSAGE_EDIT_SUCCESS, COMPANY_NAME, editedCompany);
        return getCommandResult(model, editSuccessMessage, TabName.COMPANY);
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static CompanyItem createEditedCompany(CompanyItem companyToEdit,
            EditCompanyDescriptor editCompanyDescriptor) {
        assert companyToEdit != null;

        CompanyName updatedName = editCompanyDescriptor.getName().orElse(companyToEdit.getCompanyName());
        Phone updatedPhone = editCompanyDescriptor.getPhone().orElse(companyToEdit.getPhone());
        Email updatedEmail = editCompanyDescriptor.getEmail().orElse(companyToEdit.getEmail());
        Address updatedAddress = editCompanyDescriptor.getAddress().orElse(companyToEdit.getAddress());
        Set<Industry> updatedIndustries = editCompanyDescriptor.getIndustries().orElse(companyToEdit.getIndustries());
        List<InternshipItem> internships = companyToEdit.getInternships();

        CompanyItem updatedCompany = new CompanyItem(updatedName, updatedPhone, updatedEmail, updatedAddress,
                updatedIndustries, internships);
        updatedCompany.updateAllInternshipsCompanyName();
        return updatedCompany;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCompanyCommand)) {
            return false;
        }

        // state check
        EditCompanyCommand e = (EditCompanyCommand) other;
        return index.equals(e.index)
                && editCompanyDescriptor.equals(e.editCompanyDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditCompanyDescriptor {
        private CompanyName name;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Industry> industries;

        public EditCompanyDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditCompanyDescriptor(EditCompanyDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setIndustries(toCopy.industries);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, industries);
        }

        public void setName(CompanyName name) {
            this.name = name;
        }

        public Optional<CompanyName> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setIndustries(Set<Industry> industries) {
            this.industries = (industries != null) ? new HashSet<>(industries) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Industry>> getIndustries() {
            return (industries != null) ? Optional.of(Collections.unmodifiableSet(industries)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditCompanyDescriptor)) {
                return false;
            }

            // state check
            EditCompanyDescriptor e = (EditCompanyDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getIndustries().equals(e.getIndustries());
        }
    }
}
