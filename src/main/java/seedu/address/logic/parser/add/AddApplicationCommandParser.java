package seedu.address.logic.parser.add;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.clisyntax.ApplicationCliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.clisyntax.ApplicationCliSyntax.PREFIX_STATUS_DATE;
import static seedu.address.logic.parser.clisyntax.ItemCliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.util.Util.arePrefixesPresent;

import java.time.LocalDateTime;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.add.AddApplicationCommand;
import seedu.address.logic.parser.ApplicationParserUtil;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.application.Status;
import seedu.address.model.application.StatusDate;

/**
 * Parses input arguments and creates a new AddApplicationCommand object.
 */
public class AddApplicationCommandParser implements Parser<AddApplicationCommand> {

    private static final int INDEX_FIRST = 0;
    private static final int INDEX_SECOND = 1;
    private static final int NUMBER_OF_ARGUMENTS_TYPES = 2;

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public AddApplicationCommand parse(String args) throws ParseException {

        String[] argumentArr = args.strip().split(" ", NUMBER_OF_ARGUMENTS_TYPES);
        checkArgumentTypeSufficiency(argumentArr);
        Index companyIndex = ParserUtil.parseIndex(argumentArr[INDEX_FIRST]);
        String remainingTokens = " " + argumentArr[INDEX_SECOND];

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(remainingTokens, PREFIX_INDEX, PREFIX_STATUS, PREFIX_STATUS_DATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_INDEX) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddApplicationCommand.MESSAGE_USAGE));
        }

        Index internshipIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());
        Status status = getStatus(argMultimap);
        StatusDate statusDate = getStatusDate(argMultimap);

        return new AddApplicationCommand(companyIndex, internshipIndex, status, statusDate);
    }

    /**
     * Obtains the status from the user input. Returns default applied status if not provided by user.
     *
     * @param argMultimap ArgumentMultimap.
     * @return Status for this application.
     * @throws ParseException if the given {@code status} is invalid.
     */
    private Status getStatus(ArgumentMultimap argMultimap) throws ParseException {
        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            return ApplicationParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get());
        } else {
            return Status.APPLIED;
        }
    }

    /**
     * Obtains the status from the user input. Returns default today's date if not provided by user.
     *
     * @param argMultimap ArgumentMultimap.
     * @return StatusDate for this application.
     * @throws ParseException if the given {@code StatusDate} is invalid.
     */
    private StatusDate getStatusDate(ArgumentMultimap argMultimap) throws ParseException {
        if (argMultimap.getValue(PREFIX_STATUS_DATE).isPresent()) {
            return ApplicationParserUtil.parseStatusDate(argMultimap.getValue(PREFIX_STATUS_DATE).get());
        } else {
            return new StatusDate(LocalDateTime.now());
        }
    }

    /**
     * Checks if number of argument types are sufficient.
     *
     * @param argumentTypes is a list of arguments delimited by the
     * first space in the user argument after stripping wrapping spaces.
     */
    private void checkArgumentTypeSufficiency(String[] argumentTypes) throws ParseException {
        if (argumentTypes.length < NUMBER_OF_ARGUMENTS_TYPES) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, AddApplicationCommand.MESSAGE_USAGE));
        }
    }
}
