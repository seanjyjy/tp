package seedu.internhunter.logic.parser.view;

import static java.util.Objects.requireNonNull;
import static seedu.internhunter.logic.parser.util.GeneralParserUtil.checkCommandDetailsIsNotBlank;

import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.logic.commands.view.ViewProfileCommand;
import seedu.internhunter.logic.parser.Parser;
import seedu.internhunter.logic.parser.exceptions.ParseException;
import seedu.internhunter.logic.parser.util.GeneralParserUtil;

/**
 * Parses input arguments and creates a new ViewProfileCommand object.
 */
public class ViewProfileCommandParser implements Parser<ViewProfileCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewProfileCommand
     * and returns a ViewProfileCommand object for execution.
     *
     * @param args Arguments to be parsed.
     * @return ViewProfileCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public ViewProfileCommand parse(String args) throws ParseException {
        requireNonNull(args);
        checkCommandDetailsIsNotBlank(args, ViewProfileCommand.MESSAGE_USAGE);
        Index index = GeneralParserUtil.parseIndex(args);
        return new ViewProfileCommand(index);
    }
}
