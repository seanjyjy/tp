package seedu.internhunter.logic.parser.edit;

import static java.util.Objects.requireNonNull;
import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internhunter.logic.commands.edit.EditCommand.MESSAGE_NOT_EDITED;
import static seedu.internhunter.logic.parser.clisyntax.GeneralCliSyntax.PREFIX_INDEX;
import static seedu.internhunter.logic.parser.clisyntax.InternshipCliSyntax.PREFIX_JOB_TITLE;
import static seedu.internhunter.logic.parser.clisyntax.InternshipCliSyntax.PREFIX_PERIOD;
import static seedu.internhunter.logic.parser.clisyntax.InternshipCliSyntax.PREFIX_REQUIREMENT;
import static seedu.internhunter.logic.parser.clisyntax.InternshipCliSyntax.PREFIX_WAGE;
import static seedu.internhunter.logic.parser.util.GeneralParserUtil.argumentsAreValid;
import static seedu.internhunter.logic.parser.util.GeneralParserUtil.getIndexInPreamble;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.logic.commands.edit.EditCommand;
import seedu.internhunter.logic.commands.edit.EditInternshipCommand;
import seedu.internhunter.logic.commands.edit.EditInternshipCommand.EditInternshipDescriptor;
import seedu.internhunter.logic.parser.ArgumentMultimap;
import seedu.internhunter.logic.parser.ArgumentTokenizer;
import seedu.internhunter.logic.parser.Parser;
import seedu.internhunter.logic.parser.exceptions.ParseException;
import seedu.internhunter.logic.parser.util.GeneralParserUtil;
import seedu.internhunter.logic.parser.util.InternshipParserUtil;
import seedu.internhunter.model.internship.Requirement;

/**
 * todo javadocs (shawn)
 */
public class EditInternshipCommandParser implements Parser<EditCommand> {

    /**
     * todo javadocs (shawn)
     */
    public EditInternshipCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_INDEX, PREFIX_JOB_TITLE, PREFIX_WAGE,
                PREFIX_PERIOD, PREFIX_REQUIREMENT);

        if (!argumentsAreValid(true, argMultimap, PREFIX_INDEX)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditInternshipCommand.MESSAGE_USAGE));
        }

        Index companyIndex = getIndexInPreamble(argMultimap);
        Index internshipIndex = GeneralParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());

        EditInternshipDescriptor editInternshipDescriptor = new EditInternshipDescriptor();
        if (argMultimap.getValue(PREFIX_JOB_TITLE).isPresent()) {
            editInternshipDescriptor.setJobTitle(InternshipParserUtil.parseJobTitle(argMultimap
                    .getValue(PREFIX_JOB_TITLE).get()));
        }
        if (argMultimap.getValue(PREFIX_WAGE).isPresent()) {
            editInternshipDescriptor.setWage(InternshipParserUtil.parseWage(argMultimap.getValue(PREFIX_WAGE).get()));
        }
        if (argMultimap.getValue(PREFIX_PERIOD).isPresent()) {
            editInternshipDescriptor.setPeriod(InternshipParserUtil.parsePeriod(argMultimap.getValue(PREFIX_PERIOD)
                    .get()));
        }
        parseRequirementsForEdit(argMultimap.getAllValues(PREFIX_REQUIREMENT))
                .ifPresent(editInternshipDescriptor::setRequirements);

        if (!editInternshipDescriptor.isAnyFieldEdited()) {
            throw new ParseException(MESSAGE_NOT_EDITED);
        }

        return new EditInternshipCommand(companyIndex, internshipIndex, editInternshipDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Requirement>> parseRequirementsForEdit(Collection<String> requirements)
            throws ParseException {
        assert requirements != null;

        if (requirements.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> requirementSet = requirements.size() == 1 && requirements.contains("")
                ? Collections.emptySet()
                : requirements;
        return Optional.of(InternshipParserUtil.parseRequirements(requirementSet));
    }
}
