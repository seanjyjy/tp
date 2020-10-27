package seedu.internhunter.logic.parser;

import seedu.internhunter.logic.commands.Command;
import seedu.internhunter.logic.parser.exceptions.ParseException;

/**
 * Represents a Parser that is able to parse user input into a {@code Command} of type {@code T}.
 */
public interface Parser<T extends Command> {

    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @param userInput User input string.
     * @return Command of type T.
     * @throws ParseException if {@code userInput} does not conform to the expected format.
     */
    T parse(String userInput) throws ParseException;
}
