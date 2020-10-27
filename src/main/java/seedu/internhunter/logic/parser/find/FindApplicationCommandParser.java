package seedu.internhunter.logic.parser.find;

import static java.util.Objects.requireNonNull;
import static seedu.internhunter.logic.parser.util.GeneralParserUtil.checkCommandDetailsIsNotBlank;
import static seedu.internhunter.logic.parser.util.GeneralParserUtil.getTrimmedArgsKeywords;

import java.util.Arrays;

import seedu.internhunter.logic.commands.find.FindApplicationCommand;
import seedu.internhunter.logic.parser.Parser;
import seedu.internhunter.logic.parser.exceptions.ParseException;
import seedu.internhunter.model.application.ApplicationNameContainsKeyWordsPredicate;

public class FindApplicationCommandParser implements Parser<FindApplicationCommand> {

    @Override
    public FindApplicationCommand parse(String args) throws ParseException {
        requireNonNull(args);
        checkCommandDetailsIsNotBlank(args, FindApplicationCommand.MESSAGE_USAGE);
        String[] jobTitleKeywords = getTrimmedArgsKeywords(args);
        ApplicationNameContainsKeyWordsPredicate predicate =
            new ApplicationNameContainsKeyWordsPredicate(Arrays.asList(jobTitleKeywords));
        return new FindApplicationCommand(predicate);
    }
}
