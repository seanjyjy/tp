package seedu.internhunter.model.application;

import java.util.List;
import java.util.function.Predicate;

import seedu.internhunter.commons.util.StringUtil;

/**
 * Tests that a {@code ApplicationItem}'s {@code JobTitle} matches any of the keywords given.
 */
public class ApplicationNameContainsKeyWordsPredicate implements Predicate<ApplicationItem> {
    private final List<String> keywords;

    public ApplicationNameContainsKeyWordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(ApplicationItem applicationItem) {
        return keywords.stream()
            .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(
                applicationItem.getInternshipJobTitleValue(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ApplicationNameContainsKeyWordsPredicate // instanceof handles nulls
            && keywords.equals(((ApplicationNameContainsKeyWordsPredicate) other).keywords)); // state check
    }
}
