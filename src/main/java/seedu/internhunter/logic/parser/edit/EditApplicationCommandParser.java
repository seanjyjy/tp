package seedu.internhunter.logic.parser.edit;

import static java.util.Objects.requireNonNull;
import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internhunter.logic.commands.edit.EditCommand.MESSAGE_NOT_EDITED;
import static seedu.internhunter.logic.parser.clisyntax.ApplicationCliSyntax.PREFIX_STATUS;
import static seedu.internhunter.logic.parser.clisyntax.ApplicationCliSyntax.PREFIX_STATUS_DATE;
import static seedu.internhunter.logic.parser.util.ApplicationParserUtil.parseStatus;
import static seedu.internhunter.logic.parser.util.ApplicationParserUtil.parseStatusDate;
import static seedu.internhunter.logic.parser.util.GeneralParserUtil.argumentsAreValid;
import static seedu.internhunter.logic.parser.util.GeneralParserUtil.getIndexInPreamble;

import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.logic.commands.edit.EditApplicationCommand;
import seedu.internhunter.logic.commands.edit.EditApplicationCommand.EditApplicationDescriptor;
import seedu.internhunter.logic.parser.ArgumentMultimap;
import seedu.internhunter.logic.parser.ArgumentTokenizer;
import seedu.internhunter.logic.parser.Parser;
import seedu.internhunter.logic.parser.exceptions.ParseException;
import seedu.internhunter.model.application.Status;
import seedu.internhunter.model.application.StatusDate;

/**
 * Parses input arguments and creates a new EditApplicationCommand object.
 */
public class EditApplicationCommandParser implements Parser<EditApplicationCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditApplicationCommand
     * and returns an EditApplicationCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public EditApplicationCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_STATUS, PREFIX_STATUS_DATE);

        if (!argumentsAreValid(true, argMultimap)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditApplicationCommand.MESSAGE_USAGE));
        }
        Index index = getIndexInPreamble(argMultimap);
        EditApplicationDescriptor editApplicationDescriptor = new EditApplicationDescriptor();

        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            String inputStatus = argMultimap.getValue(PREFIX_STATUS).get();
            Status editedStatus = parseStatus(inputStatus);
            editApplicationDescriptor.setStatus(editedStatus);
        }
        if (argMultimap.getValue(PREFIX_STATUS_DATE).isPresent()) {
            String inputStatusDate = argMultimap.getValue(PREFIX_STATUS_DATE).get();
            StatusDate editedStatusDate = parseStatusDate(inputStatusDate);
            editApplicationDescriptor.setStatusDate(editedStatusDate);
        }

        if (!editApplicationDescriptor.isAnyFieldEdited()) {
            throw new ParseException(MESSAGE_NOT_EDITED);
        }

        return new EditApplicationCommand(index, editApplicationDescriptor);
    }

}
