package seedu.internhunter.logic.parser.view;

import static java.util.Objects.requireNonNull;
import static seedu.internhunter.logic.parser.util.GeneralParserUtil.checkCommandDetailsIsNotBlank;

import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.logic.commands.view.ViewApplicationCommand;
import seedu.internhunter.logic.parser.Parser;
import seedu.internhunter.logic.parser.exceptions.ParseException;
import seedu.internhunter.logic.parser.util.GeneralParserUtil;

/**
 * Parses input arguments and creates a new ViewApplicationCommand object.
 */
public class ViewApplicationCommandParser implements Parser<ViewApplicationCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewApplicationCommand
     * and returns a ViewApplicationCommand object for execution.
     *
     * @param args Arguments to be parsed.
     * @return ViewApplicationCommand object for execution
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public ViewApplicationCommand parse(String args) throws ParseException {
        requireNonNull(args);
        checkCommandDetailsIsNotBlank(args, ViewApplicationCommand.MESSAGE_USAGE);
        Index index = GeneralParserUtil.parseIndex(args);
        return new ViewApplicationCommand(index);
    }
}
