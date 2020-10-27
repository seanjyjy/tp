package seedu.internhunter.logic.commands.delete;

import static seedu.internhunter.logic.parser.clisyntax.GeneralCliSyntax.PREFIX_INDEX;
import static seedu.internhunter.model.util.ItemUtil.APPLICATION_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.APPLICATION_NAME;
import static seedu.internhunter.model.util.ItemUtil.COMPANY_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.COMPANY_NAME;
import static seedu.internhunter.model.util.ItemUtil.INTERNSHIP_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.INTERNSHIP_NAME;
import static seedu.internhunter.model.util.ItemUtil.PROFILE_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.PROFILE_ITEM_NAME;

import seedu.internhunter.logic.commands.Command;

/** todo javadocs */
public abstract class DeleteCommandAbstract extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes a "
            + COMPANY_NAME + ", "
            + INTERNSHIP_NAME + ", "
            + APPLICATION_NAME + " or "
            + PROFILE_ITEM_NAME
            + " from InternHunter by the index number used in the displayed list.\n"
            + "Parameters (for ITEM_TYPE "
            + "'" + COMPANY_ALIAS + "', "
            + "'" + APPLICATION_ALIAS + "', "
            + "'" + PROFILE_ALIAS + "'): "
            + "ITEM_TYPE INDEX\n"
            + "Parameters (for ITEM_TYPE "
            + "'" + INTERNSHIP_ALIAS + "'): "
            + "ITEM_TYPE INDEX " + PREFIX_INDEX + "INDEX\n"
            + "Note: Valid ITEM_TYPEs are "
            + "'" + COMPANY_ALIAS + "', "
            + "'" + INTERNSHIP_ALIAS + "', "
            + "'" + APPLICATION_ALIAS + "' or "
            + "'" + PROFILE_ALIAS + "'. "
            + "INDEX must be a positive integer.\n"
            + "Examples:\n"
            + COMMAND_WORD + " " + COMPANY_ALIAS + " 2\n"
            + COMMAND_WORD + " " + INTERNSHIP_ALIAS + " 1 " + PREFIX_INDEX + "3";

}
