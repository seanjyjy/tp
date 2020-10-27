package seedu.internhunter.logic.commands.add;

import static java.util.Objects.requireNonNull;
import static seedu.internhunter.commons.core.Messages.MESSAGE_ADD_SUCCESS;
import static seedu.internhunter.commons.core.Messages.MESSAGE_DUPLICATE_ITEM;
import static seedu.internhunter.logic.commands.util.CommandUtil.getCommandResult;
import static seedu.internhunter.logic.parser.clisyntax.ProfileCliSyntax.PREFIX_CATEGORY;
import static seedu.internhunter.logic.parser.clisyntax.ProfileCliSyntax.PREFIX_DESCRIPTOR;
import static seedu.internhunter.logic.parser.clisyntax.ProfileCliSyntax.PREFIX_TITLE;
import static seedu.internhunter.model.util.ItemUtil.PROFILE_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.PROFILE_ITEM_NAME;
import static seedu.internhunter.model.util.ItemUtil.PROFILE_NAME;

import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.logic.commands.CommandResult;
import seedu.internhunter.logic.commands.exceptions.CommandException;
import seedu.internhunter.model.Model;
import seedu.internhunter.model.profile.ProfileItem;
import seedu.internhunter.ui.tabs.TabName;

/**
 * Adds a Profile Item to the Model's Profile list.
 */
public class AddProfileCommand extends AddCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + PROFILE_ALIAS
            + ": Adds a " + PROFILE_ITEM_NAME + " item to "
            + "InternHunter.\nParameters: "
            + "Parameters: "
            + PREFIX_TITLE + "TITLE "
            + PREFIX_CATEGORY + "CATEGORY "
            + "[" + PREFIX_DESCRIPTOR + "DESCRIPTOR]...\n"
            + "Example: " + COMMAND_WORD + " " + PROFILE_ALIAS + " "
            + PREFIX_TITLE + "Learn HTML "
            + PREFIX_CATEGORY + "skill "
            + PREFIX_DESCRIPTOR + "Learn how to use div and classes. "
            + PREFIX_DESCRIPTOR + "Learn how to inject javascript. ";

    private final ProfileItem toAdd;

    /**
     * Creates an AddCommand to add the specified {@code ProfileItem}.
     */
    public AddProfileCommand(ProfileItem profileItem) {
        requireNonNull(profileItem);
        toAdd = profileItem;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasProfileItem(toAdd)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_ITEM, PROFILE_NAME));
        }

        model.addProfileItem(toAdd);
        setProfileViewIndex(model);
        String addSuccessMessage = String.format(MESSAGE_ADD_SUCCESS, PROFILE_NAME, toAdd);
        return getCommandResult(model, addSuccessMessage, TabName.PROFILE);
    }

    /**
     * Sets the profile view index to the newly added profile.
     *
     * @param model {@code Model} which the command should operate on.
     */
    private void setProfileViewIndex(Model model) {
        int size = model.getFilteredProfileListSize();
        model.setProfileViewIndex(Index.fromOneBased(size));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AddProfileCommand // instanceof handles nulls
                && toAdd.equals(((AddProfileCommand) other).toAdd));
    }

}
