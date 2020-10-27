package seedu.internhunter.logic.parser.delete;

import static java.util.Objects.requireNonNull;
import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internhunter.logic.parser.clisyntax.GeneralCliSyntax.PREFIX_INDEX;
import static seedu.internhunter.logic.parser.util.GeneralParserUtil.argumentsAreValid;
import static seedu.internhunter.logic.parser.util.GeneralParserUtil.getIndexInPreamble;
import static seedu.internhunter.logic.parser.util.GeneralParserUtil.parseIndex;

import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.logic.commands.delete.DeleteInternshipCommand;
import seedu.internhunter.logic.parser.ArgumentMultimap;
import seedu.internhunter.logic.parser.ArgumentTokenizer;
import seedu.internhunter.logic.parser.Parser;
import seedu.internhunter.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteInternshipCommand object.
 */
public class DeleteInternshipCommandParser implements Parser<DeleteInternshipCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteInternshipCommand
     * and returns an DeleteInternshipCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteInternshipCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_INDEX);

        if (!argumentsAreValid(true, argMultimap, PREFIX_INDEX)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteInternshipCommand.MESSAGE_USAGE));
        }

        Index companyIndex = getIndexInPreamble(argMultimap);
        Index internshipIndex = parseIndex(argMultimap.getValue(PREFIX_INDEX).get());

        return new DeleteInternshipCommand(companyIndex, internshipIndex);
    }

}
