package seedu.internhunter.logic.parser.add;

import static java.util.Objects.requireNonNull;
import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internhunter.logic.parser.clisyntax.ApplicationCliSyntax.PREFIX_STATUS;
import static seedu.internhunter.logic.parser.clisyntax.ApplicationCliSyntax.PREFIX_STATUS_DATE;
import static seedu.internhunter.logic.parser.clisyntax.GeneralCliSyntax.PREFIX_INDEX;
import static seedu.internhunter.logic.parser.util.GeneralParserUtil.argumentsAreValid;
import static seedu.internhunter.logic.parser.util.GeneralParserUtil.getIndexInPreamble;
import static seedu.internhunter.logic.parser.util.GeneralParserUtil.parseIndex;

import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.logic.commands.add.AddApplicationCommand;
import seedu.internhunter.logic.parser.ArgumentMultimap;
import seedu.internhunter.logic.parser.ArgumentTokenizer;
import seedu.internhunter.logic.parser.Parser;
import seedu.internhunter.logic.parser.exceptions.ParseException;
import seedu.internhunter.logic.parser.util.ApplicationParserUtil;
import seedu.internhunter.model.application.Status;
import seedu.internhunter.model.application.StatusDate;
import seedu.internhunter.model.util.DateUtil;

/**
 * Parses input arguments and creates a new AddApplicationCommand object.
 */
public class AddApplicationCommandParser implements Parser<AddApplicationCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public AddApplicationCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_INDEX, PREFIX_STATUS, PREFIX_STATUS_DATE);

        if (!argumentsAreValid(true, argMultimap, PREFIX_INDEX)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddApplicationCommand.MESSAGE_USAGE));
        }

        Index companyIndex = getIndexInPreamble(argMultimap);
        Index internshipIndex = parseIndex(argMultimap.getValue(PREFIX_INDEX).get());
        Status status = getStatus(argMultimap);
        StatusDate statusDate = getStatusDate(argMultimap);

        return new AddApplicationCommand(companyIndex, internshipIndex, status, statusDate);
    }

    /**
     * Obtains the status from the user input. Returns default Applied status if not provided by user.
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
     * Obtains the status from the user input. Returns default today's date and 2359 if not provided by user.
     *
     * @param argMultimap ArgumentMultimap.
     * @return StatusDate for this application.
     * @throws ParseException if the given {@code StatusDate} is invalid.
     */
    private StatusDate getStatusDate(ArgumentMultimap argMultimap) throws ParseException {
        if (argMultimap.getValue(PREFIX_STATUS_DATE).isPresent()) {
            return ApplicationParserUtil.parseStatusDate(argMultimap.getValue(PREFIX_STATUS_DATE).get());
        } else {
            return new StatusDate(DateUtil.getTodayDate());
        }
    }

}
