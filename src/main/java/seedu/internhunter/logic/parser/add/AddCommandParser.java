package seedu.internhunter.logic.parser.add;

import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_ITEM_TYPE;
import static seedu.internhunter.logic.parser.util.GeneralParserUtil.getCommandDetails;
import static seedu.internhunter.logic.parser.util.GeneralParserUtil.getItemType;
import static seedu.internhunter.model.util.ItemUtil.APPLICATION_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.COMPANY_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.INTERNSHIP_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.PROFILE_ALIAS;

import seedu.internhunter.logic.commands.add.AddCommand;
import seedu.internhunter.logic.parser.Parser;
import seedu.internhunter.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments for item type and returns AddCommand Object.
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments for the item type
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conformClear the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        String itemType = getItemType(args, AddCommand.MESSAGE_USAGE);
        String commandDetails = getCommandDetails(args);

        switch (itemType) {
        case COMPANY_ALIAS:
            return new AddCompanyCommandParser().parse(commandDetails);
        case INTERNSHIP_ALIAS:
            return new AddInternshipCommandParser().parse(commandDetails);
        case APPLICATION_ALIAS:
            return new AddApplicationCommandParser().parse(commandDetails);
        case PROFILE_ALIAS:
            return new AddProfileCommandParser().parse(commandDetails);
        default:
            throw new ParseException(MESSAGE_INVALID_ITEM_TYPE);
        }
    }

}
