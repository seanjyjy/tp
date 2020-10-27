package seedu.internhunter.logic.commands.add;

import static seedu.internhunter.model.util.ItemUtil.APPLICATION_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.APPLICATION_NAME;
import static seedu.internhunter.model.util.ItemUtil.COMPANY_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.COMPANY_NAME;
import static seedu.internhunter.model.util.ItemUtil.INTERNSHIP_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.INTERNSHIP_NAME;
import static seedu.internhunter.model.util.ItemUtil.PROFILE_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.PROFILE_ITEM_NAME;

import seedu.internhunter.logic.commands.Command;

/**
 * Represents an Add Command for Items.
 */
public abstract class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";
    //todo Update Message Usage to standardised format
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a "
            + COMPANY_NAME + ", "
            + INTERNSHIP_NAME + ", "
            + APPLICATION_NAME + " or "
            + PROFILE_ITEM_NAME
            + " to InternHunter.\n"
            + "Parameters: ITEM_TYPE DETAILS\n"
            + "Note: Valid ITEM_TYPEs are "
            + "'" + COMPANY_ALIAS + "', "
            + "'" + INTERNSHIP_ALIAS + "', "
            + "'" + APPLICATION_ALIAS + "' or "
            + "'" + PROFILE_ALIAS + "'. "
            + "Each ITEM_TYPE requires its own set of DETAILS.\n"
            + "Example: " + COMMAND_WORD + " " + INTERNSHIP_ALIAS + " DETAILS";

}
