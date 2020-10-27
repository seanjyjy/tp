package seedu.internhunter.logic.commands.find;

import static seedu.internhunter.model.util.ItemUtil.APPLICATION_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.APPLICATION_NAME;
import static seedu.internhunter.model.util.ItemUtil.COMPANY_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.COMPANY_NAME;
import static seedu.internhunter.model.util.ItemUtil.PROFILE_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.PROFILE_NAME;

import seedu.internhunter.logic.commands.Command;

/**
 * Finds all items of a specified item type in the InternHunter to the user.
 * Keyword matching is case insensitive.
 */
public abstract class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all items by name in the "
            + COMPANY_NAME + ", "
            + APPLICATION_NAME + " or "
            + PROFILE_NAME + " list that contain any of the given keywords.\n"
            + "Parameters: ITEM_TYPE KEYWORD [ANOTHER_KEYWORD]...\n"
            + "Note: Search results are returned for all items that match at least one of the given keyword(s).\n"
            + "Valid ITEM_TYPEs are "
            + "'" + COMPANY_ALIAS + "', "
            + "'" + APPLICATION_ALIAS + "' or "
            + "'" + PROFILE_ALIAS + "'.\n"
            + "Example: " + COMMAND_WORD + " " + COMPANY_ALIAS + " Google\n";
}
