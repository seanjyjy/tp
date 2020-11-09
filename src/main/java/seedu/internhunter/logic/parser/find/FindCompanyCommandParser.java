package seedu.internhunter.logic.parser.find;

import static java.util.Objects.requireNonNull;
import static seedu.internhunter.logic.parser.util.GeneralParserUtil.checkCommandDetailsIsNotBlank;
import static seedu.internhunter.logic.parser.util.GeneralParserUtil.getTrimmedArgsKeywords;

import java.util.Arrays;

import seedu.internhunter.logic.commands.find.FindCompanyCommand;
import seedu.internhunter.logic.parser.Parser;
import seedu.internhunter.logic.parser.exceptions.ParseException;
import seedu.internhunter.model.company.CompanyNameContainsKeyWordsPredicate;

/**
 * Parses input arguments and creates a new FindCompanyCommand object.
 */
public class FindCompanyCommandParser implements Parser<FindCompanyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCompanyCommand
     * and returns a FindCompanyCommand object for execution.
     *
     * @param args User's input.
     * @return A FindCompanyCommand object that contains the input predicate.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    @Override
    public FindCompanyCommand parse(String args) throws ParseException {
        requireNonNull(args);
        checkCommandDetailsIsNotBlank(args, FindCompanyCommand.MESSAGE_USAGE);
        String[] companyNameKeywords = getTrimmedArgsKeywords(args);
        CompanyNameContainsKeyWordsPredicate predicate =
                new CompanyNameContainsKeyWordsPredicate(Arrays.asList(companyNameKeywords));
        return new FindCompanyCommand(predicate);
    }

}
