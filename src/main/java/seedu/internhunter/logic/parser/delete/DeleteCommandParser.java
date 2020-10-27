package seedu.internhunter.logic.parser.delete;

import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_ITEM_TYPE;
import static seedu.internhunter.logic.parser.util.GeneralParserUtil.getCommandDetails;
import static seedu.internhunter.logic.parser.util.GeneralParserUtil.getItemType;
import static seedu.internhunter.model.util.ItemUtil.APPLICATION_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.COMPANY_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.INTERNSHIP_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.PROFILE_ALIAS;

import seedu.internhunter.logic.commands.delete.DeleteCommandAbstract;
import seedu.internhunter.logic.parser.Parser;
import seedu.internhunter.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommandAbstract> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommandAbstract parse(String args) throws ParseException {
        String itemType = getItemType(args, DeleteCommandAbstract.MESSAGE_USAGE);
        String commandDetails = getCommandDetails(args);

        switch (itemType) {
        case COMPANY_ALIAS:
            return new DeleteCompanyCommandParser().parse(commandDetails);

        case INTERNSHIP_ALIAS:
            return new DeleteInternshipCommandParser().parse(commandDetails);

        case APPLICATION_ALIAS:
            return new DeleteApplicationCommandParser().parse(commandDetails);

        case PROFILE_ALIAS:
            return new DeleteProfileCommandParser().parse(commandDetails);

        default:
            // Invalid item type
            throw new ParseException(MESSAGE_INVALID_ITEM_TYPE);
        }
    }

}
