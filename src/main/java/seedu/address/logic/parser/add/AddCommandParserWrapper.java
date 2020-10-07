package seedu.address.logic.parser.add;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.model.util.ItemUtil.APPLICATION_ALIAS;
import static seedu.address.model.util.ItemUtil.COMPANY_ALIAS;
import static seedu.address.model.util.ItemUtil.INTERNSHIP_ALIAS;
import static seedu.address.model.util.ItemUtil.PROFILE_ALIAS;

import seedu.address.logic.commands.add.AddCommandAbstract;
import seedu.address.logic.commands.add.AddInternshipCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments for item type and returns AddCommandAbstract Object.
 */
public class AddCommandParserWrapper implements Parser<AddCommandAbstract> {

    private static final int ITEM_TYPE_INDEX = 0;
    private static final int ITEM_PREFIX_INDEX = 1;
    private static final int NUMBER_OF_ARGUMENTS_TYPES = 2;

    /**
     * Parses the given {@code String} of arguments for the item type
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommandAbstract parse(String args) throws ParseException {

        String[] argumentTypes = args.strip().split(" ", NUMBER_OF_ARGUMENTS_TYPES);
        String itemType = argumentTypes[ITEM_TYPE_INDEX];

        // comment out these 2 lines to access og addresss book
        checkArgumentTypeSufficiency(argumentTypes);
        String itemPrefixes = " " + argumentTypes[ITEM_PREFIX_INDEX];
        switch (itemType) {
        case COMPANY_ALIAS:
            return null;

        case INTERNSHIP_ALIAS:
            // todo: create parser object and return command from within
            return new AddInternshipCommand("Not an internship added");
        case APPLICATION_ALIAS:
            return new AddApplicationCommandParser().parse(itemPrefixes);

        case PROFILE_ALIAS:
            return new AddProfileCommandParser().parse(itemPrefixes);

        default:
            return new AddCommandParser().parse(itemPrefixes);
        }
    }

    /**
     * Checks if number of argument types are sufficient.
     *
     * @param argumentTypes is a list of arguments delimited by the
     * first space in the user argument after stripping wrapping spaces.
     */
    private void checkArgumentTypeSufficiency (String[] argumentTypes) throws ParseException {
        if (argumentTypes.length < NUMBER_OF_ARGUMENTS_TYPES) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, AddCommandAbstract.MESSAGE_USAGE));
        }
    }

}
