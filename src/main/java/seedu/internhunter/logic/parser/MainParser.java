package seedu.internhunter.logic.parser;

import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internhunter.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.internhunter.logic.commands.ClearCommand;
import seedu.internhunter.logic.commands.Command;
import seedu.internhunter.logic.commands.ExitCommand;
import seedu.internhunter.logic.commands.HelpCommand;
import seedu.internhunter.logic.commands.MatchCommand;
import seedu.internhunter.logic.commands.SwitchCommand;
import seedu.internhunter.logic.commands.add.AddCommand;
import seedu.internhunter.logic.commands.delete.DeleteCommand;
import seedu.internhunter.logic.commands.edit.EditCommand;
import seedu.internhunter.logic.commands.find.FindCommand;
import seedu.internhunter.logic.commands.list.ListCommand;
import seedu.internhunter.logic.commands.view.ViewCommand;
import seedu.internhunter.logic.parser.add.AddCommandParser;
import seedu.internhunter.logic.parser.delete.DeleteCommandParser;
import seedu.internhunter.logic.parser.edit.EditCommandParser;
import seedu.internhunter.logic.parser.exceptions.ParseException;
import seedu.internhunter.logic.parser.find.FindCommandParser;
import seedu.internhunter.logic.parser.list.ListCommandParser;
import seedu.internhunter.logic.parser.switchparser.SwitchCommandParser;
import seedu.internhunter.logic.parser.view.ViewCommandParser;

/**
 * Parses user input.
 */
public class MainParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final String COMMAND_WORD = "commandWord";
    private static final String ARGUMENTS = "arguments";

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group(COMMAND_WORD);
        final String arguments = matcher.group(ARGUMENTS);
        switch (commandWord) {
        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);
        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);
        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);
        case ViewCommand.COMMAND_WORD:
            return new ViewCommandParser().parse(arguments);
        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();
        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);
        case ListCommand.COMMAND_WORD:
            return new ListCommandParser().parse(arguments);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        case SwitchCommand.COMMAND_WORD:
            return new SwitchCommandParser().parse(arguments);
        case MatchCommand.COMMAND_WORD:
            return new MatchCommand();
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
