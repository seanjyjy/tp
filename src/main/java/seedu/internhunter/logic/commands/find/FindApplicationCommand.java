package seedu.internhunter.logic.commands.find;

import static java.util.Objects.requireNonNull;
import static seedu.internhunter.logic.commands.util.CommandUtil.getCommandResult;
import static seedu.internhunter.model.util.ItemUtil.APPLICATION_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.APPLICATION_NAME;

import seedu.internhunter.commons.core.Messages;
import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.logic.commands.CommandResult;
import seedu.internhunter.logic.commands.exceptions.CommandException;
import seedu.internhunter.model.Model;
import seedu.internhunter.model.application.ApplicationNameContainsKeyWordsPredicate;
import seedu.internhunter.ui.tabs.TabName;

/**
 * Finds and lists all application items in InternHunter whose jobTitle contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindApplicationCommand extends FindCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " "
            + APPLICATION_ALIAS + ": Finds all applications in the list of applications whose internship job titles "
            + "contain any of the given keywords.\n"
            + "Parameters: KEYWORD [ANOTHER_KEYWORD]...\n"
            + "Example: " + COMMAND_WORD + " " + APPLICATION_ALIAS + " tester\n";

    private final ApplicationNameContainsKeyWordsPredicate predicate;

    public FindApplicationCommand(ApplicationNameContainsKeyWordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredApplicationList(predicate);
        model.setApplicationViewIndex(Index.fromOneBased(1));
        String message = String.format(Messages.MESSAGE_FIND_SUCCESS,
            model.getFilteredApplicationListSize(), APPLICATION_NAME);
        return getCommandResult(model, message, TabName.APPLICATION);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof FindApplicationCommand // instanceof handles nulls
            && predicate.equals(((FindApplicationCommand) other).predicate)); // state check
    }
}
