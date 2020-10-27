package seedu.internhunter.logic.parser.list;

import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_ITEM_TYPE;
import static seedu.internhunter.logic.parser.util.GeneralParserUtil.getItemType;
import static seedu.internhunter.model.util.ItemUtil.APPLICATION_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.COMPANY_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.PROFILE_ALIAS;

import seedu.internhunter.logic.commands.list.ListApplicationCommand;
import seedu.internhunter.logic.commands.list.ListCommand;
import seedu.internhunter.logic.commands.list.ListCompanyCommand;
import seedu.internhunter.logic.commands.list.ListProfileCommand;
import seedu.internhunter.logic.parser.Parser;
import seedu.internhunter.logic.parser.exceptions.ParseException;

/**
 * todo javadocs
 */
public class ListCommandParser implements Parser<ListCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns a ListCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args) throws ParseException {

        String itemType = getItemType(args, ListCommand.MESSAGE_USAGE);
        checkIfHaveExcessMessage(args);

        switch (itemType) {
        case COMPANY_ALIAS:
            return new ListCompanyCommand();
        case APPLICATION_ALIAS:
            return new ListApplicationCommand();
        case PROFILE_ALIAS:
            return new ListProfileCommand();
        default:
            // Invalid item type
            throw new ParseException(MESSAGE_INVALID_ITEM_TYPE);
        }
    }

    /**
     * todo javadocs
     */
    private void checkIfHaveExcessMessage(String args) throws ParseException {
        // allows white space behind the list ITEM TYPE.
        // Strictly no other extra words behind list ITEM TYPE.
        String[] argsArray = args.split(" ");
        if (argsArray.length > 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.EXCESS_MESSAGE));
        }
    }

}
