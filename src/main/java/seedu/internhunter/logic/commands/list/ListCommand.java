package seedu.internhunter.logic.commands.list;

import static seedu.internhunter.model.util.ItemUtil.APPLICATION_NAME;
import static seedu.internhunter.model.util.ItemUtil.COMPANY_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.COMPANY_NAME;
import static seedu.internhunter.model.util.ItemUtil.PROFILE_NAME;

import seedu.internhunter.logic.commands.Command;

/**
 * Lists all items of a specified item type in the InternHunter to the user.
 */
public abstract class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Lists out all the items in the "
        + COMPANY_NAME + ", "
        + APPLICATION_NAME + " or "
        + PROFILE_NAME + " list.\n"
        + "Parameters: ITEM_TYPE\n"
        + "Example: " + COMMAND_WORD + " " + COMPANY_ALIAS + "\n";

    public static final String EXCESS_MESSAGE = "Note that there should not be any inputs after the ITEM_TYPE";
}
