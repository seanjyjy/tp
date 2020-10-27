package seedu.internhunter.logic.parser.view;

import static java.util.Objects.requireNonNull;
import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internhunter.logic.parser.util.GeneralParserUtil.argumentsAreValid;
import static seedu.internhunter.logic.parser.util.GeneralParserUtil.getIndexInPreamble;

import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.logic.commands.view.ViewCompanyCommand;
import seedu.internhunter.logic.parser.ArgumentMultimap;
import seedu.internhunter.logic.parser.ArgumentTokenizer;
import seedu.internhunter.logic.parser.Parser;
import seedu.internhunter.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewCompanyCommand object.
 */
public class ViewCompanyCommandParser implements Parser<ViewCompanyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewCompanyCommand
     * and returns a ViewCompanyCommand object for execution.
     *
     * @param args Arguments to be parsed.
     * @return ViewCompanyCommand object for execution
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public ViewCompanyCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        if (!argumentsAreValid(true, argMultimap)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCompanyCommand.MESSAGE_USAGE));
        }

        Index index = getIndexInPreamble(argMultimap);
        return new ViewCompanyCommand(index);
    }
}
