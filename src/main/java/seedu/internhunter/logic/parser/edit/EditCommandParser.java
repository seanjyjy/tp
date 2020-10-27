package seedu.internhunter.logic.parser.edit;

import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_ITEM_TYPE;
import static seedu.internhunter.logic.parser.util.GeneralParserUtil.getCommandDetails;
import static seedu.internhunter.logic.parser.util.GeneralParserUtil.getItemType;
import static seedu.internhunter.model.util.ItemUtil.APPLICATION_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.COMPANY_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.INTERNSHIP_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.PROFILE_ALIAS;

import seedu.internhunter.logic.commands.edit.EditCommand;
import seedu.internhunter.logic.parser.Parser;
import seedu.internhunter.logic.parser.exceptions.ParseException;

public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns a EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {

        String itemType = getItemType(args, EditCommand.MESSAGE_USAGE);
        String commandDetails = getCommandDetails(args);

        switch (itemType) {
        case COMPANY_ALIAS:
            return new EditCompanyCommandParser().parse(commandDetails);

        case INTERNSHIP_ALIAS:
            return new EditInternshipCommandParser().parse(commandDetails);

        case APPLICATION_ALIAS:
            return new EditApplicationCommandParser().parse(commandDetails);

        case PROFILE_ALIAS:
            return new EditProfileCommandParser().parse(commandDetails);

        default:
            // Invalid item type
            throw new ParseException(MESSAGE_INVALID_ITEM_TYPE);
        }
    }

}
