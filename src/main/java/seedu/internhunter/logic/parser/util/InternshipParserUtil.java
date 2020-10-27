package seedu.internhunter.logic.parser.util;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.internhunter.logic.parser.exceptions.ParseException;
import seedu.internhunter.model.internship.JobTitle;
import seedu.internhunter.model.internship.Period;
import seedu.internhunter.model.internship.Requirement;
import seedu.internhunter.model.internship.Wage;

/**
 * Contains utility methods used for parsing Strings to fields in a Internship Item.
 */
public class InternshipParserUtil {

    private static final String ERROR_MESSAGE = "Checks for status validity failed";

    /**
     * Parses a {@code String title} into a {@code InternshipTitle}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code title} is invalid.
     */
    public static JobTitle parseJobTitle(String title) throws ParseException {
        requireNonNull(title);
        String trimmedTitle = title.trim();
        if (!JobTitle.isValidJobTitle(trimmedTitle)) {
            throw new ParseException(JobTitle.MESSAGE_CONSTRAINTS);
        }
        return new JobTitle(trimmedTitle);
    }

    /**
     * Parses a {@code String period} into a {@code Period}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code period} is invalid.
     */
    public static Period parsePeriod(String period) throws ParseException {
        requireNonNull(period);
        String trimmedPeriod = period.trim();
        if (!Period.isValidPeriod(trimmedPeriod)) {
            throw new ParseException(Period.MESSAGE_CONSTRAINTS);
        }
        return new Period(trimmedPeriod);
    }

    /**
     * Parses a {@code String wage} into a {@code Wage}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code wage} is invalid.
     */
    public static Wage parseWage(String wage) throws ParseException {
        requireNonNull(wage);
        String trimmedWage = wage.trim();
        if (!Wage.isValidWage(trimmedWage)) {
            throw new ParseException(Wage.MESSAGE_CONSTRAINTS);
        }
        return new Wage(trimmedWage);
    }

    /**
     * Parses a {@code String requirement} into a {@code Requirement}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code requirement} is invalid.
     */
    public static Requirement parseRequirement(String requirement) throws ParseException {
        requireNonNull(requirement);
        String trimmedRequirement = requirement.trim();
        if (!Requirement.isValidRequirement(trimmedRequirement)) {
            throw new ParseException(Requirement.MESSAGE_CONSTRAINTS);
        }
        return new Requirement(trimmedRequirement);
    }

    /**
     * Parses {@code Collection<String> requirements} into a {@code Set<Requirement>}.
     */
    public static Set<Requirement> parseRequirements(Collection<String> requirements) throws ParseException {
        requireNonNull(requirements);
        final Set<Requirement> requirementSet = new HashSet<>();
        for (String requirement : requirements) {
            requirementSet.add(parseRequirement(requirement));
        }
        return requirementSet;
    }
}
