package seedu.internhunter.logic.commands.edit;

import static java.util.Objects.requireNonNull;
import static seedu.internhunter.commons.core.Messages.MESSAGE_EDIT_SUCCESS;
import static seedu.internhunter.commons.util.CollectionUtil.isAnyNonNull;
import static seedu.internhunter.logic.commands.util.CommandUtil.getCommandResult;
import static seedu.internhunter.logic.commands.util.CommandUtil.getProfileItem;
import static seedu.internhunter.logic.parser.clisyntax.ProfileCliSyntax.PREFIX_CATEGORY;
import static seedu.internhunter.logic.parser.clisyntax.ProfileCliSyntax.PREFIX_DESCRIPTOR;
import static seedu.internhunter.logic.parser.clisyntax.ProfileCliSyntax.PREFIX_TITLE;
import static seedu.internhunter.model.util.ItemUtil.PROFILE_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.PROFILE_ITEM_NAME;
import static seedu.internhunter.model.util.ItemUtil.PROFILE_NAME;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.internhunter.commons.core.Messages;
import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.logic.commands.CommandResult;
import seedu.internhunter.logic.commands.exceptions.CommandException;
import seedu.internhunter.model.Model;
import seedu.internhunter.model.profile.Descriptor;
import seedu.internhunter.model.profile.ProfileItem;
import seedu.internhunter.model.profile.ProfileItemCategory;
import seedu.internhunter.model.profile.Title;
import seedu.internhunter.ui.tabs.TabName;

/**
 * Edits the details of a existing profileItem in the InternHunter.
 */
public class EditProfileCommand extends EditCommand {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + PROFILE_ALIAS
            + ": Edits the details of a " + PROFILE_ITEM_NAME + " from InternHunter accessed "
            + "by the index number used in the displayed list.\n"
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX "
            + "[" + PREFIX_TITLE + "TITLE] "
            + "[" + PREFIX_CATEGORY + "CATEGORY] "
            + "[" + PREFIX_DESCRIPTOR + "DESCRIPTOR]...\n"
            + "Note: At least one of the optional fields must be provided. INDEX must be a positive integer.\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_CATEGORY + "achievement "
            + PREFIX_DESCRIPTOR + "Devised a mobile transaction solution. ";


    private final Index targetIndex;
    private final EditProfileItemDescriptor editProfileItemDescriptor;

    /**
     * @param index of the profileItem in the filtered profile item list to edit
     * @param editProfileItemDescriptor details to edit the profile item with
     */
    public EditProfileCommand(Index index, EditProfileItemDescriptor editProfileItemDescriptor) {
        requireNonNull(index);
        requireNonNull(editProfileItemDescriptor);

        this.targetIndex = index;
        this.editProfileItemDescriptor = new EditProfileItemDescriptor(editProfileItemDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ProfileItem profileItemToEdit = getProfileItem(model, targetIndex);
        ProfileItem editedProfileItem = createEditedProfileItem(profileItemToEdit, editProfileItemDescriptor);

        if (!profileItemToEdit.isSameItem(editedProfileItem) && model.hasProfileItem(editedProfileItem)) {
            throw new CommandException(String.format(Messages.MESSAGE_DUPLICATE_ITEM, PROFILE_NAME));
        }

        model.setProfileItem(profileItemToEdit, editedProfileItem);
        model.setProfileViewIndex(targetIndex);
        String editSuccessMessage = String.format(MESSAGE_EDIT_SUCCESS, PROFILE_NAME, editedProfileItem);
        return getCommandResult(model, editSuccessMessage, TabName.PROFILE);
    }

    /**
     * Creates and returns a {@code ProfileItem} with the details of {@code profileItemToEdit}
     * edited with {@code editProfileItemDescriptor}.
     */
    private static ProfileItem createEditedProfileItem(ProfileItem profileItemToEdit,
            EditProfileItemDescriptor editProfileItemDescriptor) {
        assert profileItemToEdit != null;

        Title updatedTitle = editProfileItemDescriptor.getTitle()
                .orElse(profileItemToEdit.getTitle());
        ProfileItemCategory updatedCategory = editProfileItemDescriptor
                .getProfileItemCategory().orElse(profileItemToEdit.getCategory());
        Set<Descriptor> updatedDescriptor = editProfileItemDescriptor
                .getDescriptors().orElse(profileItemToEdit.getDescriptors());

        return new ProfileItem(updatedTitle, updatedCategory, updatedDescriptor);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof EditProfileCommand)) {
            return false;
        }

        // state check
        EditProfileCommand e = (EditProfileCommand) other;
        return targetIndex.equals(e.targetIndex)
                && editProfileItemDescriptor.equals(e.editProfileItemDescriptor);
    }

    /**
     * Stores the details to edit the profile item with. Each non-empty field value will replace the
     * corresponding field value of the profile item.
     */
    public static class EditProfileItemDescriptor {
        private Title title;
        private ProfileItemCategory profileItemCategory;
        private Set<Descriptor> descriptors;

        public EditProfileItemDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code descriptors} is used internally.
         */
        public EditProfileItemDescriptor(EditProfileItemDescriptor toCopy) {
            setTitle(toCopy.title);
            setProfileItemCategory(toCopy.profileItemCategory);
            setDescriptors(toCopy.descriptors);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return isAnyNonNull(title, profileItemCategory, descriptors);
        }

        public void setTitle(Title title) {
            this.title = title;
        }

        public Optional<Title> getTitle() {
            return Optional.ofNullable(title);
        }


        public void setProfileItemCategory(ProfileItemCategory cat) {
            this.profileItemCategory = cat;
        }

        public Optional<ProfileItemCategory> getProfileItemCategory() {
            return Optional.ofNullable(profileItemCategory);
        }

        /**
         * Sets {@code descriptors} to this object's {@code descriptors}.
         * A defensive copy of {@code descriptors} is used internally.
         */
        public void setDescriptors(Set<Descriptor> descriptors) {
            this.descriptors = (descriptors != null) ? new HashSet<>(descriptors) : null;
        }

        /**
         * Returns an unmodifiable descriptor set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code descriptors} is null.
         */
        public Optional<Set<Descriptor>> getDescriptors() {
            return (descriptors != null) ? Optional.of(Collections.unmodifiableSet(descriptors)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditProfileItemDescriptor)) {
                return false;
            }

            // state check
            EditProfileItemDescriptor e = (EditProfileItemDescriptor) other;

            return getTitle().equals(e.getTitle())
                    && getProfileItemCategory().equals(e.getProfileItemCategory())
                    && getDescriptors().equals(e.getDescriptors());
        }
    }
}
